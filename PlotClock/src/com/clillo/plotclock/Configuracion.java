package com.clillo.plotclock;

public class Configuracion {

	private double largoBrazo1 = 33.89;  
	private double largoBrazo2 = 33.89;
	private double largoBrazo3 = 45.0;  
	private double largoBrazo4 = 45.0;  
	private double largoBrazo5 = 15.6;  

	private double origen1X = 21.0;
	private double origen1Y = -32.0;
	
	private double origen2X = 48.4;
	private double origen2Y = -32.0;
	
	private double anguloBrazo5 = 0.3;
	
	public Configuracion() {
		
	}
	
	public Configuracion(Configuracion c) {
		this.largoBrazo1 = c.largoBrazo1;
		this.largoBrazo2 = c.largoBrazo2;
		this.largoBrazo3 = c.largoBrazo3;
		this.largoBrazo4 = c.largoBrazo4;
		this.largoBrazo5 = c.largoBrazo5;
		
		this.origen1X = c.origen1X;
		this.origen1Y = c.origen1Y;
				
		this.origen2X = c.origen2X;
		this.origen2Y = c.origen2Y;
		
		this.anguloBrazo5 = c.anguloBrazo5;
	}
	
	public double getLargoBrazo1() {
		return largoBrazo1;
	}
	public void setLargoBrazo1(double largoBrazo1) {
		this.largoBrazo1 = largoBrazo1;
	}
	public double getLargoBrazo2() {
		return largoBrazo2;
	}
	public void setLargoBrazo2(double largoBrazo2) {
		this.largoBrazo2 = largoBrazo2;
	}
	public double getLargoBrazo3() {
		return largoBrazo3;
	}
	public void setLargoBrazo3(double largoBrazo3) {
		this.largoBrazo3 = largoBrazo3;
	}
	public double getLargoBrazo4() {
		return largoBrazo4;
	}
	public void setLargoBrazo4(double largoBrazo4) {
		this.largoBrazo4 = largoBrazo4;
	}
	public double getLargoBrazo5() {
		return largoBrazo5;
	}
	public void setLargoBrazo5(double largoBrazo5) {
		this.largoBrazo5 = largoBrazo5;
	}
	public double getOrigen1X() {
		return origen1X;
	}
	public void setOrigen1X(double origen1x) {
		origen1X = origen1x;
	}
	public double getOrigen1Y() {
		return origen1Y;
	}
	public void setOrigen1Y(double origen1y) {
		origen1Y = origen1y;
	}
	public double getOrigen2X() {
		return origen2X;
	}
	public void setOrigen2X(double origen2x) {
		origen2X = origen2x;
	}
	public double getOrigen2Y() {
		return origen2Y;
	}
	public void setOrigen2Y(double origen2y) {
		origen2Y = origen2y;
	}
	public double getAnguloBrazo5() {
		return anguloBrazo5;
	}
	public void setAnguloBrazo5(double anguloBrazo5) {
		this.anguloBrazo5 = anguloBrazo5;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Configuracion [largoBrazo1=");
		builder.append(largoBrazo1);
		builder.append(',');
		//		builder.append(", largoBrazo2=");
//		builder.append(largoBrazo2);
//		builder.append(", largoBrazo3=");
		builder.append(largoBrazo3);
		builder.append(',');
		
//		builder.append(", largoBrazo4=");
//		builder.append(largoBrazo4);
		//builder.append(", largoBrazo5=");
		builder.append(largoBrazo5);
		builder.append(',');
/*		builder.append(", origen1X=");
		builder.append(origen1X);
		builder.append(", origen1Y=");
		builder.append(origen1Y);
		builder.append(", origen2X=");
		builder.append(origen2X);
		builder.append(", origen2Y=");
		builder.append(origen2Y);
		builder.append(", anguloBrazo5=");
	*/	builder.append(anguloBrazo5);
		builder.append("]");
		return builder.toString();
	}
	
	
}

