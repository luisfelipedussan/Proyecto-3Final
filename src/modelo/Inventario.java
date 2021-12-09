package modelo;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;



public class Inventario implements Serializable {
	private String encargado;
	private Map<String, Producto> mapaProductos;
	private Date fechaHoy;
	
	
	public Inventario(String encargado, Map<String, Producto> mapaProductos, Date fechaHoy) {
		super();
		this.encargado = encargado;
		this.mapaProductos = mapaProductos;

		this.fechaHoy = fechaHoy;
	}
	//############################################### GETTERS AND SETTERS ######################################################
	public String getEncargado() {
		return encargado;
	}
	public Map<String, Producto> getMapaProductos() {
		return mapaProductos;
	}

	public Date getFechaHoy() {
		return fechaHoy;
	}
	public void setMapaProductos(Map<String, Producto> mapaProductos)
	{
		this.mapaProductos = mapaProductos;
	}
	//############################################### Otros Metodos ######################################################
	
	public Producto getProducto(String codigo) {
		Producto producto = mapaProductos.get(codigo);
		
		
		System.out.println("El codigo de barras" + codigo + " Corresponde al producto " + producto.getNombre());
		return producto;
	}
	
	public void agregarLote(Lote lote, String codigo) {
		//Funcion obsoleta, al final se agregan lotes en el Loader, por como vienen.
		Producto producto = getProducto(codigo);
		producto.getLotes().add(lote);
		producto.setPrecioDelProducto(lote.getPrecioProveedor());
	}
	
	public void eliminarLotesVencidosProducto(String codigo) {
		Producto producto = getProducto(codigo);
		ArrayList<Lote> lotes = producto.getLotes();
		int vencidos = 0;
		for(int i = 0 ; i < lotes.size() ; i++) {
			Lote lote = lotes.get(i);
			if(lote.isVencido(fechaHoy)) {
				vencidos += lote.getUnidadesDisponibles();
				lotes.remove(i);
				
			}
		}
		producto.getDesempenoFinanciero().agregarExpirados(vencidos);
		
	}
	public String consultarDesempeno(String codigo) {
		Producto producto = getProducto(codigo);
		return "Productos Vendidos" + producto.getDesempenoFinanciero().getGananciaGenerada() + "Productos Vencidos" + producto.getDesempenoFinanciero().getProductosExpirados();
		//TODO SUJETO A CAMBIOS, CUANDO NOS DIGAN COMO CALCULAR DESEMPENO.
	}
	
	public String consultarUbicacion(String codigo) {
		Producto producto = getProducto(codigo);
		return producto.getCategoria().getGondola();
	}
	public String disponibilidadProducto(String codigo) {
		String cadena = "";
		Producto producto = getProducto(codigo);
		ArrayList<Lote> lotes = producto.getLotes();
		int contador = 0;
		for(int i = 0 ; i < lotes.size() ; i++) {
			cadena += "Lote: " + i + ", Unidades disponibles: " + lotes.get(i).getUnidadesDisponibles();
			contador += lotes.get(i).getUnidadesDisponibles();
		}
		cadena += "Unidades Totales: " + contador;
		return cadena;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
