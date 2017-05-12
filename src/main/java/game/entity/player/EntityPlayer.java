package game.entity.player;

import game.common.TextureManager;
import game.common.objects.IPlayer;
import game.entity.Entity;
import game.entity.room.Room;
import game.entity.tile.Tile;
import game.renderer.Resource;
import game.renderer.texture.ITexture;

public class EntityPlayer extends Entity implements IPlayer {

	private String name;
	private int xp;

	public EntityPlayer(String name, int xp) {
		this.name = name;
		this.xp = xp;
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
		return null;
	}

	@Override
	public Tile standingOn(Room room) {
		return null;
	}

	@Override
	public Tile getRightTile(Room room) {
		return null;
	}

	@Override
	public Tile getUpTile(Room room) {
		return null;
	}

	@Override
	public Tile getDownTile(Room room) {
		return null;
	}

}
