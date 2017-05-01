package game.common.objects.entity.player;

import java.util.ArrayList;
import java.util.List;

import game.common.objects.IInventory;
import game.common.objects.IItem;
import game.common.objects.IPlayer;

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
