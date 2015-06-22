package com.clillo.plotclock;

import com.clillo.plotclock.Punto;

public class Calculo {

	private Punto O1 = new Punto(21.0, -32.0);
	private Punto O2 = new Punto(48.4, -32.0);

	private Punto B1 = new Punto(0, 0);
	private Punto B2 = new Punto(0, 0);

	private Punto T = new Punto(0, 0);
	private Punto H = new Punto(0, 0);
	
	private static Configuracion configuracion;
	
	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public static void setConfiguracion(Configuracion configuracion) {
		Calculo.configuracion = configuracion;
	}

	public Punto getO1() {
		return O1;
	}

	public Punto getO2() {
		return O2;
	}

	public Punto getB1() {
		return B1;
	}

	public Punto getB2() {
		return B2;
	}

	public Punto getT() {
		return T;
	}

	public Punto getH() {
		return H;
	}

	public void calculaPosicionBrazo(double angulo2, double angulo1) throws Exception{
		if (configuracion==null)
			return;
		
		double piMedios = Math.PI/2.0;
		
		O1.setDx(configuracion.getOrigen1X());
		O1.setDy(configuracion.getOrigen1Y());
		O2.setDx(configuracion.getOrigen2X());
	 	O2.setDy(configuracion.getOrigen2Y());
		
		double x = O1.getDx() + configuracion.getLargoBrazo1()*Math.cos(angulo1*piMedios + piMedios);
		double y = O1.getDy() + configuracion.getLargoBrazo1()*Math.sin(angulo1*piMedios + piMedios);
		B1.setDx(x);
		B1.setDy(y);

		x = O2.getDx() + configuracion.getLargoBrazo2()*Math.cos(angulo2*piMedios);
		y = O2.getDy() + configuracion.getLargoBrazo2()*Math.sin(angulo2*piMedios);

		B2.setDx(x);
		B2.setDy(y);

		circulo();
		
		T.setDx(H.getDx()+configuracion.getLargoBrazo5()*Math.cos(angulo2*piMedios+configuracion.getAnguloBrazo5()));
		T.setDy(H.getDy()+configuracion.getLargoBrazo5()*Math.sin(angulo2*piMedios+configuracion.getAnguloBrazo5()));
	}
	
	private void circulo() throws Exception{
		double dx, dy;
		double dist, a, h, cx2, cy2;
		
		double cx0 = B1.getDx();
		double cx1 = B2.getDx();
		double cy0 = B1.getDy();
		double cy1 = B2.getDy();

	    dx = cx0 - cx1;
	    dy = cy0 - cy1;
	    dist =  Math.sqrt(dx * dx + dy * dy);

		double radius0 = configuracion.getLargoBrazo3();
		double radius1 = configuracion.getLargoBrazo4();

		if (dist > radius0 + radius1) 
		  throw new Exception("No solutions, the circles are too far apart: "+radius0 + ","+radius1+" -> "+dist);
		
		if (dist < Math.abs(radius0 - radius1))
			 throw new Exception("No solutions, one circle contains the other.");
		    
		if ((dist == 0) && (radius0 == radius1))
			 throw new Exception("No solutions, the circles coincide.");

		a = (radius0 * radius0 - radius1 * radius1 + dist * dist) / (2 * dist);
		h = Math.sqrt(radius0 * radius0 - a * a);

		cx2 = cx0 + a * (cx1 - cx0) / dist;
		cy2 = cy0 + a * (cy1 - cy0) / dist;
		
		double intersectionx2 = (cx2 - h * (cy1 - cy0) / dist);
		double intersectiony2 = (cy2 + h * (cx1 - cx0) / dist);

		H.setDx(intersectionx2);
		H.setDy(intersectiony2);
	}
}
