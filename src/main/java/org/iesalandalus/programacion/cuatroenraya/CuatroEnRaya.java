package org.iesalandalus.programacion.cuatroenraya;

import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.modelo.Tablero;

public class CuatroEnRaya {

	/*********ATRIBUTOS*********/
	private static final int NUMERO_JUGADORES = 2;
	
	private Jugador[] jugadores;
	private Tablero tablero;
	
	
	/*******CONSTRUCTORES*******/
	/**
	 * Constructor con parámetros:
	 * Pasaremos dos instancias del tipo Jugador, representando a cada uno de
	 * los jugadores.
	 */
	public CuatroEnRaya(Jugador jugador1, Jugador jugador2) {
		
		if (jugador1 == null || jugador2 == null) {
			throw new NullPointerException("ERROR: el jugador no puede ser nulo. ");
		}
		
		jugadores = new Jugador[NUMERO_JUGADORES];
		jugadores[0] = jugador1;
		jugadores[1] = jugador2;
		tablero = new Tablero();
	}
	
	
}
