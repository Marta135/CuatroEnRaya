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
		
		for (int i=0; i<COLUMNAS; i++) {
			if (columnaVacia(i)==false){
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
			
			if(casillas[i][columna].estaOcupada()==true) {
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
			if (columnaLlena(i) == true) {
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
			
			if (casillas[i][columna].estaOcupada()==true) {
				columnaLlena = true;
			}
		}
		
		return columnaLlena;
	}
	
	
	/**
	 * Método introducirFicha:
	 * Aceptará como parámetros la columna en la que queremos introducir la ficha y la ficha
	 * a introducir.
	 * Devolverá true o false dependiendo de si la jugada ha sido ganadora o no. 
	 */
	public boolean introducirFicha(int columna, Ficha ficha) throws OperationNotSupportedException {
		
		boolean jugadaGanadora = false;
		
		comprobarColumna(columna);
		comprobarFicha(ficha);
		
		if(columnaLlena(columna)==true) {
			throw new OperationNotSupportedException("ERROR: Columna llena.");
		} else {
			
			int filaVacia = getPrimeraFilaVacia(columna);
			casillas[filaVacia][columna].setFicha(ficha);
			jugadaGanadora = comprobarTirada(filaVacia, columna);
		}
		return jugadaGanadora;
	}
	
	
	/**
	 * Método comprobarFicha:
	 * Lanzará una excepción si el parámetro no es correcto.
	 */
	private void comprobarFicha(Ficha ficha) {
		
		if (ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		} else if (ficha != Ficha.AZUL && ficha != Ficha.VERDE) {
			throw new IllegalArgumentException("ERROR: El color escogido es incorrecto.");
		}
	}
	
	
	/**
	 * Método comprobarColumna:
	 * Lanzará una excepción si el parámetro no es correcto.
	 */
	private void comprobarColumna(int columna) {
		
		if (columna<0 || columna>COLUMNAS) {
			throw new IllegalArgumentException("ERROR: La columna introducida es incorrecta.");
		}
	}
	
	
	/**
	 * Método getPrimeraFilaVacia:
	 * Devolverá la primera fila vacía para la columna pasada por parámetro. 
	 */
	private int getPrimeraFilaVacia (int columna) {
		
		int primeraFilaVacia = 0;
		
		for (int i=0; i<FILAS; i++) {
			if(casillas[i][columna].estaOcupada() == false) {
				primeraFilaVacia = i;
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
		
		boolean victoria = false;
		
		if (comprobarHorizontal(fila, casillas[fila][columna].getFicha()) || 
				comprobarVertical(columna, casillas[fila][columna].getFicha()) ||
				comprobarDiagonalNE(fila, columna, casillas[fila][columna].getFicha()) ||
				comprobarDiagonalNO(fila, columna, casillas[fila][columna].getFicha())) {
			
			victoria = true;
		}
		return victoria;	
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
				
				if(objetivoAlcanzado(contadorFichas) == true) {
					conseguido = true;
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
				
				if(objetivoAlcanzado(contadorFichas) == true) {
					conseguido = true;
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
		
		int desplazamientoInicial = menor(fila, columna);
		
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna - desplazamientoInicial;
		
		int contadorFichas = 0;
		boolean conseguido = false;
		
		for (int i=filaInicial; i<FILAS; i++) {
			for (int j=columnaInicial; i<COLUMNAS; j++) {
				
				if(casillas[i][j].getFicha() == ficha) {
					contadorFichas++;
					
					if(objetivoAlcanzado(contadorFichas)==true) {
						conseguido = true;
					}
				} else {
					contadorFichas = 0;
				}
			}
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
		
		int desplazamientoInicial = menor(fila, ((COLUMNAS-1)-columna));
		
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna + desplazamientoInicial;
		
		int contadorFichas = 0;
		boolean conseguido = false;
		
		for (int i=filaInicial; i<FILAS; i++) {
			for (int j=columnaInicial; j>0; j--) {
				
				if(casillas[i][j].getFicha() == ficha) {
					contadorFichas++;
					
					if(objetivoAlcanzado(contadorFichas)==true) {
						conseguido = true;
					}
				} else {
					contadorFichas = 0;
				}
			}
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
	
}
