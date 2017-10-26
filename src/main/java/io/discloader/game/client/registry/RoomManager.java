package io.discloader.game.client.registry;

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
	private int currentRoom = 0;

	public RoomManager() {
		rooms = new ArrayList<>();
	}

	public static void registerRoom(Room room) {
		instance.rooms.add(room);
	}

	public static Room getRoom(int number) {
		return instance.rooms.get(number);
	}

	public static Room getCurrentRoom() {
		return getRoom(instance.currentRoom);
	}
}
