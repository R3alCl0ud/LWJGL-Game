package game.entity.tile;

import org.lwjgl.opengl.GL11;

/**
 * @author Perry Berman
 *
 */
public class TileWall extends Tile {
	private float[] greens = new float[256];
	private short i = 0;
	
	public void draw() {
		draw(256);
	}
	
	public void draw(int tiles) {
		if (greens[i] < 0.8)
			greens[i] = 0.85f + (float) (Math.random() * 0.025f);
		GL11.glColor3f(0.7f, greens[i], 0.97f);
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