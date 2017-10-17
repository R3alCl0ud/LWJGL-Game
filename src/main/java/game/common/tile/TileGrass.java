package game.common.tile;

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
