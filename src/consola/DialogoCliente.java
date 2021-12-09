package consola;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.*;
public class DialogoCliente extends JDialog implements ActionListener  {
	private GraficaPOS interfaz;
	
	
	private final static String AGREGAR = "Confirmar Cliente";
	
	
	private final static String CANCELAR = "Cancelar";
	
	
	private JTextField txtCliente;
	
	
	private JButton botonAgregar;
	
	
	private JButton botonCancelar;
	
	
	
	public DialogoCliente(GraficaPOS interfazp)
	{
		this.interfaz = interfazp;
		setLayout( new BorderLayout( ) );
        setSize( 320, 270 );
        setLocationRelativeTo( null );
        setTitle( "Seleccione el cliente" );
        
        JPanel panelCliente = new JPanel();
        panelCliente.setLayout(new GridLayout(3,1));
        
        txtCliente = new JTextField();
        panelCliente.add(txtCliente);
        
        botonAgregar = new JButton(AGREGAR);
        botonAgregar.setActionCommand(AGREGAR);
        botonAgregar.addActionListener(this);
        panelCliente.add(botonAgregar);
        
        botonCancelar = new JButton(CANCELAR);
        botonCancelar.setActionCommand(CANCELAR);
        botonCancelar.addActionListener(this);
        panelCliente.add(botonCancelar);
        
        add(panelCliente);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if(comando.equals(AGREGAR))
		{
			String nombreCliente = txtCliente.getText();
			interfaz.nuevaCompra(nombreCliente);
			this.dispose( );
		}
		else if(comando.equals(CANCELAR))
		{
			dispose();
		}
	}

}
