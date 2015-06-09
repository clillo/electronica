package com.clillo.graficas;
public class Matriz {

	  private double m[][] = new double[4][4];

	  public Matriz() {
	    m[0][0] = 1.0;    m[0][1] = 0.0;    m[0][2] = 0.0;  m[0][3] = 0.0;
	    m[1][0] = 0.0;    m[1][1] = 1.0;    m[1][2] = 0.0;  m[1][3] = 0.0;
	    m[2][0] = 0.0;    m[2][1] = 0.0;    m[2][2] = 1.0;  m[2][3] = 0.0;
	    m[3][0] = 0.0;    m[3][1] = 0.0;    m[3][2] = 0.0;  m[3][3] = 1.0;
	  }

	  public void translacion(double tx, double ty, double tz){
	    m[0][3]+=tx;
	    m[1][3]+=ty;
	    m[2][3]+=tz;

	  }

	  public void escalamiento(double sx, double sy, double sz){
	    m[0][0]*=sx;
	    m[1][1]*=sy;
	    m[2][2]*=sz;
	  }

	  public void rotacionx(double alfa){
	    alfa *= (Math.PI / 180);
	    double ca=Math.cos(alfa);
	    double sa=Math.sin(alfa);

	    double bt=m[0][1]*ca + m[0][2]*sa;   double ct=m[0][2]*ca - m[0][1]*sa;
	    double ft=m[1][1]*ca + m[1][2]*sa;   double gt=m[1][2]*ca - m[1][1]*sa;
	    double jt=m[2][1]*ca + m[2][2]*sa;   double kt=m[2][2]*ca - m[2][1]*sa;
	    double nt=m[3][1]*ca + m[3][2]*sa;   double ot=m[3][2]*ca - m[3][1]*sa;

	    m[0][1]=bt; m[0][2]=ct;
	    m[1][1]=ft; m[1][2]=gt;
	    m[2][1]=jt; m[2][2]=kt;
	    m[3][1]=nt; m[3][2]=ot;
	  }

	  public void rotaciony(double alfa){
	   alfa *= (Math.PI / 180);
	    double ca=Math.cos(alfa);
	    double sa=Math.sin(alfa);

	    double at=m[0][0]*ca-m[0][2]*sa;   double ct=m[0][0]*sa+m[0][2]*ca;
	    double et=m[1][0]*ca-m[1][2]*sa;   double gt=m[1][0]*sa+m[1][2]*ca;
	    double it=m[2][0]*ca-m[2][2]*sa;   double kt=m[2][0]*sa+m[2][2]*ca;
	    double mt=m[3][0]*ca-m[3][2]*sa;   double ot=m[3][0]*sa+m[3][2]*ca;

	    m[0][0]=at; m[0][2]=ct;
	    m[1][0]=et; m[1][2]=gt;
	    m[2][0]=it; m[2][2]=kt;
	    m[3][0]=mt; m[3][2]=ot;
	  }

	  public void rotacionz(double alfa){
	    alfa *= (Math.PI / 180);
	    double ca=Math.cos(alfa);
	    double sa=Math.sin(alfa);

	    double at=m[0][0]*ca+m[0][1] *sa;   double bt=-m[0][0]*sa+m[0][1]*ca;
	    double et=m[1][0]*ca+m[1][1]*sa;   double ft=-m[1][0]*sa+m[1][1]*ca;
	    double it=m[2][0]*ca+m[2][1]*sa;   double jt=-m[2][0]*sa+m[2][1]*ca;
	    double mt=m[3][0]*ca+m[3][1]*sa;   double nt=-m[3][0]*sa+m[3][1]*ca;

	    m[0][0]=at; m[0][1]=bt;
	    m[1][0]=et; m[1][1]=ft;
	    m[2][0]=it; m[2][1]=jt;
	    m[3][0]=mt; m[3][1]=nt;
	  }


	  public Punto multiplicar(Punto p) {
	    double x, y, z;

	    x = m[0][0] * p.getX() + m[0][1] * p.getY() + m[0][2]* p.getZ() + m[0][3];
	    y = m[1][0] * p.getX() + m[1][1] * p.getY() + m[1][2]* p.getZ() + m[1][3];
	    z = m[2][0] * p.getX() + m[2][1] * p.getY() + m[2][2]* p.getZ() + m[2][3];

	    return new Punto(x, y, z);
	  }

	  public void multiplicar(Matriz mm) {
	    Matriz r = new Matriz();

	    r.m[0][0] = m[0][0] * mm.m[0][0] + m[0][1] * mm.m[1][0] +    m[0][2] * mm.m[2][0]+    m[0][3] * mm.m[3][0];
	    r.m[1][0] = m[1][0] * mm.m[0][0] + m[1][1] * mm.m[1][0] +    m[1][2] * mm.m[2][0]+    m[1][3] * mm.m[3][0];
	    r.m[2][0] = m[2][0] * mm.m[0][0] + m[2][1] * mm.m[1][0] +    m[2][2] * mm.m[2][0]+    m[1][3] * mm.m[3][0];
	    r.m[3][0] = m[3][0] * mm.m[0][0] + m[3][1] * mm.m[1][0] +    m[3][2] * mm.m[2][0]+    m[2][3] * mm.m[3][0];

	    r.m[0][1] = m[0][0] * mm.m[0][1] + m[0][1] * mm.m[1][1] +    m[0][2] * mm.m[2][1]+    m[0][3] * mm.m[3][1];
	    r.m[1][1] = m[1][0] * mm.m[0][1] + m[1][1] * mm.m[1][1] +    m[1][2] * mm.m[2][1]+    m[1][3] * mm.m[3][1];
	    r.m[2][1] = m[2][0] * mm.m[0][1] + m[2][1] * mm.m[1][1] +    m[2][2] * mm.m[2][1]+    m[1][3] * mm.m[3][1];
	    r.m[3][1] = m[3][0] * mm.m[0][1] + m[3][1] * mm.m[1][1] +    m[3][2] * mm.m[2][1]+    m[2][3] * mm.m[3][1];

	    r.m[0][2] = m[0][0] * mm.m[0][2] + m[0][1] * mm.m[1][2] +    m[0][2] * mm.m[2][2]+    m[0][3] * mm.m[3][2];
	    r.m[1][2] = m[1][0] * mm.m[0][2] + m[1][1] * mm.m[1][2] +    m[1][2] * mm.m[2][2]+    m[1][3] * mm.m[3][2];
	    r.m[2][2] = m[2][0] * mm.m[0][2] + m[2][1] * mm.m[1][2] +    m[2][2] * mm.m[2][2]+    m[1][3] * mm.m[3][2];
	    r.m[3][2] = m[3][0] * mm.m[0][2] + m[3][1] * mm.m[1][2] +    m[3][2] * mm.m[2][2]+    m[2][3] * mm.m[3][2];

	    r.m[0][3] = m[0][0] * mm.m[0][3] + m[0][1] * mm.m[1][3] +    m[0][2] * mm.m[2][3]+    m[0][3] * mm.m[3][3];
	    r.m[1][3] = m[1][0] * mm.m[0][3] + m[1][1] * mm.m[1][3] +    m[1][2] * mm.m[2][3]+    m[1][3] * mm.m[3][3];
	    r.m[2][3] = m[2][0] * mm.m[0][3] + m[2][1] * mm.m[1][3] +    m[2][2] * mm.m[2][3]+    m[1][3] * mm.m[3][3];
	    r.m[3][3] = m[3][0] * mm.m[0][3] + m[3][1] * mm.m[1][3] +    m[3][2] * mm.m[2][3]+    m[2][3] * mm.m[3][3];


	    m = r.m;
	  }

	}
