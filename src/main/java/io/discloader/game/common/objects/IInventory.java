package io.discloader.game.common.objects;

import java.util.List;

public interface IInventory {

	public List<IItem> getItems();

	public void addItem(IItem item);

	public IItem getItem(String name);

	public IItem removeItem(IItem item);
}
