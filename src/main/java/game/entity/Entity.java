package game.entity;

import static org.lwjgl.system.MemoryUtil.memAllocFloat;

import java.nio.FloatBuffer;

/**
 * @author Perry Berman
 */
public class Entity {

	private int room;
	private int posX;
	private int posY;
	private float yaw;

	public Entity() {
		posX = -2;
		posY = 0;
	}

	public FloatBuffer draw() {
		FloatBuffer buffer = memAllocFloat(4 * 2);
		buffer.put((+0.5f + posX)).put(+0.5f);
		buffer.put((+0.0f + posX)).put(+0.5f);
		buffer.put((+0.5f + posX)).put(-0.5f);
		buffer.put((+0.0f + posX)).put(-0.5f);
		buffer.flip();
		if (posX % 2 == 0 && posX % 3 == 0) posX += 2;
		else if (posX % 2 == 1) posX++;
		else posX--;
		return buffer;
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @return the poxY
	 */
	public int getPoxY() {
		return posY;
	}

	/**
	 * @return the room
	 */
	public int getRoom() {
		return room;
	}

	/**
	 * @return the yaw
	 */
	public float getYaw() {
		return yaw;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @param poxY the poxY to set
	 */
	public void setPosY(int poxY) {
		this.posY = poxY;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(int room) {
		this.room = room;
	}

	/**
	 * @param yaw the yaw to set
	 */
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

}
