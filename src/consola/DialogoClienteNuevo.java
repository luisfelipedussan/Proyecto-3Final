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

public class DialogoClienteNuevo extends JDialog implements ActionListener{
	
	
	private GraficaPOS interfaz;
	
	
	private JTextField txtNombre;
	
	
	private JTextField txtEdad;
	
	
	private JTextField txtSexo;
	
	
	private JTextField txtEstadoCivil;
	
	
	private JTextField txtTrabajo;
	
	
	private JButton botonAgregar;
	
	
	private final static String AGREGAR = "Agregar Cliente";
	
	
	private JButton botonCancelar;
	
	
	private final static String CANCELAR = "Cancelar";
	
	
	public DialogoClienteNuevo(GraficaPOS interfazp)
	{
		this.interfaz = interfazp;
		setLayout(new GridLayout(6,2));
		setSize( 500,300 );
		
		JLabel labelNombre = new JLabel("Nombre:");
		add(labelNombre);
		
		txtNombre = new JTextField();
		add(txtNombre);
		
		JLabel labelEdad = new JLabel("Edad:");
		add(labelEdad);
		
		txtEdad = new JTextField();
		add(txtEdad);
		
		JLabel labelSexo = new JLabel("Sexo (M/F):");
		add(labelSexo);
		
		txtSexo = new JTextField();
		add(txtSexo);
		
		JLabel labelEstadoCivil = new JLabel("Estado Civil:");
		add(labelEstadoCivil);
		
		txtEstadoCivil = new JTextField();
		add(txtEstadoCivil);
		
		JLabel labelTrabajo = new JLabel("Situacion Laboral:");
		add(labelTrabajo);
		
		txtTrabajo = new JTextField();
		add(txtTrabajo);
		
		botonAgregar = new JButton(AGREGAR);
		botonAgregar.setActionCommand(AGREGAR);
		botonAgregar.addActionListener(this);
		add(botonAgregar);
		
		botonCancelar = new JButton(CANCELAR);
		botonCancelar.setActionCommand(CANCELAR);
		botonCancelar.addActionListener(this);
		add(botonCancelar);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		System.out.println(comando);
		if(comando.equals(AGREGAR))
		{
			crearCliente();
			System.out.println("Se creo el cliente");
			this.dispose( );
		}
		else if(comando.equals(CANCELAR))
		{
			dispose();
		}
		
	}
	public void crearCliente() 
	{
		String nombre = txtNombre.getText();
		int edad = Integer.parseInt(txtEdad.getText());
		String sexo = txtSexo.getText();
		String estadoCivil = txtEstadoCivil.getText();
		String situacion = txtTrabajo.getText();
		interfaz.getPOS().addCliente(nombre,edad,sexo,estadoCivil,situacion);
	}
}
