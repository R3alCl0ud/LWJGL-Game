package game.renderer.texture;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;

public abstract class AbstractTexture implements ITexture {

	private int width, height;
	protected final int id;

	public AbstractTexture() {
		id = GL11.glGenTextures();
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		if (width > 0) {
			this.width = width;
		}
	}

	public void setHeight(int height) {
		if (height > 0) {
			this.height = height;
		}
	}

	public void delete() {
		GL11.glDeleteTextures(id);
	}

	@Override
	public void setParameter(int name, int value) {
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, name, value);
	}

	@Override
	public void bind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}

	public void uploadData(int width, int height, ByteBuffer data) {
		uploadData(GL11.GL_RGBA, width, height, GL11.GL_RGBA, data);
	}

	@Override
	public void uploadData(int internalFormat, int width, int height, int format, ByteBuffer data) {
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, internalFormat, width, height, 0, format, GL11.GL_UNSIGNED_BYTE, data);
	}

}
