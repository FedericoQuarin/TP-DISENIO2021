package main.java.interfaces.CU07;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.dtos.FacturaDTO;
import main.java.dtos.ItemFilaDTO;
import main.java.dtos.OcupacionDTO;
import main.java.dtos.ResponsableDePagoDTO;
import main.java.enums.PosicionFrenteIva;
import main.java.enums.TipoFactura;
import main.java.enums.TipoMensaje;
import main.java.excepciones.NingunElementoSeleccionadoFacturacionException;
import main.java.excepciones.RecargoNoEstaEnUltimaFacturaException;
import main.java.gestores.GestorFactura;
import main.java.gestores.GestorOcupacion;
import main.java.interfaces.MenuPrincipal.PanelMenuPrincipal;
import main.java.interfaces.clasesExtra.Mensaje;
import main.java.interfaces.clasesExtra.PanelPermiteMensajes;
import main.java.interfaces.clasesExtra.RoundedBorder;
import main.java.interfaces.frames.FramePrincipal;

public class PanelFacturarConsumos extends JPanel implements PanelPermiteMensajes{
	
	private static final long serialVersionUID = 1L;
	
	private PanelFacturarConsumosGroupBox panelFacturarConsumosGroupBox;
	
	private JButton aceptar;
	private JButton cancelar;
	
	private String textoMensajeCancelar = "<html><p>�Est� seguro que desea cancelar la operaci�n?</p><html>";
	private Mensaje mensajeCancelar = new Mensaje(1, textoMensajeCancelar, TipoMensaje.CONFIRMACION, "Si", "No");
	
	private String textoNadaSeleccionado = "<html><p>Por favor, seleccione al menos un consumo para ser adherido a la factura.</p><html>";
	private Mensaje mensajeNadaSeleccionado = new Mensaje(2, textoNadaSeleccionado, TipoMensaje.ERROR, "Aceptar", null);
	
	private String textoRecargoUltimoSeleccionado = "<html><p>No se puede facturar un recargo sin facturar todo el resto de la factura. "
													+ "Si no desea facturarlo en este momento, puede facturarlo en su �ltimo factura.</p><html>";
	private Mensaje mensajeRecargoUltimoSeleccionado = new Mensaje(3, textoRecargoUltimoSeleccionado, TipoMensaje.ERROR, "Aceptar", null);
	
	private String textoFacturaCreada = "<html><p>La factura se ha creado con �xito.</p><html>";
	private Mensaje mensajeFacturaCreada = new Mensaje(4, textoFacturaCreada, TipoMensaje.ERROR, "Imprimir", "Aceptar");
	
	private Font fuenteBoton = new Font("SourceSansPro", Font.PLAIN, 14);
	
	private RoundedBorder bordeBoton = new RoundedBorder(10, Color.decode("#BDBDBD"));
	
	private Insets insetPanelFacturarConsumos = new Insets(30,30,20,30);
	
	private FramePrincipal frameActual;
	private PanelFacturar panelAnterior;
	
	private GestorOcupacion gestorOcupacion;
	private GestorFactura gestorFactura;
	//TODO: Imprimir
	
	private Dimension dimensionBoton = new Dimension(90, 33);
	
	public PanelFacturarConsumos(final FramePrincipal frame, PanelFacturar panelAnterior, OcupacionDTO ocupacionDTO, ResponsableDePagoDTO responsablePagoDTO) {
		
		this.frameActual = frame;
		this.panelAnterior = panelAnterior;
		
		this.gestorOcupacion = GestorOcupacion.getInstance();
		this.gestorFactura = GestorFactura.getInstance();
		
		List<ItemFilaDTO> listaItemsFilaDTO = gestorOcupacion.calcularItemsAPagar(ocupacionDTO, responsablePagoDTO);
		
		this.setBackground(Color.WHITE);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		panelFacturarConsumosGroupBox = new PanelFacturarConsumosGroupBox(ocupacionDTO, responsablePagoDTO, listaItemsFilaDTO);
		c.insets = insetPanelFacturarConsumos;
		c.fill = GridBagConstraints.BOTH; 		c.gridx = 0; c.gridy = 0;	c.gridwidth = 2;
		c.weightx = 0.1; c.weighty = 0.1;			this.add(panelFacturarConsumosGroupBox, c);
		
		c.weightx = 0.0; c.weighty = 0.0;	
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,0,0,0);
		
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
		c.anchor = GridBagConstraints.WEST;		c.insets = new Insets(0,60,20,0);
		c.gridx = 0; c.gridy = 1;
		this.add(cancelar, c);

		aceptar = new JButton("Aceptar");
		aceptar.setMinimumSize(dimensionBoton);
		aceptar.setPreferredSize(dimensionBoton);
		aceptar.setBackground(Color.decode("#E0E0E0"));
		aceptar.setFont(fuenteBoton);
		aceptar.setBorder(bordeBoton);
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<ItemFilaDTO> listaItemsFila = panelFacturarConsumosGroupBox.getListaItems();
				FacturaDTO facturaDTO = crearFacturaDTO(responsablePagoDTO, listaItemsFila);
				
				try {
					gestorFactura.crearFactura(facturaDTO, ocupacionDTO);
					
					mensajeFacturaCreada.mostrar(getPanel(), frame);
					
				} catch (NingunElementoSeleccionadoFacturacionException e1) {
					//Si no seleccion� ning�n consumo hay que mostrar: 
					mensajeNadaSeleccionado.mostrar(getPanel(), frame);
					
				} catch (RecargoNoEstaEnUltimaFacturaException e1) {

					//Si no seleccion� ning�n consumo hay que mostrar: 
					mensajeRecargoUltimoSeleccionado.mostrar(getPanel(), frame);
				}
				
				
			}
		});
		c.anchor = GridBagConstraints.EAST;		c.insets = new Insets(0,0,20,60);
		c.gridx = 1; c.gridy = 1;
		this.add(aceptar, c);
	}
	
	public PanelPermiteMensajes getPanel() {
		return this;
	}
	
	public void confirmoElMensaje(Integer idMensaje) {
		
		switch(idMensaje) {
		case 1:	//Si cancela, vuelve al panel anterior
			frameActual.setNuevoPanel(panelAnterior);
			break;
		case 2:	//Si no selecciona ning�n consumo a facturar, simplemente muestra el mensaje
			break;
		case 3:	//Si selecciona el recargo pero no el resto de la factura, simplemente muestra el mensaje
			break;
		case 4:	//TODO: Ver si poner mensaje o mostrar directamente la factura impresa
			gestorFactura.imprimir();
			
			//TODO: Cuando se cierre la factura quizas:
			frameActual.setNuevoPanel(new PanelMenuPrincipal(frameActual));
			break;
		}
	}


	public void confirmoCancelar(Integer idMensaje) {

		//Ninguno de los mensajes tiene una funci�n si se presiona el bot�n de la izquierda
		
		switch(idMensaje) {
		case 1:
		case 2:	
		case 3:	
			break;
		case 4:	//Presiono "Volver al men� principal"
			frameActual.setNuevoPanel(new PanelMenuPrincipal(frameActual));
			break;
		}
	}
	
	public FacturaDTO crearFacturaDTO(ResponsableDePagoDTO responsablePagoDTO, List<ItemFilaDTO> listaItemsFila) {
		
		if(responsablePagoDTO.getPosicionFrenteIva() == PosicionFrenteIva.CONSUMIDOR_FINAL) {
			return new FacturaDTO(responsablePagoDTO, TipoFactura.B, panelFacturarConsumosGroupBox.getIVA(), panelFacturarConsumosGroupBox.getTotalAPagar(), listaItemsFila);
		}
		
		return new FacturaDTO(responsablePagoDTO, TipoFactura.A, panelFacturarConsumosGroupBox.getIVA(), panelFacturarConsumosGroupBox.getTotalAPagar(), listaItemsFila);
		
	}
}
