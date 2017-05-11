package game.entity.render;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;

import game.entity.item.EntityItem;
import game.entity.item.ItemJar;
import game.render.IRender;
import game.render.texture.ITexture;
import game.render.texture.ItemTexture;

public class ItemRender implements IRender<EntityItem> {

	private ITexture jarText = null;

	public void draw(EntityItem item, ITexture texture) {
		if (jarText == null && item instanceof ItemJar) {
			jarText = new ItemTexture(item.getResourceLocation());
		}
		jarText.bind();
		// GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glViewport(item.getPosX(), item.getPosY(), 64, 64);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glOrtho(0, 32, 0, 32, -1, 1);
		GL11.glPushMatrix();
		GL11.glTranslatef(8, 8, 0);
		GL11.glRotatef(item.getYaw(), 0f, 0f, 1f);
		GL11.glTranslatef(-8, -8, 0);

		GL11.glBegin(GL11.GL_POLYGON);
		glTexCoord2f(0.0f, 0.0f);
		glVertex2f(0.0f, 0.0f);

		glTexCoord2f(1.0f, 0.0f);
		glVertex2f(32, 0.0f);

		glTexCoord2f(1.0f, 1.0f);
		glVertex2f(32, 32);

		glTexCoord2f(0.0f, 1.0f);
		glVertex2f(0.0f, 32);
		GL11.glEnd();
		 GL11.glPopMatrix();

	}

	@Override
	public void draw(EntityItem item) {
		draw(item, null);
	}
}
