package com.clillo.graficas;

import java.awt.*;

public class Punto extends Figura{
  private double x, y, z;
  private int ix, iy;

  public Punto() {
  }

  public Punto(double x, double y, double z) {
    setX(x);
    setY(y);
    setZ(z);
  }

  public void setX(double x){
    this.x=x;
    ix=new Double(x).intValue();
    if (minX == -1 || ix < minX) {
       minX = ix;
     }
    if (maxX == -1 || ix > maxX) {
       maxX = ix;
    }

  }

  public void setY(double y){
    this.y=y;
    iy=new Double(y).intValue();
    if (minY == -1 || iy < minY) {
       minY = iy;
     }
    if (maxY == -1 || iy > maxY) {
       maxY = iy;
    }
  }

  public void setZ(double z){
    this.z=z;
    iy=new Double(y).intValue();
  }

  public int getIX(){
    return ix;
  }

  public int getIY(){
    return iy;
  }

  public double getX(){
    return x;
  }

  public double getY(){
    return y;
  }

  public double getZ(){
    return z;
  }

  public void paint(Graphics g, Matriz m){
    Punto p=m.multiplicar(this);
    g.fillOval(p.getIX()-2, p.getIY()-2, 4, 4);
  }

}
