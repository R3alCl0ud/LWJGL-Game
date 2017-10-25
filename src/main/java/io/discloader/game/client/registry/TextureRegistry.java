package io.discloader.game.client.registry;

import java.util.HashMap;
import java.util.Map;

import io.discloader.game.render.Resource;
import io.discloader.game.render.texture.ITexture;
import io.discloader.game.render.texture.Texture;

/**
 * @author Perry Berman
 *
 */
public class TextureRegistry {

	private static TextureRegistry instance = new TextureRegistry();

	private Map<String, ITexture> textures;

	public TextureRegistry() {
		textures = new HashMap<>();
		System.out.printf("[TextureRegistry] [INFO]: Registered texture: %s.png\n", "characters");
		textures.put("characters", new Texture(new Resource("game", "texture/font.png")));
	}

	public static void registerTexture(String name, ITexture texture) {
		System.out.printf("[TextureRegistry] [INFO]: Registered texture: %s.png\n", name);
		instance.textures.put(name, texture);
	}

	public static ITexture getTexture(String name) {
		return instance.textures.get(name);
	}

}
