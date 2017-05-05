package game.common.render;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window implements Runnable {

	private long window;
	private int width = 0, height = 0;
	private String title = "SCHTY RPG";
	
	public Window() throws NullPointerException {
		GLFWErrorCallback.createPrint(System.err).set();
		
		this.width = width==0?300:width;
		this.height = height==0?300:height;
		
		if (!glfwInit()) throw new NullPointerException();
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		this.window = glfwCreateWindow(width, height, title, NULL, NULL);
		
		if (this.window == NULL) {
			glfwTerminate();
			throw new NullPointerException();
		}
		
		glfwMakeContextCurrent(window);
	}
	
	public Window(int width, int height) throws NullPointerException {
		this();
		this.width = width;
		this.height = height;
	}
	
	private void render() {
		
	}
	
	private void update() {
		
	}
	
	private void init() {
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			
		});
		
		// Using example stack buffer
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		}
		
		glfwMakeContextCurrent(window);
		
		// Synchronize frames
		glfwSwapInterval(1);
		glfwShowWindow(window);
	}
	
	public void loop() {
		
	}

	/**
	 * @return the window ID
	 */
	public long getWindow() {
		return window;
	}
	
	public void run() {
		// finishes init
		init();
	}

}
