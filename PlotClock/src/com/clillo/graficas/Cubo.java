package com.clillo.graficas;

import java.awt.Graphics;

public class Cubo extends Figura{
	  Punto p[] = new Punto[8];
	  Linea arista[] = new Linea[13];

	  public Cubo() {
	    p[0] = new Punto( -20.0, -20.0, 20.0);
	    p[1] = new Punto(20.0, 20.0, 20.0);
	    p[2] = new Punto(20.0, 20.0, -20.0);
	    p[3] = new Punto( -20.0, 20.0, -20.0);
	    p[4] = new Punto( -20.0, 20.0, 20.0);
	    p[5] = new Punto(20.0, -20.0, 20.0);
	    p[6] = new Punto(20.0, -20.0, -20.0);
	    p[7] = new Punto( -20.0, -20.0, -20.0);

	    arista[0]=new Linea(p[0], p[4]);
	    arista[1]=new Linea(p[0], p[7]);
	    arista[2]=new Linea(p[0], p[5]);
	    arista[3]=new Linea(p[1], p[5]);
	    arista[4]=new Linea(p[1], p[2]);
	    arista[5]=new Linea(p[2], p[3]);
	    arista[6]=new Linea(p[4], p[1]);
	    arista[7]=new Linea(p[4], p[3]);
	    arista[8]=new Linea(p[5], p[6]);
	    arista[9]=new Linea(p[6], p[2]);
	    arista[10]=new Linea(p[5], p[6]);
	    arista[11]=new Linea(p[7], p[6]);
	    arista[12]=new Linea(p[7], p[3]);

	    for (int i=0; i<8; i++){
	      if (minX == -1 || p[i].getExtentMinX() < minX) {
	        minX = p[i].getExtentMinX();
	      }
	      if (maxX == -1 || p[i].getExtentMaxX() > maxX) {
	        maxX = p[i].getExtentMaxX();
	      }
	      if (minY == -1 || p[i].getExtentMinY() < minY) {
	         minY = p[i].getExtentMinY();
	       }
	      if (maxY == -1 || p[i].getExtentMaxY() > maxY) {
	         maxY = p[i].getExtentMaxY();
	      }

	    }

	  }

	  public void paint(Graphics g, Matriz m){
	  //  for (int i=0; i<8; i++)
	   //   p[i].paint(g,m);

	    for (int i=0; i<13; i++)
	      arista[i].paint(g,m);

	  }

	}
