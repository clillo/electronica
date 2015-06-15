package com.clillo.plotclock;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class PanelPosicion extends JPanel implements MouseMotionListener, MouseListener{

	private static final long serialVersionUID = 4075738060965200021L;
	private ListenerPosicion listener;
	private CinematicaInversa cinematica;

	public PanelPosicion() {
		this.setBackground(Color.BLACK);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

	public void setCinematica(CinematicaInversa cinematica) {
		this.cinematica = cinematica;
	}

	public void setListener(ListenerPosicion listener) {
		this.listener = listener;
	}
	
	private void mover(double x, double y, boolean fake){
		double rx = x / (this.getWidth()*1.0);
		double ry = y / (this.getHeight()*1.0);
		listener.mover(rx, ry, fake);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		// Cruz central
		g.setColor(Color.lightGray);
		g.drawLine(getWidth()/2, 0,             getWidth()/2, getHeight());
		g.drawLine(0,            getHeight()/2, getWidth(),   getHeight()/2);
		
		MatrizConversion mc = new MatrizConversion();
		
		g.setColor(Color.green);
		boolean b = true;
		for (Par p: mc.getListaPares()){
			Par q = obtienePar(p);
			g.fillOval(q.getX(), q.getY(), 4, 4);
			//String s = p.getMotor1()+","+p.getMotor2();
			//String s = p.getX()+","+p.getY();
			String s = p.getId();
			if (b)
				g.drawChars(s.toCharArray(), 0, s.length(), q.getX(), q.getY());
			else
				g.drawChars(s.toCharArray(), 0, s.length(), q.getX(), q.getY()+20);
			//b = !b;
		}	
		
		g.setColor(Color.red);
		if (cinematica!=null)
			for(Par p: cinematica.getMatrizConversion().getListaPuntosActuales()){
				Par q = obtienePar(p);
				g.fillOval(q.getX(), q.getY(), 6, 6);
			}
	}
	
	private Par obtienePar(Par p){
		double absolutaX = ( (p.getX()-CinematicaInversa.DIBUJO_MIN_X) / (CinematicaInversa.DIBUJO_MAX_X - CinematicaInversa.DIBUJO_MIN_X) ) ;
		double absolutaY = ( (p.getY()-CinematicaInversa.DIBUJO_MIN_Y) / (CinematicaInversa.DIBUJO_MAX_Y - CinematicaInversa.DIBUJO_MIN_Y) );
		return new Par((int) (absolutaX * this.getWidth()), (int) (absolutaY * this.getHeight()), null);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mover(e.getX(), e.getY(), (e.getModifiersEx()!=128));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mover(e.getX(), e.getY(), false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
