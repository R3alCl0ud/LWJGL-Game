package game.entity.tile;

import org.lwjgl.opengl.GL11;

/**
 * @author Perry Berman
 *
 */
public class TileGrass extends Tile {
	private float[] greens = new float[256];
	private short draws = 0;
	
	public void draw() {
		draw(0);
	}
	
	public void draw(int tiles) {
		if (greens[draws] < 0.7f) {
			greens[draws] = 0.76f + (float) (Math.random() * 0.045f);
		}
		GL11.glColor3f(0.1f, greens[draws], 0.35f);
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex2f(0, 0);
		GL11.glVertex2f(0, 16);
		GL11.glVertex2f(16, 16);
		GL11.glVertex2f(16, 0);
		GL11.glEnd();
		draws++;
		if (draws == tiles)
			draws = 0;
	}
	
}
