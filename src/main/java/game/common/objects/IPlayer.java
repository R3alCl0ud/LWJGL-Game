package game.common.objects;

public interface IPlayer extends ILiving {

	int getLevel();

	int getXP();

	void giveXP(int xp);

	void takeXP(int xp);
}
