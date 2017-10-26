package io.discloader.game.common.objects;

import java.util.List;

public interface IShape {

	public List<IVertex2D> getVertices();
	public double getArea();
	public int getEdgeCount();
	
	
}
