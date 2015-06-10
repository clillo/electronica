package com.clillo.plotclock;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanelReal extends JPanel {
	
	private Punto actual;
	private ArrayList<Punto> trayectoria = new ArrayList<Punto>();
	
	private static final long serialVersionUID = 4075738060965200021L;

	public PanelReal() {
		this.setBackground(Color.BLACK);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//System.out.println("PanelReal.paint()");
		g.setColor(Color.lightGray);
		g.drawLine(getWidth()/2, 0,             getWidth()/2, getHeight());
		g.drawLine(0,            getHeight()/2, getWidth(),   getHeight()/2);
		
		g.setColor(Color.red);
		
		if (actual!=null)
			g.drawOval(actual.getIx(), actual.getIy(), 4, 4);
		
		g.setColor(Color.blue);
		synchronized (trayectoria) {
			for (Punto p: trayectoria)
				g.drawOval(p.getIx(), p.getIy(), 3, 3);
	
		}
	}
	
	public void agregaPuntoTrayectoria(double x, double y){
		synchronized (trayectoria) {
			trayectoria.add(obtienePunto(x,  y));
		}
		this.repaint();
	}
	
	public void dibujaPosicionActual(double x, double y){
		actual = obtienePunto(x,  y);
		this.repaint();
	}
	
	private Punto obtienePunto(double x, double y){
		double absolutaX = ( (x-CinematicaInversa.DIBUJO_MIN_X) / (CinematicaInversa.DIBUJO_MAX_X - CinematicaInversa.DIBUJO_MIN_X) ) ;
		double absolutaY = ( (y-CinematicaInversa.DIBUJO_MIN_Y) / (CinematicaInversa.DIBUJO_MAX_Y - CinematicaInversa.DIBUJO_MIN_Y) );

		//	System.out.println(absolutaX+","+absolutaY);

		return new Punto((int) (absolutaX * this.getWidth()), (int) (absolutaY * this.getHeight()));
	}
	
	public void limpia(){
		trayectoria.clear();
	}
}
