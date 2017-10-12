package game.tile;

import game.common.render.IRenderer;
import game.common.render.tile.TileDoorRenderer;

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
