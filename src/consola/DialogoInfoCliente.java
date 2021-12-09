package consola;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.*;

public class DialogoInfoCliente extends JDialog implements ActionListener  {
	
	
	private GraficaPOS interfaz;
	
	
	private Cliente cliente;

	public DialogoInfoCliente(GraficaPOS interfazp, Cliente clientep)
	{
		this.interfaz = interfazp;
		this.cliente = clientep;
		
		setLayout( new BorderLayout( ) );
        setSize( 320, 270 );
        setLocationRelativeTo( null );
        setTitle( "Informacion del cliente" );
        
        JPanel panelCliente = new JPanel();
        panelCliente.setLayout(new GridLayout(3,3));
        
        panelCliente.add(new JLabel("Cliente:"));
        
        panelCliente.add(new JLabel(this.cliente.getNombre()));
        
        panelCliente.add(new JLabel("Edad:"));
        
        String edad = Integer.toString(this.cliente.getEdad());
        panelCliente.add(new JLabel(edad));
        
        panelCliente.add(new JLabel("puntos: "));
        
        String puntos = Integer.toString(this.cliente.getPuntos().getCantidadDePuntos());
        panelCliente.add(new JLabel(puntos));
        
        add(panelCliente);
        
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
