package game.common.item;

import game.client.registry.ItemRegistry;
import game.common.RoomManager;
import game.common.entity.Entity;
import game.common.entity.item.EntityItem;
import game.common.objects.IItem;
import game.render.Resource;

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
