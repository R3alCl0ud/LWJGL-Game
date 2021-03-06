package io.discloader.game.client.render;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import io.discloader.game.client.registry.TextureRegistry;
import io.discloader.game.common.objects.IItem;
import io.discloader.game.render.texture.ITexture;

public class ItemRenderer implements IRenderer<IItem> {

	@Override
	public void draw(IItem item) {
		ITexture texture = TextureRegistry.getTexture(item.getName());
		texture.bind();
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glOrtho(0, 32, 0, 32, -1, 1);
		GL11.glPushMatrix();
		GL11.glTranslatef(8, 8, 0);
		// GL11.glRotatef(item.getYaw(), 0f, 0f, 1f);
		GL11.glTranslatef(-8, -8, 0);

		GL11.glBegin(GL11.GL_POLYGON);
		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(0.0f, 0.0f);

		glTexCoord2f(1.0f, 0.0f);
		glVertex2f(32, 0.0f);

		glTexCoord2f(1.0f, 1.0f);
		glVertex2f(32, 32);

		glTexCoord2f(0.0f, 1.0f);
		glVertex2f(0.0f, 32);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	@Override
	public void renderAt(IItem t, int x, int y, int w, int h, float yaw) {}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderAt() {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderAt(IItem t, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderAt(IItem t, int x, int y, float z) {}

	@Override
	public void renderAt(IItem t, int x, int y, int w, int h, float yaw, float z) {}

	@Override
	public void begin() {}

	@Override
	public void end() {}

	@Override
	public void drawTextureRegion(ITexture texture, float drawX, float drawY, float regX, float regY, float regWidth, float regHeight, Color c) {}
}
