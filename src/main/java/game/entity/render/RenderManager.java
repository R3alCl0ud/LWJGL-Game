package game.entity.render;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.opengl.GL;

public class RenderManager {

	private Thread screenRender;
	private long window;

	public RenderManager(long window) {
		this.window = window;
	}

	public void killRenderThread() {
		if (screenRender != null && !screenRender.isInterrupted()) {
			screenRender.interrupt();
		}
		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();

	}

	public void startRenderer() {
		screenRender = new Thread("Main Renderer") {

			public void run() {
				GL.createCapabilities();

				// Set the clear color
				glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

				// Run the rendering loop until the user has attempted to close
				// the window or has pressed the ESCAPE key.
				while (!glfwWindowShouldClose(window)) {
					glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear
																		// the
																		// framebuffer

					glfwSwapBuffers(window); // swap the color buffers

					// Poll for window events. The key callback above will only
					// be
					// invoked during this call.
					glfwPollEvents();
				}
			}
		};
		screenRender.setPriority((Thread.NORM_PRIORITY + Thread.MAX_PRIORITY) / 2);
		screenRender.start();
		System.out.println("renderer");
	}

}
