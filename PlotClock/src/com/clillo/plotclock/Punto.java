package com.clillo.plotclock;

public class Punto {

	private int ix;
	private int iy;

	private double dx;
	private double dy;
	
	private double normalX;
	private double normalY;
	
	public enum PosicionVertical {ARRIBA, ABAJO};
	
	private PosicionVertical posicion = PosicionVertical.ARRIBA;

	public Punto(int ix, int iy, PosicionVertical posicion) {
		super();
		this.ix = ix;
		this.iy = iy;
		this.posicion = posicion;
	}
	
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
	
	public double distanciaReal(Punto p){
		return Math.sqrt( (dx - p.dx) * (dx - p.dx) + (dy - p.dy) * (dy - p.dy));
	}

	public double distanciaEntera(Punto p){
		return Math.sqrt((ix - p.ix) * (ix - p.ix) + (iy - p.iy) * (iy - p.iy));
	}

	public PosicionVertical getPosicion() {
		return posicion;
	}

	public void setPosicion(PosicionVertical posicion) {
		this.posicion = posicion;
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

	public double getNormalX() {
		return normalX;
	}

	public void setNormalX(double normalX) {
		this.normalX = normalX;
	}

	public double getNormalY() {
		return normalY;
	}

	public void setNormalY(double normalY) {
		this.normalY = normalY;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Punto [ix=");
		builder.append(ix);
		builder.append(", iy=");
		builder.append(iy);
		builder.append("]");
		return builder.toString();
	}	

}
