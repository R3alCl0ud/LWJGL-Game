package io.discloader.game.client.render;

import io.discloader.game.render.GLRU;

public interface IRenderer<T> {

	public final int multi = GLRU.getMultiplier();

	void draw();

	void draw(T t);

	void renderAt();

	void renderAt(T t, int x, int y);

	void renderAt(T t, int x, int y, float z);

	void renderAt(T t, int x, int y, int w, int h, float yaw);

	void renderAt(T t, int x, int y, int w, int h, float yaw, float z);
}
