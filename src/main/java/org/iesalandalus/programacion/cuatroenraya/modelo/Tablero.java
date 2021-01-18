package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Tablero {

	/*********ATRIBUTOS*********/
	private Casilla[][] casillas;
	public static final int FILAS = 6;
	public static final int COLUMNAS = 7;
	public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
	
	
	/*******CONSTRUCTORES*******/
	/**
	 * Constructor por defecto.
	 * Inicializará el array bidimensional de casillas.
	 */
	public Tablero () {
		casillas = new Casilla [FILAS][COLUMNAS];
		
		for (int i = 0; i<FILAS; i++) {
			for (int j = 0; j<COLUMNAS; j++) {
				casillas[i][j] = new Casilla();
			}
		}
	}
	
	
	/**********MÉTODOS**************/
	
	/**
	 * Método estaVacio:
	 * Devolverá true o false dependiendo de que el tablero esté vacío o no.
	 */
	public boolean estaVacio() {
		
		boolean tableroVacio = true;
		
		for (int i=0; i<COLUMNAS; i++) {
		
			boolean columnaVacia = columnaVacia(i);
			if (columnaVacia == false) {
				tableroVacio = false;	
			}
		}
		
		return tableroVacio;
	}
	
	
	/**
	 * Método columnaVacia:
	 * Devolverá true o false dependiendo de si la columna pasada como
	 * parámetro está vacía o no. 
	 */
	private boolean columnaVacia(int columna) {
		
		boolean columnaVacia = true;
		
		for (int i=0; i<FILAS; i++) {
			
			if(casillas[i][columna].estaOcupada()) {
				columnaVacia = false;
			}	
		}
		
		return columnaVacia;
	}
	
	
	/**
	 * Método estaLleno:
	 * Devolverá true o false dependiendo de que el tablero esté lleno o no.
	 */
	public boolean estaLleno() {
		
		boolean tableroLleno = false;
		
		for (int i=0; i<COLUMNAS; i++) {
			
			boolean columnaLlena = columnaLlena(i);
			if (columnaLlena == true) {
				tableroLleno = true;	
			}
		}
		
		return tableroLleno;
	}
	
	
	/**
	 * Método columnaLlena:
	 * Devolverá true o false dependiendo de si la columna pasada como
	 * parámetro está llena o no. 
	 */
	private boolean columnaLlena(int columna) {
		
		boolean columnaLlena = false;
		
		for (int i=0; i<FILAS; i++) {
			
			if (casillas[i][columna].estaOcupada()) {
				columnaLlena = true;
			}
		}
		
		return columnaLlena;
	}
	
	
}
