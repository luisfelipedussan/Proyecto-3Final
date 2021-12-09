package interfazinventario;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.*;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import Procesamiento.Loader;




import modelo.*;
import java.io.File;

public class buscarArchivo extends JDialog implements ActionListener{

	
	
	//// CONSTANTES
	/**
	 * Opción para buscar el archivo y añadirlo al inventario
	 */
	public final static String BUSCAR = "BUSCAR_ARCHIVO";

    /**
     * Opción para agregar el archivo que ya está cargado
     */
    public final static String AGREGAR = "AGREGAR";
    
    
    private InterfazInventario principal;
    
    private String filePath;
  
    private JButton bBuscar;
     
    private JButton bAgregar;
    
    private String paramnombre;
    
    
    /// Crea el panel para agregar un lote 
   public buscarArchivo(InterfazInventario pPrincipal) {
	   
	   principal = pPrincipal;
	   
	   
	   setTitle( "AGREGAR LOTE AL INVENTARIO" );
       setSize( 500, 500);
       setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

       setLayout( new BorderLayout( ) );
       
       JPanel panelBotonesLote = new JPanel( );
       panelBotonesLote.setBorder( new CompoundBorder( new TitledBorder( "Archivo" ), new EmptyBorder( 3, 3, 3, 3 ) ) );
       panelBotonesLote.setLayout( new GridLayout( 5, 2, 1, 1 ) );

  
       bBuscar = new JButton("Buscar Archivo");
       bBuscar.setActionCommand( BUSCAR);
       bBuscar.addActionListener( this );
       bBuscar.setPreferredSize( new Dimension( 249,250) );
       bBuscar.setBackground(Color.ORANGE);
       add( bBuscar,BorderLayout.NORTH);
       
       bAgregar = new JButton( "Agregar Lotes");
       bAgregar.setActionCommand( AGREGAR );
       bAgregar.addActionListener( this );
       bAgregar.setPreferredSize( new Dimension( 249, 250) );
       bAgregar.setBackground(Color.CYAN);
       add( bAgregar,BorderLayout.SOUTH);
       
       setModal( true );
       setLocationRelativeTo( principal );
       setResizable( false );
       
       
      
       
   }
    
    
    
	@Override
	public void actionPerformed(ActionEvent pEvento) {
		String comando = pEvento.getActionCommand( );
		 
		 if( comando.equals( BUSCAR ) )
	        {
			 JFileChooser fc = new JFileChooser();
			 
			 int response = fc.showOpenDialog(null);
			 
			 if(response == JFileChooser.APPROVE_OPTION) {
				 File file = new File(fc.getSelectedFile().getAbsolutePath());
				 paramnombre = file.getName();
				 filePath = file.getName();
				JOptionPane.showMessageDialog(principal,"El lote "+paramnombre+" se cargó, "
						+ "seleccione la opción Agregar Lotes para añadirlo al inventario");
				
			 }
	            
	        }
		 else if (comando.equals(AGREGAR)) {
			 if (filePath ==null){
				JOptionPane.showMessageDialog(principal, "ERROR: No se ha seleccionado ningun archivo"); 
			 }else {
				try {
					filePath = "data/"+paramnombre;
					///JOptionPane.showMessageDialog(principal,filePath);
					principal.ejecutarCargarNuevoLote(filePath);
					JOptionPane.showMessageDialog(principal, "El lote: "+paramnombre+" se cargó correctamente al inventario!!!");
					filePath = null;
					paramnombre = null;
					
				} catch (IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			 }
			
			}
		 }
	}





