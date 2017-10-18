package io.discloader.game.common.entity.item;

import io.discloader.game.common.TextureManager;
import io.discloader.game.common.entity.Entity;
import io.discloader.game.common.objects.IItem;
import io.discloader.game.render.Resource;
import io.discloader.game.render.texture.ITexture;

public class EntityItem extends Entity {
	
	private IItem item;
	
	public EntityItem(IItem item, int x, int y) {
		this.item = item;
		this.posX = x;
		this.posY = x;
	}
	
	@Override
	public ITexture getTexture() {
		return TextureManager.getTexture(item.getName());
	}
	
	@Override
	public Resource getResourceLocation() {
		return null;
	}
	
}
