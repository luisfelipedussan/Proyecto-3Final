package modelo;
import java.io.Serializable;
import java.util.ArrayList;
public class CompraActual implements Serializable{
	private ArrayList<Producto> productos;
	private Cliente cliente;
	public CompraActual( Cliente cliente) {
		super();
		this.productos = new ArrayList<Producto>();
		this.cliente = cliente;
	}
	public ArrayList<Producto> getProductos() {
		return productos;
	}
	public Cliente getCliente() {
		return cliente;
	}
	
	public void anadirProducto(Producto producto) {
		this.productos.add(producto);
	}
	
	public void anadirProductoPeso(Producto producto, int peso)
	{
		for (int i = 0 ; i < peso ; i++)
		{
			anadirProducto(producto);
		}
	}
	
	public String generarFactura() {
		String cadena = "";
		for (int i = 0 ; i < this.productos.size(); i++) {
			Producto producto = this.productos.get(i);
			cadena += producto.getNombre() + " : " + producto.getPrecioDelProducto() + "\n";
		}
		cadena += "Precio Total de la compra: " + this.getPrecioTotalCompra();
		return cadena;
	}
	public int getPrecioTotalCompra() {
		int sumatoria = 0;
		for (int i = 0; i<this.productos.size() ; i++) {
			Producto producto = this.productos.get(i);
			int precioProducto = producto.getPrecioDelProducto();
			sumatoria += precioProducto;
			
		}
		return sumatoria;
	}	
	
}
