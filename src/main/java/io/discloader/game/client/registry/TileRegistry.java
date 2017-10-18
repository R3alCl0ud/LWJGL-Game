package io.discloader.game.client.registry;

import java.util.HashMap;
import java.util.Map;

import io.discloader.game.common.tile.Tile;

public class TileRegistry {

	private static final TileRegistry instance = new TileRegistry();
	private Map<String, Tile> tiles;

	public TileRegistry() {
		tiles = new HashMap<>();
	}

	public static void registerTile(Tile tile, String name) {
		instance.tiles.put(name, tile);
	}

	public static Tile getTile(String name) {
		return instance.tiles.get(name);
	}

}
