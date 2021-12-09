package modelo;

import java.io.Serializable;
import java.util.Date;

public class Lote implements Serializable{
	private Producto producto;
	private Date fechaDeIngreso;
	private Date fechaDeVencimiento;
	private int precioProveedor;
	private String nombreUndDeMedida;
	private int precioPorUndDeMedida;
	private int cantidadDeUndsDeMedida;
	private int unidadesDisponibles;
	
	public Lote(Producto producto, Date fechaDeIngreso, Date fechaDeVencimiento, int precioProveedor, String nombreUndDeMedida,
			int precioPorUndDeMedida, int cantidadDeUndsDeMedida, int unidadesDisponibles) {
		this.producto = producto;
		this.fechaDeIngreso = fechaDeIngreso;
		this.fechaDeVencimiento = fechaDeVencimiento;
		this.precioProveedor = precioProveedor;
		this.nombreUndDeMedida = nombreUndDeMedida;
		this.precioPorUndDeMedida = precioPorUndDeMedida;
		this.cantidadDeUndsDeMedida = cantidadDeUndsDeMedida;
		this.unidadesDisponibles = unidadesDisponibles;
	}
	//###############################################GETTERS AND SETTERS######################################################
	

	


	public Producto getProducto() {
		return producto;
	}


	public Date getFechaDeIngreso() {
		return fechaDeIngreso;
	}


	public Date getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}


	public int getPrecioProveedor() {
		return precioProveedor;
	}


	public String getNombreUndDeMedida() {
		return nombreUndDeMedida;
	}


	public int getPrecioPorUndDeMedida() {
		return precioPorUndDeMedida;
	}


	public int getCantidadDeUndsDeMedida() {
		return cantidadDeUndsDeMedida;
	}


	public int getUnidadesDisponibles() {
		return unidadesDisponibles;
	}
	
	//###############################################OTROS METODOS######################################################
	public boolean isVencido(Date fechaHoy) {
		if(this.fechaDeVencimiento.before(fechaHoy)) {
			return true;
		}
		else return false;
	}
	
	
	

}
