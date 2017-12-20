package io.discloader.game.client.render;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.Color;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import io.discloader.game.client.registry.TextureRegistry;
import io.discloader.game.render.texture.ITexture;

public class TextRenderer implements IRenderer<String> {

	private ITexture characters = TextureRegistry.getTexture("characters");
	private float height = 0.03125f, width = 0.03125f, numStart = 0.59375f, spaceWidth = 0.0625f;
	private float lowerStart = 0.8125f, offset = 0.015625f;
	private FloatBuffer vertices;
	private int vertexCount = 0;
	private int vaoId;
	private int vboId;

	public TextRenderer() {
		super();
		vertices = MemoryUtil.memAllocFloat(4096);
	}

	@Override
	public void begin() {
		vertices.clear();
		GL11.glViewport(0, 0, multi * 16, multi * 16);
		GL11.glLoadIdentity();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix();
		GL11.glBegin(GL11.GL_POLYGON);
	}

	@Override
	public void draw() {

	}

	@Override
	public void draw(String t) {

	}

	@Override
	public void drawTextureRegion(ITexture texture, float x, float y, float regX, float regY, float regWidth, float regHeight, Color c) {
		/* Vertex positions */
		float x1 = x;
		float y1 = y;
		float x2 = x + regWidth;
		float y2 = y + regHeight;

		/* Texture coordinates */
		float s1 = regX / texture.getWidth();
		float t1 = regY / texture.getHeight();
		float s2 = (regX + regWidth) / texture.getWidth();
		float t2 = (regY + regHeight) / texture.getHeight();

		/* Draw text */
		//
		// GL11.glColor4f(c.getRed() / 256f, c.getGreen() / 256f, c.getBlue() / 256f,
		// 0f);
		//
		// glTexCoord2f(s1, t1);
		// glVertex3f(x1, y1, 0.3f);
		//
		// glTexCoord2f(s2, t1);
		// glVertex3f(x2, y1, 0.3f);
		//
		// glTexCoord2f(s2, t2);
		// glVertex3f(x2, y2, 0.3f);
		//
		// glTexCoord2f(s1, t2);
		// glVertex3f(x1, y2, 0.3f);
		float r = c.getRed();
		float g = c.getGreen();
		float b = c.getBlue();

		vertices.put(x1).put(y1).put(r).put(g).put(b).put(s1).put(t1);
		vertices.put(x1).put(y2).put(r).put(g).put(b).put(s1).put(t2);
		vertices.put(x2).put(y2).put(r).put(g).put(b).put(s2).put(t2);

		vertices.put(x1).put(y1).put(r).put(g).put(b).put(s1).put(t1);
		vertices.put(x2).put(y2).put(r).put(g).put(b).put(s2).put(t2);
		vertices.put(x2).put(y1).put(r).put(g).put(b).put(s2).put(t1);
		vertices.flip();
		vertexCount = 6;
		vaoId = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoId);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
		// Put the VBO in the attributes list at index 0
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		// Deselect (bind to 0) the VBO
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

		// Deselect (bind to 0) the VAO
		// GL30.glBindVertexArray(0);
		GL30.glBindVertexArray(vaoId);
		GL20.glEnableVertexAttribArray(0);

		// Draw the vertices
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);

		// Put everything back to default (deselect)
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
//		vertices.
	}

	@Override
	public void end() {
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	@Override
	public void renderAt() {

	}

	@Override
	public void renderAt(String t, int x, int y) {
		characters.bind();
		int drawX = x * multi, drawY = (multi * y);
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			float left = offset, right = width, top = 1f, bottom = 1f - height;
			int offsetY = 0;
			if (c == ' ') {
				continue;
			} else if (c == '\n') {
				y--;
				continue;
			} else if (c >= 'a' && c <= 'f') {
				left = lowerStart + (width * (c - 'a'));
				right = width + lowerStart + (width * (c - 'a'));
			} else if (c >= 'g' && c <= 'z') {
				top = bottom;
				bottom -= height;
				offsetY = -14;
			} else if (c >= 'A' && c <= 'Z') {
				left = (width * (c - 'A'));
				right = (offset * 1.5f) + (width * (c - 'A'));
			} else if (c >= '0' && c <= '9') {
				top = 0.96875f;
				bottom = 0.9375f;
				left = numStart + (width * ((c - '0')));
				right = width + numStart + (width * ((c - '0')));
				offsetY = -14;
			} else if (c >= '!' && c <= '/') {
				left = (width * ((c - '!')));
				right = (width * ((c - ' ')));
				offsetY = -2;
			} else if (c >= ':' && c <= '?') {
				top = height * 28;
				bottom = height * 27;
				left = (width * ((c - ':') + 15));
				right = (width * ((c - ':') + 16));
				offsetY = -12;
			} else {
				continue;
			}
			drawX = (multi * (x + i)) - (24 * i);
			GL11.glViewport(drawX, drawY + offsetY, multi, multi);
			GL11.glLoadIdentity();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glPushMatrix();
			GL11.glBegin(GL11.GL_POLYGON);
			glTexCoord2f(left, bottom);
			glVertex2f(-1f, -1f);

			glTexCoord2f(right, bottom);
			glVertex2f(1.0f, -1.0f);

			glTexCoord2f(right, top);
			glVertex2f(1f, 1f);

			glTexCoord2f(left, top);
			glVertex2f(-1.0f, 1f);
			GL11.glEnd();
			GL11.glPopMatrix();
		}
	}

	@Override
	public void renderAt(String t, int x, int y, float z) {}

	@Override
	public void renderAt(String t, int x, int y, int w, int h, float yaw) {
		characters.bind();
		int drawX = x * multi;
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			float left = 0f, right = width, top = 1f, bottom = 1f - height;
			if (c == ' ') {

			}
			if (c >= 'a' && c <= 'f') {
				left = offset + lowerStart + (width * (c - 'a'));
				right = (offset * 2) + lowerStart + (width * (c - 'a'));
			}
			if (c >= 'g' && c <= 'z') {
				top = bottom;
				bottom -= height;
			}
			GL11.glViewport(multi * (i / t.length()), multi * (i % t.length()), multi, multi);
			GL11.glLoadIdentity();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glPushMatrix();
			GL11.glBegin(GL11.GL_POLYGON);
			glTexCoord2f(left, bottom);
			glVertex3f(-1f, -1f, 1f);

			glTexCoord2f(right, bottom);
			glVertex3f(1.0f, -1.0f, 1f);

			glTexCoord2f(right, top);
			glVertex3f(1f, 1f, 1f);

			glTexCoord2f(left, top);
			glVertex3f(-1.0f, 1f, 1f);
			GL11.glEnd();
			GL11.glPopMatrix();
		}
	}

	@Override
	public void renderAt(String t, int x, int y, int w, int h, float yaw, float z) {}

}
