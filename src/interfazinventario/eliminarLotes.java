package interfazinventario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.*;

public class eliminarLotes extends JDialog implements ActionListener{

	private JPanel panelEliminar;
	
	private JButton Eliminar;
	
	private InterfazInventario interfaz;
	
	public final static String ELIMINAR = "ELIMINAR";
	
	public eliminarLotes(InterfazInventario pinterfaz) {
		
		interfaz= pinterfaz;
		setTitle( "ELIMINAR LOTES" );
	    setSize( 300, 300 );
	    setDefaultCloseOperation( DISPOSE_ON_CLOSE );
	     
	    setLayout( new BorderLayout( ) );
	     
	        
	    panelEliminar = new JPanel( );

	    panelEliminar.setBorder( new CompoundBorder( new TitledBorder( "ELIMINAR LOTES VENCIDOS" ), new EmptyBorder( 1, 1, 1, 1 ) ) );
	    panelEliminar.setLayout( new GridLayout( 2, 1, 1, 1 ) );
	    add(panelEliminar,BorderLayout.CENTER);
		
	    JLabel nombreProducto = new JLabel("Presione el botón para Eliminar los Lotes vencidos:");
	    panelEliminar.add(nombreProducto);
	    
	    Eliminar = new JButton("ELIMINAR LOTES");
	    Eliminar.setActionCommand( ELIMINAR );
	    Eliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent pEvento) {
				// TODO Auto-generated method stub
				
				String comando = pEvento.getActionCommand( );
				
				if (comando.equals(ELIMINAR)) {
					
					interfaz.eliminarLotesInventario();
					JOptionPane.showMessageDialog(interfaz, "Los Lotes vencidos fueron eliminados correctamente");
				}
			}
	    	
	    });
	    
	    Eliminar.setPreferredSize( new Dimension( 10, 15) );
	    Eliminar.setBackground(Color.RED);
	    panelEliminar.add(Eliminar,BorderLayout.AFTER_LAST_LINE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
