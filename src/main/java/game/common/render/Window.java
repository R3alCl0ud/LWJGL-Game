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
	private int width, height;
	private String title = "SCHTY RPG";
	
	private long monitor = glfwGetPrimaryMonitor();
	
	public Window() throws NullPointerException {
		
		if (!glfwInit()) throw new NullPointerException();
		
		this.window = glfwCreateWindow(width, height, title, monitor, NULL);
		
		if (this.window == NULL) {
			glfwTerminate();
			throw new NullPointerException();
		}
		
		glfwMakeContextCurrent(window);
		
		
	}
	
	private void render() {
		
	}
	
	private void update() {
		
	}

	/**
	 * @return the window ID
	 */
	public long getWindow() {
		return window;
	}
	
	public void run() {
		
	}

}
