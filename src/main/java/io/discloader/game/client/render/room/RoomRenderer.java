package io.discloader.game.client.render.room;

import io.discloader.game.client.render.IRenderer;
import io.discloader.game.client.render.RenderManager;
import io.discloader.game.client.render.entity.EntityRenderer;
import io.discloader.game.common.entity.Entity;
import io.discloader.game.common.entity.player.EntityPlayer;
import io.discloader.game.common.tile.Tile;
import io.discloader.game.common.world.room.Room;
import io.discloader.game.common.world.structure.Structure;

public class RoomRenderer implements IRenderer<Room> {

	// private IRenderer<Tile> tileRenderer;
	private IRenderer<Entity> entityRenderer;
	private EntityPlayer player;
	private boolean tc = true;

	public RoomRenderer(EntityPlayer player) {
		// tileRenderer = new TileRenderer();
		entityRenderer = new EntityRenderer();
		this.player = player;
	}

	@Override
	public void draw(Room room) {
		Tile ground = room.getGround();
		int a = room.getHeight(), b = room.getWidth();
		for (int i = 0; true; i++) {
			int x = multi * (i / a);
			int y = multi * (i % b);

			if (player.getPosX() >= (multi * 7.5) && player.getPosX() <= multi * (b - 8.5)) {
				x -= (player.getPosX() - (multi * 7.5));
			}
			if (player.getPosY() >= (multi * 8) && player.getPosY() <= multi * (b - 8.5)) {
				y -= Math.abs(player.getPosY() - (multi * 8));
			}
			if (x < multi * -1 || y < multi * -1) {
				continue;
			}
			if (x > multi * 16) {
				break;
			}
			if (!tc) {
				System.out.printf("(%d, %d)\n", x, y);
			}
			RenderManager.getRenderer(ground).renderAt(ground, x, y, multi, multi, ground.getYaw());
		}
		if (!tc) {
			tc = true;
		}
		for (int i = 0; i < room.getStructures().size(); i++) {
			Structure s = room.getStructures().get(i);
			for (int n = 0; n < s.getHeight(); n++) {
				String r = s.getRow(n);
				if (player.getPosX() > multi * (((s.getWidth() * 2.5) + s.getPosX()))
						|| player.getPosY() > multi * ((s.getHeight() + s.getPosY()) * 2))
					break;
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
					if (player.getPosX() > (multi * 7.5) && player.getPosX() < multi * (b - 8)) {
						x -= (player.getPosX() - (multi * 7.5));
					}
					if (player.getPosY() > (multi * 8) && player.getPosY() < multi * (b - 8)) {
						y -= (player.getPosY() - (multi * 8));
					}
					if (y > multi * 16)
						continue;
					if (x > multi * 16)
						break;
					RenderManager.getRenderer(tile).renderAt(tile, x, y, multi, multi, tile.getYaw());
				}
			}
		}
		for (int i = 0; i < room.getEntities().size(); i++) {
			Entity entity = room.getEntities().get(i);
			int x = multi * (entity.getPosX());
			int y = multi * (entity.getPosY());
			if (player.getPosX() > (multi * 7.5) && player.getPosX() < multi * (b - 8)) {
				x -= (player.getPosX() - (multi * 7.5));
			}
			if (player.getPosY() > (multi * 8) && player.getPosY() < multi * (b - 8)) {
				y -= (player.getPosY() - (multi * 8));
			}
			// System.out.println(entity.getClass().getName());
			entityRenderer.renderAt(entity, x, y, multi, multi, entity.getYaw());
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
