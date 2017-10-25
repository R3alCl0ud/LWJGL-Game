package io.discloader.game.common.start;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import io.discloader.game.client.registry.ItemRegistry;
import io.discloader.game.client.registry.TextureRegistry;
import io.discloader.game.client.render.IRenderer;
import io.discloader.game.client.render.RenderManager;
import io.discloader.game.client.render.TextRenderer;
import io.discloader.game.client.render.entity.EntityRenderer;
import io.discloader.game.client.render.room.RoomRenderer;
import io.discloader.game.common.RoomManager;
import io.discloader.game.common.entity.Entity;
import io.discloader.game.common.entity.player.EntityPlayer;
import io.discloader.game.common.item.Item;
import io.discloader.game.common.objects.IItem;
import io.discloader.game.common.tile.Tile;
import io.discloader.game.common.world.room.Room;
import io.discloader.game.common.world.room.RoomHome;
import io.discloader.game.render.GLRU;
import io.discloader.game.render.Resource;
import io.discloader.game.render.texture.ITexture;
import io.discloader.game.render.texture.Texture;

public class Index implements Runnable {

	// The window handle
	private long window;

	// Event Polling Thread
	// private Thread evntPoll;
	private Room current;
	private EntityPlayer player;
	private IRenderer<Entity> playerRenderer;
	private IRenderer<Room> roomRenderer;
	private final int multi = GLRU.getMultiplier();
	private ITexture characters;
	private final float charSize = 0.03125f;
	private long lastFrameTime = 0, lastFPSShown = 0;
	private int fps;

	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		System.setProperty("java.awt.headless", "true");
		init();
		Item.registerItems();
		Tile.registerTextures();
		Tile.registerTiles();
		TextureRegistry.registerTexture("Jar", new Texture(ItemRegistry.getItem("Jar").getResourceLocation()));
		TextureRegistry.registerTexture("player", new Texture(new Resource("game", "texture/entity/player.png")));
		current = new RoomHome();
		characters = TextureRegistry.getTexture("characters");
		player = new EntityPlayer("Player 1", 20);
		playerRenderer = new EntityRenderer();
		roomRenderer = new RoomRenderer(player);
		RoomManager.registerRoom(current);
		RenderManager.registerRenderer(String.class, new TextRenderer());

		IItem jar = ItemRegistry.getItem("Jar");
		if (jar != null) {
			jar.createEntity(10, 10, 0);
		}
		loop();
		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		//
		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	// private void registerTextures() {
	//
	// }
	private boolean up, down, left, right;

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are
									// already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden
													// after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be
													// resizable

		// Create the window
		window = glfwCreateWindow(510, 510, "AN RPG", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		/*
		 * Setup a key callback. It will be called every time a key is pressed, repeated
		 * or released.
		 */
		glfwSetKeyCallback(window, new GLFWKeyCallback() {

			// private long lastKeyCheck = System.currentTimeMillis();

			public void invoke(long window, int key, int scancode, int action, int mods) {
				// long currentTime = System.currentTimeMillis();
				if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
					// We will detect this in the rendering loop
					glfwSetWindowShouldClose(window, true);
				} else if (key == GLFW.GLFW_KEY_W) {
					if (action == GLFW.GLFW_PRESS)
						up = true;
					if (action == GLFW.GLFW_RELEASE)
						up = false;
				} else if (key == GLFW.GLFW_KEY_S) {
					if (action == GLFW.GLFW_PRESS)
						down = true;
					if (action == GLFW.GLFW_RELEASE)
						down = false;
				} else if (key == GLFW.GLFW_KEY_D) {
					if (action == GLFW.GLFW_PRESS)
						right = true;
					if (action == GLFW.GLFW_RELEASE)
						right = false;
				} else if (key == GLFW.GLFW_KEY_A) {
					if (action == GLFW.GLFW_PRESS)
						left = true;
					if (action == GLFW.GLFW_RELEASE)
						left = false;
				}
				// lastKeyCheck = currentTime;
			}
		});

		// Get the thread stack and push a new frame
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		GLFW.glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);

		GL.createCapabilities();

		// manager = new RenderManager(window);
		// renderer = new Renderer(window);
	}

	private void loop() {
		/*
		 * This line is critical for LWJGL's interoperation with GLFW's OpenGL context,
		 * or any context that is managed externally. LWJGL detects the context that is
		 * current in the current thread, creates the GLCapabilities instance and makes
		 * the OpenGL bindings available for use.
		 */

		// Set the clear color
		GL11.glClearColor(1f, 1f, 1f, 1.0f);

		glEnable(GL_TEXTURE_2D);

		System.out.println(player.getLeftTile(current));
		System.out.println(player.getRightTile(current));
		System.out.println(player.getUpTile(current));
		System.out.println(player.getDownTile(current));

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while (!glfwWindowShouldClose(window)) {
			// clear the framebuffer
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			// Draw shit
			doDraw();
			// handle the players movement first, because it's more logical to
			// do it like this
			// this will be moved into a different thread later

			// swap the color buffers
			glfwSwapBuffers(window);
			// event shit
			GLFW.glfwPollEvents();

		}
		GL11.glDisable(GL_TEXTURE_2D);
	}

	private void doDraw() {
		roomRenderer.draw(current);
		int x = player.getPosX(), y = player.getPosY();
		if (x > 15 * (multi / 2) && x < (multi * (current.getWidth() - 8)) - (multi / 2)) {
			x = 15 * (multi / 2);
		} else if (x > (multi * (current.getWidth() - 8)) - (multi / 2)) {
			x %= (16 * multi);
		}
		if (y > 15 * (multi / 2) && y < (multi * (current.getHeight() - 8)) - (multi / 2)) {
			y = 15 * (multi / 2);
		} else if (y > multi * (current.getHeight() - 8) - (multi / 2)) {
			y %= (16 * multi);
		}
		// renderCount();
		playerRenderer.renderAt(player, x, y, multi, multi * 2, player.getYaw());
		playerMovement();
		long currentTime = System.currentTimeMillis(), deltaTime = currentTime - lastFrameTime;
		lastFrameTime = currentTime;
		lastFPSShown += deltaTime;
		if (lastFPSShown > 100) {
			fps = (int) (1000l / deltaTime);
			lastFPSShown = 0;
		}
		RenderManager.getRenderer("").renderAt("FPS: " + fps, 1, 15);
	}

	private void renderCount() {
		characters.bind();
		for (int i = 0; i < 100; i++) {
			GL11.glViewport(multi * (i / 10), multi * (i % 10), multi, multi);
			GL11.glLoadIdentity();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glPushMatrix();
			// GL11.glRotatef(yaw, 0f, 0f, 1f);
			GL11.glBegin(GL11.GL_POLYGON);
			float right = charSize * (21 + (i / 10)), left = charSize * (20 + (i / 10));
			glTexCoord2f(left, 0.9375f);
			glVertex2f(-1f, -1f);

			glTexCoord2f(right, 0.9375f);
			glVertex2f(1.0f, -1.0f);

			glTexCoord2f(right, 0.96875f);
			glVertex2f(1f, 1f);

			glTexCoord2f(left, 0.96875f);
			glVertex2f(-1.0f, 1f);
			GL11.glEnd();
			GL11.glPopMatrix();
		}
	}

	private void playerMovement() {
		// int hw = 1020;
		if (up && !down && player.getPosY() <= multi * (current.getHeight() - 2)) {
			// System.out.println("current: " + player.standingOn(current));
			System.out.println("up: " + player.getUpTile(current));
			if (player.getUpTile(current) != null && player.getUpTile(current).isFloor()
					&& !player.getUpTile(current).isSolid()) {
				if ((left && !right) || (right && !left)) {
					player.setPosY((int) (player.getPosY() + (3.5d / 1.25d)));
				} else {
					player.setPosY((int) (player.getPosY() + 3.5d));
				}
			}
		} else if (down && !up && player.getPosY() > 0) {
			// System.out.println("current: " + player.standingOn(current));
			System.out.println("down: " + player.getDownTile(current));
			if (player.getDownTile(current).isFloor() && !player.getDownTile(current).isSolid()) {
				if ((left && !right) || (right && !left)) {
					player.setPosY((int) (player.getPosY() - (3d / 2d)));
				} else {
					player.setPosY((int) (player.getPosY() - 3d));
				}
			}
			if (player.getPosY() < 0)
				player.setPosY(0);
		}
		if (left && !right && player.getPosX() > 0) {
			// System.out.println("current: " + player.standingOn(current));
			// System.out.println("left: " + player.getLeftTile(current));
			if (player.getLeftTile(current).isFloor() && !player.getLeftTile(current).isSolid()) {
				if ((up && !down) || (down && !up)) {
					player.setPosX((int) (player.getPosX() - (2d / 1.5d)));
				} else {
					player.setPosX((int) (player.getPosX() - 3d));
				}
			}
		} else if (right && !left && player.getPosX() < multi * (current.getWidth() - 1)) {
			System.out.println("current: " + player.standingOn(current));
			System.out.println("right: " + player.getRightTile(current));
			if (player.getRightTile(current) != null && player.getRightTile(current).isFloor()
					&& !player.getRightTile(current).isSolid()) {
				if ((up && !down) || (down && !up)) {
					player.setPosX((int) (player.getPosX() + (3.5d / 1.25d)));
				} else {
					player.setPosX((int) (player.getPosX() + 3.5d));
				}
			}
		}
	}

	public static void main(String[] args) {
		new Index().run();
	}

}
