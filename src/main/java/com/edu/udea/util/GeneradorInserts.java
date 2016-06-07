package com.edu.udea.util;

public class GeneradorInserts {
	
	public static void main(String[] args) {
		System.out.println(
				insertsSillas(1330, 20, 10, 9, 20));
	}
	
	public static String insertsSillas (Integer inicio, Integer filas, Integer columnas, 
			Integer vuelo, Integer totalPrimeraClase) {
		
		String t = "";
		Integer cPrimeraClase = 0;
		Integer id = inicio;
		for (int i = 1; i <= columnas; i++){
			for (int j = 1; j <= filas; j++){
				id++;
				t += "(" + id + ", " + vuelo + ", NULL, ";
				if (cPrimeraClase < totalPrimeraClase) {
					t += "1, ";
					cPrimeraClase++;
				}
				else {
					t += "2, ";
				}
				t += j + ", " + i + "),";
			}
		}
		
		return t;
		
	}
	
}
