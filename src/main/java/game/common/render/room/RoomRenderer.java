package game.common.render.room;

import game.common.render.IRenderer;
import game.common.render.entity.EntityRenderer;
import game.common.render.tile.TileRenderer;
import game.entity.Entity;
import game.room.Room;
import game.tile.Tile;

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
		for (int i = 0; i < room.getStructures().length; i++) {
			Tile tile = room.getStructures()[i];
			if (tile.getRenderer() != null) {
				tile.getRenderer().draw();
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
