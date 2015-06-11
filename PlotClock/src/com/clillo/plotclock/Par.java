package com.clillo.plotclock;

public class Par {

	private int x;
	private int y;
	
	public Par(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public double distancia (Par p){
		int dx = x-p.x;
		int dy = y-p.y;
		return Math.sqrt(dx * dx + dy * dy);
	}
}
