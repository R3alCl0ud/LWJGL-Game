package game.entity;

import org.lwjgl.opengl.GL11;

import game.render.Resource;

/**
 * @author Perry Berman
 */
public class EntityTriangles extends Entity {

	public EntityTriangles() {
	}

	public void draw() {

		GL11.glTranslatef(8, 8, 0);
		// GL11.glRotatef(getYaw(), 0, 0, 1);
		GL11.glTranslatef(-8, -8, 0);
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glColor3d(0.5d, Math.random(), 0.5d);
		GL11.glVertex2f(0, 0);
		GL11.glVertex2f(0, 16);
		GL11.glVertex2f(16, 16);
		GL11.glVertex2f(16, 0);
		GL11.glEnd();
		setYaw(getYaw() + 0.01f);
	}

	@Override
	public Resource getResourceLocation() {
		return null;
	}
}
