package io.discloader.game.common.objects;

public class Vertex2D implements IVertex2D {

	private int positionX, positionY;

	public Vertex2D(int x, int y) {
		positionX = x;
		positionY = y;
	}

	@Override
	public int getPositionX() {
		return positionX;
	}

	@Override
	public int getPositionY() {
		return positionY;
	}

	@Override
	public void setPositionX(int posX) {
		positionX = posX;
	}

	@Override
	public void setPositionY(int posY) {
		positionY = posY;
	}

	public String toString() {
		return String.format("(%d, %d)", positionX, positionY);
	}

}
