package game.common.render.tile;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;

import game.common.TextureManager;
import game.common.render.IRenderer;
import game.render.texture.ITexture;
import game.tile.TileDoor;

public class TileDoorRenderer implements IRenderer<TileDoor> {

	@Override
	public void draw(TileDoor t) {
		// TODO Auto-generated method stub
		System.out.println("drawing door");
		ITexture texture = TextureManager.getTexture("door_bottom");
		texture.bind();
		GL11.glViewport(64 * 8, 64 * 8, 64, 128);
		GL11.glPushMatrix();
		GL11.glBegin(GL11.GL_POLYGON);
		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(0.0f, 0.0f);

		glTexCoord2f(1.0f, 0.0f);
		glVertex2f(texture.getWidth(), 0.0f);

		glTexCoord2f(1.0f,1f);
		glVertex2f(texture.getWidth(), texture.getHeight());

		glTexCoord2f(0.0f, 1f);
		glVertex2f(0.0f, texture.getHeight());
		GL11.glEnd();
		GL11.glPopMatrix();
		ITexture texture2 = TextureManager.getTexture("door_top");
		texture2.bind();
		GL11.glPushMatrix();
		GL11.glBegin(GL11.GL_POLYGON);
		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(0.0f, 0.0f);

		glTexCoord2f(1.0f, 0.0f);
		glVertex2f(texture2.getWidth(), 0.0f);

		glTexCoord2f(1.0f, 1.0f);
		glVertex2f(texture2.getWidth(), texture2.getHeight());

		glTexCoord2f(0.0f, 1.0f);
		glVertex2f(0.0f, texture2.getHeight());
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	@Override
	public void renderAt(TileDoor t, int x, int y, int w, int h, float yaw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		draw(null);
	}

	@Override
	public void renderAt() {
		// TODO Auto-generated method stub

	}

}
