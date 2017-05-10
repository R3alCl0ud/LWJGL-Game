package game.common.start;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
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
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.system.MemoryStack.stackPop;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowRefreshCallbackI;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import game.entity.Entity;
import game.entity.EntityCube;

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
		if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are
									// already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden
													// after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be
													// resizable

		// Create the window
		window = glfwCreateWindow(300, 300, "AN RPG", NULL, NULL);
		if (window == NULL) throw new RuntimeException("Failed to create the GLFW window");

		/*
		 * Setup a key callback. It will be called every time a key is pressed,
		 * repeated or released.
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
		 * This line is critical for LWJGL's interoperation with GLFW's OpenGL
		 * context, or any context that is managed externally. LWJGL
		 * detects the context that is current in the current thread, creates
		 * the GLCapabilities instance and makes the OpenGL bindings
		 * available for use.
		 */
		GL.createCapabilities();

		// Set the clear color
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

		/*
		 * Create the OpenGL Vertex Buffer Object. Don't focus on the next two
		 * calls...
		 */

		// memFree(buffer);

		// stackPop();

		Entity cube = new EntityCube();
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while (!glfwWindowShouldClose(window)) {
			// clear the framebuffer
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			stackPush();
			GL11.glViewport(0, 0, 300, 300);
			GL11.glMatrixMode(GL11.GL_PROJECTION);

			GL11.glLoadIdentity();

			// set the projection (could use glTranslate/glScale but this
			// utility function is simpler)
			GL11.glOrtho(0, 300, 0, 300, -1, 1); // left,right,bottom,top,front,back

			// common practice to leave modelview as the current matrix for
			// editing
			GL11.glMatrixMode(GL11.GL_MODELVIEW);

			
			cube.draw();
//			GL11.glEnd();
			
//			int vbo = glGenBuffers();

			// glBindBuffer(GL_ARRAY_BUFFER, vbo);
			// glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
			// glEnableClientState(GL_VERTEX_ARRAY);
			// glVertexPointer(3, GL_FLOAT, 0, 0L);
			// GL11.glTranslatef(100, 100, 0);
			// GL11.glRotatef(1f, 0, 1, 0);
			// GL11.glColor3f(0.0f, 0.0f, 0.0f);
			// GL11.glTranslatef(-100, -100, 0);
			// GL11.glBegin(GL11.GL_QUADS);
			// GL11.glDrawArrays(GL11.GL_POLYGON, 0, 12);
			// GL11.glEnd();
			stackPop();
			// swap the color buffers
			glfwSwapBuffers(window);

			/*
			 * Poll for window events. The key callback above will only be
			 * invoked during this call.
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