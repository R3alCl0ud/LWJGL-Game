package io.discloader.game.common.world.room;

import java.util.ArrayList;
import java.util.List;

import io.discloader.game.client.render.IRenderer;
import io.discloader.game.client.render.entity.EntityRenderer;
import io.discloader.game.client.render.tile.TileRenderer;
import io.discloader.game.common.entity.Entity;
import io.discloader.game.common.tile.Tile;
import io.discloader.game.common.tile.TileDoor;
import io.discloader.game.common.world.structure.Structure;
import io.discloader.game.render.GLRU;

/**
 * Abstract class for everyroom in the game
 * 
 * @author Perry Berman
 */
public abstract class Room {

	protected int num;
	private IRenderer<Tile> tileRenderer;
	private IRenderer<Entity> entityRenderer;
	private List<Structure> structures;
	protected final int multi = GLRU.getMultiplier();

	public Room() {
		tileRenderer = new TileRenderer();
		entityRenderer = new EntityRenderer();
		structures = new ArrayList<>();
	}

	public void drawTiles() {
		Tile[][] tiles = getTiles();
		int a = tiles.length, b = tiles[0].length, c = a * b;
		int hw = 64;
		for (int i = 0; i < c; i++) {
			Tile tile = tiles[i / a][i % b];
			tileRenderer.renderAt(tile, hw * (i / a), hw * (i % b), hw, hw, tile.getYaw());
		}
	}

	public void renderEntities() {
		List<Entity> entities = getEntities();
		for (Entity entity : entities) {
			entityRenderer.renderAt(entity, entity.getPosX(), entity.getPosY(), 32, 32, entity.getYaw());
		}
	}

	/**
	 * @return the num
	 */
	public int getNumber() {
		return this.num;
	}

	public Tile getGround() {
		return null;
	}

	/**
	 * Returns the rooms height in number of tiles
	 * 
	 * @return the room's height
	 */
	public abstract int getHeight();

	/**
	 * Returns the rooms width in number of tiles
	 * 
	 * @return the room's width
	 */
	public abstract int getWidth();

	public abstract Tile[][] getTiles();

	public abstract List<Entity> getEntities();

	public abstract void addEntity(Entity entity);

	public List<Structure> getStructures() {
		return structures;
	}

	public Structure getStructureAt(int x, int y) {
		for (Structure s : structures) {
			int l, r, t, b;
			b = s.getPosY() * multi;
			t = b + (s.getHeight() * multi);
			l = s.getPosX() * multi;
			r = l + (s.getWidth() * multi);
			if (x < l || x > r || y < b || y > t)
				continue;
			return s;
		}
		return null;

	}

}
