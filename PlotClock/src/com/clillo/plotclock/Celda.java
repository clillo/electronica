package com.clillo.plotclock;

public class Celda {

	private Par p1;
	private Par p2;
	private Par p3;
	private Par p4;
	
	public Celda(Par p1, Par p2, Par p3, Par p4) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
	}
	public Par getP1() {
		return p1;
	}
	public void setP1(Par p1) {
		this.p1 = p1;
	}
	public Par getP2() {
		return p2;
	}
	public void setP2(Par p2) {
		this.p2 = p2;
	}
	public Par getP3() {
		return p3;
	}
	public void setP3(Par p3) {
		this.p3 = p3;
	}
	public Par getP4() {
		return p4;
	}
	public void setP4(Par p4) {
		this.p4 = p4;
	}
	
	public boolean perteneceA(Par p){
		//System.out.println( p1+","+p2+","+p3+","+p4+","+p);
		boolean x =  p1.getX()<=p.getX() && p2.getX()<=p.getX() && p.getX()<=p3.getX() && p.getX()<=p4.getX();
		if (!x)
			return false;
		
		//System.out.println("Paso los X");
		return p1.getY()<=p.getY() && p4.getY()<=p.getY() && p.getY()<=p2.getY() && p.getY()<=p3.getY();
	}
}
