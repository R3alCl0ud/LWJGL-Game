package io.discloader.game.common.entity.player;

import java.util.ArrayList;
import java.util.List;

import io.discloader.game.common.objects.IInventory;
import io.discloader.game.common.objects.IItem;
import io.discloader.game.common.objects.IPlayer;

public class PlayerInventory implements IInventory {

	private List<IItem> items;
	public final IPlayer player;

	public PlayerInventory(IPlayer p) {
		player = p;
		items = new ArrayList<>();
	}

	public List<IItem> getItems() {
		return items;
	}

	public void addItem(IItem item) {
		items.add(item);
	}

	@Override
	public IItem getItem(String name) {
		for (IItem item : items) {
			if (item.getName().equals(name)) return item;
		}
		return null;
	}

	@Override
	public IItem removeItem(IItem item) {
		return items.remove(items.indexOf(item));
	}

}
