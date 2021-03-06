package io.discloader.game.client.render.tile;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import io.discloader.game.client.registry.TextureRegistry;
import io.discloader.game.client.render.IRenderer;
import io.discloader.game.common.tile.TileDoor;
import io.discloader.game.render.texture.ITexture;

public class TileDoorRenderer implements IRenderer<TileDoor> {

	@Override
	public void draw(TileDoor t) {
		ITexture texture = TextureRegistry.getTexture("door");
		GL11.glLoadIdentity();
		GL11.glViewport(192, 0, 64, 128);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		texture.bind();
		GL11.glBegin(GL11.GL_POLYGON);
		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(-1.0f, -1.0f);

		glTexCoord2f(1.0f, 0.0f);
		glVertex2f(1f, -1.0f);

		glTexCoord2f(1.0f, 1f);
		glVertex2f(1f, 1f);

		glTexCoord2f(0.0f, 1f);
		glVertex2f(-1.0f, 1f);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	@Override
	public void renderAt(TileDoor t, int x, int y, int w, int h, float yaw) {
		// TODO Auto-generated method stub
		ITexture texture = TextureRegistry.getTexture("door");
		GL11.glLoadIdentity();
		GL11.glViewport(x, y, multi, multi * 2);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		texture.bind();
		GL11.glBegin(GL11.GL_POLYGON);
		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(-1.0f, -1.0f);

		glTexCoord2f(1.0f, 0.0f);
		glVertex2f(1f, -1.0f);

		glTexCoord2f(1.0f, 1f);
		glVertex2f(1f, 1f);

		glTexCoord2f(0.0f, 1f);
		glVertex2f(-1.0f, 1f);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		draw(null);
	}

	@Override
	public void renderAt() {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderAt(TileDoor t, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderAt(TileDoor t, int x, int y, float z) {}

	@Override
	public void renderAt(TileDoor t, int x, int y, int w, int h, float yaw, float z) {
		ITexture texture = TextureRegistry.getTexture("door");
		GL11.glLoadIdentity();
		GL11.glViewport(x, y, multi, multi * 2);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		texture.bind();
		GL11.glBegin(GL11.GL_POLYGON);
		glTexCoord2f(0.0f, 0.0f);
		glVertex3f(-1.0f, -1.0f, z);

		glTexCoord2f(1.0f, 0.0f);
		glVertex3f(1f, -1.0f, z);

		glTexCoord2f(1.0f, 1f);
		glVertex3f(1f, 1f, z);

		glTexCoord2f(0.0f, 1f);
		glVertex3f(-1.0f, 1f, z);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	@Override
	public void begin() {}

	@Override
	public void end() {}

	@Override
	public void drawTextureRegion(ITexture texture, float drawX, float drawY, float regX, float regY, float regWidth, float regHeight, Color c) {}

}
