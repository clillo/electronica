package com.clillo.plotclock;

public class Servo {
	
	private double angulo;
	
	public enum Id{IZQUERDO, DERECHO, VERTICAL};
	
	private Id id;
	
	public Servo(Id id) {
		this.id = id;
	}
	
	public double getAnguloMinimo(){
		if (id == Id.IZQUERDO )
			return 890.0;

		if (id == Id.DERECHO )
			return 1133.0;

		return 0.0;
	}

	public double getAnguloMaximo(){
		if (id == Id.IZQUERDO )
			return 1908.0;

		if (id == Id.DERECHO )
			return 2065.0;

		return 0.0;
	}
	
	public void writeMicroseconds(double angulo){
		if (angulo<getAnguloMinimo()-20)
			return;
		if (angulo>getAnguloMaximo()+20)
			return;
		this.angulo = angulo;
	}

	public double getAngulo() {
		return angulo;
	}	
	
	public double getAnguloNormalizado(double fake){
		return (fake - getAnguloMinimo()) / (getAnguloMaximo() - getAnguloMinimo());
	}
	
	public double getAnguloNormalizado(){
		double rango = getAnguloMaximo() - getAnguloMinimo();
	//	System.out.println(angulo+"\t"+rango);
		return (angulo - getAnguloMinimo()) / rango;
	}
	
	public double getAnguloNormalizadoGrados(){
		return (angulo - getAnguloMinimo()) / (getAnguloMaximo() - getAnguloMinimo())*90.0;
	}

	public String getEstado(){
		return (int)getAnguloNormalizadoGrados()+ "°";
	}
}
