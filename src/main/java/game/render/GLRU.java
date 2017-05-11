package game.render;

import org.lwjgl.opengl.GL11;

/**
 * @author Perry Berman
 *
 */
public class GLRU {
	
	public static void glColorh(int c) {
		float blue = ((c << 16) >> 16) / 255;
		float green = (((c >> 8) << 16) >> 8) / 255;
		float red = (c >> 16) / 255;
		GL11.glColor3f(red, green, blue);
	}
	
}
