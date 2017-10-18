package io.discloader.game.common.lib;

import io.discloader.game.common.objects.IItem;

public abstract class Util implements IItem {
	private int xPos, yPos, r = -1;
	private int speed;
	private String name;
	
	private boolean isTool;
	
	public int getReachPosDX() {
		return (int)(Math.cos(xPos)) * r;
	}
	
	public int getReachPosDY() {
		return (int)(Math.sin(yPos)) * r;
	}
	
	public int getPosDX(int d) {
		return (int)(Math.cos(xPos + d)) * r;
	}
	
	public void setPosX(int d) {
		this.xPos += getPosDX(d);
	}
	
	public int getPosDY(int d) {
		return (int)(Math.sin(yPos+d)) * r;
	}
	
	public void setPosY(int d) {
		this.yPos += getPosDY(d);
	}
	
	public int getX() { return xPos; }
	public int getY() { return yPos; }
	public int getReachDistance() { return r; }
	public int getSpeed() { return speed; }
	
	public int distanceTraveled(int t) { return speed * t; }
	public double distanceTraveled(int xpos1, int ypos1, int xpos2, int ypos2) {
		return Math.sqrt(Math.pow(xpos1 - xpos2, 2) + Math.pow(ypos1 - ypos2, 2));
	}
	
	// Default constructor
	public Util() {
		xPos = 0;
		yPos = 0;
		r = 1;
		speed = 1;
		name = "timmy tron doll";
		isTool = false;
	}
	
	// Recommended Constructor
	public Util(int startX, int startY, int reach, int speed, String name, boolean isTool) {
		xPos = startX;
		yPos = startY;
		r = reach;
		this.speed = speed;
		this.name = name;
		this.isTool = isTool;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isTool() {
		return isTool;
	}
}
