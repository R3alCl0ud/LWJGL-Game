package game.client.registry;

import java.util.HashMap;
import java.util.Map;

import game.common.objects.IItem;

/**
 * @author Perry Berman
 *
 */
public class ItemRegistry {
	private static ItemRegistry instance = new ItemRegistry();
	private Map<String, IItem> items;
	
	public ItemRegistry() {
		items = new HashMap<>();
	}
	
	public static void registerItem(IItem item) {
		instance.items.put(item.getName(), item);
	}
	
	public static IItem getItem(String name) {
		return instance.items.get(name);
	}
}
