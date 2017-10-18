package io.discloader.game.common.objects;

import io.discloader.game.common.tile.Tile;
import io.discloader.game.common.world.room.Room;

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
