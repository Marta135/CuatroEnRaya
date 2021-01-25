package org.iesalandalus.programacion.cuatroenraya;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.modelo.Tablero;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

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
	
	
	/**********MÉTODOS**************/
	/**
	 * Método jugar:
	 *Mientras no queden casillas libres, repetirá la acción de tirar, alternando
	 *los jugadores en cada iteración.
	 *Al terminar informará de quién ha ganado o que no hay más casillas libres.
	 */
	public void jugar() {
		
		int turno = 0;
		boolean hayGanador = false;
		Jugador jugadorQueJuega = jugadores[turno];
		
		while (!tablero.estaLleno() && !hayGanador) {
			jugadorQueJuega = jugadores[turno++ % NUMERO_JUGADORES];
			hayGanador = tirar(jugadorQueJuega);
		}
		
		if(hayGanador) {
			System.out.printf("ENHORABUENA, %s has ganado!!!", jugadorQueJuega.getNombre());
		} else {
			System.out.println("Habéis empatado ya que no quedan más casillas libres. ");
		}
	}
	
	
	/**
	 * Método tirar:
	 * Recibirá como parámetro el jugador que va a tirar y devolverá true o false
	 * indicando si esa jugada ha resultado ganadora o no.
	 */
	private boolean tirar(Jugador jugador) {
		
		boolean jugadaGanadora = false;
		boolean jugadaValida = false;
		
		do {
			try {
				jugadaGanadora = tablero.introducirFicha(Consola.leerColumna(jugador), jugador.getColorFichas());
				System.out.printf("%n%s%n", tablero);
				jugadaValida = true;
			} catch (OperationNotSupportedException e) {
				System.out.println(e.getMessage());
				jugadaValida = false;
			}
		} while (!jugadaValida);
		return jugadaGanadora;
	}
	
}
