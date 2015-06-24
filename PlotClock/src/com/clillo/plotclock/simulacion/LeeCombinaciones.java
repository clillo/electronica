package com.clillo.plotclock.simulacion;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.clillo.plotclock.Calculo;
import com.clillo.plotclock.CinematicaInversa;
import com.clillo.plotclock.Configuracion;
import com.clillo.plotclock.MatrizConversion;
import com.clillo.plotclock.Par;
import com.clillo.plotclock.Punto;
import com.clillo.plotclock.Util;

public class LeeCombinaciones {
	
	private MatrizConversion mc = new MatrizConversion();
	private Calculo calculo = new Calculo();
	private int numeroPares;
	
	public LeeCombinaciones() {
		numeroPares = 0;
		for (Par p: mc.getListaPares()){			
			p.setMotor1Normalizado(CinematicaInversa.servoIzquerdo.getAnguloNormalizado(p.getMotor1()));
			p.setMotor2Normalizado(CinematicaInversa.servoDerecho.getAnguloNormalizado(p.getMotor2()));
			
		 	p.setxNormalizado((p.getX()*1.0-CinematicaInversa.DIBUJO_MIN_X) / (CinematicaInversa.DIBUJO_MAX_X - CinematicaInversa.DIBUJO_MIN_X)) ;
		 	p.setyNormalizado((p.getY()*1.0-CinematicaInversa.DIBUJO_MIN_Y) / (CinematicaInversa.DIBUJO_MAX_Y - CinematicaInversa.DIBUJO_MIN_Y));
		 	numeroPares++;
		}
	}

	public void leer(String ruta) throws IOException{
		File f = new File(ruta);
		String l[] = f.list();
		for (int i=0; i<l.length; i++){
			String x = l[i];
			if (x.endsWith(".txt"))
				procesaArchivo(ruta+x);
		}
	}
		
	public void procesaArchivo(String nombreArchivo) throws IOException{
		DataOutputStream out = null;
		out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(nombreArchivo+".salida.txt")));
		
		LeeArchivo la = new LeeArchivo();
		la.abrir(nombreArchivo);
				
		long tiempoInicial = System.currentTimeMillis();
		String tiempoAnterior = "";
		int n=0;
		long total = 10000000;
		try {
			Configuracion c = new Configuracion();
		    while (true) {
		    	la.leer(c);
		    	float distancias [] = realizaTest(c);
		    	for (float d: distancias){
		    		out.writeFloat(d);
		    		//System.out.print(d+"\t");
		    	}
		    	//System.out.println();
		    	
		    	//System.out.println(c);
				String tiempo = Util.getTiempo(tiempoInicial);
				if (!tiempo.substring(0,3).equals(tiempoAnterior)){
					double p = n/(total*1.0)*100.0;
					System.out.println(tiempo+ "\tProbando "+Util.miles(n)+" de "+Util.miles(total)+" ("+Util.redondea(p)+" %) ");
					tiempoAnterior = tiempo.substring(0,3);
				}
				n++;
		    }
		} catch (EOFException e) {
		}finally{
			la.cerrar();
			out.close();
		}
	}
	
	private float [] realizaTest(Configuracion c){
		float distancias [] = new float[numeroPares];
		Calculo.setConfiguracion(c);
		int n=0;
		for (Par p: mc.getListaPares()){	
			float distanciaCuadrada = -1;
			try {
				calculo.calculaPosicionBrazo(p.getMotor2Normalizado(), p.getMotor1Normalizado());
				
				Punto calculado = calculo.getT();
			 	double tx = ((calculado.getDx()*1.0-CinematicaInversa.DIBUJO_MIN_X) / (CinematicaInversa.DIBUJO_MAX_X - CinematicaInversa.DIBUJO_MIN_X)) ;
			 	double ty = ((calculado.getDy()*1.0-CinematicaInversa.DIBUJO_MIN_Y) / (CinematicaInversa.DIBUJO_MAX_Y - CinematicaInversa.DIBUJO_MIN_Y));

			 	double dx = (tx - p.getxNormalizado());
			 	double dy = (ty - p.getyNormalizado());
				
				distanciaCuadrada = (float)Math.abs(dx*dx + dy*dy);
				
			} catch (Exception e) {
			}
			distancias[n] = distanciaCuadrada;
			n++;
		}
		
		return distancias;
	}

	public static void main(String[] args) throws IOException {
//		String ruta = "G:\\cache\\";
		String ruta = "tmp/";
		LeeCombinaciones lc = new LeeCombinaciones();
		lc.leer(ruta);
	}
}
