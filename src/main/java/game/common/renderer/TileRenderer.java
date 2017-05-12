package game.common.renderer;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;

import game.entity.tile.Tile;
import game.render.texture.ITexture;

public class TileRenderer implements IRender<Tile> {
	
	public void draw(Tile tile) {
		if (tile == null)
			return;
		ITexture texture = tile.getTexture();
		texture.bind();
		GL11.glPushMatrix();
		GL11.glBegin(GL11.GL_POLYGON);
		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(0.0f, 0.0f);
		
		glTexCoord2f(1.0f, 0.0f);
		glVertex2f(texture.getWidth(), 0.0f);
		
		glTexCoord2f(1.0f, 1.0f);
		glVertex2f(texture.getWidth(), texture.getHeight());
		
		glTexCoord2f(0.0f, 1.0f);
		glVertex2f(0.0f, texture.getHeight());
		GL11.glEnd();
		GL11.glPopMatrix();
	}
}
