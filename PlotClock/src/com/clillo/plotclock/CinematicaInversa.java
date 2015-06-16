package com.clillo.plotclock;

import java.io.IOException;
import java.util.ArrayList;

import com.clillo.graficas.Matriz;
import com.clillo.plotclock.Servo.Id;

public class CinematicaInversa {
	
	private PanelReal panelReal;
	private PanelPrincipal panelPrincipal;
	private PanelRobot panelRobot;
	private PanelPosicion panelPosicion;

//	private double SERVOFAKTOR=600; // 630
	
	private double SERVOLEFTNULL=2020; //1900
	private double SERVORIGHTNULL=880;  //984
	
	// origin points of left and right servo 
	private double O1X = 22;
	private double O1Y = -25;
	private double O2X = 47;
	private double O2Y = -25;

	// length of arms
	public static double L1 = 34.6;  // 35
	public static double L2 = 54.5;  // 55.1
	public static double L3 = 12.6;  // 13.2
	public static double L4 = 53.9;  // 13.2

	public static Servo servoDerecho = new Servo(Id.DERECHO);
	public static Servo servoIzquerdo = new Servo(Id.IZQUERDO);
	
	private double lastX = 75;
	private double lastY = 47.5;
	
	public static double DIBUJO_MIN_X = 1.0;
	public static double DIBUJO_MAX_X = 70.0;
	public static double DIBUJO_MIN_Y = 22.0;
	public static double DIBUJO_MAX_Y = 61.0;
	
	public static double coordenadaRealX;
	public static double coordenadaRealY;
	
	private Serial serial;
	
	private MatrizConversion matrizConversion = new MatrizConversion();
	
	public MatrizConversion getMatrizConversion() {
		return matrizConversion;
	}

	public CinematicaInversa() {
		try {
			serial = new Serial();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CinematicaInversa(boolean sinComm) {
		serial = null;
	}
	
	public void dibuja(int a1, int a2, int a3, int a4){
		Numeros n = new Numeros();
		n.dibuja(a1, a2, a3, a4);
		ArrayList<Punto> trayectoria = n.getTrayectoria();
		
		for (Punto p: trayectoria)		
			drawTo(p.getDx(), p.getDy());
	}
	
	public void dibuja(int motor1[], int motor2[]){
		
		for (int i=0; i<motor1.length; i++){
				servoDerecho.writeMicroseconds(motor2[i]);
				servoIzquerdo.writeMicroseconds(motor1[i]);
			
				try {
					if (serial!=null)
						serial.punto(servoDerecho, servoIzquerdo);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				panelPrincipal.repaint();
				panelReal.repaint();
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
		}
	}
	
	public void moverA(double x, double y){
		coordenadaRealX = (x * (DIBUJO_MAX_X - DIBUJO_MIN_X) ) + DIBUJO_MIN_X;
		coordenadaRealY = (y * (DIBUJO_MAX_Y - DIBUJO_MIN_Y) ) + DIBUJO_MIN_Y;
		drawTo(coordenadaRealX, coordenadaRealY);
	}
	
	public static void real2Pantalla(Punto p){
		p.setDx((p.getDx() - DIBUJO_MIN_X) / (DIBUJO_MAX_X - DIBUJO_MIN_X));
		p.setDy((p.getDy() - DIBUJO_MIN_Y) / (DIBUJO_MAX_Y - DIBUJO_MIN_Y));
	}
	
	public static void pantalla2Real(Punto p){
		p.setIx((int) ( (p.getDx() * (DIBUJO_MAX_X - DIBUJO_MIN_X) ) + DIBUJO_MIN_X));
		p.setIy((int) ((p.getDy() * (DIBUJO_MAX_Y - DIBUJO_MIN_Y) ) + DIBUJO_MIN_Y));
	}
	
	private double returnAngle(double a, double b, double c) { 		  // cosine rule for angle between c and a
		return Math.acos((a * a + c * c - b * b) / (2 * a * c));
	}
	
	private void drawTo(double pX, double pY) {
		double dx, dy, c;
		int i;

		// dx dy of new point
		dx = pX - lastX;
		dy = pY - lastY;
		// path lenght in mm, times 4 equals 4 steps per mm
		c = Math.floor(4 * Math.sqrt(dx * dx + dy * dy));
		c = Math.floor(4 * Math.sqrt(dx * dx + dy * dy));

		if (c < 1)
			c = 1;

		for (i = 0; i <= c; i++) {
			// draw line point by point
			setXY(lastX + (i * dx / c), lastY + (i * dy / c));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}

		lastX = pX;
		lastY = pY;
	}
	
	private void setXY(double Tx, double Ty){
		setXY(Tx, Ty, PanelPrincipal.getServoFaktor(), null);
	}
	
	private void setXY(double Tx, double Ty, double SERVOFAKTOR, PanelRobot panelRobot){
	//	Ty = 70- Ty; 
	/*	Matriz mRotacion = new Matriz();
		mRotacion.rotacionz(2.5);
		
		com.clillo.graficas.Punto p = new com.clillo.graficas.Punto();
		p.setX(Tx);
		p.setY(Ty);
		p.setZ(0);
		
		com.clillo.graficas.Punto q = mRotacion.multiplicar(p);
		
		Tx = q.getX();
		Ty = q.getY();
		*/
		
		/*
		double dx, dy, c, a1, a2, Hx, Hy;

		if (panelReal!=null)
			panelReal.agregaPuntoTrayectoria(Tx, Ty);
		
		if (this.panelRobot!=null)
			this.panelRobot.agregaPuntoActual(new Punto(Tx, Ty));

		// calculate triangle between pen, servoLeft and arm joint
		// cartesian dx/dy
		dx = Tx - O1X;
		dy = Ty - O1Y;

		// polar lemgth (c) and angle (a1)
		c = Math.sqrt(dx * dx + dy * dy);
		a1 = Math.atan2(dy, dx); //
		a2 = returnAngle(L1, L2, c);

	//	System.out.println("c1: "+c+" a1: "+a1+" a2: "+a2+" angulo: "+(Math.floor(((a2 + a1 - Math.PI) * SERVOFAKTOR)	+ SERVOLEFTNULL)));

		double anguloIzquerdo = Math.floor(((a2 + a1 - Math.PI) * SERVOFAKTOR)	+ SERVOLEFTNULL);

		// calculate joinr arm point for triangle of the right servo arm
		a2 = returnAngle(L2, L1, c);
		
		double angulo = 0.621;// 36,5°
		Hx = Tx + L3 * Math.cos((a1 - a2 + angulo) + Math.PI); 
		Hy = Ty + L3 * Math.sin((a1 - a2 + angulo) + Math.PI);

		// calculate triangle between pen joint, servoRight and arm joint
		dx = Hx - O2X;
		dy = Hy - O2Y;

		c = Math.sqrt(dx * dx + dy * dy);
		a1 = Math.atan2(dy, dx);
		a2 = returnAngle(L1, (L2 - L3), c);

		double anguloDerecho = Math.floor(((a1 - a2) * SERVOFAKTOR) + SERVORIGHTNULL);

		servoDerecho.writeMicroseconds(anguloDerecho);
		servoIzquerdo.writeMicroseconds(anguloIzquerdo);
	
		*/
		
		if (panelPosicion!=null)
			panelPosicion.agregaPuntoTrayectoria(Tx, Ty);

		int salida[] = matrizConversion.getValor((int)Tx, (int)Ty);
		
//		panelReal.eliminaPuntoElegidos();
//		ArrayList<Par> listaElegidos = matrizConversion.getListaPuntosActuales();
		
//		if (listaElegidos!=null)
//			for (Par p: listaElegidos)
//				panelReal.agregaPuntoElegidos(p);
		
		moverHasta(salida[0], salida[1]);
		
	//	this.panelRobot.agregaPuntoActual(new Punto(Tx, Ty));

		panelPrincipal.repaint();
	}
	
	public void moverHasta(int angulo1, int angulo2){
		servoDerecho.writeMicroseconds(angulo1);
		servoIzquerdo.writeMicroseconds(angulo2);
	
		try {
			if (serial!=null)
			//	serial.punto(servoIzquerdo, servoDerecho);
				serial.punto(servoDerecho, servoIzquerdo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public double getCoordenadaRealX() {
		return coordenadaRealX;
	}

	public double getCoordenadaRealY() {
		return coordenadaRealY;
	}
	
	public void setPanelReal(PanelReal panelReal) {
		this.panelReal = panelReal;
	}
	
	public void setPanelPrincipal(PanelPrincipal panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}
	
	public void setPanelRobot(PanelRobot panelRobot) {
		this.panelRobot = panelRobot;
	}
	
	public void setPanelPosicion(PanelPosicion panelPosicion) {
		this.panelPosicion = panelPosicion;
	}

	public static void main(String[] args) {
		CinematicaInversa ci = new CinematicaInversa(true);
		
		double min = 999999999;
		for (double factor= 100.0; factor< 1000.0; factor = factor+1.0){
			PanelRobot pr = new PanelRobot();
			for (double Tx= 0.0; Tx< 100.0; Tx = Tx+1.0)
				for (double Ty= 0.0; Ty< 100.0; Ty = Ty+1.0){
					coordenadaRealX = Tx;
					coordenadaRealY = Ty;
				
					ci.setXY(Tx, Ty, factor, pr);
					pr.conviertePunto();
				}
			if (pr.promedio()<min){
				min = pr.promedio();
				System.out.println(factor +": "+min);
			}
				
			//System.out.println(factor +": "+pr.promedio());
		}
	}
}
