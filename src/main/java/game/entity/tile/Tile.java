package game.entity.tile;

import game.common.TextureManager;
import game.renderer.Resource;
import game.renderer.texture.ITexture;
import game.renderer.texture.Texture;

/**
 * @author Perry Berman
 */
public class Tile {

	public static void registerTextures() {
		TextureManager.registerTexture("grass", new Texture(new Resource("game", "texture/tile/grass.png")));
		TextureManager.registerTexture("sand", new Texture(new Resource("game", "texture/tile/sand.png")));
		TextureManager.registerTexture("wall", new Texture(new Resource("game", "texture/tile/wall.png")));
		TextureManager.registerTexture("water", new Texture(new Resource("game", "texture/tile/water.png")));
		TextureManager.registerTexture("window", new Texture(new Resource("game", "texture/tile/window.png")));
		TextureManager.registerTexture("wood", new Texture(new Resource("game", "texture/tile/wood.png")));
	}
	private String name;
	private int posX, posY;
	private float yaw;

	public String getName() {
		return name;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public Resource getResourceLocation() {
		return null;
	}

	public ITexture getTexture() {
		return TextureManager.getTexture(getName());
	}

	public float getYaw() {
		return yaw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	
	public boolean isSolid() {
		return false;
	}

	public boolean isFloor() {
		return true;
	}
}

