package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.Serializable;

import Procesamiento.Loader;
import modelo.Inventario;
import modelo.Producto;
import persist.ArchivoMercado;
import modelo.POS;
import modelo.Lote;
import modelo.Cliente;



public class Consola implements Serializable{
	
	private Map<String, Producto> mapaProductos;
	private Inventario inventario;
	private POS pos;
	private Date fechaHoy;
	
	
	//########################################################## DEL OBJETO CONSOLA ###########################################
	
	public Consola(Map<String, Producto> mapaProductos)
	{
		this.mapaProductos = mapaProductos;
		this.fechaHoy = Calendar.getInstance().getTime();
		this.inventario = new Inventario("Pepe", mapaProductos, fechaHoy);
		this.pos = new POS("Ana", mapaProductos);
	}
	
	public void setMapaProductos(Map<String, Producto> mapaProductos)
	{
		this.mapaProductos = mapaProductos;
		
	}
	/**
	
	public static void guardar(Consola consola) throws FileNotFoundException, IOException
	{
		ArchivoMercado per = new ArchivoMercado();
		per.guardar(pos);
		System.out.println("Almacenamiento de la informacion del mercado hecho correctamente");
	}
	
	*/
	
	
	
	
	
	//########################################################## DE LA EJECUCION DE METODOS INVENTARIO ###########################################
	
	private static ArrayList<Producto> ejecutarCargarProductos(String rutaArchivo){
		ArrayList<Producto> productos = new ArrayList<Producto>();
		try {
			productos = Loader.leerInfoProductos(rutaArchivo);
			System.out.println("OK Se cargÛ el archivo " + rutaArchivo + " con informaciÛn de los Productos");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo " + rutaArchivo + " no se encontrÛ.");
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo " + rutaArchivo);
			System.out.println(e.getMessage());
		}
		return productos;
	}
	
	private void ejecutarCargarNuevoLote() throws FileNotFoundException, IOException, ParseException
	{
		String direccion = "./data/LOTE.csv";
		this.mapaProductos = Procesamiento.Loader.leerLote(direccion, this.mapaProductos, this.fechaHoy);
		System.out.println("El nuevo lote se ha cargado correctamente!");
		
	}
	private void ejecutarEliminarLotesVencidos()
	{
		String codigo = input("Ingrese el codigo de barras del producto");
		inventario.eliminarLotesVencidosProducto(codigo);
	}
	
	private void consultarDesempeno()
	{
		String codigo = input("Ingrese el codigo de barras del producto");
		String respuesta =inventario.consultarDesempeno(codigo);
		System.out.println(respuesta);
	}
	private void consultarUbicacion()
	{
		String codigo = input("Ingrese el codigo de barras del producto");
		String respuesta = inventario.consultarUbicacion(codigo);
		System.out.println(respuesta);
	}
	private void consultarDisponibilidad()
	{
		String codigo = input("Ingrese el codigo de barras del producto");
		String respuesta = inventario.disponibilidadProducto(codigo);
		System.out.println(respuesta);
	}
	//########################################################## DE LA EJECUCION DE METODOS POS ###########################################
	private void registrarCliente()
	{
		String cliente = input("Ingrese el nombre del cliente a registrar");
		
		pos.addCliente(cliente, 20, "M", "casado", "trabaja");
	}
	
	private void iniciarNuevaCompra() {
		String cliente = input("Ingrese el nombre del cliente registrado");
		pos.nuevaCompra(cliente);
		
	}
	private void agregarProductoCompra()
	{
		String codigo = input("Ingrese el codigo de barras del producto");
		pos.agregarProducto(codigo);
	}
	private void finalizarCompra()
	{
		
		String factura = pos.finalizarCompra();
		System.out.println(factura);
	}
	private void consultarPuntos()
	{
		String cliente = input("Ingrese el nombre del cliente registrado");
		String puntos = pos.puntosCliente( cliente);
		System.out.println(puntos);
	}
	
	
	
	//#######################################FUNCIONAMIENTO DE LA CONSOLA#########################################
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public void elegirAplicacion() {
		System.out.println("1. Aplicacion Manejo de Inventario");
		System.out.println("2. Aplicacion POS");
		
	}
	public void opcionesInventario() {
		System.out.println("1. Cargar nuevo Lote");
		System.out.println("2. Eliminar lotes vencidos de un producto");
		System.out.println("3. Consultar desempeno financiero de un producto");
		System.out.println("4. Consultar ubicacion/Gondola de un producto");
		System.out.println("5. Consultar disponibilidad de un producto");
		System.out.println("6. Finalizar aplicacion Inventario");
	}
	
	
	
	
	public void opcionesPOS() {
		System.out.println("Para multiples compras continuas se recomienda primero finalizar compra actual y luego Iniciar una nueva compra");
		System.out.println("0. Registrar cliente (necesario para crear una nueva compra)");
		System.out.println("1. Iniciar una nueva Compra");
		System.out.println("2. Agregar un Producto a la compra actual");
		System.out.println("3. Finalizar la compra actual");
		System.out.println("4. Consultar Puntos de un cliente");
		System.out.println("5. Finalizar aplicacion Inventario");
		
	}
	public void menuInventario() throws FileNotFoundException, IOException, ParseException {
		opcionesInventario();
		System.out.println("BIENVENIDO A LA APLICACION DE MANEJO DE INVENTARIO.");
		boolean continuar = true;
		while(continuar)
		{
			try
			{
				
				int opcion_seleccionada = Integer.parseInt(input("Para continuar, por favor seleccione una opci√≥n"));
				if(opcion_seleccionada ==1)
					ejecutarCargarNuevoLote();
				else if (opcion_seleccionada ==2)
					this.ejecutarEliminarLotesVencidos();
				else if (opcion_seleccionada ==3 )
					consultarDesempeno();
				else if (opcion_seleccionada ==4)
					consultarUbicacion();
				else if(opcion_seleccionada ==5)
					consultarDisponibilidad();
				else if (opcion_seleccionada == 6)
				{
					
					System.out.println("Saliendo de la aplicaci√≥n ...");
					continuar = false;
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los n√∫meros de las opciones.");
			}
		}
		
	}
	public void menuPOS()
	{
		opcionesPOS();
		System.out.println("BIENVENIDO A LA APLICACION DE MANEJO DE INVENTARIO.");
		boolean continuar = true;
		while(continuar)
		{
			try
			{
				
				int opcion_seleccionada = Integer.parseInt(input("Para continuar, por favor seleccione una opci√≥n"));
				if(opcion_seleccionada ==0 )
				{
					System.out.println("Antes de iniciar, registre al cliente si no esta registrado");
					registrarCliente();
					
				}
					
				if(opcion_seleccionada ==1)
					iniciarNuevaCompra();
				else if (opcion_seleccionada ==2)
					agregarProductoCompra();
				else if (opcion_seleccionada ==3 )
					finalizarCompra();
				else if (opcion_seleccionada ==4)
					consultarPuntos();
				else if(opcion_seleccionada ==5)
				{
					System.out.println("Saliendo de la aplicaci√≥n ...");
					continuar = false;
				}
				
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los n√∫meros de las opciones.");
			}
		}
	}
	
	
	
	public void ejecutarApp() throws FileNotFoundException, IOException, ParseException {
		boolean continuar = true;
		while(continuar) {
			try {
				elegirAplicacion();
				int opcionSeleccionada = Integer.parseInt(input("Seleccione una opcion"));
				if(opcionSeleccionada == 1) {
					menuInventario();
				}
				else if (opcionSeleccionada == 2) {
					menuPOS();
				}
				}
			
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los n√∫meros de las opciones.");
			}
		}
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		System.out.println("Inicio de ejecuciÛn de la aplicaciÛn");
		
		
		
		ArrayList<Producto> productos = Procesamiento.Loader.leerInfoProductos("./data/mercado.csv");
		for(Producto producto : productos)
			System.out.println(producto);
		
		Map<String, Producto> mapaProductos = Procesamiento.Loader.crearMapaProductos(productos);
		
		
		Consola consola = new Consola(mapaProductos);
		
		System.out.println(consola.inventario.getMapaProductos());
		
		consola.ejecutarApp();
	}

}
