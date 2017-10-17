package game.common.objects;

import game.common.tile.Tile;
import game.common.world.room.Room;

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
