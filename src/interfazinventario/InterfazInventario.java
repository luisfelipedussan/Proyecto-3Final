package interfazinventario;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.swing.*;

import modelo.Inventario;
import modelo.Producto;
import Procesamiento.Loader;




public class InterfazInventario extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	///*********ATRIBUTOS***************

	
	private panelBotonesInventario panelBotones;
	
	public Inventario inventario;
	
	private buscarArchivo dAsignacion;
	
	private consultarProducto sAsignacion;
	
	private eliminarLotes eAsignacion;
	
	private PanelImagen panelImagen;
	
	
	private Map<String, Producto> mapaProductos;
	
	private ArrayList<Producto> productos;
	
	private Date fechaHoy;
	
	String encargado = "Luis y David";

	
	public InterfazInventario()
	{	
		setResizable( false );
		setTitle( "INVENTARIO" );
	        setSize( 600, 1000 );
	        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	        
	        setLayout( new BorderLayout( ) );
	        
	        panelBotones = new panelBotonesInventario( this );
	        add( panelBotones, BorderLayout.WEST);
	        
	        panelImagen = new PanelImagen( );
	        add( panelImagen, BorderLayout.EAST);
	        
	        
	}
	
	
	
	public void agregarLote() {
		dAsignacion = new buscarArchivo(this);
		dAsignacion.setVisible(true);
		
	}
	
	public void consultarlosLotes() {
		sAsignacion= new consultarProducto(this);
		sAsignacion.setVisible(true);
		
	}
	
	public void eliminarLotes() {
		eAsignacion = new eliminarLotes(this);
		eAsignacion.setVisible(true);
		
	}
	
	public void actualizarInterfaz() {
		
	
	        remove( sAsignacion );
	        sAsignacion = new consultarProducto(this);
	        setVisible(true);
	        validate( );
	    
	}
	
	public void ejecutarCargarNuevoLote(String fileParam) throws FileNotFoundException, IOException, ParseException
	{
		
	
		String direccion = fileParam;
		this.mapaProductos =Loader.leerLote(direccion, this.mapaProductos, this.fechaHoy);
		this.actualizarInventario(mapaProductos);
	}
	
	private void actualizarInventario( Map<String, Producto> mapProduc) {
		
		this.inventario = new Inventario(this.encargado,mapProduc,this.fechaHoy);
	}
	
	private void ejercutarDefaultLote() throws FileNotFoundException, IOException, ParseException
	{
		
		String dirmercado ="data/mercado.csv";
		this.productos = Loader.leerInfoProductos(dirmercado);
		this.mapaProductos = Loader.crearMapaProductos(productos);
		this.inventario= new Inventario(encargado,mapaProductos,fechaHoy);
		
	}
	
	 public Producto buscarProducto(String codigo) {
		 Producto productoEncontrado = inventario.getProducto(codigo);
		 if (productoEncontrado==null) {
			 JOptionPane.showMessageDialog(this, "ERROR: El producto fue encontrado");
		 }
		 return productoEncontrado;
	 }
	 
	 public void eliminarLotesInventario() {
		 
		 ///Hay que recorrer todo el mapa de productos y borrar los vencidos de cada uno
		this.inventario.eliminarLotesVencidosProducto(encargado);
	 }
	
	public static void main( String[] pArgs )
    {
        try
        {
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );

            InterfazInventario interfaz = new InterfazInventario( );
            interfaz.ejercutarDefaultLote();
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
