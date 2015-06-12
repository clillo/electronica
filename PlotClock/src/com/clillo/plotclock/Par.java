package com.clillo.plotclock;

public class Par {

	private int x;
	private int y;
	private String id;
	
	public Par(int x, int y, String id) {
		super();
		this.x = x;
		this.y = y;
		this.id = id;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Par [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
}

