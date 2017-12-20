package io.discloader.game.physics;

import java.awt.Rectangle;
import java.awt.Shape;

public class BoxCollider2D implements ICollider2D {

	private Rectangle rectangle;

	public int getHeight() {
		return rectangle.height;
	}

	public int getWidth() {
		return rectangle.width;
	}

	@Override
	public boolean intersects(ICollider2D collider2D) {
		return collider2D.getShape().intersects(rectangle);
	}

	public void setHeight(int height) {
		rectangle.setSize(rectangle.width, (int) height);
	}

	public void setWidth(int width) {
		rectangle.setSize(width, rectangle.height);
	}

	public Shape getShape() {
		return rectangle;
	}

}
