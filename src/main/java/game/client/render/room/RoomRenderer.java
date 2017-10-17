package game.client.render.room;

import game.client.render.IRenderer;
import game.client.render.entity.EntityRenderer;
import game.client.render.tile.TileRenderer;
import game.common.entity.Entity;
import game.common.tile.Tile;
import game.common.world.room.Room;
import game.common.world.structure.Structure;

public class RoomRenderer implements IRenderer<Room> {

	private IRenderer<Tile> tileRenderer;
	private IRenderer<Entity> entityRenderer;
	private final int multi = 64;

	public RoomRenderer() {
		tileRenderer = new TileRenderer();
		entityRenderer = new EntityRenderer();
	}

	@Override
	public void draw(Room room) {
		// TODO Auto-generated method stub
		Tile ground = room.getGround();
		int a = room.getHeight(), b = room.getWidth(), c = a * b;
		for (int i = 0; i < c; i++) {
			tileRenderer.renderAt(ground, multi * (i / a), multi * (i % b), multi, multi, ground.getYaw());
		}
		for (int i = 0; i < room.getStructures().size(); i++) {
			Structure s = room.getStructures().get(i);
			for (int n = 0; n < s.getHeight(); n++) {
				String r = s.getRow(n);
				for (int t = 0; t < s.getWidth(); t++) {
					if (r.charAt(t) == ' ')
						continue;
					Tile tile = s.getTile(r.charAt(t));
					if (tile == null)
						continue;
					if (tile.getRenderer() != null) {
						tile.getRenderer().draw();
					} else {
						tileRenderer.renderAt(tile, multi * t, multi * n, multi, multi, 0f);
					}
				}
			}
		}

	}

	@Override
	public void renderAt(Room t, int x, int y, int w, int h, float yaw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderAt() {
		// TODO Auto-generated method stub

	}

}
