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

public class Derecha extends JPanel implements ActionListener{
	private GraficaPOS interfaz;
	
	
	private Cliente cliente;
	
	
	private JTextField txtCliente;
	
	
	private JButton botonConsultar;
	
	
	private final static String CONSULTAR = "Consultar Cliente";
	
	
	private JButton botonNuevoCliente;
	
	
	private final static String NUEVO = "Registrar nuevo Cliente";
	
	public Derecha(GraficaPOS interfazp)
	{
		this.interfaz = interfazp;
		//this.cliente = interfaz.getCliente();
		setLayout(new GridLayout(5,1));
		setBorder( new TitledBorder(" Informacion del Cliente "));
		
		JPanel panelCliente = new JPanel();
		/**
        panelCliente.setLayout(new GridLayout(3,3));
        
        panelCliente.add(new JLabel("Cliente:"));
        
        panelCliente.add(new JLabel(this.cliente.getNombre()));
        
        panelCliente.add(new JLabel("Edad:"));
        
        String edad = Integer.toString(this.cliente.getEdad());
        panelCliente.add(new JLabel(edad));
        
        panelCliente.add(new JLabel("puntos: "));
        
        String puntos = Integer.toString(this.cliente.getPuntos().getCantidadDePuntos());
        panelCliente.add(new JLabel(puntos));
        */
        add(panelCliente);
        
        txtCliente = new JTextField();
        add(txtCliente);
        
        botonConsultar = new JButton(CONSULTAR);
        botonConsultar.setActionCommand(CONSULTAR);
        botonConsultar.addActionListener(this);
        add(botonConsultar);
        
        botonNuevoCliente = new JButton(NUEVO);
        botonNuevoCliente.setActionCommand(NUEVO);
        botonNuevoCliente.addActionListener(this);
        add(botonNuevoCliente);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if(comando.equals(CONSULTAR))
		{
			String nombre = this.txtCliente.getText();
			if(nombre!= null)
			{
				Cliente cliente = interfaz.getCliente(nombre);
				DialogoInfoCliente dialogoInfoCliente = new DialogoInfoCliente(this.interfaz, cliente);
				dialogoInfoCliente.setVisible(true);
			}
		}
		else if(comando.equals(NUEVO))
		{
			DialogoClienteNuevo dialogoClienteNuevo = new DialogoClienteNuevo(this.interfaz);
			dialogoClienteNuevo.setVisible(true);
		}
	}

}
