package com.clillo.plotclock;

import java.util.ArrayList;

public class MatrizConversion {

	private int motor1[][] = new int[100][100];
	private int motor2[][] = new int[100][100];
	
	private ArrayList<Par> listaPares;	
	private ArrayList<Par> listaPuntosActuales;
	private ArrayList<Celda> listaCeldas;

	public MatrizConversion(){
		motor1[5][30] = 1935;	motor2[5][30] = 1742;
		motor1[5][37] = 1848;	motor2[5][37] = 1805;
		motor1[5][45] = 1771;	motor2[5][45] = 1889;
		motor1[14][30] = 1842;	motor2[14][30] = 1652;
		motor1[14][37] = 1792;	motor2[14][37] = 1710;
		motor1[14][45] = 1727;	motor2[14][45] = 1780;
		motor1[23][30] = 1785;	motor2[23][30] = 1537;
		motor1[23][37] = 1699;	motor2[23][37] = 1595;
		motor1[23][45] = 1651;	motor2[23][45] = 1671;
		motor1[29][30] = 1715;	motor2[29][30] = 1424;
		motor1[29][37] = 1669;	motor2[29][37] = 1509;
		motor1[29][45] = 1622;	motor2[29][45] = 1571;
		motor1[35][30] = 1627;	motor2[35][30] = 1345;
		motor1[35][37] = 1594;	motor2[35][37] = 1418;
		motor1[35][45] = 1522;	motor2[35][45] = 1520;
		motor1[41][30] = 1543;	motor2[41][30] = 1262;
		motor1[41][37] = 1488;	motor2[41][37] = 1353;
		motor1[41][45] = 1456;	motor2[41][45] = 1434;
		motor1[47][30] = 1476;	motor2[47][30] = 1174;
		motor1[47][37] = 1431;	motor2[47][37] = 1265;
		motor1[47][45] = 1375;	motor2[47][45] = 1385;
		motor1[52][30] = 1387;	motor2[52][30] = 1121;
		motor1[52][37] = 1350;	motor2[52][37] = 1219;
		motor1[52][45] = 1317;	motor2[52][45] = 1320;
		motor1[56][30] = 1281;	motor2[56][30] = 1087;
		motor1[56][37] = 1258;	motor2[56][37] = 1177;
		motor1[56][45] = 1219;	motor2[56][45] = 1289;
		motor1[61][30] = 1200;	motor2[61][30] = 1023;
		motor1[61][37] = 1168;	motor2[61][37] = 1150;
		motor1[61][45] = 1093;	motor2[61][45] = 1255;
		motor1[65][30] = 1095;	motor2[65][30] = 1009;
		motor1[65][37] = 1085;	motor2[65][37] = 1118;
		motor1[65][45] = 977;	motor2[65][45] = 1231;
		
		listaPares = new ArrayList<Par>();
		listaPares.add(new Par(5,30, "00"));
		listaPares.add(new Par(5,37, "01"));
		listaPares.add(new Par(5,45, "02"));
		listaPares.add(new Par(14,30, "03"));
		listaPares.add(new Par(14,37, "04"));
		listaPares.add(new Par(14,45, "05"));
		listaPares.add(new Par(23,30, "06"));
		listaPares.add(new Par(23,37, "07"));
		listaPares.add(new Par(23,45, "08"));
		listaPares.add(new Par(29,30, "09"));
		listaPares.add(new Par(29,37, "10"));
		listaPares.add(new Par(29,45, "11"));
		listaPares.add(new Par(35,30, "12"));
		listaPares.add(new Par(35,37, "13"));
		listaPares.add(new Par(35,45, "14"));
		listaPares.add(new Par(41,30, "15"));
		listaPares.add(new Par(41,37, "16"));
		listaPares.add(new Par(41,45, "17"));
		listaPares.add(new Par(47,30, "18"));
		listaPares.add(new Par(47,37, "19"));
		listaPares.add(new Par(47,45, "20"));
		listaPares.add(new Par(52,30, "21"));
		listaPares.add(new Par(52,37, "22"));
		listaPares.add(new Par(52,45, "23"));
		listaPares.add(new Par(56,30, "24"));
		listaPares.add(new Par(56,37, "25"));
		listaPares.add(new Par(56,45, "26"));
		listaPares.add(new Par(61,30, "27"));
		listaPares.add(new Par(61,37, "28"));
		listaPares.add(new Par(61,45, "29"));
		listaPares.add(new Par(65,30, "30"));
		listaPares.add(new Par(65,37, "31"));
		listaPares.add(new Par(65,45, "32"));
		
		listaCeldas = new ArrayList<Celda>();
		for (int i=0; i<30; i+=3){
			listaCeldas.add(new Celda(listaPares.get(0+i), listaPares.get(1+i), listaPares.get(4+i), listaPares.get(3+i)));
			listaCeldas.add(new Celda(listaPares.get(1+i), listaPares.get(2+i), listaPares.get(5+i), listaPares.get(4+i)));
		}
	}
	
	public int [] getValor(int x, int y){
		Par buscado = new Par(x, y, "FK");
		Par[] puntosCercanos = cercanos(buscado);
	
		if (puntosCercanos[0]==null || puntosCercanos[1]==null || puntosCercanos[2]==null || puntosCercanos[3]==null )
			return new int[] {0, 0};
		
		double d1 = 1 / buscado.distancia(puntosCercanos[0]);
		double d2 = 1 / buscado.distancia(puntosCercanos[1]);		
		double d3 = 1 / buscado.distancia(puntosCercanos[2]);
		double d4 = 1 / buscado.distancia(puntosCercanos[3]);
		
		double suma = d1 + d2 + d3 + d4;
		
		System.out.println("\tSumas y distancia: "+suma+"\t"+d1+","+d2+","+d3+","+d4);
		double peso1 = d1 / suma;
		double peso2 = d2 / suma;
		double peso3 = d3 / suma;
		double peso4 = d4 / suma;

		double psuma = (peso1+peso2+peso3+peso4);
		System.out.println("Pesos: " +peso1+","+peso2+","+peso3+","+peso4+"\t"+psuma);
		
		double m1 = (getV1(puntosCercanos[0])*peso1 + getV1(puntosCercanos[1])*peso2 + getV1(puntosCercanos[2])*peso3 + getV1(puntosCercanos[3])*peso4)/psuma;
		double m2 = (getV2(puntosCercanos[0])*peso1 + getV2(puntosCercanos[1])*peso2 + getV2(puntosCercanos[2])*peso3 + getV2(puntosCercanos[3])*peso4)/psuma;

		System.out.println(m1+","+m2);
		
		listaPuntosActuales = new ArrayList<Par>();
		listaPuntosActuales.add(puntosCercanos[0]);
		listaPuntosActuales.add(puntosCercanos[1]);
		listaPuntosActuales.add(puntosCercanos[2]);
		listaPuntosActuales.add(puntosCercanos[3]);
		
		
		return new int[] {(int)m1, (int)m2};
	}
	
	private Par[] cercanos(Par buscado){
	
		Par pMinimo1 = null;
		Par pMinimo2 = null;
		Par pMinimo3 = null;
		Par pMinimo4 = null;
		
		for (Celda c: listaCeldas){
			//System.out.println(c.perteneceA(buscado));
			if (c.perteneceA(buscado)){
				pMinimo1 = c.getP1();
				pMinimo2 = c.getP2();
				pMinimo3 = c.getP3();
				pMinimo4 = c.getP4();
				break;
			}
		}
				
				
		return new Par[]{pMinimo1, pMinimo2, pMinimo3, pMinimo4};
	}

		
	public ArrayList<Par> getListaPuntosActuales() {
		return listaPuntosActuales;
	}

	private int getV1(Par p){
		return motor1[p.getX()][p.getY()];
	}

	private int getV2(Par p){
		return motor2[p.getX()][p.getY()];
	}

	private Par[] cercanos2(Par buscado){
		double minimo = 1000000000;

		Par pMinimo1 = null;
		Par pMinimo2 = null;
		Par pMinimo3 = null;
		Par pMinimo4 = null;
		
		for (Par p: listaPares)
			if (p.distancia(buscado)<minimo){
				minimo = p.distancia(buscado);
				pMinimo1 = p;
			}
	
		minimo = 1000000000;
		for (Par p: listaPares)
			if (p!=pMinimo1 && p.distancia(buscado)<minimo){
				minimo = p.distancia(buscado);
				pMinimo2 = p;
			}
		
		minimo = 1000000000;
		for (Par p: listaPares)
			if (p!=pMinimo1 && p!=pMinimo2 && p.distancia(buscado)<minimo){
				minimo = p.distancia(buscado);
				pMinimo3 = p;
			}

		minimo = 1000000000;
		for (Par p: listaPares)
			if (p!=pMinimo1 && p!=pMinimo2 && p!=pMinimo3 && p.distancia(buscado)<minimo){
				minimo = p.distancia(buscado);
				pMinimo4 = p;
			}
		
		return new Par[]{pMinimo1, pMinimo2, pMinimo3, pMinimo4};
	}
	
	public void imprime(){
		for (int y=0; y<50; y++){
			for (int x=0; x<70; x++)
				System.out.print(motor1[x][y]+" ");
			System.out.println();
		}
	}
		
	public ArrayList<Par> getListaPares() {
		return listaPares;
	}

	public static void main(String[] args) {
		MatrizConversion mc = new MatrizConversion();
		mc.imprime();
	}
}
