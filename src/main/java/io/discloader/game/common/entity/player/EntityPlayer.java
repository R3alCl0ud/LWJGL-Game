package io.discloader.game.common.entity.player;

import io.discloader.game.client.registry.TextureRegistry;
import io.discloader.game.common.entity.Entity;
import io.discloader.game.common.objects.IPlayer;
import io.discloader.game.common.tile.Tile;
import io.discloader.game.common.world.room.Room;
import io.discloader.game.common.world.structure.Structure;
import io.discloader.game.render.GLRU;
import io.discloader.game.render.Resource;
import io.discloader.game.render.texture.ITexture;

public class EntityPlayer extends Entity implements IPlayer {

	private String name;
	private int xp;
	private final int multi = GLRU.getMultiplier();

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
		return (posY / ((float) multi));
	}

	public float fX() {
		return ((1f * posX) / ((float) multi)) + Math.round(((1f * posX) % ((float) multi)) / ((float) multi));
	}

	public int locY() {
		return (posY / multi);
	}

	public int locX() {
		return (posX / multi) + ((((1f * posX) % ((float) multi)) / ((float) multi)) >= 0f ? 1 : 0);
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
		return TextureRegistry.getTexture("door");
	}

	@Override
	public Resource getResourceLocation() {
		return null;
	}

	@Override
	public Tile getLeftTile(Room room) {
		Structure s = room.getStructureAt(getPosX() - multi, getPosY());
		if (s == null)
			return room.getGround();
		return s.getTileAt(getPosX() - ((s.getPosX()) * multi), getPosY() - (s.getPosY() * multi));
	}

	@Override
	public Tile standingOn(Room room) {
		Structure s = room.getStructureAt(getPosX(), getPosY());
		if (s == null)
			return room.getGround();
		return s.getTileAt(getPosX() - (s.getPosX() * multi), getPosY() - (s.getPosY() * multi));
	}

	@Override
	public Tile getRightTile(Room room) {
		Structure s = room.getStructureAt((int) (getPosX() + (multi * 1.5)), getPosY());
		if (s == null)
			return room.getGround();
		return s.getTileAt(getPosX() - ((s.getPosX() - 1) * multi), getPosY() - (s.getPosY() * multi));
	}

	@Override
	public Tile getUpTile(Room room) {
		Structure s = room.getStructureAt(getPosX() + (multi / 2), getPosY() + multi);
		if (s == null)
			return room.getGround();
		int x = getPosX() - (s.getPosX() * multi);
		if (x % multi > 0)
			x += multi;
		return s.getTileAt(x, getPosY() - ((s.getPosY() - 1) * multi));
	}

	@Override
	public Tile getDownTile(Room room) {
		Structure s = room.getStructureAt(getPosX(), getPosY() - multi);
		if (s == null)
			return room.getGround();
		return s.getTileAt(getPosX() - (s.getPosX() * multi), (getPosY() - ((s.getPosY()) * multi)) - (multi / 2));
	}

	public Tile getTileAt(Room room, int x, int y) {
		Structure s = room.getStructureAt(getPosX() + (multi * x), getPosY() + (multi * y));
		if (s == null)
			return room.getGround();

		Tile tile = s.getTileAt(getPosX() - ((s.getPosX() - x) * multi), getPosY() - ((s.getPosY() - y) * multi));
		return tile == null ? Tile.Air : tile;
	}

}
