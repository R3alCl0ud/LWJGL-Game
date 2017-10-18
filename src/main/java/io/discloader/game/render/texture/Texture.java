package io.discloader.game.render.texture;

import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.stb.STBImage;

import io.discloader.game.render.Resource;

public class Texture extends AbstractTexture {

	public Texture(Resource resource) {
		super();
		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		IntBuffer comp = BufferUtils.createIntBuffer(1);
		stbi_set_flip_vertically_on_load(true);
		ByteBuffer buf = STBImage.stbi_load(resource.getPath(), w, h, comp, 4);
		if (buf == null) {
			try {
				buf = STBImage.stbi_load(resource.getFile().getPath(), w, h, comp, 4);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		if (buf == null) {
			throw new RuntimeException("MISSING TEXTURES");
		}
		setWidth(w.get(0));
		setHeight(h.get(0));
		bind();
		uploadData(getWidth(), getHeight(), buf);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		setParameter(GL11.GL_TEXTURE_WRAP_S, GL13.GL_CLAMP_TO_BORDER);
		setParameter(GL11.GL_TEXTURE_WRAP_T, GL13.GL_CLAMP_TO_BORDER);
		setParameter(GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		setParameter(GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
	}

}
