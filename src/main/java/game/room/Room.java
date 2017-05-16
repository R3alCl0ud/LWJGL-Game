package game.room;

import game.common.renderer.IRenderer;
import game.common.renderer.TileRenderer;
import game.common.renderer.entity.EntityRenderer;
import game.entity.Entity;
import game.tile.Tile;

/**
 * Abstract class for everyroom in the game
 * 
 * @author Perry Berman
 */
public abstract class Room {
	
	protected int num;
	private IRenderer<Tile> tileRenderer;
	private IRenderer<Entity> entityRenderer;
	
	public Room() {
		tileRenderer = new TileRenderer();
		entityRenderer = new EntityRenderer();
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
		Entity[] entities = getEntities();
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
	
	public abstract Tile[][] getTiles();
	
	public abstract Entity[] getEntities();
	
	public abstract void addEntity(Entity entity);
}
