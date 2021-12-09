package modelo;

import java.io.Serializable;

public class Categoria implements Serializable{
	private String nombre;
	private String gondola;
	private boolean refrigeracion;
	private boolean fresco;
	private boolean congelado;
	
	
	
	public Categoria(String nombre, String gondola, boolean refrigeracion, boolean fresco, boolean congelado) {
		this.nombre = nombre;
		this.gondola = gondola;
		this.refrigeracion = refrigeracion;
		this.fresco = fresco;
		this.congelado = congelado;
	}
	//###############################################GETTERS AND SETTERS######################################################
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGondola() {
		return gondola;
	}
	public void setGondola(String gondola) {
		this.gondola = gondola;
	}
	public boolean isRefrigeracion() {
		return refrigeracion;
	}
	public void setRefrigeracion(boolean refrigeracion) {
		this.refrigeracion = refrigeracion;
	}
	public boolean isFresco() {
		return fresco;
	}
	public void setFresco(boolean fresco) {
		this.fresco = fresco;
	}
	public boolean isCongelado() {
		return congelado;
	}
	public void setCongelado(boolean congelado) {
		this.congelado = congelado;
	}
	

}
