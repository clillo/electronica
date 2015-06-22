package com.clillo.plotclock;

import java.util.ArrayList;

public class MatrizConversion {
	
	private ArrayList<Par> listaPares;	
	private ArrayList<Par> listaPuntosActuales;
	private ArrayList<Celda> listaCeldas;

	public MatrizConversion(){
		listaPuntosActuales = new ArrayList<Par>();
		
		listaPares = new ArrayList<Par>();
		
/*		listaPares.add(new Par(5,24,"0",1707,1993));
		listaPares.add(new Par(5,30,"1",1734,1919));
		listaPares.add(new Par(5,36,"2",1750,1833));
		listaPares.add(new Par(5,42,"3",1896,1788));
*/	//	listaPares.add(new Par(5,48,"4",2044,1707));
		listaPares.add(new Par(12,24,"5",1583,1925));
		listaPares.add(new Par(12,30,"6",1644,1832));
		listaPares.add(new Par(12,36,"7",1690,1777));
		listaPares.add(new Par(12,42,"8",1737,1749));
	//	listaPares.add(new Par(12,48,"9",1907,1637));
		listaPares.add(new Par(19,24,"10",1508,1849));
		listaPares.add(new Par(19,30,"11",1534,1776));
		listaPares.add(new Par(19,36,"12",1595,1727));
		listaPares.add(new Par(19,42,"13",1686,1651));
	//	listaPares.add(new Par(19,48,"14",1793,1598));
		listaPares.add(new Par(26,24,"15",1376,1760));
		listaPares.add(new Par(26,30,"16",1451,1714));
		listaPares.add(new Par(26,36,"17",1506,1668));
		listaPares.add(new Par(26,42,"18",1603,1619));
	//	listaPares.add(new Par(26,48,"19",1685,1551));
		listaPares.add(new Par(33,24,"20",1285,1676));
		listaPares.add(new Par(33,30,"21",1329,1638));
		listaPares.add(new Par(33,36,"22",1402,1604));
		listaPares.add(new Par(33,42,"23",1525,1539));
	//	listaPares.add(new Par(33,48,"24",1597,1490));
		listaPares.add(new Par(40,24,"25",1191,1592));
		listaPares.add(new Par(40,30,"26",1268,1570));
		listaPares.add(new Par(40,36,"27",1373,1520));
		listaPares.add(new Par(40,42,"28",1470,1467));
	//	listaPares.add(new Par(40,48,"29",1531,1423));
		listaPares.add(new Par(47,24,"30",1096,1492));
		listaPares.add(new Par(47,30,"31",1189,1477));
		listaPares.add(new Par(47,36,"32",1284,1449));
		listaPares.add(new Par(47,42,"33",1385,1398));
	//	listaPares.add(new Par(47,48,"34",1470,1357));
		listaPares.add(new Par(54,24,"35",1021,1403));
		listaPares.add(new Par(54,30,"36",1131,1394));
		listaPares.add(new Par(54,36,"37",1224,1362));
		listaPares.add(new Par(54,42,"38",1328,1322));
	//	listaPares.add(new Par(54,48,"39",1421,1277));
		
		listaPares.add(new Par(61,24,"40",966,1318));
		listaPares.add(new Par(61,30,"41",1064,1303));
		listaPares.add(new Par(61,36,"42",1168,1279));
		listaPares.add(new Par(61,42,"43",1286,1232));
	//	listaPares.add(new Par(61,48,"44",1377,1191));
		listaPares.add(new Par(68,24,"45",911,1240));
		listaPares.add(new Par(68,30,"46",1022,1223));
		listaPares.add(new Par(68,36,"47",1124,1197));
		listaPares.add(new Par(68,42,"48",1238,1142));
	//	listaPares.add(new Par(68,48,"49",1348,1092));
	
		listaPares.add(new Par(75,24,"50",888,1154));
		listaPares.add(new Par(75,30,"51",993,1127));
		listaPares.add(new Par(75,36,"52",1109,1097));
		listaPares.add(new Par(75,42,"53",1214,1053));
	//	listaPares.add(new Par(75,48,"54",1331,981));

		listaCeldas = new ArrayList<Celda>();
		for (int i=0; i<48; i+=3){
			if (i<listaPares.size()-6)
				listaCeldas.add(new Celda(listaPares.get(0+i), listaPares.get(1+i), listaPares.get(6+i), listaPares.get(5+i)));
			
			if (i<listaPares.size()-7)
				listaCeldas.add(new Celda(listaPares.get(1+i), listaPares.get(2+i), listaPares.get(7+i), listaPares.get(6+i)));
			
			if (i<listaPares.size()-8)
			listaCeldas.add(new Celda(listaPares.get(2+i), listaPares.get(3+i), listaPares.get(8+i), listaPares.get(7+i)));
			
			if (i<listaPares.size()-9)
			listaCeldas.add(new Celda(listaPares.get(3+i), listaPares.get(4+i), listaPares.get(9+i), listaPares.get(8+i)));
		}
	}

	public int [] getValor(int x, int y){
		Par buscado = new Par(x, y, "FK");
		Par p1 = null;
		Par p2 = null;
		Par p3 = null;
		Par p4 = null;
		
		for (Celda c: listaCeldas){
			//System.out.println(c.perteneceA(buscado));
			if (c.perteneceA(buscado)){
				p1 = c.getP1();
				p2 = c.getP2();
				p3 = c.getP3();
				p4 = c.getP4();
				break;
			}
		}
	
		listaPuntosActuales.clear();
		listaPuntosActuales.add(p1);
		listaPuntosActuales.add(p2);
		listaPuntosActuales.add(p3);
		listaPuntosActuales.add(p4);
		
		if (p1==null || p2==null || p3==null || p4==null )
			return new int[] {0, 0};
		
		
		/**
		 * p1      p4
		 * 
		 * p2      p3
		 */
		
		int dx = (p3.getX() - p2.getX());
		int dy = (p2.getY() - p1.getY());
		
		double p1x = Math.abs(p3.getX() - x) / (dx*1.0);
		double p2x = Math.abs(p2.getX() - x) / (dx*1.0);

		double p1y = Math.abs(p2.getY() - y) / (dy*1.0);
		double p2y = Math.abs(p1.getY() - y) / (dy*1.0);

		double m1 = (getV1(p1) * p1x + getV1(p4) * p2x)*p1y + (getV1(p2) * p1x + getV1(p3) * p2x)*p2y;
		double m2 = (getV2(p1) * p1y + getV2(p2) * p2y)*p1x + (getV2(p4) * p1y + getV2(p3) * p2y)*p2x;

		//System.out.println(p1+"\t"+p2+"\t"+p3+"\t"+p4+"\t"+dx+"\t"+dy+"\t"+p1x+"\t"+p2x+"\t"+ m1+"\t"+m2);

		
		return new int[] {(int)m1, (int)m2};
	}
		
	public ArrayList<Par> getListaPuntosActuales() {
		return listaPuntosActuales;
	}

	private int getV1(Par p){
		return p.getMotor1();
	}

	private int getV2(Par p){
		return p.getMotor2();
	}

	public void imprime(){
	/*	for (int y=0; y<50; y++){
			for (int x=0; x<70; x++)
				System.out.print(motor1[x][y]+" ");
			System.out.println();
		}*/
	}
		
	public ArrayList<Par> getListaPares() {
		return listaPares;
	}

	public static void main(String[] args) {
		MatrizConversion mc = new MatrizConversion();
		mc.imprime();
	}
}
