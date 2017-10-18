package game.client.render;

import java.util.HashMap;
import java.util.Map;

public class RenderManager {

	private static final RenderManager instance = new RenderManager();
	private Map<Class<?>, IRenderer<?>> renderers;

	public RenderManager() {
		renderers = new HashMap<>();
	}

	public static <T> void registerRenderer(Class<? extends T> cls, IRenderer<T> renderer) {
		instance.renderers.put(cls, renderer);
	}

	@SuppressWarnings("unchecked")
	public static <T> IRenderer<T> getRenderer(T o) {
		return (IRenderer<T>) instance.renderers.get(o.getClass());
	}

}
