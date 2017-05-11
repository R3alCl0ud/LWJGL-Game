package game.entity.room;

import org.lwjgl.opengl.GL11;

import game.entity.render.TileRender;
import game.entity.tile.Tile;
import game.entity.tile.TileGrass;
import game.entity.tile.TileWall;
import game.entity.tile.TileWater;
import game.entity.tile.TileWindow;
import game.entity.tile.TileWood;
import game.render.IRender;

/**
 * @author Perry Berman
 */
public class RoomHome extends Room {

	private Tile[][] tiles = new Tile[16][16];
	private Tile grass;
	private Tile water;
	private Tile wood;
	private Tile wall;
	private Tile window;
	private IRender<Tile> render;

	public RoomHome() {
		grass = new TileGrass();
		water = new TileWater();
		wood = new TileWood();
		wall = new TileWall();
		window = new TileWindow();
		render = new TileRender();
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
					tiles[x][y] = grass;
				} else if (x > 6 && x < 9 && y == 14) {
					tiles[x][y] = grass;
				} else if (x > 7 && x < 8 && y == 15) {
					tiles[x][y] = grass;
				}
			} else if (x > 2 && x < 13 && y >= 3 && y <= 11) {
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
			GL11.glViewport(64 * (i / a), 64 * (i % b), 64, 64);
			GL11.glLoadIdentity();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glOrtho(0, 16, 0, 16, -1, 1);
			render.draw(tiles[i / a][i % b]);
		}
	}

	public Tile[][] getTiles() {
		return tiles;
	}
}
