package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {


	/*******CONSTRUCTORES*******/
	/**
	 * Constructor:
	 * Evita que se puedan crear instancias de esta clase.
	 */
	private Consola () {
		
	}
	
	
	/**********MÉTODOS**************/
	/**
	 * Método leerNombre:
	 * Leerá el nombre del jugador mientras este sea válido.
	 */
	public static String leerNombre() {
		
		String nombre;
		
		do {
			System.out.println("Introduce el nombre del jugador: ");
			nombre = Entrada.cadena();
		} while (nombre.matches("") || nombre.matches("\s+") || nombre == null);
	
		return nombre;
	}
	
	
	/**
	 * Método elegirColorFichas:
	 * Leerá el color de una ficha mientras éste no sea válido. 
	 */
	public static Ficha elegirColorFichas() {
		
		int opcion;
		
		do {
			System.out.println("Elige el color de tus fichas: (0-AZUL, 1-VERDE): ");
			opcion = Entrada.entero();
		} while (opcion!=0 || opcion!=1);
		
		Ficha colorFichas;
		
		if (opcion == 0) {
			colorFichas = Ficha.AZUL;
		} else {
			colorFichas = Ficha.VERDE;
		}
		
		return colorFichas;
	}
	
}
