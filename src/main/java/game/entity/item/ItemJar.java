package game.entity.item;

import game.render.Resource;

public class ItemJar extends EntityItem {

	private Resource texture;

	public ItemJar() {
		texture = new Resource("game", "texture/item/jar.png");
		posX = 64;
		posY = 64;
	}

	public Resource getResourceLocation() {
		return texture;
	}
}
