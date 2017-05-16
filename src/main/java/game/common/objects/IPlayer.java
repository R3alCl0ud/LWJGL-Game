package game.common.objects;

import game.room.Room;
import game.tile.Tile;

public interface IPlayer extends ILiving {

	int getLevel();

	int getXP();
	
	void giveXP(int xp);

	void takeXP(int xp);

	Tile getLeftTile(Room room);

	Tile standingOn(Room room);

	Tile getRightTile(Room room);

	Tile getUpTile(Room room);

	Tile getDownTile(Room room);
}
