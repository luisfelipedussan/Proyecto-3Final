package interfazinventario;

import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;


public class panelBotonesInventario  extends JPanel implements ActionListener{

	
	/// Constantes 
	
	/// *Opción para agregar lotes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String AGREGAR = "AGREGAR_LOTES";
	
	/**
	 * 
	 *Opción de consultar un producto 
	 */
	public final static String CONSULTAR = "CONSULTAR_PRODUCTO";

    /**
     * Opción Eliminar Lotes Vencidos.
     */
    public final static String ELIMINAR = "ELIMINAR_VENCIDOS";

    
    /**
     * BOTONES
     * 
     * Botón de agregar
     */
    private JButton bAgregar;

    /**
     * Botón de Consultar .
     */
    private JButton bConsultar;

    /**
     * Botón de Eliminar
     */
    private JButton bEliminar;
    
    private InterfazInventario interfaz;
    
    public panelBotonesInventario( InterfazInventario pinterfaz){
    	
    	interfaz = pinterfaz;
    
    	// Configura propiedades visuales
        setLayout( new GridLayout( 3, 1, 2, 10) );
        setBorder( new EmptyBorder( 5, 8, 8, 8 ) );
        
      
        
        bAgregar = new JButton( "Agregar Lotes" );
        bAgregar.setActionCommand( AGREGAR );
        bAgregar.addActionListener( this );
        bAgregar.setPreferredSize( new Dimension( 180, 10) );
        bAgregar.setBackground(Color.YELLOW); 
        add( bAgregar );
        
        bConsultar = new JButton( "Consultar Producto" );
        bConsultar.setActionCommand( CONSULTAR);
        bConsultar.addActionListener( this );
        bConsultar.setPreferredSize( new Dimension( 180, 10) );
        bConsultar.setBackground(Color.YELLOW);
        add( bConsultar );
        
        bEliminar = new JButton( "Eliminar Lotes Vencidos" );
        bEliminar.setActionCommand( ELIMINAR);
        bEliminar.addActionListener( this );
        bEliminar.setPreferredSize( new Dimension( 180, 10) );
        bEliminar.setBackground(Color.YELLOW);
        add( bEliminar );
        

    }

	@Override
	public void actionPerformed(ActionEvent pEvento) {
		 String comando = pEvento.getActionCommand( );
		 
		 if( comando.equals( AGREGAR ) )
	        {
	            interfaz.agregarLote( );
	        }else if (comando.equals(CONSULTAR)) {
	        	interfaz.consultarlosLotes();
	        }else if (comando.equals(ELIMINAR)){
	        	interfaz.eliminarLotes();
	        }
		
	}
	
}