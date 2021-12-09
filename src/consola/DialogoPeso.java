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

public class DialogoPeso extends JDialog implements ActionListener {
	
	private final static String AGREGAR = "Agregar a la compra";
	
	
	private final static String CANCELAR = "Cancelar";
	
	
	private JTextField txtPeso;
	
	
	private JButton botonAgregar;
	
	
	private Producto producto;
	
	
	private GraficaPOS interfaz;
	
	
	private JButton botonCancelar;
	
	
	public DialogoPeso(GraficaPOS interfazp, Producto productop)
	{
		this.interfaz = interfazp;
		this.producto = productop;
		
		setLayout( new BorderLayout( ) );
        setSize( 320, 270 );
        setLocationRelativeTo( null );
        setTitle( "Seleccione el peso a comprar" );
        
        JPanel panelPeso = new JPanel();
        panelPeso.setLayout(new GridLayout(3,1));
        
        txtPeso = new JTextField();
        panelPeso.add(txtPeso);
        
        botonAgregar = new JButton(AGREGAR);
        botonAgregar.setActionCommand(AGREGAR);
        botonAgregar.addActionListener(this);
        panelPeso.add(botonAgregar);
        
        botonCancelar = new JButton(CANCELAR);
        botonCancelar.setActionCommand(CANCELAR);
        botonCancelar.addActionListener(this);
        panelPeso.add(botonCancelar);
        
        add(panelPeso);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if(comando.equals(AGREGAR))
		{
			int peso = Integer.parseInt(txtPeso.getText());
			interfaz.agregarProductoPeso(producto.getCodigoDeBarras(), peso);
			this.dispose( );
		}
		else if(comando.equals(CANCELAR))
		{
			dispose();
		}
	}

}
