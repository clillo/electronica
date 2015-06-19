package com.clillo.plotclock;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.clillo.plotclock.core.Calculo;

public class PanelRobot extends JPanel {
	
	private static final long serialVersionUID = 4075738060965200021L;

	private ArrayList<Punto> trayectoriaReal = new ArrayList<Punto>();
	private ArrayList<Punto> trayectoriaDeseada = new ArrayList<Punto>();

	private double escala;
	private double origenX;
	private double origenY;
	
	private Calculo calculo = new Calculo(); 
	private MatrizConversion mc = new MatrizConversion();

	public PanelRobot() {
		calculo.setup(CinematicaInversa.L1, CinematicaInversa.L2, CinematicaInversa.L4);
		this.setBackground(Color.BLACK);
		setOrigen(140, -200);
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
		
		real2Pantalla(calculo.getO1());
		real2Pantalla(calculo.getO2());
	
		g.fillOval(calculo.getO1().getIx()-5, calculo.getO1().getIy()-5, 10, 10);
		g.fillOval(calculo.getO2().getIx()-5, calculo.getO2().getIy()-5, 10, 10);
		g.drawBytes("O1".getBytes(), 0, 2, calculo.getO1().getIx()-5, calculo.getO1().getIy()-5);
		g.drawBytes("O2".getBytes(), 0, 2, calculo.getO2().getIx()-5, calculo.getO2().getIy()-5);
		
		calculo.calculaPosicionBrazo(CinematicaInversa.servoIzquerdo.getAnguloNormalizado(), CinematicaInversa.servoDerecho.getAnguloNormalizado());
	
		real2Pantalla(calculo.getB1());
		g.drawBytes("B1".getBytes(), 0, 2, calculo.getB1().getIx()-5, calculo.getB1().getIy()-5);
		g.fillOval(calculo.getB1().getIx()-4, calculo.getB1().getIy()-4, 8, 8);
		g.drawLine(calculo.getB1().getIx(), calculo.getB1().getIy(), calculo.getO1().getIx(), calculo.getO1().getIy());

		real2Pantalla(calculo.getB2());
		g.drawBytes("B2".getBytes(), 0, 2, calculo.getB2().getIx()-5, calculo.getB2().getIy()-5);
		g.fillOval(calculo.getB2().getIx()-4, calculo.getB2().getIy()-4, 8, 8);
		g.drawLine(calculo.getB2().getIx(), calculo.getB2().getIy(), calculo.getO2().getIx(), calculo.getO2().getIy());

		real2Pantalla(calculo.getT());
		trayectoriaReal.add(new Punto(calculo.getT().getDx(), calculo.getT().getDy()));

		g.fillOval(calculo.getT().getIx()-4, calculo.getT().getIy()-4, 8, 8);

		g.drawLine(calculo.getT().getIx(), calculo.getT().getIy(), calculo.getB1().getIx(), calculo.getB1().getIy());
		g.drawLine(calculo.getT().getIx(), calculo.getT().getIy(), calculo.getB2().getIx(), calculo.getB2().getIy());
		
		Punto min = new Punto(CinematicaInversa.DIBUJO_MIN_X, CinematicaInversa.DIBUJO_MIN_Y);
		Punto max = new Punto(CinematicaInversa.DIBUJO_MAX_X, CinematicaInversa.DIBUJO_MAX_Y);
		Punto cero = new Punto(0, 0);
		
		real2Pantalla(min);
		real2Pantalla(max);
		real2Pantalla(cero);
		
		g.setColor(Color.lightGray);
		g.drawLine(min.getIx(), cero.getIy(), max.getIx(), cero.getIy());
		g.drawLine(cero.getIx(), min.getIy(), cero.getIx(), max.getIy());
		
		for (Par p: mc.getListaPares()){
			
			double a1 = CinematicaInversa.servoIzquerdo.getAnguloNormalizado(p.getMotor2());
			double a2 = CinematicaInversa.servoDerecho.getAnguloNormalizado(p.getMotor1());
		//	System.out.println(p.getMotor1()+","+p.getMotor2()+"\t"+a1+","+a2);
			calculo.calculaPosicionBrazo(a1, a2);
			real2Pantalla(calculo.getT());
			g.setColor(Color.cyan);
			g.fillOval(calculo.getT().getIx()-1, calculo.getT().getIy()-1, 2, 2);
			
			String s = p.getId();
			g.drawChars(s.toCharArray(), 0, s.length(), calculo.getT().getIx(), calculo.getT().getIy());

		}
	}
	
	public void setEscala(int escala){		
		this.escala = (100.0/(escala*1.0));
	//	System.out.println("Escala: "+escala+" "+this.escala);
		this.repaint();
	}
	
	public void setOrigen(int x, int y){
	//	System.out.println("Origen: "+x+","+y);
		origenX = (x - 5) - 50.0 ;
		origenY = 200.0 + (y - 50);
		this.repaint();
	}
	
	private void real2Pantalla(Punto p){
		double absolutaX = ((p.getDx()-CinematicaInversa.DIBUJO_MIN_X) / (CinematicaInversa.DIBUJO_MAX_X - CinematicaInversa.DIBUJO_MIN_X)) ;
		double absolutaY = 1.0 - ((p.getDy()-CinematicaInversa.DIBUJO_MIN_Y) / (CinematicaInversa.DIBUJO_MAX_Y - CinematicaInversa.DIBUJO_MIN_Y));
			
		double rx = absolutaX * this.getWidth()/2 + origenX;
		double ry = absolutaY * this.getHeight()/2 + origenY;
		
	//	double rx = absolutaX;
	//	double ry = absolutaY;
		p.setIx((int)rx);
		p.setIy((int)ry);
	}

	public void agregaPuntoActual(Punto puntoActual) {
		trayectoriaDeseada.add(puntoActual);
	}
}
