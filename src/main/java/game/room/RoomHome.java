package game.room;

import java.util.ArrayList;
import java.util.List;

import game.entity.Entity;
import game.tile.Tile;
import game.tile.TileGrass;
import game.tile.TileWall;
import game.tile.TileWater;
import game.tile.TileWindow;
import game.tile.TileWood;

/**
 * @author Perry Berman
 */
public class RoomHome extends Room {

	private Tile[][] tiles = new Tile[16][16];
	private List<Entity> entities;
	private Tile ground = new TileGrass();

	public RoomHome() {
		super();
		entities = new ArrayList<>();
		for (int i = 0; i < 16 * 16; i++) {
			int x = i / 16, y = i % 16;
			if ((x > 3 && x < 12 && y == 11) || (x > 4 && x < 11 && y == 12)) {
				tiles[x][y] = new TileWood(90f);
			} else if (x > 2 && x < 13 && y >= 3 && y <= 11) {
				if ((x == 6 || x == 9) && y == 9) {
					tiles[x][y] = new TileWindow();
				} else if (x >= 5 && x < 11 && y >= 7) {
					tiles[x][y] = new TileWall(x == 6 && y == 8 ? 90f : 0f);
				} else if (x > 6 && x <= 8) {
					tiles[x][y] = new TileWood();
				} else {
					tiles[x][y] = new TileWater();
				}
			} else {
				tiles[x][y] = new TileGrass();
			}
		}
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	@Override
	public List<Entity> getEntities() {
		return entities;
	}

	@Override
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	@Override
	public Tile getGround() {
		return ground;
	}

	public void setGround(Tile ground) {
		this.ground = ground;
	}

	@Override
	public int getHeight() {
		return 16;
	}

	@Override
	public int getWidth() {
		return 16;
	}
}
