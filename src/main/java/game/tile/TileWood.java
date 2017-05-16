package game.tile;

/**
 * @author Perry Berman
 */
public class TileWood extends Tile {

	public TileWood() {
		this(0f);
	}

	public TileWood(float yaw) {
		setName("wood");
		setYaw(yaw);
	}

}
