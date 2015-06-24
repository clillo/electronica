package com.clillo.plotclock.simulacion;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import com.clillo.plotclock.Configuracion;

public class LeeArchivo {
	
	private DataInputStream in = null; 

	public void abrir(String nombreArchivo) throws IOException{
		System.out.println("Procesando "+nombreArchivo);
		in = new DataInputStream(new BufferedInputStream(new FileInputStream(nombreArchivo)));
	}
	
	public void leer(Configuracion c) throws IOException{
		if (in==null)
			throw new IOException("No se ha abierto el archivo");
				
		float l1 = in.readFloat();
		float l3 = in.readFloat();
		float l5 = in.readFloat();
		float anguloBrazo5 = in.readFloat();
        
		float origen1x = in.readFloat();
		float origen1y = in.readFloat();
		float origen2x = in.readFloat();
		float origen2y = in.readFloat();
		
		c.setLargoBrazo1(l1);
		c.setLargoBrazo2(l1);
		c.setLargoBrazo3(l3);
		c.setLargoBrazo4(l3);
		c.setLargoBrazo5(l5);
		
		c.setAnguloBrazo5(anguloBrazo5);
		
		c.setOrigen1X(origen1x);
		c.setOrigen1Y(origen1y);
		
		c.setOrigen2X(origen2x);
		c.setOrigen2Y(origen2y);
		
	}
	
	public void cerrar() throws IOException{
		if (in==null)
			throw new IOException("No se ha abierto el archivo");
		
		in.close();
	}
}
