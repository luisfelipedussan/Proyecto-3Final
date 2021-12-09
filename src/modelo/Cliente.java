package modelo;

import java.io.Serializable;

public class Cliente implements Serializable{
	private String nombre;
	private int edad;
	private String sexo;
	private String estadoCivil;
	private String situacionLaboral;
	private Punto puntos;
	
	public Cliente(String nombre, int edad, String sexo, String estadoCivil, String situacionLaboral, Punto puntos) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.situacionLaboral = situacionLaboral;
		this.puntos = puntos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String isSituacionLaboral() {
		return situacionLaboral;
	}

	public void setSituacionLaboral(String situacionLaboral) {
		this.situacionLaboral = situacionLaboral;
	}

	public Punto getPuntos() {
		return puntos;
	}

	public void setPuntos(Punto puntos) {
		this.puntos = puntos;
	}
	
	
	
	
}
