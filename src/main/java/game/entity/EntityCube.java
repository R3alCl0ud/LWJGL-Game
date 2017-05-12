package game.entity;

import org.lwjgl.opengl.GL11;

import game.renderer.Resource;
import game.renderer.texture.ITexture;

public class EntityCube extends Entity {
	private float a = 0.25f, b = -0.5f;
	
	public void draw() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glRotatef(5, 0, 0, 1);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(1f, 0.5f, 0.0f);
		GL11.glVertex3f(a, b, a);
		GL11.glVertex3f(b, b, a);
		GL11.glVertex3f(b, b, b);
		GL11.glVertex3f(a, b, b);
		GL11.glColor3f(1f, 0.0f, 1f);
		GL11.glVertex3f(a, a, b);
		GL11.glVertex3f(a, a, a);
		GL11.glVertex3f(a, b, a);
		GL11.glVertex3f(a, b, b);
		GL11.glColor3f(1f, 0.0f, 0.0f);
		GL11.glVertex3f(a, a, a);
		GL11.glVertex3f(b, a, a);
		GL11.glVertex3f(b, b, a);
		GL11.glVertex3f(a, b, a);
		GL11.glColor3f(1f, 0.25f, 0.0f);
		GL11.glVertex3f(a, b, b);
		GL11.glVertex3f(b, b, b);
		GL11.glVertex3f(b, a, b);
		GL11.glVertex3f(a, a, b);
		GL11.glColor3f(0.0f, 0.0f, 1f);
		GL11.glVertex3f(b, a, a);
		GL11.glVertex3f(b, a, b);
		GL11.glVertex3f(b, b, b);
		GL11.glVertex3f(b, b, a);
		GL11.glColor3f(1f, 1f, 0.0f);
		GL11.glVertex3f(a, a, b);
		GL11.glVertex3f(b, a, b);
		GL11.glVertex3f(b, a, a);
		GL11.glVertex3f(a, a, a);
		GL11.glEnd();
	}

	@Override
	public Resource getResourceLocation() {
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see game.entity.Entity#getTexture()
	 */
	@Override
	public ITexture getTexture() {
		return null;
	}
}
