package consola;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

public class DialogoFactura extends JDialog {
	
	
	private GraficaPOS interfaz;
	
	
	private JList listaProductos; 
	
	
	private JScrollPane scrollProductos;
	
	
	private String precioTotal;
	
	
	private JLabel labelPrecioTotal;
	
	
	private JLabel labelPuntos;
	
	public DialogoFactura(GraficaPOS interfazp, JList listaProductosp)
	{
		this.interfaz = interfazp;
		this.precioTotal = Integer.toString( interfaz.precioTotal());
		System.out.println(precioTotal);
		
		setLayout( new BorderLayout( ) );
		setSize( 320, 570 );
        setLocationRelativeTo( null );
        setTitle( "Factura de la compra" );
        
        
        JPanel panel = new JPanel();
        panel.setLayout( new GridLayout( 3, 1 ) );
        
        listaProductos = listaProductosp;
		listaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollProductos = new JScrollPane(listaProductos);
		scrollProductos.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollProductos.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollProductos.setBorder( new TitledBorder( "Factura: " ) );
        panel.add(scrollProductos);
		
		
		labelPrecioTotal = new JLabel("Precio Total:" + this.precioTotal);
		panel.add(labelPrecioTotal);
		
		int puntosAcumulados = Integer.parseInt(precioTotal)/1000;
		String strPuntos = Integer.toString(puntosAcumulados);
		labelPuntos = new JLabel("Puntos acumulados:" + strPuntos);
		panel.add(labelPuntos);
		
		add(panel);
		

	}

}
