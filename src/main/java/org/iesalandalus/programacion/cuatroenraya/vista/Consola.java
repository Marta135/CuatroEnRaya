package org.iesalandalus.programacion.cuatroenraya.vista;

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
	
	
	
	
}
