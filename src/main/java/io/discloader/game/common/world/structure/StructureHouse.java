package io.discloader.game.common.world.structure;

import io.discloader.game.common.tile.Tile;

public class StructureHouse extends Structure {

	private int x, y;

	public StructureHouse(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		addRows(" wwdww ", " ww ww ", " wwwww ", "rwwWwwr", "rrrrrrr", " rrrrr ", "  rrr  ", "   r   ");
		addTile('w', Tile.wall).addTile('r', Tile.wood).addTile('W', Tile.window).addTile('d', Tile.door);
		setDepth(3);
	}

	public int getPosX() {
		return x;

	}

	public int getPosY() {
		return y;
	}
}
