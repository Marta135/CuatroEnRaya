package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Jugador {

	/*********ATRIBUTOS*********/
	private String nombre;
	private Ficha colorFichas;
	
	
	/*******CONSTRUCTORES*******/
	/**
	 * Constructor con parámetros (nombre y colorFichas).
	 * Hará uso de los métodos set para asignar valor a los atributos.
	 */
	public Jugador (String nombre, Ficha colorFichas) {
		setNombre(nombre);
		setColorFichas(colorFichas);
	}
		
	
	/*********GETTERS Y SETTERS**********/
	
	public Ficha getColorFichas() {
		return colorFichas;
	}
	
	private void setColorFichas(Ficha colorFichas) {
		
		if (colorFichas == null) {
			throw new NullPointerException("ERROR: El color de las fichas no puede ser nulo.");
		} else {
			this.colorFichas = colorFichas;
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	
	private void setNombre(String nombre) {
		
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		} else if (nombre.matches("") || nombre.matches("\s+")) {
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
		} else {
			this.nombre = nombre;
		}
	}


	/********OTROS MÉTODOS********/
	/**
	 * Método toString
	 * Imprimirá el nombre y color de fichas del jugador.
	 */
	@Override
	public String toString() {
		return String.format("%s (%s)", nombre, colorFichas);
	}

}


