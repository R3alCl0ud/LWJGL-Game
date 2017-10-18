package io.discloader.game.common.entity;

import java.util.List;

import io.discloader.game.common.objects.IInventory;
import io.discloader.game.common.objects.IItem;
import io.discloader.game.render.Resource;
import io.discloader.game.render.texture.ITexture;

/**
 * @author Perry Berman
 *
 */
public class EntityChest extends Entity implements IInventory {
	
	public EntityChest() {
	}
	
	@Override
	public List<IItem> getItems() {
		return null;
	}
	
	@Override
	public void addItem(IItem item) {
	}
	
	@Override
	public IItem getItem(String name) {
		return null;
	}
	
	@Override
	public IItem removeItem(IItem item) {
		return null;
	}
	
	@Override
	public ITexture getTexture() {
		return null;
	}
	
	@Override
	public Resource getResourceLocation() {
		return null;
	}
	
}
