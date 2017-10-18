package io.discloader.game.common.item;

import io.discloader.game.render.Resource;

public class ItemJar extends Item {
	
	public ItemJar() {
		
	}

	public String getName() {
		return "Jar";
	}
	
	@Override
	public Resource getResourceLocation() {
		return new Resource("game", "texture/item/jar.png");
	}
	
}
