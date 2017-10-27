package io.discloader.game.common.world.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.discloader.game.common.tile.Tile;
import io.discloader.game.render.GLRU;

public class Structure {

	private final List<String> rows;
	private final Map<Character, Tile> tiles;
	private final int multi = GLRU.getMultiplier();

	public Structure() {
		rows = new ArrayList<>();
		tiles = new HashMap<>();
		addTile(' ', Tile.Air);
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

	public Tile getTileAt(int x, int y) {
		if (y / multi >= rows.size())
			return Tile.Air;
		String row = rows.get(y / multi);
		if (row == null || x / multi >= row.length() || x / multi < 0)
			return Tile.Air;
		return getTile(row.charAt(x / multi)) == null ? Tile.Air : getTile(row.charAt(x / multi));
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
