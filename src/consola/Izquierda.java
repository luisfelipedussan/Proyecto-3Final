package consola;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


import javax.swing.*;
import javax.swing.border.*;
import modelo.*;
public class Izquierda  extends JPanel implements ActionListener{
	
	private GraficaPOS interfaz;

	private ImageIcon imagen;
	
	private JLabel labelNombreProducto;
	
	private JLabel labelImagen;
	
	private final static String BARRAS = "Ingrese el codigo de barras del producto";
	
	private JLabel labelBarras;

	private JTextField txtBarras;
	
	private JButton botonConfirmarBusqueda;
	
	private final static String CONFIRMAR = "Confirmar busqueda";
	
	private JButton botonAgregarProducto;
	
	private final static String AGREGAR = "Agregar a la compra";
	
	
	

	public Izquierda(GraficaPOS interfazp)
	{
		this.interfaz = interfazp;
		setLayout(new GridLayout(6,1));
		setBorder( new TitledBorder(" Informacion del producto "));
		
		labelNombreProducto = new JLabel("NOMBRE DEL PRODUCTO#############");
		labelNombreProducto.setPreferredSize(new Dimension(50,400));
		add(labelNombreProducto);
		
		imagen = new ImageIcon("RUTA O MEJOR CREAR UNA FUNCION");
		labelImagen = new JLabel();
		labelImagen.setMinimumSize(new Dimension(400,400));
		
		labelImagen.setIcon(imagen);
		
		add(labelImagen);
		
		
		labelBarras = new JLabel(BARRAS);
		labelBarras.setPreferredSize(new Dimension(50,400));
		add(labelBarras);
		
		txtBarras = new JTextField();
		txtBarras.setPreferredSize(new Dimension(50,400));
		add(txtBarras);
		
		botonConfirmarBusqueda = new JButton(CONFIRMAR);
		botonConfirmarBusqueda.setPreferredSize(new Dimension(20,400));
		botonConfirmarBusqueda.setMaximumSize(new Dimension(20,400));
		botonConfirmarBusqueda.setActionCommand(CONFIRMAR);
		botonConfirmarBusqueda.addActionListener(this);
		add(botonConfirmarBusqueda);
		
		botonAgregarProducto = new JButton(AGREGAR);
		botonAgregarProducto.setMaximumSize(new Dimension(20,400));
		botonAgregarProducto.setActionCommand(AGREGAR);
		botonAgregarProducto.addActionListener(this);
		
		add(botonAgregarProducto);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String comando = e.getActionCommand();
		System.out.println(comando);
		System.out.println(comando);
		if(comando.equals(CONFIRMAR));
		{
			String codigo = txtBarras.getText();
			if(codigo != null)
			{
				Producto producto = interfaz.getProducto(codigo);
				labelNombreProducto.setText(producto.getNombre());
				labelImagen.setIcon(encontrarImagen(producto));
			}
		}
		if(comando.equals(AGREGAR))
		{
			String codigo = txtBarras.getText();
			if(codigo != null)
			{
				Producto producto = interfaz.getProducto(codigo);
				boolean isPorPeso = producto.isVentaPorPeso();
				if(isPorPeso) 
				{
					DialogoPeso dialogoPeso = new DialogoPeso(this.interfaz, producto);
					dialogoPeso.setVisible(true);
				}
				else
				{
					interfaz.agregarProductoNoPeso(codigo);
				}
			}
		}
		
		
		
		
	}
	public ImageIcon encontrarImagen(Producto producto)
	{
		String rutaImagen = producto.getRutaImagen();
		if (rutaImagen != null)
		{
			ImageIcon imagenProducto = new ImageIcon(rutaImagen);
			Image image = imagenProducto.getImage();
			Image newimg = image.getScaledInstance(400, 400,  java.awt.Image.SCALE_SMOOTH);
			imagenProducto = new ImageIcon(newimg);
			
			return imagenProducto;
		}
		else
		{
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
