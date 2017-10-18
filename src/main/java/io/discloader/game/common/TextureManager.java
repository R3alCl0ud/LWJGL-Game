package io.discloader.game.common;

import java.util.HashMap;
import java.util.Map;

import io.discloader.game.render.texture.ITexture;

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
		System.out.printf("[TextureManager] [DEBUG]: Registered texture: %s.png\n", name);
		instance.textures.put(name, texture);
	}
	
	public static ITexture getTexture(String name) {
		return instance.textures.get(name);
	}
	
}
