package modelo;
import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable{
	private ArrayList<Lote> lotes;
	private int precioDelProducto;
	private String nombre;
	private String marca;
	private String codigoDeBarras;
	private boolean ventaPorPeso;
	private DesempenoFinanciero desempenoFinanciero;
	private Categoria categoria;
	private String rutaImagen;
	
	public Producto(Lote lote, int precioDelProducto, String nombre, String marca, String codigoDeBarras,
			boolean ventaPorPeso, DesempenoFinanciero desempenoFinanciero, Categoria categoria, String rutaImagen) {
		this.lotes = new ArrayList<Lote>();
		this.precioDelProducto = precioDelProducto;
		this.nombre = nombre;
		this.marca = marca;
		this.codigoDeBarras = codigoDeBarras;
		this.ventaPorPeso = ventaPorPeso;
		this.desempenoFinanciero = desempenoFinanciero;
		this.categoria = categoria;
		this.rutaImagen = rutaImagen;
	}
//###############################################GETTERS AND SETTERS######################################################
	@Override
	public String toString() {
		return this.nombre + "-" + this.precioDelProducto;
		
	}
	public ArrayList<Lote> getLotes() {
		return lotes;
	}

	public int getPrecioDelProducto() {
		return precioDelProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public String getMarca() {
		return marca;
	}

	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public boolean isVentaPorPeso() {
		return ventaPorPeso;
	}

	public DesempenoFinanciero getDesempenoFinanciero() {
		return desempenoFinanciero;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	public void setPrecioDelProducto(int precio) {
		this.precioDelProducto = precio;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getRutaImagen()
	{
		return this.rutaImagen;
	}
	
	
}
