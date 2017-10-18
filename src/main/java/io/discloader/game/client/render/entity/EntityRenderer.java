package io.discloader.game.client.render.entity;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;

import io.discloader.game.client.render.IRenderer;
import io.discloader.game.common.entity.Entity;
import io.discloader.game.render.texture.ITexture;

/**
 * @author Perry Berman
 */
public class EntityRenderer implements IRenderer<Entity> {

	@Override
	public void draw(Entity entity) {
		ITexture texture = entity.getTexture();
		texture.bind();
		GL11.glLoadIdentity();
		GL11.glViewport(entity.getPosX(), entity.getPosY(), 64, 64);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glOrtho(0, 32, 0, 32, -1, 1);
		GL11.glPushMatrix();
		GL11.glTranslatef(8, 8, 0);
		GL11.glRotatef(entity.getYaw(), 0f, 0f, 1f);
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
	public void renderAt(Entity t, int x, int y, int w, int h, float yaw) {
		ITexture texture = t.getTexture();
		GL11.glLoadIdentity();
		GL11.glViewport(x, y, w, h);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		texture.bind();
		GL11.glRotatef(yaw, 0f, 0f, 1f);
		GL11.glBegin(GL11.GL_POLYGON);
		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(-1f, -1f);

		glTexCoord2f(1.0f, 0.0f);
		glVertex2f(1.0f, -1.0f);

		glTexCoord2f(1.0f, 1.0f);
		glVertex2f(1f, 1f);

		glTexCoord2f(0.0f, 1.0f);
		glVertex2f(-1.0f, 1f);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderAt() {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderAt(Entity t, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
