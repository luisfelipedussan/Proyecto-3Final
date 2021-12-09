package modelo;

import java.io.Serializable;

public class Punto implements Serializable{
	private int cantidadDePuntos;

	public Punto(int cantidadDePuntos) {
		this.cantidadDePuntos = cantidadDePuntos;
	}

	public int getCantidadDePuntos() {
		return cantidadDePuntos;
	}

	public void setCantidadDePuntos(int cantidadDePuntos) {
		this.cantidadDePuntos = cantidadDePuntos;
	}
	public void agregarPuntos(int puntos) {
		this.cantidadDePuntos += puntos;
	}
	
}
