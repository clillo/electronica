package com.clillo.plotclock;

public class Par {

	private int x;
	private int y;
	private int motor1;
	private int motor2;
	private String id;
	
	private double motor1Normalizado;
	private double motor2Normalizado;
	
	private double xNormalizado;
	private double yNormalizado;
	
	public Par(int x, int y, String id) {
		super();
		this.x = x;
		this.y = y;
		this.id = id;
	}
	public Par(int x, int y, String id, int motor1, int motor2) {
		super();
		this.x = x;
		this.y = y;
		this.id = id;
		this.motor1 = motor1;
		this.motor2 = motor2;
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
	public int getMotor1() {
		return motor1;
	}
	public void setMotor1(int motor1) {
		this.motor1 = motor1;
	}
	public int getMotor2() {
		return motor2;
	}
	public void setMotor2(int motor2) {
		this.motor2 = motor2;
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
	public double getMotor1Normalizado() {
		return motor1Normalizado;
	}
	public void setMotor1Normalizado(double motor1Normalizado) {
		this.motor1Normalizado = motor1Normalizado;
	}
	public double getMotor2Normalizado() {
		return motor2Normalizado;
	}
	public void setMotor2Normalizado(double motor2Normalizado) {
		this.motor2Normalizado = motor2Normalizado;
	}	
	public double getxNormalizado() {
		return xNormalizado;
	}
	public void setxNormalizado(double xNormalizado) {
		this.xNormalizado = xNormalizado;
	}
	public double getyNormalizado() {
		return yNormalizado;
	}
	public void setyNormalizado(double yNormalizado) {
		this.yNormalizado = yNormalizado;
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

