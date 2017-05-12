package game.common;

import java.util.HashMap;
import java.util.Map;

import game.render.texture.ITexture;

/**
 * @author Perry Berman
 *
 */
public class TextureManager {
	
	private static TextureManager instance = new TextureManager();
	
	private Map<String, ITexture> textures;
	
	public TextureManager() {
		textures = new HashMap<>();
	}
	
	public static void registerTexture(String name, ITexture texture) {
		instance.textures.put(name, texture);
	}
	
	public static ITexture getTexture(String name) {
		return instance.textures.get(name);
	}
	
}
