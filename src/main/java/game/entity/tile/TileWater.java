package game.entity.tile;

import org.lwjgl.opengl.GL11;

/**
 * @author Perry Berman
 *
 */
public class TileWater extends Tile {
	private float[] blues = new float[256];
	private short i = 0;
	
	public void draw() {
		draw(254);
	}
	
	public void draw(int tiles) {
		if (i == 256)
			i = 0;
		if (blues[i] < 0.7f) {
			blues[i] = 0.76f + (float) (Math.random() * 0.3f);
		}
		GL11.glColor3f(0.1f, 0.35f, blues[i]);
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex2f(0, 0);
		GL11.glVertex2f(0, 16);
		GL11.glVertex2f(16, 16);
		GL11.glVertex2f(16, 0);
		GL11.glEnd();
		i++;
		if (i == tiles)
			i = 0;
	}
}
