package game.render.texture;

import java.nio.ByteBuffer;

public interface ITexture {

	int getID();

	int getWidth();

	int getHeight();

	void setHeight(int height);

	void setWidth(int width);

	void bind();

	void delete();

	void uploadData(int width, int height, ByteBuffer data);

	void setParameter(int name, int value);

	void uploadData(int internalFormat, int width, int height, int format, ByteBuffer data);

}
