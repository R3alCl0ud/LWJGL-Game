package game.entity.tile;

import org.lwjgl.opengl.GL11;

/**
 * @author Perry Berman
 *
 */
public class TileWindow extends Tile {
	public void draw() {
		GL11.glColor3f(0.65f, 0.65f, 0.65f);
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex2f(0, 0);
		GL11.glVertex2f(0, 10);
		GL11.glVertex2f(10, 10);
		GL11.glVertex2f(10, 0);
		GL11.glVertex2f(0, 10);
		GL11.glVertex2f(0, 16);
		GL11.glVertex2f(10, 16);
		GL11.glVertex2f(10, 10);
		GL11.glVertex2f(10, 10);
		GL11.glVertex2f(10, 16);
		GL11.glVertex2f(16, 16);
		GL11.glVertex2f(16, 10);
		GL11.glVertex2f(10, 0);
		GL11.glVertex2f(10, 10);
		GL11.glVertex2f(16, 10);
		GL11.glVertex2f(16, 0);
		GL11.glEnd();
		GL11.glColor3f(0.1f, 0.1f, 0.1f);
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex2f(8, 0);
		GL11.glVertex2f(9, 0);
		GL11.glVertex2f(9, 16);
		GL11.glVertex2f(8, 16);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex2f(0, 8);
		GL11.glVertex2f(0, 9);
		GL11.glVertex2f(16, 9);
		GL11.glVertex2f(16, 8);
		GL11.glEnd();
	}
}
