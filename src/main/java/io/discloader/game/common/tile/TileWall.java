package io.discloader.game.common.tile;

/**
 * @author Perry Berman
 */
public class TileWall extends Tile {

	public TileWall() {
		this(0.0f);
	}
	
	public TileWall(float yaw) {
		setName("wall");
		setYaw(yaw);
	}

	public boolean isSolid() {
		return true;
	}

	public boolean isFloor() {
		return false;
	}
}
