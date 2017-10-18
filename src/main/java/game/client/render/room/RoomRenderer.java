package game.client.render.room;

import game.client.render.IRenderer;
import game.client.render.RenderManager;
import game.client.render.entity.EntityRenderer;
import game.client.render.tile.TileRenderer;
import game.common.entity.Entity;
import game.common.entity.player.EntityPlayer;
import game.common.tile.Tile;
import game.common.world.room.Room;
import game.common.world.structure.Structure;

public class RoomRenderer implements IRenderer<Room> {

	private IRenderer<Tile> tileRenderer;
	private IRenderer<Entity> entityRenderer;
	private final int multi = 64;
	private EntityPlayer player;
	private boolean tc = false;

	public RoomRenderer(EntityPlayer player) {
		tileRenderer = new TileRenderer();
		entityRenderer = new EntityRenderer();
		this.player = player;
	}

	@Override
	public void draw(Room room) {
		Tile ground = room.getGround();
		int a = room.getHeight(), b = room.getWidth(), c = a * b;
		for (int i = 0; true; i++) {
			int x = multi * (i / a);
			int y = multi * (i % b);
			if (!tc) {
				System.out.printf("(%d, %d)\n", x, y);
			}
			if (player.getPosX() > multi * 7 && player.getPosX() < multi * (b - 8)) {
				x -= player.getPosX();
			}
			if (player.getPosY() > (multi * 8) - 4  && player.getPosY() < multi * (b - 8)) {
				y -= player.getPosY();
			}
			if (y > multi * 16)
				continue;
			if (x > multi * 16)
				break;
			tileRenderer.renderAt(ground, x, y, multi, multi, ground.getYaw());
		}
		if (!tc)
			tc = true;
		for (int i = 0; i < room.getStructures().size(); i++) {
			Structure s = room.getStructures().get(i);
			for (int n = 0; n < s.getHeight(); n++) {
				String r = s.getRow(n);
				for (int t = 0; t < s.getWidth(); t++) {
					if (r.charAt(t) == ' ') {
						continue;
					}
					Tile tile = s.getTile(r.charAt(t));
					if (tile == null) {
						continue;
					}
					int x = multi * (t + s.getPosX());
					int y = multi * (n + s.getPosY());
					if (player.getPosX() > x  && player.getPosX() < multi * (b - 8)) {
						x -= player.getPosX();
					}
					if (player.getPosY() > y  && player.getPosY() < multi * (b - 8)) {
						y -= player.getPosY();
					}
					if (y > multi * 16)
						continue;
					if (x > multi * 16)
						break;
					RenderManager.getRenderer(tile).renderAt(tile, x, y, multi, multi, tile.getYaw());
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

	@Override
	public void renderAt(Room t, int x, int y) {
		// TODO Auto-generated method stub

	}

}
