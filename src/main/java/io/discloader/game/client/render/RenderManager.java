package io.discloader.game.client.render;

import java.util.HashMap;
import java.util.Map;

import io.discloader.game.client.registry.RoomManager;
import io.discloader.game.common.entity.player.EntityPlayer;
import io.discloader.game.common.start.Index;
import io.discloader.game.common.tile.Tile;
import io.discloader.game.common.world.room.Room;
import io.discloader.game.common.world.structure.Structure;
import io.discloader.game.render.GLRU;

public class RenderManager {

	private static final RenderManager instance = new RenderManager();
	private Map<Class<?>, IRenderer<?>> renderers;
	// private IRenderer<Room> roomRenderer;
	private static final int multi = GLRU.getMultiplier();
	private static long lastFrameTime = 0, lastFPSShown = 0;
	private static int fps, maxFPS = -1, minFPS = -1;

	public RenderManager() {
		renderers = new HashMap<>();
	}

	public static <T> void registerRenderer(Class<? extends T> cls, IRenderer<T> renderer) {
		instance.renderers.put(cls, renderer);
		// instance.renderers.
	}

	@SuppressWarnings("unchecked")
	public static <T> IRenderer<T> getRenderer(T o) {
		if (o instanceof Room)
			return (IRenderer<T>) instance.renderers.get(Room.class);
		return (IRenderer<T>) instance.renderers.get(o.getClass());
	}

	public static void beginRender() {
		Room room = RoomManager.getCurrentRoom();
		EntityPlayer player = Index.getPlayer();

		getRenderer(room).draw(room);
		renderPlayer(room, player);

		long currentTime = System.currentTimeMillis(), deltaTime = currentTime - lastFrameTime;
		lastFrameTime = currentTime;
		lastFPSShown += deltaTime;
		if (lastFPSShown > 100) {
			fps = (int) (1000l / deltaTime);
			if (fps > maxFPS || maxFPS == -1)
				maxFPS = fps;
			if (fps < minFPS || minFPS == -1)
				minFPS = fps;
			lastFPSShown = 0;
		}
		RenderManager.getRenderer("").renderAt("FPS: " + fps, 0, 15);
		RenderManager.getRenderer("").renderAt("MAX FPS: " + maxFPS, 0, 14);
		RenderManager.getRenderer("").renderAt("MIN FPS: " + minFPS, 0, 13);

	}

	protected static void renderPlayer(Room room, EntityPlayer player) {
		int x = player.getPosX(), y = player.getPosY();
		if (x > 15 * (multi / 2) && x < (multi * (room.getWidth() - 8)) - (multi / 2)) {
			x = 15 * (multi / 2);
		} else if (x > (multi * (room.getWidth() - 8)) - (multi / 2)) {
			x %= (16 * multi);
		}
		if (y > 15 * (multi / 2) && y < (multi * (room.getHeight() - 8)) - (multi / 2)) {
			y = 15 * (multi / 2);
		} else if (y > multi * (room.getHeight() - 8) - (multi / 2)) {
			y %= (16 * multi);
		}
		float z = playerOnTop() ? -0.1f : 0.1f;
		getRenderer(player).renderAt(player, x, y, multi, multi * 2, player.getYaw(), z);
	}

	protected static boolean isAir(Tile tile) {
		return tile.getName().equalsIgnoreCase("air");
	}

	public static boolean playerOnTop() {
		Room room = RoomManager.getCurrentRoom();
		EntityPlayer player = Index.getPlayer();
		int x = player.getPosX(), y = player.getPosY();

		// check if player is occupying same space as a structure.
		Structure s = room.getStructureAt(x, y);
		// if no structure was found check if there is one directly right of the player
		if (s == null)
			room.getStructureAt(x + multi, y);
		if (s != null)
			System.out.println(s.getDepth());
		if (s != null && ((y / multi) - s.getPosY()) + 2 >= s.getDepth()) {
			return false;
		}
		return true;
	}

	@Deprecated
	public static boolean shouldRenderPlayerFirst() {
		return playerOnTop();
	}

}
