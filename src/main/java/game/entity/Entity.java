package game.entity;

import game.render.Resource;
import game.render.texture.ITexture;

/**
 * @author Perry Berman
 */
public abstract class Entity {
	
	protected int posX, posY;
	private float yaw;
	
	public Entity() {
		posX = 0;
		posY = 0;
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
	public int getPosY() {
		return posY;
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
	 * @param yaw the yaw to set
	 */
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	
	public abstract ITexture getTexture();
	
	public abstract Resource getResourceLocation();
	
}
