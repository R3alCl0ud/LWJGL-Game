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
public class TileWindow extends Tile {

	private ITexture texture;

	public TileWindow() {
		texture = new ItemTexture(new Resource("game", "texture/tile/window.png"));
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
	// public void draw() {
	// GL11.glColor3f(0.65f, 0.65f, 0.65f);
	// GL11.glBegin(GL11.GL_POLYGON);
	// GL11.glVertex2f(0, 0);
	// GL11.glVertex2f(0, 10);
	// GL11.glVertex2f(10, 10);
	// GL11.glVertex2f(10, 0);
	// GL11.glVertex2f(0, 10);
	// GL11.glVertex2f(0, 16);
	// GL11.glVertex2f(10, 16);
	// GL11.glVertex2f(10, 10);
	// GL11.glVertex2f(10, 10);
	// GL11.glVertex2f(10, 16);
	// GL11.glVertex2f(16, 16);
	// GL11.glVertex2f(16, 10);
	// GL11.glVertex2f(10, 0);
	// GL11.glVertex2f(10, 10);
	// GL11.glVertex2f(16, 10);
	// GL11.glVertex2f(16, 0);
	// GL11.glEnd();
	// GL11.glColor3f(0.1f, 0.1f, 0.1f);
	// GL11.glBegin(GL11.GL_POLYGON);
	// GL11.glVertex2f(8, 0);
	// GL11.glVertex2f(9, 0);
	// GL11.glVertex2f(9, 16);
	// GL11.glVertex2f(8, 16);
	// GL11.glEnd();
	// GL11.glBegin(GL11.GL_POLYGON);
	// GL11.glVertex2f(0, 8);
	// GL11.glVertex2f(0, 9);
	// GL11.glVertex2f(16, 9);
	// GL11.glVertex2f(16, 8);
	// GL11.glEnd();
	// }
	//

}
