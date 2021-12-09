package Procesamiento;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import modelo.Producto;
import modelo.Categoria;
import modelo.DesempenoFinanciero;
import modelo.Lote;
import modelo.Inventario;


public class Loader {
	/**
	 * 
	 */
	public static ArrayList<Producto> leerInfoProductos(String rutaArchivo) throws FileNotFoundException, IOException{
		ArrayList<Producto> productos = new ArrayList<Producto>();
		BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
		String linea = br.readLine();
		linea = br.readLine();
		Map<String, Categoria> mapaCategorias = new HashMap<>();
		while(linea != null) {

			String[] partes = linea.split(",");
			boolean ventaPorPeso = comprobador(partes[4]);
			
			Categoria getCategoria = mapaCategorias.get(partes[8]);
			if(getCategoria != null) {
				
				if(ventaPorPeso) {
					
					Producto nuevoProducto = new Producto(null, Integer.parseInt(partes[1]), partes[0], partes[2], partes[3], true, new DesempenoFinanciero(0, 0), getCategoria, partes[13]);
					productos.add(nuevoProducto);
				}
				else {
					
					Producto nuevoProducto = new Producto(null, Integer.parseInt(partes[1]), partes[0], partes[2], partes[3], false, new DesempenoFinanciero(0, 0), getCategoria,partes[13]);
					productos.add(nuevoProducto);
				}
				
				
			}
			else{
				Categoria categoria = new Categoria(partes[8], partes[9], comprobador(partes[10]), comprobador(partes[11]), comprobador(partes[12]));
				mapaCategorias.put(partes[8], categoria);
				
				if(ventaPorPeso) {
					
					
					Producto nuevoProducto = new Producto(null, Integer.parseInt(partes[1]), partes[0], partes[2], partes[3], true, new DesempenoFinanciero(0, 0), categoria,partes[13]);
					productos.add(nuevoProducto);
				}
				else {
					Producto nuevoProducto = new Producto(null, Integer.parseInt(partes[1]), partes[0], partes[2], partes[3], false, new DesempenoFinanciero(0, 0), categoria,partes[13]);
					productos.add(nuevoProducto);
				}
			}	
			linea = br.readLine();
		}
		br.close();
		return productos;
	}
	
	public static Map<String, Producto> crearMapaProductos(ArrayList<Producto> productos)
	{
		Map<String, Producto> mapaProductos = new HashMap<>();
		for(int i=0 ; i< productos.size(); i++)
		{
			Producto producto = productos.get(i);
			mapaProductos.put(producto.getCodigoDeBarras(), producto);
			
		}
		return mapaProductos;
	}
	
	
	public static Map<String, Producto> leerLote(String rutaArchivo, Map<String, Producto> mapaProductos, Date fechaHoy) throws FileNotFoundException, IOException, ParseException
	{
		BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
		String linea = br.readLine();
		linea = br.readLine();
		while(linea != null)
		{
			String[] partes = linea.split(",");
			Producto producto = mapaProductos.get(partes[7]);
			Date fechaVencimiento = new SimpleDateFormat("dd/MM/yyyy").parse(partes[2]);
			
			Lote nuevoLote = new Lote(producto, fechaHoy, fechaVencimiento, Integer.parseInt(partes[4]), partes[5], 0, Integer.parseInt(partes[6]), Integer.parseInt(partes[3]));
			producto.getLotes().add(nuevoLote);
			producto.setPrecioDelProducto(nuevoLote.getPrecioProveedor());
			mapaProductos.replace(producto.getNombre(), producto);
			linea = br.readLine();
		}
		br.close();
		///System.out.println("El lote se cargo correctamente!");
		return mapaProductos;
	}
	
	
	
	
	
	public static boolean comprobador(String cadena) {
		if(cadena.equals("1")) {
			return true;
		}
		else {
			return false;
		}
			
	}
}
