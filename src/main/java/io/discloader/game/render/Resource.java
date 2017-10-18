package io.discloader.game.render;

import static org.lwjgl.BufferUtils.createByteBuffer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * @author Perry Berman
 */
public class Resource {

	private String location;
	private String name;
	private ByteBuffer buf = null;

	public Resource(String domain, String name) {
		location = domain;
		this.name = name;
	}

	public String getDomain() {
		return location;
	}

	public String getName() {
		return name;
	}

	public String getFileName() {
		String[] l = getPath().split("/");
		return l[l.length - 1];
	}

	public String getPath() {
		return "/assets/" + location + "/" + name;
	}

	public File getFile() throws IOException {
		// try loading from the ClassLoader
		URL url = ClassLoader.getSystemClassLoader().getResource(getPath());
		// try from the class it's self because java just doesn't understand how
		// file systems work
		if (url == null) url = Resource.class.getResource(getPath());
		// since we still can't find it, throw the exception
		if (url == null) throw new FileNotFoundException(getPath());
		File file = new File(url.getFile());
		return file;
	}

	public InputStream getResourceAsStream() {
		InputStream is = Resource.class.getResourceAsStream(getPath());
		return is == null ? ClassLoader.getSystemResourceAsStream(getPath()) : is;
	}

	public URL toURL() {
		return Resource.class.getResource(getPath());
	}

	public ByteBuffer getBytes() {
		if (buf == null) {
			InputStream is = getResourceAsStream();
			ReadableByteChannel rbc = Channels.newChannel(is);
			buf = createByteBuffer(8 * 1024);
			try {
				while (true) {
					int bytes = rbc.read(buf);
					if (bytes == -1) {
						break;
					}
					if (buf.remaining() == 0) {
						buf = GLRU.resizeBuffer(buf, buf.capacity() * 2);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buf;
	}

}
