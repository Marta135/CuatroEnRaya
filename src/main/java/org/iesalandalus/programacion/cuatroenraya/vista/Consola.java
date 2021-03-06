package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
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
			System.out.print("Introduce el nombre del jugador: ");
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
			System.out.print("Elige el color de tus fichas: (0-AZUL, 1-VERDE): ");
			opcion = Entrada.entero();
		} while (opcion!=0 && opcion!=1);
		
		Ficha colorFichas;
		
		if (opcion == 0) {
			colorFichas = Ficha.AZUL;
		} else {
			colorFichas = Ficha.VERDE;
		}
		return colorFichas;
	}
	
	
	/**
	 * Método leerJugador:
	 * Leerá el nombre del primer jugador y el color de sus fichas.
	 */
	public static Jugador leerJugador() {
		
		System.out.println("Introduce los datos del PRIMER jugador ");
		String primerNombre = leerNombre();
		Ficha primeraFicha = elegirColorFichas();
		Jugador primerJugador = new Jugador(primerNombre, primeraFicha);
		
		return primerJugador;
	}
	

	/**
	 * Método leerJugador:
	 * Aceptará como parámetro una ficha.
	 * Leerá el nombre del segundo jugador y devolverá dicho jugador con el color de
	 * ficha pasado como parámetro.
	 */
	public static Jugador leerJugador (Ficha ficha) {
		
		System.out.println("Introduce los datos del SEGUNDO jugador ");
		String segundoNombre = leerNombre();
		Jugador segundoJugador = new Jugador(segundoNombre, ficha);
		
		return segundoJugador;
	}
	
	
	/**
	 * Método leerColumna:
	 * Recibirá como parámetro el jugador que debe introducir la columna para una tirada.
	 * Devolverá un entero representando la columna elegida.
	 * El método imprimirá el nombre del jugador y le indicará que elija la columna en 
	 * la que quiere introducir su ficha. 
	 */
	public static int leerColumna(Jugador jugador) {
		
		int columna;
		
		do {
			System.out.printf("%s, introduce la columna en la que deseas introducir la ficha (0 - 6): ", jugador.getNombre());
			columna = Entrada.entero();
		} while (columna<0 || columna>6);
		
		return columna;
	}

}
