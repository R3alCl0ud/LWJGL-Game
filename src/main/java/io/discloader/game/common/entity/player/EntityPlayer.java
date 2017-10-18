package io.discloader.game.common.entity.player;

import io.discloader.game.common.TextureManager;
import io.discloader.game.common.entity.Entity;
import io.discloader.game.common.objects.IPlayer;
import io.discloader.game.common.tile.Tile;
import io.discloader.game.common.world.room.Room;
import io.discloader.game.render.Resource;
import io.discloader.game.render.texture.ITexture;

public class EntityPlayer extends Entity implements IPlayer {

	private String name;
	private int xp;

	public EntityPlayer(String name, int xp) {
		this.name = name;
		this.xp = xp;
		yaw = 0f;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return (int) (100 * Math.sqrt((double) xp));
	}

	public int getXP() {
		return xp;
	}

	public void giveXP(int p) {
		xp += p;
	}

	public void takeXP(int p) {
		xp -= p;
	}

	public float fY() {
		return (posY / 64f);
	}

	public float fX() {
		return ((1f * posX) / 64f) + Math.round(((1f * posX) % 64f) / 64f);
	}

	public int locY() {
		return (posY / 64);
	}

	public int locX() {
		return (posX / 64) + ((((1f * posX) % 64f) / 64f) >= 0f ? 1 : 0);
	}

	@Override
	public float getYaw() {
		return yaw;
	}

	@Override
	public boolean isHostile() {
		return false;
	}

	@Override
	public ITexture getTexture() {
		return TextureManager.getTexture("player");
	}

	@Override
	public Resource getResourceLocation() {
		return null;
	}

	@Override
	public Tile getLeftTile(Room room) {
		Tile[][] tiles = room.getTiles();
		int b = tiles[0].length;
		int x = locX() - 1 < 0 ? 0 : locX() - 1, y = locY() % b;
		return tiles[x][y];
	}

	@Override
	public Tile standingOn(Room room) {
		Tile[][] tiles = room.getTiles();
		System.out.println(locX());
		System.out.println((((1f * posX) % 64f) / 64f) >= 0.5f);
		if (locX() == 7 && locY() == 6) {
			System.out.println(fX());
		}
		
		return tiles[locX()][locY()];
	}

	@Override
	public Tile getRightTile(Room room) {
		Tile[][] tiles = room.getTiles();
		int a = tiles.length, b = tiles[0].length;
		int x = (locX()) % a, y = locY() % b >= 0 ? locY() % b : 0;
		return tiles[x][y];
	}

	@Override
	public Tile getUpTile(Room room) {
		Tile[][] tiles = room.getTiles();
		int b = tiles[0].length;
		int x = locX(), y = ((locY()) + 1) >= b ? b - 1 : ((locY()) + 1);
		// System.out.printf("(%d, %d)\n", x, y);
		return tiles[x][y];
	}

	@Override
	public Tile getDownTile(Room room) {
		Tile[][] tiles = room.getTiles();
		// int a = tiles.length, b = tiles[0].length;
		int x = locX(), y = ((locY()) - 1) < 0 ? 0 : ((locY()) - 1);
		// System.out.printf("(%d, %d)\n", x, y);
		return tiles[x][y];
	}

}
