package com.clillo.plotclock;

public class Punto {

	private int ix;
	private int iy;

	private double dx;
	private double dy;

	public Punto(int ix, int iy) {
		super();
		this.ix = ix;
		this.iy = iy;
	}

	public Punto(double dx, double dy) {
		super();
		this.dx = dx;
		this.dy = dy;
	}

	public int getIx() {
		return ix;
	}
	public void setIx(int ix) {
		this.ix = ix;
	}
	public int getIy() {
		return iy;
	}
	public void setIy(int iy) {
		this.iy = iy;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}	
}
