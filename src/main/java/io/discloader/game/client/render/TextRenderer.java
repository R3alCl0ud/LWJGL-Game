package io.discloader.game.client.render;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;

import io.discloader.game.client.registry.TextureRegistry;
import io.discloader.game.render.texture.ITexture;

public class TextRenderer implements IRenderer<String> {

	private ITexture characters = TextureRegistry.getTexture("characters");
	private float height = 0.03125f, width = 0.03125f, numStart = 0.59375f, spaceWidth = 0.0625f;
	private float lowerStart = 0.8125f, offset = 0.015625f;

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
//			System.out.printf();
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
	public void draw() {

	}

	@Override
	public void draw(String t) {

	}

	@Override
	public void renderAt() {

	}

	@Override
	public void renderAt(String t, int x, int y, float z) {}

	@Override
	public void renderAt(String t, int x, int y, int w, int h, float yaw, float z) {}

}
