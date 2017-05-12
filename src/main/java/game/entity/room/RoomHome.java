package game.entity.room;

import java.util.ArrayList;
import java.util.List;

import game.entity.Entity;
import game.entity.tile.Tile;
import game.entity.tile.TileGrass;
import game.entity.tile.TileWall;
import game.entity.tile.TileWater;
import game.entity.tile.TileWindow;
import game.entity.tile.TileWood;

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
	private List<Entity> entities;
	
	public RoomHome() {
		entities = new ArrayList<>();
		grass = new TileGrass();
		water = new TileWater();
		wood = new TileWood();
		wall = new TileWall();
		window = new TileWindow();
		
		for (int i = 0; i < 16 * 16; i++) {
			int x = i / 16, y = i % 16;
			if (x > 3 && x < 12 && y == 11) {
				tiles[x][y] = wood;
			} else if (x > 4 && x < 11 && y == 12) {
				tiles[x][y] = wood;
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
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
	@Override
	public Entity[] getEntities() {
		return entities.toArray(new Entity[0]);
	}
	
	@Override
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
}
