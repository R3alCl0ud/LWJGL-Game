package io.discloader.game.common;

import java.util.ArrayList;
import java.util.List;

import io.discloader.game.common.world.room.Room;

/**
 * @author Perry Berman
 *
 */
public class RoomManager {
	private static RoomManager instance = new RoomManager();
	private List<Room> rooms;
	
	public RoomManager() {
		rooms = new ArrayList<>();
	}
	
	public static void registerRoom(Room room) {
		instance.rooms.add(room);
	}
	
	public static Room getRoom(int number) {
		return instance.rooms.get(number);
	}
}
