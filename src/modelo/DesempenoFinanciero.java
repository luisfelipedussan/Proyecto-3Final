package modelo;

import java.io.Serializable;

public class DesempenoFinanciero implements Serializable {
	private int gananciaGenerada;
	private int productosExpirados;
	
	public DesempenoFinanciero (int gananciaGeneradaP, int productosExpiradosP) {
		this.gananciaGenerada = gananciaGeneradaP;
		this.productosExpirados = productosExpiradosP;
	}
	//###############################################GETTERS AND SETTERS######################################################
	public int getGananciaGenerada() {
		return gananciaGenerada;
	}

	public void setGananciaGenerada(int gananciaGenerada) {
		this.gananciaGenerada = gananciaGenerada;
	}

	public int getProductosExpirados() {
		return productosExpirados;
	}

	public void setProductosExpirados(int productosExpirados) {
		this.productosExpirados = productosExpirados;
	}
	//############################################### Otros Metodos ######################################################
	public void agregarGanancia(int ganancia) {
		this.gananciaGenerada += ganancia;
	}
	public void agregarExpirados(int expirados) {
		this.productosExpirados += expirados;
	}
}
