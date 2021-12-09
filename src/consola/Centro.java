package consola;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import modelo.*;



public class Centro extends JPanel implements ActionListener{
	
	
	private GraficaPOS interfaz;
	
	
	private JList listaProductos; 
	
	
	private JScrollPane scrollProductos;
	
	
	private JButton botonFinalizar;
	
	
	private final static String FINALIZAR = "Finalizar Compra Actual";
	
	
	private JButton botonNuevaCompra;
	
	
	private final static String NUEVA = "Iniciar Nueva Compra";
	
	
	public Centro (GraficaPOS interfazp) 
	{
		this.interfaz = interfazp;
		setLayout(new GridLayout(3,1));
		setBorder(new TitledBorder("Compra actual"));
		
		listaProductos = new JList();
		listaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollProductos = new JScrollPane(listaProductos);
		scrollProductos.setPreferredSize(new Dimension(0,200));
		scrollProductos.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollProductos.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollProductos.setBorder( new TitledBorder( "Carrito: " ) );
		add(scrollProductos, BorderLayout.NORTH);
		
		botonFinalizar = new JButton(FINALIZAR);
		botonFinalizar.setActionCommand(FINALIZAR);
		botonFinalizar.addActionListener(this);
		add(botonFinalizar);
		
		botonNuevaCompra = new JButton(NUEVA);
		botonNuevaCompra.setActionCommand(NUEVA);
		botonNuevaCompra.addActionListener(this);
		add(botonNuevaCompra);
		
		
	}
	
	

	@Override

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		System.out.println(comando);
		if(comando.equals(FINALIZAR))
		{
			if(interfaz.getCompraActual().getCliente()!=null)
			{
				
				String factura = interfaz.finalizarCompra();
				DialogoFactura dialogoFactura = new DialogoFactura(this.interfaz, this.listaProductos);
				dialogoFactura.setVisible(true);
				/**
				listaProductos.removeAll();
				
				
				scrollProductos = new JScrollPane(listaProductos);
				scrollProductos.setPreferredSize(new Dimension(0,200));
				scrollProductos.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		        scrollProductos.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		        scrollProductos.setBorder( new TitledBorder( "Carrito: " ) );
		        this.add(scrollProductos, 0);
				
				*/
				repaint();
			}
			
		}
		if(comando.equals(NUEVA))
		{
			System.out.println("que onda que pex");
			DialogoCliente dialogoCliente = new DialogoCliente(this.interfaz);
			dialogoCliente.setVisible(true);
			/**
			listaProductos = new JList();
			listaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			scrollProductos = new JScrollPane(listaProductos);
			scrollProductos.setPreferredSize(new Dimension(0,200));
			scrollProductos.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
	        scrollProductos.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
	        scrollProductos.setBorder( new TitledBorder( "Carrito: " ) );
			this.revalidate();
			this.repaint();
			*/
			repaint();
		}
	}
	public void refrescarLista( ArrayList pNuevaLista )
    {
        listaProductos.setListData( pNuevaLista.toArray( ) );
        if( !pNuevaLista.isEmpty( ) )
        {
            listaProductos.setSelectedIndex( 0 );
        }
    }
}
