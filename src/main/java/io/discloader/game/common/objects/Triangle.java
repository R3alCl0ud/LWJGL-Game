package io.discloader.game.common.objects;

import java.util.ArrayList;
import java.util.List;

public class Triangle implements IShape {

	private List<IVertex2D> vertices;

	public Triangle(IVertex2D top, IVertex2D left, IVertex2D right) {
		vertices = new ArrayList<>();
		vertices.add(top);
		vertices.add(left);
		vertices.add(right);
	}

	@Override
	public List<IVertex2D> getVertices() {
		return vertices;
	}

	@Override
	public double getArea() {
		IVertex2D vl = vertices.get(1), vr = vertices.get(2);
		double base = Math.sqrt(Math.pow(Math.abs(vl.getPositionY() - vr.getPositionY()), 2d)
				+ Math.pow(Math.abs(vl.getPositionX() - vr.getPositionX()), 2d));

		return ((((double) getHeight()) * base)) / 2.0d;
	}

	public IVertex2D getTopVertex() {
		IVertex2D top = vertices.get(0);
		for (IVertex2D v : vertices) {
			if (v.getPositionY() > top.getPositionY())
				top = v;
		}
		return top;
	}

	public IVertex2D getLeftVertex() {
		IVertex2D left = vertices.get(0);
		for (IVertex2D v : vertices) {
			if (v.getPositionX() < left.getPositionX() || vertices.indexOf(left) == vertices.indexOf(getTopVertex()))
				left = v;
		}
		return left;
	}

	public IVertex2D getRightVertex() {
		IVertex2D right = vertices.get(0);
		for (IVertex2D v : vertices) {
			if (v.getPositionX() > right.getPositionX() || vertices.indexOf(right) == vertices.indexOf(getTopVertex()))
				right = v;
		}
		return right;
	}

	@Override
	public int getEdgeCount() {
		return 3;
	}

	public int getHeight() {
		int height = 0;
		IVertex2D vt = vertices.get(0), vl = vertices.get(1), vr = vertices.get(2);
		if (vl.getPositionY() <= vr.getPositionY()) {
			height = vt.getPositionY() - vl.getPositionY();
		} else {
			height = vt.getPositionY() - vr.getPositionY();
		}
		return height;
	}

	public int getWidth() {
		IVertex2D vt = vertices.get(0), vl = vertices.get(1), vr = vertices.get(2);
		if (vt.getPositionX() < vl.getPositionX())
			return vr.getPositionX() - vt.getPositionX();
		if (vt.getPositionX() > vr.getPositionX())
			return vt.getPositionX() - vl.getPositionX();
		return vr.getPositionX() - vl.getPositionX();
	}

}
