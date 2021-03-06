package io.discloader.game.render;

import static org.lwjgl.BufferUtils.createByteBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

/**
 * @author Perry Berman
 */
public class GLRU {
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	public static int getMultiplier() {
		if (isWindows()) {
			return 32;
		} else if (isMac()) {
			return 64;
		} else {
			return 32;
		}
	}
	
	public static void glColorh(int c) {
		float blue = ((c << 16) >> 16) / 255;
		float green = (((c >> 8) << 16) >> 8) / 255;
		float red = (c >> 16) / 255;
		GL11.glColor3f(red, green, blue);
	}
	
	/**
	 * Reads the specified resource and returns the raw data as a ByteBuffer.
	 *
	 * @param resource the resource to read
	 * @param bufferSize the initial buffer size
	 * @return the resource data
	 * @throws IOException if an IO error occurs
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
			try (InputStream source = GLRU.class.getClassLoader().getResourceAsStream(resource); ReadableByteChannel rbc = Channels.newChannel(source)) {
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
	
	public static boolean isMac() {
		
		return (OS.indexOf("mac") >= 0);
		
	}
	
	public static boolean isSolaris() {
		
		return (OS.indexOf("sunos") >= 0);
		
	}
	
	public static boolean isUnix() {
		
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
		
	}
	
	public static boolean isWindows() {
		
		return (OS.indexOf("win") >= 0);
		
	}
	
	public static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
		ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
		buffer.flip();
		newBuffer.put(buffer);
		return newBuffer;
	}
}
