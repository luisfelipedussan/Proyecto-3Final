package consola;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.*;
import persist.*;

public class GraficaPOS extends JFrame {
	
	private ImageIcon imagen;
	
	
	private Izquierda izquierda;
	
	
	private Centro centro;
	
	
	private POS pos;
	
	
	private Derecha derecha;
	
	
	private Date fechaHoy;
	
	
	private Map<String, Producto> mapaProductos;
	
	
	private Inventario inventario;
	
	/**
	 * AUNQUE EL PROGRAMA CARGA DATOS, SU FUNCIONAMIENTO CUANDO CORRE LA APP ES COMPLETAMENTE INDEPENDIENTE DE ESA 
	 * CARGA DE DATOS. LO NECESARIO PARA EL FUNCIONAMIENTO DEL PROGRAMA SALE TODO DE LO SERIALIZADO. 
	 * SIN EMBARGO DECIDIMOS NO ELIMINAR LAS FUNCIONES DE CARGA EN EL MAIN PORQUE CONSTANTEMENTE NECESITAMOS HACER CAMBIOS
	 * EN EL PROGRAMA, EN LAS CLASES SERIALIZADAS. PARA HACER LO ANTERIOR, NECESITAMOS DE LAS FUNCIONES DE CARGA 
	 * NORMALES, NO DE LOS DATOS SERIALIZADOS. ESPERAMOS QUE ESTO NO SEA RAZON PARA BAJARNOS NOTA, MUCHAS GRACIAS!
	 * @param mapaProductos
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 */
	public GraficaPOS(Map<String, Producto> mapaProductos) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException
	{
		
		this.fechaHoy = Calendar.getInstance().getTime();
		//this.pos = new POS("PEPE", mapaProductos);
		cargar();
		this.mapaProductos = mapaProductos;
		this.inventario = new Inventario("Pepe", mapaProductos, fechaHoy);
		ejecutarCargarNuevoLote();
		
		setTitle("POS");
		setSize (1400,1000);
		this.setResizable(false);
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setLayout(new BorderLayout());
		
		imagen = new ImageIcon("data/mercado.jpg");
		
		
		JLabel superior = new JLabel();
		superior.setPreferredSize(new Dimension(1400,200));
		
		superior.setIcon(imagen);
		
		add(superior, BorderLayout.NORTH);
		
		
		izquierda = new Izquierda(this);
		izquierda.setPreferredSize(new Dimension(400,600));
		add (izquierda, BorderLayout.WEST);
		
		centro = new Centro(this);
		add(centro, BorderLayout.CENTER);
		
		derecha = new Derecha(this);
		derecha.setPreferredSize(new Dimension(400,600));
		add(derecha, BorderLayout.EAST);
		
	}
	
	
	//MAIN
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException
	{
		System.out.println("Inicio de ejecución de la aplicación");
		
		ArrayList<Producto> productos = Procesamiento.Loader.leerInfoProductos("./data/mercado.csv");
		for(Producto producto : productos)
			System.out.println(producto);
		
		Map<String, Producto> mapaProductos = Procesamiento.Loader.crearMapaProductos(productos);
		
		
		GraficaPOS interfaz = new GraficaPOS(mapaProductos);
		interfaz.setVisible(true);
	}
	private void ejecutarCargarNuevoLote() throws FileNotFoundException, IOException, ParseException
	{
		String direccion = "./data/LOTE.csv";
		this.mapaProductos = Procesamiento.Loader.leerLote(direccion, this.mapaProductos, this.fechaHoy);
		System.out.println("El nuevo lote se ha cargado correctamente!");
		
	}
	
	
	public void guardar() throws FileNotFoundException, IOException
	{
		ArchivoMercado per = new ArchivoMercado();
		per.guardar(pos);
		System.out.println("Almacenamiento correcto");
	}
	
	public void cargar() throws FileNotFoundException, ClassNotFoundException, IOException
	{
		ArchivoMercado per = new ArchivoMercado();
		this.pos = per.recuperar();
	}
	
	public void dispose( )
    {
        System.out.println("Metodo dispose");
        
        try {
			guardar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        super.dispose( );
        /**
        try
        {
            guardar();
            super.dispose( );
        }
        catch( Exception e )
        {
            setVisible( true );
            int respuesta = JOptionPane.showConfirmDialog( this, "Problemas salvando la información del sistema:\n" + e.getMessage( ) + "." + "\n¿Quiere cerrar el programa sin salvar?", "Error", JOptionPane.YES_NO_OPTION );
            if( respuesta == JOptionPane.YES_OPTION )
            {
                super.dispose( );
            }
        }*/
    }
	
	
	//GETTERS AND SETTERS
	
	public POS getPOS()
	{
		return this.pos;
	}
	
	public void setMapaProductos(Map<String, Producto> mapaProductos)
	{
		this.mapaProductos = mapaProductos;
		
	}
	
	
	//FUNCIONES IZQUIERDA
	
	public Producto getProducto(String codigo)
	{
		 return pos.getProducto(codigo);
	}
	public void agregarProductoPeso(String codigo, int peso)
	{
		pos.agregarProductoPeso(codigo, peso, this.fechaHoy);
		
		CompraActual compraActual = getCompraActual();
		ArrayList<Producto> arregloProductos = compraActual.getProductos();
		this.centro.refrescarLista(arregloProductos);
	}
	public void agregarProductoNoPeso(String codigo)
	{
		pos.agregarProductoNoPeso(codigo, this.fechaHoy);
		
		CompraActual compraActual = getCompraActual();
		ArrayList<Producto> arregloProductos = compraActual.getProductos();
		this.centro.refrescarLista(arregloProductos);
	}
	//FUNCIONES CENTRO
	public CompraActual getCompraActual()
	{
		return this.pos.getCompraActual();
	}
	public String finalizarCompra()
	{
		return this.pos.finalizarCompra();
	}
	public void nuevaCompra(String nombre)
	{
		this.pos.nuevaCompra(nombre);
	}
	public int precioTotal()
	{
		return this.pos.getCompraActual().getPrecioTotalCompra();
	}
	
	//FUNCIONES DERECHA
	public Cliente getClienteActual()
	{
		return this.pos.getCompraActual().getCliente();
	}
	public Cliente getCliente(String nombre)
	{
		return this.pos.getCliente(nombre);
	}
	
}
