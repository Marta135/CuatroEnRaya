package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Casilla {

	/*********ATRIBUTOS*********/
	private Ficha ficha;

	
	/*******CONSTRUCTORES*******/
	/**
	 * Constructor por defecto, que inicialmente pondrá el atributo 
	 * ficha a null.
	 */
	public Casilla () {
		
		ficha = null;
	}
	
	
	/*********GETTERS Y SETTERS**********/
	
	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		
		if (ficha==null)
			throw new NullPointerException("ERROR: No se puede poner una ficha nula.");
		else
			this.ficha = ficha;
	}
	
}
