package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Casilla {

	/*********ATRIBUTOS*********/
	private Ficha ficha;

	
	/*******CONSTRUCTORES*******/
	/**
	 * Constructor por defecto, que inicialmente pondrá el atributo 
	 * ficha a null.
	 */
	public Casilla () {
		this.ficha = null;
	}
	
	
	/*********GETTERS Y SETTERS**********/
	
	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) throws OperationNotSupportedException {
		
		if (ficha==null) {
			throw new NullPointerException("ERROR: No se puede poner una ficha nula.");
		}
		
		if (estaOcupada()== true) {
			throw new OperationNotSupportedException("ERROR: Ya contengo una ficha.");
		} 
		
		this.ficha = ficha;			
	}	
	
	
	/********OTROS MÉTODOS********/
	/**
	 * Método estaOcupada
	 * Devolverá true o false, dependiendo si la casilla está ocupada o no por
	 * una ficha. 
	 */
	public boolean estaOcupada() {
		
		boolean casillaOcupada = false;
		
		if (this.ficha==Ficha.AZUL || this.ficha==Ficha.VERDE) {
			casillaOcupada = true;
		}
			
		return casillaOcupada;
	}

	
	/**
	 * Método toString
	 * Mostrará la inicial del valor de la ficha o un espacio en blanco si
	 * la casilla no está ocupada
	 */
	@Override
	public String toString() {
		
		if (ficha == null) {
			return " ";
		} else {
			return String.format("%.1s", ficha);
		}	
	}
	
	
}
