package io.discloader.game.common.item;

import io.discloader.game.client.registry.ItemRegistry;
import io.discloader.game.client.registry.RoomManager;
import io.discloader.game.common.entity.Entity;
import io.discloader.game.common.entity.item.EntityItem;
import io.discloader.game.common.objects.IItem;
import io.discloader.game.render.Resource;

/**
 * @author Perry Berman
 *
 */
public class Item implements IItem {
	
	private String name;
	
	
	public Item() {
		
	}
	
	public static void registerItems() {
		ItemRegistry.registerItem(new ItemJar());
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean isTool() {
		return false;
	}
	
	@Override
	public int getReachDistance() {
		return 2;
	}
	
	@Override
	public Entity createEntity(int x, int y, int room) {
		Entity entity = new EntityItem(this, x, y);
		RoomManager.getRoom(room).addEntity(entity);
		return entity;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Resource getResourceLocation() {
		return null;
	}
	
}
