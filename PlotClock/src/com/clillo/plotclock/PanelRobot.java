package com.clillo.plotclock;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanelRobot extends JPanel {
	
	private static final long serialVersionUID = 4075738060965200021L;

	private Punto O1 = new Punto(22.0, -25.0);
	private Punto O2 = new Punto(47.0, -25.0);

	private Punto B1 = new Punto(0, 0);
	private Punto B2 = new Punto(0, 0);

	private Punto T = new Punto(0, 0);
	private Punto Q = new Punto(0, 0);

	private ArrayList<Punto> trayectoriaReal = new ArrayList<Punto>();
	private ArrayList<Punto> trayectoriaDeseada = new ArrayList<Punto>();

	private double escala;
	private double origenX;
	private double origenY;
	
	public PanelRobot() {
		this.setBackground(Color.BLACK);
		setOrigen(0, 0);
	}
	
	public void limpia(){
		trayectoriaReal.clear();
		trayectoriaDeseada.clear();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.blue);
		for (Punto p: trayectoriaReal){
			real2Pantalla(p);
			g.drawOval(p.getIx(), p.getIy(), 2, 2);
		}
		
		g.setColor(Color.yellow);
		for (Punto p: trayectoriaDeseada){
			real2Pantalla(p);
			g.drawOval(p.getIx(), p.getIy(), 2, 2);
		}
		
		g.setColor(Color.red);
		
		real2Pantalla(O1);
		real2Pantalla(O2);
	

		g.fillOval(O1.getIx()-5, O1.getIy()-5, 10, 10);
		g.fillOval(O2.getIx()-5, O2.getIy()-5, 10, 10);
		
		calculaPosicionBrazo();
	
		real2Pantalla(B1);
		g.fillOval(B1.getIx()-4, B1.getIy()-4, 8, 8);
		g.drawLine(B1.getIx(), B1.getIy(), O1.getIx(), O1.getIy());

		real2Pantalla(B2);
		g.fillOval(B2.getIx()-4, B2.getIy()-4, 8, 8);
		g.drawLine(B2.getIx(), B2.getIy(), O2.getIx(), O2.getIy());

		real2Pantalla(T);
		trayectoriaReal.add(new Punto(T.getDx(), T.getDy()));

		g.fillOval(T.getIx()-4, T.getIy()-4, 8, 8);

		g.drawLine(T.getIx(), T.getIy(), B1.getIx(), B1.getIy());
		g.drawLine(T.getIx(), T.getIy(), B2.getIx(), B2.getIy());

		real2Pantalla(Q);
		g.setColor(Color.cyan);
		g.fillOval(Q.getIx(), Q.getIy(), 8, 8);
		
		Punto min = new Punto(CinematicaInversa.DIBUJO_MIN_X, CinematicaInversa.DIBUJO_MIN_Y);
		Punto max = new Punto(CinematicaInversa.DIBUJO_MAX_X, CinematicaInversa.DIBUJO_MAX_Y);
		Punto cero = new Punto(0, 0);
		
		real2Pantalla(min);
		real2Pantalla(max);
		real2Pantalla(cero);
		
		g.setColor(Color.lightGray);
		g.drawLine(min.getIx(), cero.getIy(), max.getIx(), cero.getIy());
		g.drawLine(cero.getIx(), min.getIy(), cero.getIx(), max.getIy());	
	}
	
	public void setEscala(int escala){
		
		this.escala = (100.0/(escala*1.0));
		System.out.println("Escala: "+escala+" "+this.escala);
//		for (Punto p: trayectoria)
//			real2Pantalla(p);
		this.repaint();
	}
	
	public void setOrigen(int x, int y){
		System.out.println("Origen: "+x+","+y);
		origenX = (x - 5) - 50.0 ;
		origenY = 200.0 + (y - 50);
		this.repaint();
	}
	
	private void real2Pantalla(Punto p){
		Punto tmp = new Punto(p.getDx(), p.getDy());
		CinematicaInversa.real2Pantalla(tmp);
		double rx = tmp.getDx() * this.getWidth()*escala + origenX ;
		double ry = this.getHeight() - ( tmp.getDy() * this.getHeight()*escala + origenY );
	
		//System.out.println(p.getDx() + "," + p.getDy()+" -> "+rx+","+ry);
		p.setIx((int)rx);
		p.setIy((int)ry);
	}
	
	double suma = 0;
	long cuantos = 0;
	
	public void conviertePunto(){
		calculaPosicionBrazo();
		double d = Math.abs(distancia(T, new Punto(CinematicaInversa.coordenadaRealX,CinematicaInversa.coordenadaRealY)));
		if ( !(d>=0) )
			return; 
		suma+= d;
		cuantos++;
	//	System.out.println("T->Real: "+T.getDx()+","+T.getDy() +"\t"+CinematicaInversa.coordenadaRealX+","+CinematicaInversa.coordenadaRealY);
	//	System.out.println(suma/cuantos);

	}
	
	public double promedio(){
		return suma/cuantos;
	}
	
	private double distancia(Punto p1, Punto p2){
		return Math.sqrt( (p1.getDx() - p2.getDx()) * (p1.getDx() - p2.getDx()) + (p1.getDy() - p2.getDy()) * (p1.getDy() - p2.getDy()) );
	}
	
	private void calculaPosicionBrazo(){
		double a2 = CinematicaInversa.servoDerecho.getAnguloNormalizado();
		double a1 = CinematicaInversa.servoIzquerdo.getAnguloNormalizado();
		
		double x = O1.getDx() + CinematicaInversa.L1*Math.cos(a1*Math.PI/2 + Math.PI/2);
		double y = O1.getDy() + CinematicaInversa.L1*Math.sin(a1*Math.PI/2 + Math.PI/2);
		
		//System.out.println(a1+","+a2+"\t"+x+","+y);
		B1.setDx(x);
		B1.setDy(y);
		
		x = O2.getDx() + CinematicaInversa.L1*Math.cos(a2*Math.PI/2);
		y = O2.getDy() + CinematicaInversa.L1*Math.sin(a2*Math.PI/2);

		B2.setDx(x);
		B2.setDy(y);
	
		//System.out.println("O1->B1: "+distancia(O1, B1)+", "+CinematicaInversa.L1);
		//System.out.println("O2->B2: "+distancia(O2, B2)+", "+CinematicaInversa.L1);
		circulo();
		
		
	//	System.out.println("T->B2: "+distancia(T, B2)+", "+CinematicaInversa.L2);
	//	System.out.println("T->B1: "+distancia(T, B1)+", "+CinematicaInversa.L2);
		
	//	System.out.println("T->Real: "+T.getDx()+","+T.getDy() +"\t"+CinematicaInversa.coordenadaRealX+","+CinematicaInversa.coordenadaRealY);
	//	System.out.println("T->Real: "+distancia(T, new Punto(CinematicaInversa.coordenadaRealX,CinematicaInversa.coordenadaRealY)));
	}
	
	private int circulo(){
		double dx, dy;
		double dist, a, h, cx2, cy2;
		
		double cx0 = B1.getDx();
		double cx1 = B2.getDx();
		double cy0 = B1.getDy();
		double cy1 = B2.getDy();

	    dx = cx0 - cx1;
	    dy = cy0 - cy1;
	    dist =  Math.sqrt(dx * dx + dy * dy);

		double radius0 = CinematicaInversa.L2;
		double radius1 = CinematicaInversa.L4;
		
//		System.out.println("dist: "+dist);

		if (dist > radius0 + radius1){ 
		   System.out.println("No solutions, the circles are too far apart");
		   return 0;
		}
		
		if (dist < Math.abs(radius0 - radius1)){
		   System.out.println("No solutions, one circle contains the other.");
		   return 0;
		}
		    
		if ((dist == 0) && (radius0 == radius1)) {
		   System.out.println("No solutions, the circles coincide.");
		   return 0;
		}

		// Find a and h.
		a = (radius0 * radius0 - radius1 * radius1 + dist * dist) / (2 * dist);
		h = Math.sqrt(radius0 * radius0 - a * a);

		 // Find P2.
		cx2 = cx0 + a * (cx1 - cx0) / dist;
		cy2 = cy0 + a * (cy1 - cy0) / dist;

		// Get the points P3.
		
		double intersectionx1 = (cx2 + h * (cy1 - cy0) / dist);
		double intersectiony1 = (cy2 - h * (cx1 - cx0) / dist);
		
		double intersectionx2 = (cx2 - h * (cy1 - cy0) / dist);
		double intersectiony2 = (cy2 + h * (cx1 - cx0) / dist);

		T.setDx(intersectionx2);
		T.setDy(intersectiony2);
		
		Q.setDx(intersectionx1);
		Q.setDy(intersectiony1);
		
		//System.out.println(intersectionx1+","+intersectiony1);
		
		if (dist == radius0 + radius1) 
		    return 1;
		else
		    return 2;
	}

	public void agregaPuntoActual(Punto puntoActual) {
		trayectoriaDeseada.add(puntoActual);
	}
}
