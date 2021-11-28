package main.java.interfaces.CU05;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.enums.TipoMensaje;
import main.java.excepciones.FechaInvalidaException;
import main.java.excepciones.InputVacioException;
import main.java.interfaces.MenuPrincipal.FrameMenuPrincipal;
import main.java.interfaces.clasesExtra.FrameMuestraEstadoHabitaciones;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;



public class PanelMostrarEstadoHabitaciones extends JPanel implements PanelPermiteMensajes{
	
	private PanelMostrarEstadoHabitacionesGroupBox panelMostrarEstadoHabitacionesGroupBox = new PanelMostrarEstadoHabitacionesGroupBox();
	private PanelResultadosDeBusquedaHabitacionesGroupBox panelResultadosDeBusquedaHabitacionesGroupBox = new PanelResultadosDeBusquedaHabitacionesGroupBox();
	
	private JButton buscar;
	private JButton siguiente;
	private JButton cancelar;
	
	private JLabel label;
	
	private String textoMensajeCancelar = "<html><p>�Est� seguro que desea cancelar la operaci�n?</p><html>";
	private Mensaje mensajeCancelar = new Mensaje(1, textoMensajeCancelar, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoHabitacionInexistente = "<html><p>El n�mero de habitaci�n no se corresponde con ninguna habitaci�n en el sistema.</p><html>";
	private Mensaje mensajeHabitacionInexistente = new Mensaje(2, textoHabitacionInexistente, TipoMensaje.ERROR, "Aceptar", null);
	
	private String textoHabitacionSinFacturasPendientes = "<html><p>La habitaci�n no tiene facturas pendientes de pago.</p><html>";
	private Mensaje mensajeHabitacionSinFacturasPendientes = new Mensaje(3, textoHabitacionSinFacturasPendientes, TipoMensaje.ERROR, "Aceptar", null);
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Insets insetPanelBusqueda = new Insets(30,30,5,30);
	private Insets insetPanelTabla = new Insets(0,30,0,30);
	
	private FrameMenuPrincipal frameAnterior;
	private FrameMuestraEstadoHabitaciones frameActual;
	private JFrame frameSiguiente;	//Dependiendo quien lo llame, cambia el frame que se mostrara al presionar "Siguiente"
	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	public PanelMostrarEstadoHabitaciones(final FrameMuestraEstadoHabitaciones frame) {
		
		this.frameActual = frame;
		
		this.setBackground(Color.WHITE);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = insetPanelBusqueda;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 0;	c.gridwidth = 3;
		c.weightx = 0.1; c.weighty = 0.1;			this.add(panelMostrarEstadoHabitacionesGroupBox, c);
		
		c.weightx = 0.0; c.weighty = 0.0;	
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,0,0,0);
		
		buscar = new JButton("Buscar");
		buscar.setMinimumSize(dimensionBoton);
		buscar.setPreferredSize(dimensionBoton);
		buscar.setBackground(Color.decode("#E0E0E0"));
		buscar.setFont(fuenteBoton);
		buscar.setBorder(bordeBoton);
		buscar.addActionListener(e -> {
			
			try{
					this.panelMostrarEstadoHabitacionesGroupBox.inputNoEsVacia();
					this.panelMostrarEstadoHabitacionesGroupBox.inputEsValida();
////				gestorPasajero.validarDatosBusqueda(filtros);	//TODO: Buscar si la habitacion tiene facturas y conforme a eso tirar las excepciones
////				Integer cantResultados = gestorPasajero.buscarCantidadPasajeros(filtros);
////				
////				panelGestionarPasajeroTabla.buscarResultados(filtros, cantResultados);
//				
			}
			catch(InputVacioException exc) {
				
				this.panelMostrarEstadoHabitacionesGroupBox.colocarLabelVacio(exc.inputsVacios);
			}
			catch (FechaInvalidaException exc) {
				System.out.println("FechaInvalida");
				this.panelMostrarEstadoHabitacionesGroupBox.colocarLabelInvalido(exc.inputsInvalidos);
			}	
		});
		c.anchor = GridBagConstraints.CENTER;		//c.insets = new Insets(0,60,10,0);
		c.gridx = 1; c.gridy = 1;
		this.add(buscar, c);
		
		c.insets = insetPanelTabla;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 2;	c.gridwidth = 3;
		c.weightx = 0.8; c.weighty = 0.8;			this.add(panelResultadosDeBusquedaHabitacionesGroupBox, c);
		c.weightx = 0.1; c.weighty = 0.1;	
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		
		cancelar = new JButton("Cancelar");
		cancelar.setMinimumSize(dimensionBoton);
		cancelar.setPreferredSize(dimensionBoton);
		cancelar.setBackground(Color.decode("#E0E0E0"));
		cancelar.setFont(fuenteBoton);
		cancelar.setBorder(bordeBoton);
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mensajeCancelar.mostrar(getPanel(), frame);
			}
		});
		c.anchor = GridBagConstraints.WEST;		c.insets = new Insets(0,60,10,0);
		c.gridx = 0; c.gridy = 3;
		this.add(cancelar, c);

		siguiente = new JButton("Siguiente");
		siguiente.setMinimumSize(dimensionBoton);
		siguiente.setPreferredSize(dimensionBoton);
		siguiente.setBackground(Color.decode("#E0E0E0"));
		siguiente.setFont(fuenteBoton);
		siguiente.setBorder(bordeBoton);
		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.apretoSiguiente();
				//frame.dispose();
				//frameSiguiente = new FrameIngresarDatosPago();
			}
//				try {
//					PasajeroDTO pasajero = panelGestionarPasajeroTabla.pasajeroSeleccionado();
//					
//					mensajeModificarPasajero.mostrar(getPanel(), frame);
//				}
//				catch (PasajeroNoSeleccionadoException exc) {
//					mensajeNoExistePasajeroSiguiente.mostrar(getPanel(), frame);
//				}
//				
//				//mensajeNoExistePasajeroSiguiente.mostrar(getPanel(), frame);
//			}
		});
		c.anchor = GridBagConstraints.EAST;		c.insets = new Insets(0,0,10,60);
		c.gridx = 2; c.gridy = 3;
		this.add(siguiente, c);
	}
	
	public PanelPermiteMensajes getPanel() {
		return this;
	}
	
	public void confirmoElMensaje(Integer idMensaje) {
		
		switch(idMensaje) {
		case 1:	//Si cancela, vuelve a MenuPrincipal
			frameActual.dispose();
			frameAnterior = new FrameMenuPrincipal();	
			break;
		case 2:	//Si la habitaci�n no existe, simplemente muestra el mensaje
			break;
		case 3:	//Si la habitaci�n no posee facturas, simplemente muestra el mensaje
			break;		
		}
	}


	public void confirmoCancelar(Integer idMensaje) {

		//Ninguno de los mensajes tiene una funci�n si se presiona el bot�n de la izquierda
	}

}