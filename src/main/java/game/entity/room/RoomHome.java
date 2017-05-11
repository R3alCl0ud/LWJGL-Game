package game.entity.room;

import org.lwjgl.opengl.GL11;

import game.entity.tile.Tile;
import game.entity.tile.TileGrass;
import game.entity.tile.TileWall;
import game.entity.tile.TileWater;
import game.entity.tile.TileWindow;
import game.entity.tile.TileWood;

/**
 * @author Perry Berman
 *
 */
public class RoomHome extends Room {
	
	private Tile[][] tiles = new Tile[16][16];
	private Tile grass = new TileGrass();
	private Tile water = new TileWater();
	private Tile wood = new TileWood();
	private Tile wall = new TileWall();
	private Tile window = new TileWindow();
	
	public RoomHome() {
		for (int i = 0; i < 16 * 16; i++) {
			int x = i / 16, y = i % 16;
			tiles[x][y] = grass;
		}
		
		for (int i = 0; i < 16 * 16; i++) {
			int x = i / 16, y = i % 16;
			if (x > 3 && x < 12 && y > 10) {
				if (x > 3 && x < 12 && y == 11) {
					tiles[x][y] = wood;
				} else if (x > 4 && x < 11 && y == 12) {
					tiles[x][y] = wood;
				} else if (x > 5 && x < 10 && y == 13) {
					tiles[x][y] = wood;
				} else if (x > 6 && x < 9 && y == 14) {
					tiles[x][y] = wood;
				} else if (x > 7 && x < 8 && y == 15) {
					tiles[x][y] = wood;
				}
			}
			else if (x > 2 && x < 13 && y >= 3 && y <= 11) {
				if ((x == 6 || x == 9) && y == 9) {
					tiles[x][y] = window;
				} else if (x >= 5 && x < 11 && y >= 7) {
					tiles[x][y] = wall;
				} else if (x > 6 && x <= 8) {
					tiles[x][y] = wood;
				} else {
					tiles[x][y] = water;
				}
			} else {
				tiles[x][y] = grass;
			}
		}
	}
	
	@Override
	public void drawTiles() {
		Tile[][] tiles = getTiles();
		int a = tiles.length, b = tiles[0].length, c = a * b;
		for (int i = 0; i < c; i++) {
			GL11.glViewport(32 * (i / a), 32 * (i % b), 32, 32);
			GL11.glLoadIdentity();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glOrtho(0, 16, 0, 16, -1, 1);
			Tile tile = tiles[i / a][i % b];
			if (tile instanceof TileGrass)
				((TileGrass) tile).draw(154);
			else if (tile instanceof TileWood)
				((TileWood) tile).draw(28);
			else if (tile instanceof TileWater)
				((TileWater) tile).draw(50);
			else if (tile instanceof TileWall)
				((TileWall) tile).draw(22);
			else
				tile.draw();
		}
	}
	public Tile[][] getTiles() {
		return tiles;
	}
}
