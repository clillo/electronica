package com.clillo.plotclock;

public class Util {

	public static String getTiempo(long tiempoInicial){
		long tiempoActual = (System.currentTimeMillis() - tiempoInicial)/1000;
		StringBuilder sb = new StringBuilder();
		sb.append(formatea3(tiempoActual / 60));
		sb.append(':');
		sb.append(formatea2(tiempoActual % 60));
		return sb.toString();
	}
	
	public static String formatea3(long n){
		StringBuilder sb = new StringBuilder();
		if (n<100)
			sb.append('0');
		if (n<10)
			sb.append('0');

		sb.append(n);

		return sb.toString();
	}

	public static String formatea2(long n){
		StringBuilder sb = new StringBuilder();
		if (n<10)
			sb.append('0');

		sb.append(n);

		return sb.toString();
	}

	public static String redondea(double d){
		d = ((int)(d*10000.0))/10000.0;
		return d+"";
	}
	
	public static String miles(long l){
		StringBuilder sb = new StringBuilder();
		sb.append(l);
		String str = sb.reverse().toString();
		sb = new StringBuilder();
		for (int i=0; i<str.length(); i++){
			if (i!=0 && i%3==0)
				sb.append('.');
			sb.append(str.charAt(i));
		}
		return sb.reverse().toString();
	}
}
