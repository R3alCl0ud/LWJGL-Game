package io.discloader.game.client.registry;

import java.util.HashMap;
import java.util.Map;

import io.discloader.game.common.objects.IItem;

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
		System.out.printf("[ItemRegistry] [INFO]: registered %s [%s]\n", item.getName(), item.getClass().getName());
		instance.items.put(item.getName(), item);
	}

	public static IItem getItem(String name) {
		return instance.items.get(name);
	}
}
