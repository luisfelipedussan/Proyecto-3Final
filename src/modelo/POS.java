package modelo;
import java.util.HashMap;
import java.util.Map;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class POS implements Serializable{
	private String cajero;
	private Map<String, Producto> mapaProductos; 
	private Map<String, Cliente> mapaClientes;
	private CompraActual compraActual;
	
	public POS(String cajero, Map<String, Producto> mapaProductos) {
		super();
		this.cajero = cajero;
		this.mapaProductos = mapaProductos;
		this.mapaClientes = new HashMap<>();
		this.compraActual = null;
	}
	
	
	//############################################### PERSISTENCIA ######################################################
	
	
	
	
	/**
     * Guarda el estado del sistema en un archivo serializado. <br>
     * @param pRuta Ruta del archivo donde se guarda el estado del sistema. pRuta != null && pRuta != "".
     * @throws PersistenciaException Se lanza una excepción si hay algún error guardando los datos del archivo.
     */
    public void guardar( String pRuta ) throws PersistenciaException
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream (pRuta));

            oos.writeObject( this );
            oos.close( );

        }
        catch (Exception e)
        {
            throw new PersistenciaException ("Error al guardar:" + e.getMessage( ));
        }
    }
	
	
    /**
     * Carga el estado del sistema de un archivo serializado. <br>
     * <b>post: </b> Se cargó el estado del sistema. <br>
     * @param pArchivo Archivo con la información del sistema. pArchivo != null.
     * @throws PersistenciaException Se lanza una excepción si hay algún error cargando los datos del archivo.
     */
    public void cargar( File pArchivo ) throws PersistenciaException    
    {
        File archivo = pArchivo;

        {
            try
            {
                ObjectInputStream ois = new ObjectInputStream (new FileInputStream (archivo));  
                //this = (POS) ois.readObject( );
                ois.close( );
            }
            catch (Exception e)
            {   
                throw new PersistenciaException ("Error al cargar el archivo:" + e.getMessage( ) );
            }
        }
    }
	
	//############################################### GETTERS AND SETTERS ######################################################
	public String getCajero() {
		return cajero;
	}

	public Map<String, Producto> getMapaProductos() {
		return mapaProductos;
	}

	public Map<String, Cliente> getMapaClientes() {
		return mapaClientes;
	}

	public CompraActual getCompraActual() {
		return compraActual;
	}
	
	//############################################### OTROS METODOS ######################################################
	
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
	public Producto getProducto(String codigo) {
		Producto producto = mapaProductos.get(codigo);
		System.out.println("El codigo de barras" + codigo + " Corresponde al producto " + producto.getNombre());
		return producto;
	}
	public void agregarProducto(String codigo) {
		//AGREGA PRODUCTO A LA COMPRA ACTUAL
		/**  DEPRECATED
		Producto producto = getProducto(codigo);
		if(producto != null) {
			
			
			
			if(producto.isVentaPorPeso())
			{
				System.out.println("El producto que usted quiere comprar es de venta por peso.");
				int peso = Integer.parseInt(input("Que peso de este producto desea comprar?, en unidades de Kg, numeros enteros"));
				ArrayList<Lote> lotes = producto.getLotes();
				for (int i = 0 ; i < lotes.size() ; i++) {
					Lote lote = lotes.get(i);
					if(lote.getUnidadesDisponibles() > peso && (lote.isVencido(fechaHoy) == false)) {
						System.out.println("sisi");
						this.compraActual.anadirProductoPeso(producto, peso);
						producto.getDesempenoFinanciero().agregarGanancia(peso);
						break;
			}
				}
				}
			
			else
			{
				ArrayList<Lote> lotes = producto.getLotes();
				for (int i = 0 ; i < lotes.size() ; i++) {
					Lote lote = lotes.get(i);
					if(lote.getUnidadesDisponibles() > 0 && (lote.isVencido(fechaHoy) == false)) {
						this.compraActual.anadirProducto(producto);
						producto.getDesempenoFinanciero().agregarGanancia(1);
						break;
			}
			
				}
			}
		}*/
	}
	
	public void agregarProductoPeso(String codigo, int peso, Date fecha)
	{
		Producto producto = getProducto(codigo);
		if(producto!= null) 
		{
			ArrayList<Lote> lotes = producto.getLotes();
			for (int i = 0 ; i < lotes.size() ; i++) 
			{
				Lote lote = lotes.get(i);
				if(lote.getUnidadesDisponibles() > peso && (lote.isVencido(fecha) == false))
				{
					System.out.println("sisi");
					this.compraActual.anadirProductoPeso(producto, peso);
					producto.getDesempenoFinanciero().agregarGanancia(peso);
					break;
				}
			}
			
		}
	}
	
	public void agregarProductoNoPeso(String codigo, Date fecha)
	{
		Producto producto = getProducto(codigo);
		if(producto!= null) 
		{
			ArrayList<Lote> lotes = producto.getLotes();
			for (int i = 0 ; i < lotes.size() ; i++) {
				Lote lote = lotes.get(i);
				if(lote.getUnidadesDisponibles() > 0 && (lote.isVencido(fecha) == false)) {
					this.compraActual.anadirProducto(producto);
					producto.getDesempenoFinanciero().agregarGanancia(1);
					break;
					}
				
	}
		}
	}
	
	
	public Cliente getCliente(String nombre) {
		Cliente cliente = this.mapaClientes.get(nombre);
		return cliente;
	}
	
	public void addCliente(String nombre, int edad, String sexo, String estadoCivil, String situacionLaboral) {
		Punto puntos = new Punto(0);
		Cliente cliente = new Cliente(nombre, edad, sexo, estadoCivil, situacionLaboral, puntos);
		this.mapaClientes.put(nombre, cliente);
	}
	
	public String finalizarCompra() {
		String factura = this.compraActual.generarFactura();
		Cliente cliente = this.compraActual.getCliente();
		Punto puntosCliente = cliente.getPuntos();
		puntosCliente.agregarPuntos(this.compraActual.getPrecioTotalCompra()/1000);
		
		return factura;
	}

	public void nuevaCompra(String nombreCliente) {
		Cliente cliente = getCliente(nombreCliente);
		if(cliente == null) {
			System.out.println("ese cliente no existe, reinicie la aplicacion.");
		}
		this.compraActual = new CompraActual(cliente);
	}
	public String puntosCliente(String nombre) {
		Cliente cliente = getCliente(nombre);
		return "El cliente " + nombre + "tiene " + cliente.getPuntos().getCantidadDePuntos() + " disponibles";
	}
	
	
	
	
}
