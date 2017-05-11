package game.common.start;

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
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowRefreshCallbackI;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import game.entity.room.Room;
import game.entity.room.RoomHome;

public class Index implements Runnable {
	
	// The window handle
	private long window;
	
	// Event Polling Thread
	private Thread evntPoll;
	
	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		
		init();
		loop();
		// manager.startRenderer();
		
		// glfwMakeContextCurrent(window);
		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		//
		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
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
		 * Setup a key callback. It will be called every time a key is pressed, repeated or released.
		 */
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
				// We will detect this in the rendering loop
				glfwSetWindowShouldClose(window, true);
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
		glfwSwapInterval(1);
		
		// Make the window visible
		glfwShowWindow(window);
		// manager = new RenderManager(window);
		// renderer = new Renderer(window);
	}
	
	private void loop() {
		/*
		 * This line is critical for LWJGL's interoperation with GLFW's OpenGL context, or any context that is managed externally. LWJGL
		 * detects the context that is current in the current thread, creates the GLCapabilities instance and makes the OpenGL bindings
		 * available for use.
		 */
		GL.createCapabilities();
		
		// Set the clear color
		glClearColor(1f, 1f, 1f, 1.0f);
		
		/*
		 * Create the OpenGL Vertex Buffer Object. Don't focus on the next two calls...
		 */
		
		// memFree(buffer);
		
		// stackPop();
		
		Room current = new RoomHome();
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while (!glfwWindowShouldClose(window)) {
			// clear the framebuffer
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			// glClearColor((float) Math.random(), (float) Math.random(), (float) Math.random(), 1);
			// GL11.glViewport(0, 0, 640, 640);
			// GL11.glLoadIdentity();
			// GL11.glMatrixMode(GL11.GL_MODELVIEW);
			// GL11.glOrtho(0, 16, 0, 16, -1, 1);
			// for (int i = 0; i < 100; i++) {
			// GL11.glViewport(64 * (i / 10), 64 * (i % 10), 64, 64);
			// GL11.glLoadIdentity();
			// GL11.glMatrixMode(GL11.GL_MODELVIEW);
			// GL11.glOrtho(0, 16, 0, 16, -1, 1);
			// cube.draw();
			// }
			current.drawTiles();
			glfwSwapBuffers(window);
			// swap the color buffers
			
			/*
			 * Poll for window events. The key callback above will only be invoked during this call.
			 */
			glfwPollEvents();
		}
	}
	
	private GLFWWindowRefreshCallbackI update() {
		// Would Change later
		return null;
	}
	
	public static void main(String[] args) {
		new Index().run();
	}
	
}