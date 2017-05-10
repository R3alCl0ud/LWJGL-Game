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

	public void draw() {

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
