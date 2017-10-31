package io.discloader.game.common.tile;

import java.awt.Rectangle;

import io.discloader.game.common.entity.Entity;
import io.discloader.game.render.Resource;
import io.discloader.game.render.texture.ITexture;

public class TileEntity extends Entity {

	private int h, w;

	public TileEntity(int x, int y, int h, int w) {
		this.setPosX(x);
		this.setPosY(y);
		this.h = h;
		this.w = w;
	}

	@Override
	public ITexture getTexture() {
		return null;
	}

	@Override
	public Resource getResourceLocation() {
		return null;
	}

	public Rectangle getRectangle() {
		return new Rectangle(this.posX, this.posY, h, w);
	}

}
