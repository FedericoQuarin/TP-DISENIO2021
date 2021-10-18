package main.java.interfaces.julio.paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.interfaces.julio.otros.*;
import main.java.interfaces.nati.frames.FrameGestionarPasajero;
import main.java.interfaces.julio.frames.FrameAutenticarUsuario;
import main.java.interfaces.julio.frames.FrameMenuPrincipal;


public class PanelMenuPrincipal extends JPanel{
	
	private PanelAutenticarUsuarioGroupBox panelAutenticarUsuarioGroupBox = new PanelAutenticarUsuarioGroupBox();
	
	private JButton cerrarSesion;
	private JButton button;
	
	private JLabel label;

	private Insets der = new Insets(0,25,0,0);
	private Insets izq = new Insets(0,0,0,25);
	private Insets derF = new Insets(0,25,70,0);
	private Insets izqF = new Insets(0,0,70,25);
	
	private Font fuenteTitulo = new Font("SourceSansPro", Font.PLAIN, 46);	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 15);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	private RoundedBorder bordeSalir = new RoundedBorder(10, Color.DARK_GRAY);

	private FrameAutenticarUsuario frameAnterior;
	private FrameGestionarPasajero frameGestionarPasajero;
	
	public PanelMenuPrincipal(final FrameMenuPrincipal frame) {
		
		this.setBackground(Color.white);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		label = new JLabel("Hotel Premier");
		label.setFont(fuenteTitulo);
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,0,0,0);
		c.gridwidth = 2;
			c.gridx = 0; c.gridy = 0;
		c.weightx = 0.5; c.weighty = 0.25;			
		this.add(label, c);
		
		c.gridwidth = 1;	c.weighty = 0.1;
		
		button = new JButton("Gestionar Pasajero");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Supongamos que no te muestre un mensaje de confirmaci�n
				frame.dispose();
				frameGestionarPasajero = new FrameGestionarPasajero();
			}
		});
		c.anchor = GridBagConstraints.EAST;	c.insets = izq;
			c.gridx = 0; c.gridy = 1;
		this.add(button, c);
		
		button = new JButton("Gestionar Responsable de Pago");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		c.anchor = GridBagConstraints.WEST; c.insets = der;
			c.gridx = 1; c.gridy = 1;
		this.add(button, c);
		
		button = new JButton("Reservar Habitaci�n");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		c.anchor = GridBagConstraints.EAST; c.insets = izq;
			c.gridx = 0; c.gridy = 2;
		this.add(button, c);
		
		button = new JButton("Ocupar Habitaci�n");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		c.anchor = GridBagConstraints.WEST; c.insets = der;
			c.gridx = 1; c.gridy = 2;
		this.add(button, c);
		
		button = new JButton("Cancelar Reserva");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		c.anchor = GridBagConstraints.EAST;	c.insets = izq;
			c.gridx = 0; c.gridy = 3;
		this.add(button, c);
		
		button = new JButton("Gestionar Listados");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		c.anchor = GridBagConstraints.WEST;	c.insets = der;
			c.gridx = 1; c.gridy = 3;
		this.add(button, c);
		
		button = new JButton("Facturar");
		button.setFont(fuenteBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setBorder(bordeBoton);
		button.setPreferredSize(new Dimension(350, 40));
		c.anchor = GridBagConstraints.EAST;	c.insets = izq;
			c.gridx = 0; c.gridy = 4;
		this.add(button, c);
		
		button = new JButton("Ingresar Nota de Cr�dito");
		button.setFont(fuenteBoton);
		button.setBorder(bordeBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setPreferredSize(new Dimension(350, 40));
		c.anchor = GridBagConstraints.WEST;	c.insets = der;
			c.gridx = 1; c.gridy = 4;
		this.add(button, c);
		
		cerrarSesion = new JButton("Cerrar Sesi�n");
		cerrarSesion.setFont(fuenteBoton);
		cerrarSesion.setBackground(Color.GRAY);
		cerrarSesion.setBorder(bordeSalir);
		cerrarSesion.setPreferredSize(new Dimension(350, 40));
		cerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Supongamos que no te muestre un mensaje de confirmaci�n
				frame.dispose();
				frameAnterior = new FrameAutenticarUsuario();
			}
		});
		c.anchor = GridBagConstraints.EAST;	c.insets = izqF;
			c.gridx = 0; c.gridy = 5;
		this.add(cerrarSesion, c);

		button = new JButton("Ingresar Pago");
		button.setFont(fuenteBoton);
		button.setBorder(bordeBoton);
		button.setBackground(Color.decode("#E0E0E0"));
		button.setPreferredSize(new Dimension(350, 40));
		c.anchor = GridBagConstraints.WEST; c.insets = derF;
			c.gridx = 1; c.gridy = 5;
		this.add(button, c);
	}
}
