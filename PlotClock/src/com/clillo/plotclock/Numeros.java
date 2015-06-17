package com.clillo.plotclock;

import java.util.ArrayList;

import com.clillo.plotclock.Punto.PosicionVertical;

public class Numeros {

	private PosicionVertical posicionActual = PosicionVertical.ARRIBA;
	
	private ArrayList<Punto> trayectoria = new ArrayList<Punto>();

	private void lift(int n) {
		if (n==1)
			posicionActual = PosicionVertical.ARRIBA;

		if (n==0)
			posicionActual = PosicionVertical.ABAJO;
	}

	public void dibuja(int a1, int a2, int a3, int a4) {
		// number(3, 3, 111, 1);
		number(5, 25, a1, 0.9);
		number(19, 25, a2, 0.9);
		// number(28, 25, 11, 0.9);

		number(34, 25, a3, 0.9);
		number(48, 25, a4, 0.9);
	}

	// Writing numeral with bx by being the bottom left originpoint. Scale 1
	// equals a 20 mm high font.
	// The structure follows this principle: move to first startpoint of the
	// numeral, lift down, draw numeral, lift up
	private void number(double bx, double by, int num, double scale) {
		switch (num) {

			case 0:
				drawTo(bx + 12 * scale, by + 6 * scale);
				lift(0);
				bogenGZS(bx + 7 * scale, by + 10 * scale, 10 * scale, -0.8, 6.7, 0.5);
				lift(1);
				break;
	
			case 1:
				drawTo(bx + 3 * scale, by + 15 * scale);
				lift(0);
				drawTo(bx + 10 * scale, by + 20 * scale);
				drawTo(bx + 10 * scale, by + 0 * scale);
				lift(1);
				break;
				
			case 2:
				drawTo(bx + 2 * scale, by + 12 * scale);
				lift(0);
				bogenUZS(bx + 8 * scale, by + 14 * scale, 6 * scale, 3.0, -0.8, 1.0);
				drawTo(bx + 1 * scale, by + 0 * scale);
				drawTo(bx + 12 * scale, by + 0 * scale);
				lift(1);
				break;
				
			case 3:
				drawTo(bx + 2 * scale, by + 17 * scale);
				lift(0);
				bogenUZS(bx + 5 * scale, by + 15 * scale, 5 * scale, 3, -2, 1);
				bogenUZS(bx + 5 * scale, by + 5 * scale, 5 * scale, 1.57, -3, 1);
				lift(1);
				break;
				
			case 4:
				drawTo(bx + 10 * scale, by + 0 * scale);
				lift(0);
				drawTo(bx + 10 * scale, by + 20 * scale);
				drawTo(bx + 2 * scale, by + 6 * scale);
				drawTo(bx + 12 * scale, by + 6 * scale);
				lift(1);
				break;
				
			case 5:
				drawTo(bx + 2 * scale, by + 5 * scale);
				lift(0);
				bogenGZS(bx + 5 * scale, by + 6 * scale, 6 * scale, -2.5, 2, 1);
				drawTo(bx + 5 * scale, by + 20 * scale);
				drawTo(bx + 12 * scale, by + 20 * scale);
				lift(1);
				break;
				
			case 6:
				drawTo(bx + 2 * scale, by + 10 * scale);
				lift(0);
				bogenUZS(bx + 7 * scale, by + 6 * scale, 6 * scale, 2, -4.4, 1);
				drawTo(bx + 11 * scale, by + 20 * scale);
				lift(1);
				break;
				
			case 7:
				drawTo(bx + 2 * scale, by + 20 * scale);
				lift(0);
				drawTo(bx + 12 * scale, by + 20 * scale);
				drawTo(bx + 2 * scale, by + 0);
				lift(1);
				break;
				
			case 8:
				drawTo(bx + 5 * scale, by + 10 * scale);
				lift(0);
				bogenUZS(bx + 5 * scale, by + 15 * scale, 5 * scale, 4.7, -1.6, 1);
				bogenGZS(bx + 5 * scale, by + 5 * scale, 5 * scale, -4.7, 2, 1);
				lift(1);
				break;
	
			case 9:
				drawTo(bx + 9 * scale, by + 11 * scale);
				lift(0);
				bogenUZS(bx + 7 * scale, by + 15 * scale, 5 * scale, 4, -0.5, 1);
				drawTo(bx + 5 * scale, by + 0);
				lift(1);
				break;
	
			case 111:
				lift(0);
				drawTo(70, 46);
				drawTo(65, 43);
	
				drawTo(65, 49);
				drawTo(5, 49);
				drawTo(5, 45);
				drawTo(65, 45);
				drawTo(65, 40);
	
				drawTo(5, 40);
				drawTo(5, 35);
				drawTo(65, 35);
				drawTo(65, 30);
	
				drawTo(5, 30);
				drawTo(5, 25);
				drawTo(65, 25);
				drawTo(65, 20);
	
				drawTo(5, 20);
				drawTo(60, 44);
	
				drawTo(75.2, 47);
				lift(2);
	
				break;
	
			case 11:
				drawTo(bx + 5 * scale, by + 15 * scale);
				lift(0);
				bogenGZS(bx + 5 * scale, by + 15 * scale, 0.1 * scale, 1, -1, 1);
				lift(1);
				drawTo(bx + 5 * scale, by + 5 * scale);
				lift(0);
				bogenGZS(bx + 5 * scale, by + 5 * scale, 0.1 * scale, 1, -1, 1);
				lift(1);
				break;
		}

	}

	private void bogenGZS(double bx, double by, double radius, double start, double ende, double sqee) {
		double inkr = 0.05;
		double count = 0;

		do {
			drawTo(sqee * radius * Math.cos(start + count) + bx, radius * Math.sin(start + count) + by);
			count += inkr;
		} while ((start + count) <= ende);
	}

	private void bogenUZS(double bx, double by, double radius, double start, double ende, double sqee) {
		double inkr = -0.05;
		double count = 0;

		do {
			drawTo(sqee * radius * Math.cos(start + count) + bx, radius * Math.sin(start + count) + by);
			count += inkr;
		} while ((start + count) > ende);
	}

	private void drawTo(double x, double y) {
		Punto p = new Punto(x, y);
		p.setPosicion(posicionActual);
		trayectoria.add(p);
	}

	public ArrayList<Punto> getTrayectoria() {
		return trayectoria;
	}
}
