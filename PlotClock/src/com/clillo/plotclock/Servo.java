package com.clillo.plotclock;

public class Servo {
	
	private double angulo;
	
	public enum Id{IZQUERDO, DERECHO, VERTICAL};
	
	private Id id;
	
	public Servo(Id id) {
		this.id = id;
	}
	
	public int getAnguloMinimo(){
		if (id == Id.IZQUERDO )
			return 1150;

		if (id == Id.DERECHO )
			return 900;

		return 0;
	}

	public int getAnguloMaximo(){
		if (id == Id.IZQUERDO )
			return 1900;

		if (id == Id.DERECHO )
			return 2100;

		return 0;
	}
	
	public void writeMicroseconds(double angulo){
		this.angulo = angulo;
	}

	public double getAngulo() {
		return angulo;
	}	
	
	public double getAnguloNormalizado(){
		return (angulo - getAnguloMinimo()) / (getAnguloMaximo() - getAnguloMinimo());
	}
	
	public double getAnguloNormalizadoGrados(){
		return (angulo - getAnguloMinimo()) / (getAnguloMaximo() - getAnguloMinimo())*90.0;
	}

	public String getEstado(){
		return (int)getAnguloNormalizadoGrados()+ "°";
	}
}
