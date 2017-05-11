package game.entity.tile;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;

import game.render.Resource;
import game.render.texture.ITexture;
import game.render.texture.ItemTexture;

/**
 * @author Perry Berman
 */
public class TileWood extends Tile {

	private float[] greens = new float[256];
	private short i = 0;
	private ITexture texture;

	public TileWood() {
		texture = new ItemTexture(new Resource("game", "texture/tile/wood.png"));
	}

	@Override
	public ITexture getTexture() {
		return texture;
	}

	public void draw() {
		texture.bind();
		GL11.glPushMatrix();
		GL11.glBegin(GL11.GL_POLYGON);
		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(0.0f, 0.0f);

		glTexCoord2f(1.0f, 0.0f);
		glVertex2f(16, 0.0f);

		glTexCoord2f(1.0f, 1.0f);
		glVertex2f(16, 16);

		glTexCoord2f(0.0f, 1.0f);
		glVertex2f(0.0f, 16);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	public void draw(int tiles) {
		if (greens[i] < 0.2) greens[i] = 0.203921568627451f + (float) (Math.random() * 0.025f);
		GL11.glColor3f(0.3686274509803922f, greens[i], 0.05f);
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex2f(0, 0);
		GL11.glVertex2f(0, 16);
		GL11.glVertex2f(16, 16);
		GL11.glVertex2f(16, 0);
		GL11.glEnd();
		i++;
		if (i == tiles) i = 0;
	}

}
