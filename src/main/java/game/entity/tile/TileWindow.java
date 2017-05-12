package game.entity.tile;

import game.render.Resource;
import game.render.texture.ITexture;
import game.render.texture.ItemTexture;

/**
 * @author Perry Berman
 */
public class TileWindow extends Tile {

	private ITexture texture;

	public TileWindow() {
		texture = new ItemTexture(new Resource("game", "texture/tile/window.png"));
	}

	@Override
	public ITexture getTexture() {
		return texture;
	}
}
