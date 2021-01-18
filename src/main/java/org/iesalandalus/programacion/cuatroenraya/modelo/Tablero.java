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
	 * Método columnaVacia:
	 * Devolverá true o false dependiendo de si la columna pasada como
	 * parámetro está vacía o no. 
	 */
	private boolean columnaVacia(int columna) {
		
		boolean columnaVacia = false;
		
		if(casillas[0][columna].estaOcupada()) {
			columnaVacia = false;
		}
			
		return columnaVacia;
	}
	
	
	
}
