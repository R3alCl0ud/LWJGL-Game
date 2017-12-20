package io.discloader.game.physics;

import java.awt.Shape;

public interface ICollider2D {

	Shape getShape();
	
	boolean intersects(ICollider2D collider2D);

}
