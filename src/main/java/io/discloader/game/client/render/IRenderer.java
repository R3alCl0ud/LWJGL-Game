package io.discloader.game.client.render;

import java.awt.Color;

import io.discloader.game.render.GLRU;
import io.discloader.game.render.texture.ITexture;

public interface IRenderer<T> {

	public final int multi = GLRU.getMultiplier();

	void begin();

	void end();

	void draw();

	void draw(T t);

	void renderAt();

	void renderAt(T t, int x, int y);

	void renderAt(T t, int x, int y, float z);

	void renderAt(T t, int x, int y, int w, int h, float yaw);

	void renderAt(T t, int x, int y, int w, int h, float yaw, float z);

	void drawTextureRegion(ITexture texture, float drawX, float drawY, float regX, float regY, float regWidth, float regHeight, Color c);
}
