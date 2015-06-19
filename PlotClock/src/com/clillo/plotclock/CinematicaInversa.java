package com.clillo.plotclock;

import java.io.IOException;
import java.util.ArrayList;

import com.clillo.plotclock.Punto.PosicionVertical;
import com.clillo.plotclock.Servo.Id;

public class CinematicaInversa {
	
	private PanelReal panelReal;
	private PanelPrincipal panelPrincipal;
	private PanelRobot panelRobot;
	private PanelPosicion panelPosicion;

	public final static double L1 = 40.0;  // 35
	public final static double L2 = 55.0;  // 55.1
	public final static double L3 = 12.6;  // 13.2
	public final static double L4 = 55.0;  // 13.2

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
	
	private PosicionVertical posicionActual = PosicionVertical.ARRIBA;

	private ArrayList<Punto> ultimos = new ArrayList<Punto>();
	private static final int MEDIA_MOVIL = 20;
	
	private void agregaUltimo(int x, int y){
		Punto p = new Punto(x, y);
		if (ultimos.size()>MEDIA_MOVIL)
			ultimos.remove(0);
		ultimos.add(p);
	}

	private Punto obtienePromedio(){
		int sumaX = 0;
		int sumaY = 0;
		int cuantos = 0;
		for (Punto p: ultimos){
			sumaX+= p.getIx();
			sumaY+= p.getIy();
			cuantos++;
		}
		return new Punto((int)(sumaX/(cuantos*1.0)), (int)(sumaY/(cuantos*1.0)));
	}

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
		
		for (Punto p: trayectoria)	{
			posicionActual = p.getPosicion();
			drawTo(p.getDx(), p.getDy());
		}
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
		
		if (panelPosicion!=null)
			panelPosicion.agregaPuntoTrayectoria(Tx, Ty, posicionActual);

		int salida[] = matrizConversion.getValor((int)Tx, (int)Ty);
	
		moverHasta(salida[0], salida[1]);

		panelPrincipal.repaint();
	}
	
	public void moverHasta(int angulo1, int angulo2){
		agregaUltimo(angulo1, angulo2);
		Punto p = obtienePromedio();
	//	System.out.println(angulo1+"\t"+angulo2+"\t"+p.toString());
		servoDerecho.writeMicroseconds(p.getIx());
		servoIzquerdo.writeMicroseconds(p.getIy());
	
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

}
