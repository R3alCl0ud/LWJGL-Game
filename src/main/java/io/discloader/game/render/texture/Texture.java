package io.discloader.game.render.texture;

import static org.lwjgl.BufferUtils.createByteBuffer;
import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
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
			try {
				buf = ioResourceToByteBuffer(resource.getPath(), 8 * 256);
			} catch (IOException e) {
				throw new RuntimeException("MISSING TEXTURES");
			}
			try {
				buf = ByteBuffer.wrap(readAllBytes(resource));
			} catch (IOException e) {
				throw new RuntimeException("MISSING TEXTURES");
			}
		}
		setWidth(w.get(0));
		setHeight(h.get(0));
		bind();

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		setParameter(GL11.GL_TEXTURE_WRAP_S, GL13.GL_CLAMP_TO_BORDER);
		setParameter(GL11.GL_TEXTURE_WRAP_T, GL13.GL_CLAMP_TO_BORDER);
		setParameter(GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		setParameter(GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);

		uploadData(getWidth(), getHeight(), buf);
	}

	public Texture(int width, int height, ByteBuffer buffer) {
		setWidth(width);
		setHeight(height);
		bind();

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		setParameter(GL11.GL_TEXTURE_WRAP_S, GL13.GL_CLAMP_TO_BORDER);
		setParameter(GL11.GL_TEXTURE_WRAP_T, GL13.GL_CLAMP_TO_BORDER);
		setParameter(GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		setParameter(GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);

		uploadData(getWidth(), getHeight(), buffer);
	}

	/**
	 * A more efficient readAllBytes method
	 * 
	 * @param resource
	 * @return A byte array containing the resource's data
	 * @throws IOException
	 */
	public static byte[] readAllBytes(Resource resource) throws IOException {
		return IOUtils.toByteArray(resource.getResourceAsStream());
	}

	/**
	 * Reads the specified resource and returns the raw data as a ByteBuffer.
	 *
	 * @param resource
	 *            the resource to read
	 * @param bufferSize
	 *            the initial buffer size
	 *
	 * @return the resource data
	 *
	 * @throws IOException
	 *             if an IO error occurs
	 */
	public static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
		ByteBuffer buffer;

		Path path = Paths.get(resource);
		if (Files.isReadable(path)) {
			try (SeekableByteChannel fc = Files.newByteChannel(path)) {
				buffer = BufferUtils.createByteBuffer((int) fc.size() + 1);
				while (fc.read(buffer) != -1) {
					;
				}
			}
		} else {
			try (InputStream source = Texture.class.getClassLoader().getResourceAsStream(resource); ReadableByteChannel rbc = Channels.newChannel(source)) {
				buffer = createByteBuffer(bufferSize);

				while (true) {
					int bytes = rbc.read(buffer);
					if (bytes == -1) {
						break;
					}
					if (buffer.remaining() == 0) {
						buffer = resizeBuffer(buffer, buffer.capacity() * 2);
					}
				}
			}
		}

		buffer.flip();
		return buffer;
	}

	private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
		ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
		buffer.flip();
		newBuffer.put(buffer);
		return newBuffer;
	}

}
