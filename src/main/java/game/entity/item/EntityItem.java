package game.entity.item;

import game.common.TextureManager;
import game.common.objects.IItem;
import game.entity.Entity;
import game.render.Resource;
import game.render.texture.ITexture;

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
