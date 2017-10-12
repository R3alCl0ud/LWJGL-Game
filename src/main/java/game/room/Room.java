package game.room;

import java.util.List;

import game.common.render.IRenderer;
import game.common.render.entity.EntityRenderer;
import game.common.render.tile.TileRenderer;
import game.entity.Entity;
import game.tile.Tile;
import game.tile.TileDoor;

/**
 * Abstract class for everyroom in the game
 * 
 * @author Perry Berman
 */
public abstract class Room {
	
	protected int num;
	private IRenderer<Tile> tileRenderer;
	private IRenderer<Entity> entityRenderer;
	private Tile[] structures;
	
	public Room() {
		tileRenderer = new TileRenderer();
		entityRenderer = new EntityRenderer();
		structures = new Tile[1];
		structures[0] = new TileDoor();
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

	public Tile[] getStructures() {
		return structures;
	}

}
