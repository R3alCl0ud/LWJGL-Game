package game.common.objects;

public interface IPlayer extends ILiving {

	// String getName();

	int getLevel();

	int getXP();

	void giveXP(int xp);

	void takeXP(int xp);
}
