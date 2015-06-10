package com.clillo.graficas;
import java.awt.*;

public class Linea
    extends Figura {
  private Punto p1, p2;

  public Linea() {
  }

  public Linea(Punto p1, Punto p2) {
    this.p1 = p1;
    this.p2 = p2;
    if (minX == -1 || p1.getIX() < minX) {
      minX = p1.getIX();

    }
    if (minY == -1 || p1.getIY() < minY) {
      minY = p1.getIY();

    }
    if (maxX == -1 || p1.getIX() > maxX) {
      maxX = p1.getIX();

    }
    if (maxY == -1 || p1.getIY() > maxY) {
      maxY = p1.getIY();

    }

    if (minX == -1 || p2.getIX() < minX) {
      minX = p2.getIX();

    }
    if (minY == -1 || p2.getIY() < minY) {
      minY = p2.getIY();

    }
    if (maxX == -1 || p2.getIX() > maxX) {
      maxX = p2.getIX();

    }
    if (maxY == -1 || p2.getIY() > maxY) {
      maxY = p2.getIY();

    }

  }

}
