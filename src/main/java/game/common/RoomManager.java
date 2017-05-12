package game.common;

import java.util.ArrayList;
import java.util.List;

import game.entity.room.Room;

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
