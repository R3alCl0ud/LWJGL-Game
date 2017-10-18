package io.discloader.game.client.render.tile;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;

import io.discloader.game.client.render.IRenderer;
import io.discloader.game.common.tile.Tile;
import io.discloader.game.render.texture.ITexture;

public class TileRenderer implements IRenderer<Tile> {
	
	public void draw(Tile tile) {
		if (tile == null)
			return;
		ITexture texture = tile.getTexture();
		if (texture == null)
			return;
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
	
	@Override
	public void renderAt(Tile t, int x, int y, int w, int h, float yaw) {
		ITexture texture = t.getTexture();
		GL11.glViewport(x, y, w, h);
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glRotatef(yaw, 0f, 0f, 1f);
		texture.bind();
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
	public void renderAt(Tile t, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
