package com.clillo.plotclock;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanelRobot extends JPanel {
	
	private static final long serialVersionUID = 4075738060965200021L;

	private ArrayList<Punto> trayectoriaReal = new ArrayList<Punto>();

	private double escala;
	private double origenX;
	private double origenY;
	private double sumaDistancias; 
	
	private Calculo calculo = new Calculo(); 
	private MatrizConversion mc = new MatrizConversion();

	public PanelRobot() {
		this.setBackground(Color.BLACK);
		setOrigen(140, -100);
	}
	
	public void limpia(){
		trayectoriaReal.clear();
	}
	
	private long itera(long total, Configuracion configuracion){
		boolean contar = false;
		
		if(total==0)
			contar = true;
		
		long n = 0;
		Configuracion configuracionMin = null;
		double min = 999999999;

		long tiempoInicial = System.currentTimeMillis();
		String tiempoAnterior = "";
		
		long cantidades[] = {0, 0, 0, 0, 0, 0, 0, 0};
		
		for (double origen1X = 20.0; origen1X < 30.0; origen1X += 0.5){
			cantidades[0]++;
			cantidades[1]=0;
			for (double origen1Y = -30.0; origen1Y > -35.0; origen1Y -= 0.5){
				cantidades[1]++;
				cantidades[2]=0;
				for (double origen2X = 40.0; origen2X < 50.0; origen2X += 0.5){
					cantidades[2]++;
					cantidades[3]=0;
					for (double origen2Y = origen1Y; origen2Y >= origen1Y; origen2Y -= 0.5){
						cantidades[3]++;
						cantidades[4]=0;
						for (double l5 = 0.2; l5 <= 10; l5 += 0.2){
							cantidades[4]++;
							cantidades[5]=0;
							for (double l3 = 50; l3 <= 60; l3 += 0.2){
								cantidades[5]++;
								cantidades[6]=0;
								for (double l1 = 30; l1 <= 40; l1 += 0.2){
									cantidades[6]++;
									cantidades[7]=0;
									for (double angulo3 = 0.1; angulo3 <= 2.00; angulo3 += 0.05) {
										cantidades[7]++;
										if (!contar){
											configuracion.setLargoBrazo1(l1);
											configuracion.setLargoBrazo2(l1);
											configuracion.setLargoBrazo3(l3);
											configuracion.setLargoBrazo4(l3);
											configuracion.setLargoBrazo5(l5);
											configuracion.setAnguloBrazo5(angulo3);
											
											configuracion.setOrigen1X(origen1X);
											configuracion.setOrigen1Y(origen2Y);
											configuracion.setOrigen2X(origen2X);
											configuracion.setOrigen2Y(origen2Y);
											
											realizaTest(null);

											if (sumaDistancias > 0	&& sumaDistancias < min) {
												min = sumaDistancias;
												configuracionMin = new Configuracion(configuracion);
											}
											
											String tiempo = Util.getTiempo(tiempoInicial);
											if (!tiempo.substring(0,3).equals(tiempoAnterior)){
												double p = n/(total*1.0)*100.0;
												System.out.println(tiempo+ "\tProbando "+Util.miles(n)+" de "+Util.miles(total)+" ("+Util.redondea(p)+" %) "+Util.redondea(min)+"\t"+configuracionMin);
												tiempoAnterior = tiempo.substring(0,3);
											}

										}
										if (contar)
											total++;
										
										n++;							
									}
									
								}
//								if (contar)
//									System.out.println("paso ("+Util.miles(total)+" elementos)");
							}
						}
					}
				}
			}
		}
		
		if (contar)
			for (int i=0; i<cantidades.length; i++)
				System.out.println(i+"\t"+cantidades[i]);
		else
			System.out.println(Util.getTiempo(tiempoInicial) + "\t"+Util.redondea(min)+"\t"+configuracionMin);
		
		return total;
	}
	
	public void test(){
		Configuracion configuracion = calculo.getConfiguracion(); 		
		
		long total = itera(0, configuracion);
		itera(total, configuracion);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.blue);
		for (Punto p: trayectoriaReal){
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
		
		try {
			calculo.calculaPosicionBrazo(CinematicaInversa.servoIzquerdo.getAnguloNormalizado(), CinematicaInversa.servoDerecho.getAnguloNormalizado());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		real2Pantalla(calculo.getB1());
		g.drawBytes("B1".getBytes(), 0, 2, calculo.getB1().getIx()-5, calculo.getB1().getIy()-5);
		g.fillOval(calculo.getB1().getIx()-4, calculo.getB1().getIy()-4, 8, 8);
		g.drawLine(calculo.getB1().getIx(), calculo.getB1().getIy(), calculo.getO1().getIx(), calculo.getO1().getIy());

		real2Pantalla(calculo.getB2());
		g.drawBytes("B2".getBytes(), 0, 2, calculo.getB2().getIx()-5, calculo.getB2().getIy()-5);
		g.fillOval(calculo.getB2().getIx()-4, calculo.getB2().getIy()-4, 8, 8);
		g.drawLine(calculo.getB2().getIx(), calculo.getB2().getIy(), calculo.getO2().getIx(), calculo.getO2().getIy());

		real2Pantalla(calculo.getH());

		g.fillOval(calculo.getH().getIx()-4, calculo.getH().getIy()-4, 8, 8);
		g.drawBytes("H".getBytes(), 0, 1, calculo.getH().getIx()-5, calculo.getH().getIy()-5);
		g.drawLine(calculo.getH().getIx(), calculo.getH().getIy(), calculo.getB1().getIx(), calculo.getB1().getIy());
		g.drawLine(calculo.getH().getIx(), calculo.getH().getIy(), calculo.getB2().getIx(), calculo.getB2().getIy());
		
		real2Pantalla(calculo.getT());
		g.fillOval(calculo.getT().getIx()-4, calculo.getT().getIy()-4, 8, 8);

		g.drawLine(calculo.getH().getIx(), calculo.getH().getIy(), calculo.getT().getIx(), calculo.getT().getIy());

		trayectoriaReal.add(new Punto(calculo.getT().getDx(), calculo.getT().getDy()));

		/*
		Punto min = new Punto(CinematicaInversa.DIBUJO_MIN_X, CinematicaInversa.DIBUJO_MIN_Y);
		Punto max = new Punto(CinematicaInversa.DIBUJO_MAX_X, CinematicaInversa.DIBUJO_MAX_Y);
		Punto cero = new Punto(0, 0);
		
		real2Pantalla(min);
		real2Pantalla(max);
		real2Pantalla(cero);
		
		g.setColor(Color.lightGray);
		g.drawLine(min.getIx(), cero.getIy(), max.getIx(), cero.getIy());
		g.drawLine(cero.getIx(), min.getIy(), cero.getIx(), max.getIy());
		*/
		
		realizaTest(g);
	}
	
	private void dibujaPunto(Graphics g, Punto p, Color c, String str){
		g.setColor(c);
		g.fillOval(p.getIx()-1, p.getIy()-1, 2, 2);
		if (str!=null)
			g.drawChars(str.toCharArray(), 0, str.length(), p.getIx(), p.getIy());		
	}
	
	private void realizaTest(Graphics g){
		sumaDistancias = 0.0;
		int cuantos = 0;
		for (Par p: mc.getListaPares()){			
			double a2 = CinematicaInversa.servoIzquerdo.getAnguloNormalizado(p.getMotor1());
			double a1 = CinematicaInversa.servoDerecho.getAnguloNormalizado(p.getMotor2());
			try {
				calculo.calculaPosicionBrazo(a2, a1);
				
				Punto original = new Punto(p.getX()*1.0, p.getY()*1.0);
				real2Pantalla(original);
				Punto calculado = calculo.getT(); 
				real2Pantalla(calculado);
				if (g!=null){
					dibujaPunto(g, calculado, Color.cyan, p.getId());
					dibujaPunto(g, original, Color.magenta, null);
				}else{
					sumaDistancias+= Math.abs(original.distanciaEntera(calculado));
					cuantos++;
				}
			} catch (Exception e) {
			//	System.err.println(e.getMessage());
			}
		}
		
		if (cuantos!= mc.getListaPares().size())
			sumaDistancias = 0;
		//System.out.println(distancia);
	}
	
	public void setEscala(int escala){		
		this.escala = (100.0/(escala*1.0));
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
			
		double rx = absolutaX * this.getWidth()*escala + origenX;
		double ry = absolutaY * this.getHeight()*escala + origenY;
		p.setIx((int)rx);
		p.setIy((int)ry);
	}

	/**
	 * 
		4.200000000000001	66.15068884362896	Configuracion [largoBrazo1=32.80000000000004,57.80000000000011,0.2,1.7100000000000013]
		Probando 253400000 de 253500000 (99.96055226824457%) 295.7957571841528	Configuracion [largoBrazo1=39.999999999999964, largoBrazo2=39.999999999999964, largoBrazo3=56.799999999999976, largoBrazo4=56.799999999999976, largoBrazo5=1.4, origen1X=22.0, origen1Y=-33.0, origen2X=49.0, origen2Y=-33.0, anguloBrazo5=1.8999999999999997]
 082:47	500.395		Configuracion [largoBrazo1=39.9999, largoBrazo2=39.9999, largoBrazo3=57.5999, largoBrazo4=57.5999, largoBrazo5=0.2, origen1X=21.0, origen1Y=-33.0, origen2X=49.0, origen2Y=-33.0, anguloBrazo5=0.9]
054:13	499.9507	Configuracion [largoBrazo1=39.9999, largoBrazo2=39.9999, largoBrazo3=58.3999, largoBrazo4=58.3999, largoBrazo5=0.2, origen1X=21.0, origen1Y=-34.0, origen2X=49.0, origen2Y=-34.0, anguloBrazo5=1.8]
042:58	47.0	Configuracion [largoBrazo1=30.0, largoBrazo2=30.0, largoBrazo3=50.0, largoBrazo4=50.0, largoBrazo5=9.4, origen1X=20.0, origen1Y=-30.0, origen2X=41.0, origen2Y=-30.0, anguloBrazo5=0.7999]

	 */
}
