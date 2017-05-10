package game.entity;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.memAllocFloat;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class EntityCube extends Entity {
	private boolean b = true;
	
	public void draw() {
		// FloatBuffer buffer = memAllocFloat(12 * 3);
		// buffer.put(150f).put(50f).put(0f);
		// buffer.put(150f).put(150f).put(0f);
		// buffer.put(50f).put(150f).put(0f);
		// buffer.put(50f).put(50f).put(0f);
		// buffer.put(150f).put(50f).put(100f);
		// buffer.put(150f).put(150f).put(100f);
		// buffer.put(50f).put(150f).put(100f);
		// buffer.put(50f).put(50f).put(100f);
		// buffer.put(150f).put(50f).put(100f);
		// buffer.put(150f).put(150f).put(100f);
		// buffer.put(150f).put(50f).put(00f);
		// buffer.put(150f).put(150f).put(00f);
		// buffer.put(50f).put(150f).put(00f);
		// buffer.put(50f).put(50f).put(00f);
		// buffer.flip();
		// int vbo = GL15.glGenBuffers();
		//
		// GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		// GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		// GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		// GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0L);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		// stackPush();
		GL11.glViewport(0, 0, 300, 300);
		GL11.glMatrixMode(GL11.GL_PROJECTION);

		GL11.glLoadIdentity();

		// set the projection (could use glTranslate/glScale but this
		// utility function is simpler)
		GL11.glOrtho(0, 300, 0, 300, -1, 1); // left,right,bottom,top,front,back

		// common practice to leave modelview as the current matrix for
		// editing
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glTranslatef(100, 100, 0);
		GL11.glRotatef(0f, 0, 0, 1);
		GL11.glRotatef(0.01f, 0, 1, 0);
		if (b) {
			b = false;
			GL11.glRotatef(2f, 0, 1, 0);
		}
		GL11.glTranslatef(-100, -100, 0);
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glColor3f(1.0f, 0.0f, 1.0f);
		GL11.glVertex3f(50f, 50f, 0f);
		GL11.glVertex3f(50f, 50f, 0f);
		GL11.glVertex3f(50f, 150f, 0f);
		GL11.glVertex3f(50f, 150f, 0f);
		GL11.glColor3f(1.0f, 0.0f, 0.0f);
		GL11.glVertex3f(50f, 50f, 0f);
		GL11.glVertex3f(50f, 150f, 0f);
		GL11.glVertex3f(50f, 150f, 20f);
		GL11.glVertex3f(50f, 50f, 20f);
		GL11.glColor3f(0.0f, 1.0f, 1.0f);
		GL11.glVertex3f(50f, 50f, 20f);
		GL11.glVertex3f(50f, 150f, 20f);
		GL11.glVertex3f(150f, 150f, 20f);
		GL11.glVertex3f(150f, 50f, 20f);
		GL11.glColor3f(0.0f, 1.0f, 1.0f);
		GL11.glVertex3f(50f, 50f, 20f);
		GL11.glVertex3f(50f, 150f, 20f);
		GL11.glVertex3f(150f, 150f, 20f);
		GL11.glVertex3f(150f, 50f, 20f);
		GL11.glEnd();
		// GL11.glDrawArrays(GL11.GL_POLYGON, 0, 12);
	}
}
