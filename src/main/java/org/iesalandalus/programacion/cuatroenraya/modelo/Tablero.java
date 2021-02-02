package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Tablero {

	/*********ATRIBUTOS*********/
	public static final int FILAS = 6;
	public static final int COLUMNAS = 7;
	public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
	
	private Casilla[][] casillas;
	
	
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
        for (int i = 0; i < COLUMNAS && tableroVacio; i++) {
        	tableroVacio = columnaVacia(i);
        }
        return tableroVacio;
	}
	
	
	/**
	 * Método columnaVacia:
	 * Devolverá true o false dependiendo de si la columna pasada como
	 * parámetro está vacía o no. 
	 */
	private boolean columnaVacia(int columna) {
		return !casillas[0][columna].estaOcupada();
	}
	
	
	/**
	 * Método estaLleno:
	 * Devolverá true o false dependiendo de que el tablero esté lleno o no.
	 */
	public boolean estaLleno() {
		
		boolean tableroLleno = true;
	    for (int i = 0; i < COLUMNAS && tableroLleno; i++) {
	    	tableroLleno = columnaLlena(i);
	    }
	    return tableroLleno;
	}
	
	
	/**
	 * Método columnaLlena:
	 * Devolverá true o false dependiendo de si la columna pasada como
	 * parámetro está llena o no. 
	 */
	private boolean columnaLlena(int columna) {
		return casillas[FILAS -1][columna].estaOcupada();
	}
	
	
	/**
	 * Método introducirFicha:
	 * Aceptará como parámetros la columna en la que queremos introducir la ficha y la ficha
	 * a introducir.
	 * Devolverá true o false dependiendo de si la jugada ha sido ganadora o no. 
	 */
	public boolean introducirFicha(int columna, Ficha ficha) throws OperationNotSupportedException {
		
		comprobarColumna(columna);
		comprobarFicha(ficha);
		
		if(columnaLlena(columna)) {
			throw new OperationNotSupportedException("ERROR: Columna llena.");
		} else {
			int fila = getPrimeraFilaVacia(columna);
			casillas[fila][columna].setFicha(ficha);
			return comprobarTirada(fila, columna);
		}
	}
	
	
	/**
	 * Método comprobarFicha:
	 * Lanzará una excepción si el parámetro no es correcto.
	 */
	private void comprobarFicha(Ficha ficha) {
		
		if (ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		} 
	}
	
	
	/**
	 * Método comprobarColumna:
	 * Lanzará una excepción si el parámetro no es correcto.
	 */
	private void comprobarColumna(int columna) {
		
		if (columna<0 || columna>COLUMNAS -1) {
			throw new IllegalArgumentException("ERROR: Columna incorrecta.");
		}
	}
	
	
	/**
	 * Método getPrimeraFilaVacia:
	 * Devolverá la primera fila vacía para la columna pasada por parámetro. 
	 */
	private int getPrimeraFilaVacia (int columna) {
		
		int primeraFilaVacia = 0;
	    for (int i = 0; i < FILAS; i++) {
	    	if (!(casillas[i][columna].estaOcupada())) {
	    		primeraFilaVacia = i;
	    		return primeraFilaVacia;
	        }
	    }
	    return primeraFilaVacia;
	}
	
	
	/**
	 * Método comprobarTirada:
	 * Aceptará como parámetros la fila, columna y la ficha de la última tirada
	 * para comprobar si es ganadora o no. 
	 */
	private boolean comprobarTirada(int fila, int columna) {
				
		if (comprobarHorizontal(fila, casillas[fila][columna].getFicha()) || 
				comprobarVertical(columna, casillas[fila][columna].getFicha()) ||
				comprobarDiagonalNE(fila, columna, casillas[fila][columna].getFicha()) ||
				comprobarDiagonalNO(fila, columna, casillas[fila][columna].getFicha())) {
			
			return true;
		} else {
			return false;	
		}
	}
	
	
	/**
	 * Método objetivoAlcanzado:
	 * Aceptará como parámetro el número de fichas consecutivas que llevamos.
	 * Devolverá true o false si el parámetro pasado es mayor o igual que
	 * la constante definida en esta clase (FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS).
	 */
	private boolean objetivoAlcanzado (int fichasConsecutivas) {
		
		boolean objetivoLogrado = false;
		if (fichasConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
			objetivoLogrado = true;
			return objetivoLogrado;
		}
		return objetivoLogrado;
	}
	
	
	/**
	 * Método comprobarHorizontal:
	 * Aceptará la fila y la ficha para la que queremos comprobar si hay cuatro
	 * del mismo color seguidas en horizontal.
	 */
	private boolean comprobarHorizontal (int fila, Ficha ficha) {
		
		boolean conseguido = false;
		int contadorFichas = 0;
		
		for (int i=0; i<COLUMNAS; i++) {
			if(casillas[fila][i].getFicha() == ficha) {
				contadorFichas++;
				if(objetivoAlcanzado(contadorFichas)) {
					conseguido = true;
					return conseguido;
				}
			} else {
				contadorFichas = 0;
			}
		}
		return conseguido;
	}
	
	
	/**
	 * Método comprobarVertical:
	 * Aceptará la columna y la ficha para la que queremos comprobar si hay cuatro
	 * del mismo color seguidas en vertical.
	 */
	private boolean comprobarVertical(int columna, Ficha ficha) {
		
		boolean conseguido = false;
		int contadorFichas = 0;
		
		for (int i=0; i<FILAS; i++) {
			if(casillas[i][columna].getFicha() == ficha) {
				contadorFichas++;
				if(objetivoAlcanzado(contadorFichas)) {
					conseguido = true;
					return conseguido;
				}
			} else {
				contadorFichas = 0;
			}
		}
		return conseguido;
	}
	
	
	/**
	 * Método comprobarDiagonalNE
	 * Recibirá como parámetros fila, columna y ficha.
	 * Con este método comprobaremos si hay cuatro fichas del mismo color consecutivas
	 * en la diagonal que va desde abajo a la izquierda hasta arriba a la derecha (pasando 
	 * por la casilla indicada por la fila y la columna).
	 */
	private boolean comprobarDiagonalNE(int fila, int columna, Ficha ficha) {
		
		boolean conseguido = false;
		int contadorFichas = 0;
		
		int desplazamientoInicial = menor(fila, columna);
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna - desplazamientoInicial;
		
		while (filaInicial < FILAS && columnaInicial < COLUMNAS) {
			if(casillas[filaInicial][columnaInicial].getFicha() == ficha) {
				contadorFichas++;
				if (objetivoAlcanzado(contadorFichas)) {
					conseguido=true;
					return conseguido;
				}	
			} else {
				contadorFichas = 0;
			}
			filaInicial++;
			columnaInicial++;
		}
		return conseguido;
	}
	

	/**
	 * Método comprobarDiagonalNO:
	 * Recibirá como parámetros fila, columna y ficha.
	 * Con este método comprobaremos si hay cuatro fichas del mismo color consecutivas
	 * en la diagonal que va desde abajo a la derecha hasta arriba a la izquierda (pasando 
	 * por la casilla indicada por la fila y la columna).
	 */
	private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {
		
		boolean conseguido = false;
		int contadorFichas = 0;
		
		int desplazamientoInicial = menor(fila, COLUMNAS-1-columna);
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna + desplazamientoInicial;
		
		while (filaInicial < FILAS && columnaInicial >= 0) {
			if (casillas[filaInicial][columnaInicial].getFicha() == ficha) {
				contadorFichas++;
				if (objetivoAlcanzado(contadorFichas)) {
					conseguido = true;
					return conseguido;
				}
			} else {
				contadorFichas = 0;
			}
			filaInicial++;
			columnaInicial--;
		}
		return conseguido;
	}
	
	
	/**
	 * Método menor:
	 * Recibirá dos enteros y devolverá el menor de ellos.
	 */
	private int menor(int entero1, int entero2) {
		return Math.min(entero1, entero2);
	}
	
	
	/**
	 * Método toString:
	 * Devolverá la representación del tablero.
	 */
	public String toString() {
		
		StringBuilder tablero = new StringBuilder();
		
		for(int i=FILAS-1; i>=0; i--) {
			tablero.append("|");
			
			for(int j=0; j<COLUMNAS; j++) {
				tablero.append(casillas[i][j]);
			}
			tablero.append("|\n");
		}
		tablero.append(" -------\n");
		
		return tablero.toString();
	}
	
}
