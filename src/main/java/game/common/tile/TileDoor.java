package game.common.tile;

import game.client.render.IRenderer;
import game.client.render.tile.TileDoorRenderer;

public class TileDoor extends Tile {

	private IRenderer<TileDoor> renderer;

	public TileDoor() {
		setName("door");
		renderer = new TileDoorRenderer();
	}

	public boolean isSolid() {
		return true;
	}

	@Override
	public IRenderer<TileDoor> getRenderer() {
		return renderer;
	}
}
