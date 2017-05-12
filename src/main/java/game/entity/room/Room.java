package game.entity.room;

import org.lwjgl.opengl.GL11;

import game.common.renderer.IRender;
import game.common.renderer.TileRenderer;
import game.common.renderer.entity.EntityRenderer;
import game.entity.Entity;
import game.entity.tile.Tile;

/**
 * Abstract class for everyroom in the game
 * 
 * 
 * @author Perry Berman
 *
 */
public abstract class Room {
	protected int num;
	private IRender<Tile> render;
	private IRender<Entity> entityRenderer;
	
	public Room() {
		render = new TileRenderer();
		entityRenderer = new EntityRenderer();
	}
	
	public void drawTiles() {
		Tile[][] tiles = getTiles();
		int a = tiles.length, b = tiles[0].length, c = a * b;
		for (int i = 0; i < c; i++) {
			GL11.glViewport(32 * (i / a), 32 * (i % b), 32, 32);
			GL11.glLoadIdentity();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glOrtho(0, 16, 0, 16, -1, 1);
			render.draw(tiles[i / a][i % b]);
		}
	}
	
	public void renderEntities() {
		Entity[] entities = getEntities();
		for (Entity entity : entities) {
			entityRenderer.draw(entity);
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
