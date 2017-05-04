package game.entity;

import game.common.objects.IPlayer;

public abstract class EntityPlayer implements IPlayer {

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

}
