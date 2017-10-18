package io.discloader.game.event;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventBus {

	public static final double tick_interval = 25l;
	private static final List<Consumer<Long>> keyListeners = new ArrayList<>();
	private static Thread keyListen;

	public static void start() {
		keyListen = new Thread("Key Handling") {

			public void run() {
				while (!isInterrupted()) {
					glfwPollEvents();
				}
			}
		};
		keyListen.start();
	}

}
