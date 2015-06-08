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

	public PanelPosicion() {
		this.setBackground(Color.BLACK);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

	public void setListener(ListenerPosicion listener) {
		this.listener = listener;
	}
	
	private void mover(double x, double y){
		double rx = x / (this.getWidth()*1.0);
		double ry = y / (this.getHeight()*1.0);
		listener.mover(rx, ry);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.lightGray);
		g.drawLine(getWidth()/2, 0,             getWidth()/2, getHeight());
		g.drawLine(0,            getHeight()/2, getWidth(),   getHeight()/2);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.getModifiersEx()==128)
			mover(e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mover(e.getX(), e.getY());
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
