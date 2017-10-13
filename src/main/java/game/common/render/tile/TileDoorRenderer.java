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
//		System.out.println("drawing door");
		ITexture texture = TextureManager.getTexture("door");
//		ITexture texture2 = TextureManager.getTexture("door_top");
		GL11.glLoadIdentity();
		GL11.glViewport(432, 432, 96, 192);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		texture.bind();
		GL11.glBegin(GL11.GL_POLYGON);
		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(0.0f, 0.0f);

		glTexCoord2f(1.0f, 0.0f);
		glVertex2f(1f, 0.0f);

		glTexCoord2f(1.0f,1f);
		glVertex2f(1f, 1f);

		glTexCoord2f(0.0f, 1f);
		glVertex2f(0.0f, 1f);
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
