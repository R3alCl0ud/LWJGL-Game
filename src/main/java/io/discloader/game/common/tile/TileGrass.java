package io.discloader.game.common.tile;

/**
 * @author Perry Berman
 */
public class TileGrass extends Tile {

	public TileGrass() {
		setName("grass");
	}

	public boolean isSolid() {
		return false;
	}

}
