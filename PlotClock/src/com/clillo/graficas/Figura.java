package com.clillo.graficas;

import java.awt.Graphics;

public class Figura {
  protected int minX, minY, maxX, maxY;

  public Figura() {
        minX = minY = maxX = maxY = -1;
  }

  public void paint(Graphics g, Matriz m) {

  }

  public int getExtentMinX(){
    return minX;
  }

  public int getExtentMinY(){
    return minY;
  }

  public int getExtentMaxX(){
    return maxX;
  }

  public int getExtentMaxY(){
    return maxY;
  }


}
