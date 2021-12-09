package interfazinventario;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import modelo.*;

public class consultarProducto extends JDialog implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public final static String ACEPTAR = "ACEPTAR";
	
	private InterfazInventario principal;
	
	private JTextField txtNombre;
	
	private JButton confirmacion;
	
	public final static String CONFIRMAR = "CONFIRMAR";
	
	public final static String CODIGO= "TXTNOMBRE";
	
	private String codigoProducto;
	
	private Producto productoEncontrado;
	
	private JPanel panelConsultar;
	
	private JLabel nNombre;
	
	private JTextField txtProducto;
	
	private JLabel nDesempeño;
	
	private JTextField txtDesempeño;
	
	private JTextField txtDatos;
	
	public consultarProducto(InterfazInventario pinterfaz) {
		 
		principal = pinterfaz;
		
				
		setTitle( "CONSULTAR LOTES" );
	    setSize( 700, 700 );
	    setDefaultCloseOperation( DISPOSE_ON_CLOSE );
	     
	    setLayout( new BorderLayout( ) );
	     
	        
	    panelConsultar = new JPanel( );

	    panelConsultar.setBorder( new CompoundBorder( new TitledBorder( "CONSULTAR INFORMACIÓN DE PRODUCTO" ), new EmptyBorder( 1, 1, 1, 1 ) ) );
	    panelConsultar.setLayout( new GridLayout( 8, 1, 1, 1 ) );
	    add(panelConsultar,BorderLayout.CENTER);
	    
	    JLabel nombreProducto = new JLabel("Ingrese el código del producto:");
	    panelConsultar.add(nombreProducto);
	     
	    txtNombre = new JTextField();
	    txtNombre.setSize(20, 5);
	    txtNombre.addActionListener(this);
	    panelConsultar.add(txtNombre);
	    
	    
	    confirmacion = new JButton("Confirmar");
	    confirmacion.setActionCommand( CONFIRMAR );
	    confirmacion.addActionListener( new ActionListener(){
	    	@Override
	    	public void actionPerformed(ActionEvent pEvento) {

	    		String comando = pEvento.getActionCommand( );
	    		
	    		 if( comando.equals( CONFIRMAR ) )
	    	        {
	    			 
	    			 if (txtNombre.getText() != null) {
	    				 
	    				 String codigo = txtNombre.getText(); 
	    				 codigoProducto = codigo;
	    				 productoEncontrado = principal.buscarProducto(codigoProducto);
	    				 
	    				 if (productoEncontrado == null) {
	    					 
	    					 JOptionPane.showMessageDialog(principal, "ERROR: El producto no fue encontrado");
	    					 
	    					
	    				 }else if (productoEncontrado != null) {
	    				
	    					 
	    					 ///Crea los labels de la respuesta
	    					 String nombre = productoEncontrado.getNombre();
	    					 String marca = productoEncontrado.getMarca();
	    					 Categoria categoria = productoEncontrado.getCategoria();
	    					 Boolean ventaPeso = productoEncontrado.isVentaPorPeso();
	    					 int precio = productoEncontrado.getPrecioDelProducto();
	    					 
	    					 String categoriaNom = categoria.getNombre();
	    					 String categoriaGon = categoria.getGondola();
	    					 
	    					 
	    					 Boolean ref = categoria.isRefrigeracion();
	    					 Boolean con = categoria.isCongelado();
	    					 Boolean fres = categoria.isFresco();
	    					 
	    					 txtProducto.setText("NOMBRE:"+nombre );
	    					 txtProducto.setEditable(false);
	    					 
	    					 txtDatos.setText("MARCA:"+ marca+" | PRECIO:"+precio+ " | CATEGORÍA:"+ categoriaNom+ " | GONDOLA:"+categoriaGon+" | VP:"
	    					 +ventaPeso+" | R:"+ref + " | C:"+con+" | F:"+fres);
	    					 txtDatos.setEditable(false);
	    					 
	    					 DesempenoFinanciero desempeño = productoEncontrado.getDesempenoFinanciero();
	    					 int ganancias = desempeño.getGananciaGenerada();
	    					 int perdidas = desempeño.getProductosExpirados();
	    					 txtDesempeño.setText("Las ganancias del producto fueron "+ganancias+", y los productos expirados:"+perdidas);
	    					 txtDesempeño.setEditable(false);
	  
	    					
	    				 }
	    				
	    				
	    			 }else if(txtNombre.getText() == null) {
	    				 JOptionPane.showMessageDialog(principal, "ERROR: Debe escribir un código");
	    			 }
	    	    }
	    		  
	    	}
	    });
	    confirmacion.setPreferredSize( new Dimension( 10, 15) );
	    confirmacion.setBackground(Color.CYAN);
	    panelConsultar.add(confirmacion,BorderLayout.AFTER_LAST_LINE);
	    
	    nNombre = new JLabel("El producto que se encontró:");
	    panelConsultar.add(nNombre,BorderLayout.AFTER_LAST_LINE);
	     
	    txtProducto = new JTextField();
	    txtProducto.setSize(10, 5);
	    panelConsultar.add(txtProducto,BorderLayout.AFTER_LAST_LINE);
	    
	    txtDatos= new JTextField();
	    txtDatos.setSize(10, 5);
	    panelConsultar.add(txtDatos,BorderLayout.AFTER_LAST_LINE);
	    
	    nDesempeño = new JLabel("El desempeño Financiero del producto:");
	    panelConsultar.add(nDesempeño,BorderLayout.AFTER_LAST_LINE);
	     
	    txtDesempeño = new JTextField();
	    txtDesempeño.setSize(10, 5);
	    panelConsultar.add(txtDesempeño,BorderLayout.AFTER_LAST_LINE);
	    
	    
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
		
	}

///Falta poner lo de la imagen
/// Falta poner, que pasa si no encuentra el producto
