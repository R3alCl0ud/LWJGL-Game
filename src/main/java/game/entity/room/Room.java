package game.entity.room;

import org.lwjgl.opengl.GL11;

import game.entity.tile.Tile;

/**
 * Abstract class for everyroom in the game
 * 
 * 
 * @author Perry Berman
 *
 */
public abstract class Room {
	private int num;
	
	public Room() {
		
	}
	
	public void drawTiles() {
		Tile[][] tiles = getTiles();
		int a = tiles.length, b = tiles[0].length, c = a * b;
		for (int i = 0; i < c; i++) {
			GL11.glViewport(32 * (i / a), 32 * (i % b), 32, 32);
			GL11.glLoadIdentity();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glOrtho(0, 16, 0, 16, -1, 1);
			tiles[i / a][i % b].draw();
		}
	}
	
	/**
	 * @return the num
	 */
	public int getNum() {
		return this.num;
	}
	
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	
	/**
	 * @return the tiles
	 */
	public abstract Tile[][] getTiles();
	
}
