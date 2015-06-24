package com.clillo.plotclock.simulacion;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class GeneraCombinaciones {

	public void generar(String ruta) throws IOException{
		long total=0;
		long cantidades[] = {0, 0, 0, 0, 0, 0, 0, 0};
		
		DataOutputStream out = null;
		
		int cuantosArchivos = 0;

		for (float origen1X = 20.0f; origen1X < 30.0; origen1X += 0.5){
			cantidades[0]++;
			cantidades[1]=0;
			for (float origen1Y = -30.0f; origen1Y > -35.0; origen1Y -= 0.5){
				cantidades[1]++;
				cantidades[2]=0;
				for (float origen2X = 40.0f; origen2X < 50.0; origen2X += 0.5){
					cantidades[2]++;
					cantidades[3]=0;
					for (float origen2Y = origen1Y; origen2Y >= origen1Y; origen2Y -= 0.5){
						cantidades[3]++;
						cantidades[4]=0;
						for (float l5 = 0.2f; l5 <= 10; l5 += 0.2){
							cantidades[4]++;
							cantidades[5]=0;
							for (float l3 = 50; l3 <= 60; l3 += 0.2){
								cantidades[5]++;
								cantidades[6]=0;
								for (float l1 = 30; l1 <= 40; l1 += 0.2){
									cantidades[6]++;
									cantidades[7]=0;
									for (float angulo3 = 0.1f; angulo3 <= 2.00; angulo3 += 0.05) {
										cantidades[7]++;
										if (total%10000000==0){
											if (out!=null)
												out.close();
											out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(ruta+cuantosArchivos+".txt")));
											System.out.println(cuantosArchivos);
											cuantosArchivos++;
										}
										out.writeFloat(l1);
										out.writeFloat(l3);
										out.writeFloat(l5);
										out.writeFloat(angulo3);
										out.writeFloat(origen1X);
										out.writeFloat(origen1Y);
										out.writeFloat(origen2X);
										out.writeFloat(origen2Y);

										total++;
									}
								}
							}
						}
					}
				}
			}
		}
		
		out.close();
		
		System.out.println(total);
		
		for (int i=0; i<cantidades.length; i++)
			System.out.println(i+"\t"+cantidades[i]);

	}
	
	public static void main(String[] args) throws IOException {
		String ruta = "G:\\cache\\";
		GeneraCombinaciones gc = new GeneraCombinaciones();
		gc.generar(ruta);
	}
}
