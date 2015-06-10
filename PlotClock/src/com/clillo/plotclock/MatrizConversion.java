package com.clillo.plotclock;

public class MatrizConversion {

	private int motor1[][] = new int[100][100];
	private int motor2[][] = new int[100][100];
	
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
	}
	
	public int [] getValor(int x, int y){
		// busca a la derecha:   
		int ix = x;
		//while (motor1[ix][]==0)
		//	ix--;
		
		return new int[] {motor1[x][y], motor2[x][y]};
	}
	
	public void imprime(){
		for (int y=0; y<50; y++){
			for (int x=0; x<70; x++)
				System.out.print(motor1[x][y]+" ");
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		MatrizConversion mc = new MatrizConversion();
		mc.imprime();
	}
}
