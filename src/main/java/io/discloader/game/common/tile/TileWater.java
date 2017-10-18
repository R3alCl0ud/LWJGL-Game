package io.discloader.game.common.tile;

/**
 * @author Perry Berman
 */
public class TileWater extends Tile {

	public TileWater() {
		setName("water");
	}

	public boolean isFloor() {
		return false;
	}
}
