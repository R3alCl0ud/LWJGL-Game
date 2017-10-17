package game.common.world.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.common.tile.Tile;

public class Structure {

	private final List<String> rows;
	private final Map<Character, Tile> tiles;

	public Structure() {
		rows = new ArrayList<>();
		tiles = new HashMap<>();
	}

	public Structure addRows(String... rows) {
		for (String row : rows)
			this.rows.add(row);
		return this;
	}

	public Structure addTile(char tileID, Tile tile) {
		tiles.put(tileID, tile);
		return this;
	}

	public int getHeight() {
		return rows.size();
	}

	public String getRow(int r) {
		return rows.get(r);
	}

	public int getPosX() {
		return 0;
	}

	public int getPosY() {
		return 0;
	}

	public List<String> getRows() {
		return rows;
	}

	public Tile getTile(char tileID) {
		return tiles.get(tileID);
	}

	public int getWidth() {
		int width = 0;
		for (String row : rows) {
			if (row.length() > width)
				width = row.length();
		}
		return width;
	}
}
