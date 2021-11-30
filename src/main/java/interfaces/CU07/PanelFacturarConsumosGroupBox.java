package main.java.interfaces.CU07;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import main.java.interfaces.clasesExtra.ModeloTablaConsumos;
import main.java.interfaces.clasesExtra.RoundedBorder;

public class PanelFacturarConsumosGroupBox extends JPanel{
	
	
	Integer valorCantidad = 3;
	
	private JLabel label;
	
	private JTextField razonSocial;
	private JTextField fecha;
	private JTextField cuit;
	private JTextField posicionFrenteIVA;
	private JTextField tipoFactura;
	
	private JTextField subtotal;
	private JTextField IVA;
	private JTextField totalAPagar;
	
	private JTable tabla;
	private ModeloTablaConsumos miModelo;	
	
	private Vector filaSeleccionada = null;
	private Integer nroFilaSeleccionada;
	private JScrollPane tableContainer;
	
	private Insets insetTabla = new Insets(0,40,0,60);	//Espacios en blanco para acomodar los componentes
	private Insets insetLabel = new Insets(0,30,0,30);	
	private Insets insetCampoDerecho = new Insets(0,0,0,30);
	private Insets insetCampoTipoFactura = new Insets(0,20,0,0);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);
	private Font fuenteLabelCampo =new Font("SourceSansPro", Font.PLAIN, 14);
	
	private double pesoXLabel = 0.05;
	private double pesoYLabel = 0.05;
	private double pesoXCampo = 0.05;
	private double pesoYCampo = 0.05;
	
	private Dimension dimensionCampo = new Dimension(240, 25);
	private Dimension dimensionCamposFinales = new Dimension(180, 25);
	private Dimension dimensionCampoTipoFactura = new Dimension(22, 25);
	
	private RoundedBorder bordeCampo = new RoundedBorder(5, Color.BLACK);
	
	private ButtonRenderer renderBotonMenos = new ButtonRenderer('-');
	private ButtonRenderer renderBotonMas = new ButtonRenderer('+');
	private ButtonEditor editorBotonMenos = new ButtonEditor(new JCheckBox(), '-');
	private ButtonEditor editorBotonMas = new ButtonEditor(new JCheckBox(), '+');
	
	public PanelFacturarConsumosGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Facturar", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
			c.anchor = GridBagConstraints.WEST;
		
			c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Raz�n social");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 0;	this.add(label, c);
		
			c.anchor = GridBagConstraints.EAST;	c.fill = GridBagConstraints.CENTER; c.weightx = pesoXCampo; c.weighty = pesoYCampo; //c.insets = insetCampo;
		
		razonSocial = new JTextField(); 
		razonSocial.setText("REEMPLAZAR");
		razonSocial.setFont(fuenteLabelCampo);	razonSocial.setBorder(bordeCampo);	razonSocial.setEditable(false);
		c.gridx = 1; c.gridy = 0;	razonSocial.setMinimumSize(dimensionCampo); razonSocial.setPreferredSize(dimensionCampo);	
		this.add(razonSocial, c); 
		
			c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.NONE;	c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Fecha");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 0;	this.add(label, c);
		
			c.anchor = GridBagConstraints.EAST; c.fill = GridBagConstraints.CENTER; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampoDerecho;
		
		fecha = new JTextField(); 
		fecha.setText("REEMPLAZAR");
		fecha.setFont(fuenteLabelCampo);	fecha.setBorder(bordeCampo);	fecha.setEditable(false);
		c.gridx = 3; c.gridy = 0;	fecha.setMinimumSize(dimensionCampo);	fecha.setPreferredSize(dimensionCampo);	
		this.add(fecha, c); 	
		
			c.anchor = GridBagConstraints.WEST; c.fill = GridBagConstraints.NONE;	c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("CUIT");	label.setFont(fuenteLabelCampo);	c.gridx = 0; c.gridy = 1;	this.add(label, c);
		
			c.anchor = GridBagConstraints.EAST; c.fill = GridBagConstraints.CENTER; c.weightx = pesoXCampo; c.weighty = pesoYCampo; //c.insets = insetCampo;
		
		cuit = new JTextField(); 
		cuit.setText("REEMPLAZAR");
		cuit.setFont(fuenteLabelCampo);	cuit.setBorder(bordeCampo);	cuit.setEditable(false);
		c.gridx = 1; c.gridy = 1;	cuit.setMinimumSize(dimensionCampo);	cuit.setPreferredSize(dimensionCampo);	
		this.add(cuit, c); 
		
			c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.NONE;	c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Pos frente al IVA");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 1;	this.add(label, c);
		
			c.anchor = GridBagConstraints.EAST; c.fill = GridBagConstraints.CENTER; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampoDerecho;
		
		posicionFrenteIVA = new JTextField(); 
		posicionFrenteIVA.setText("REEMPLAZAR");
		posicionFrenteIVA.setFont(fuenteLabelCampo);	posicionFrenteIVA.setBorder(bordeCampo);	posicionFrenteIVA.setEditable(false);
		c.gridx = 3; c.gridy = 1;	posicionFrenteIVA.setMinimumSize(dimensionCampo);	posicionFrenteIVA.setPreferredSize(dimensionCampo);	
		this.add(posicionFrenteIVA, c); 
		
			c.anchor = GridBagConstraints.EAST;	c.fill = GridBagConstraints.NONE;	c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Tipo de factura");	label.setFont(fuenteLabelCampo);	c.gridx = 1; c.gridy = 2;	this.add(label, c);
		
			c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.WEST; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampoTipoFactura; 
		
		tipoFactura = new JTextField(); 
		tipoFactura.setText("A");
		tipoFactura.setFont(fuenteLabelCampo);	tipoFactura.setBorder(bordeCampo);	tipoFactura.setEditable(false);
		c.gridx = 2; c.gridy = 2;	tipoFactura.setMinimumSize(dimensionCampoTipoFactura);	tipoFactura.setPreferredSize(dimensionCampoTipoFactura);	
		this.add(tipoFactura, c); 
		
		//----------- CONFIGURACIONES TABLA -----------
		
		miModelo = new ModeloTablaConsumos();
		
		tabla = new JTable(miModelo);
		tableContainer = new JScrollPane(tabla);
		
		tabla.getTableHeader().setReorderingAllowed(false); //Para que no se muevan las columnas
		
		tabla.setRowSelectionAllowed(false);
		tabla.setColumnSelectionAllowed(false);
		
		tabla.setFocusable(false); //Para que no seleccione una sola columna
		
		tabla.getTableHeader().setResizingAllowed(false);	//Para que no puedas cambiar el tama�o de las columnas
		
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabla.setAutoCreateRowSorter(false);	//Para que no se ordenen
		
		tabla.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {				
				filaSeleccionada = miModelo.getDataVector().elementAt(tabla.getSelectedRow());
				nroFilaSeleccionada = tabla.getSelectedRow();
			}
		});
		
		//JSpinner j = new JSpinner();
		
		
		Object[] prueba = new Object[] {"VALOR DE LA ESTAD�A","", valorCantidad.toString()+"/3", "", 1400.00, 4200.00}; 
		
		miModelo.addRow(prueba);
		for(int i = 0; i < 8; i++)
			miModelo.addRow(new Object[]{null,null,null,null,null,null});	//Fila en blanco
		
		miModelo.addRow(new Object[] {"TOTAL","","","","", 4200.00});
		
		tabla.getTableHeader().setOpaque(false);
		tabla.getTableHeader().setBackground(Color.decode("#424242"));		//Para que el fondo de la cabecera sea de un color en espec�fico
		tabla.getTableHeader().setForeground(Color.WHITE);					//Para que la fuente de la cabecera sea blanca
		tabla.getTableHeader().setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		
		tabla.getTableHeader().setPreferredSize(new Dimension(400, 40));	//Dimension de la cabecera
		
		tabla.getColumnModel().getColumn(1).setCellRenderer(renderBotonMenos);
		tabla.getColumnModel().getColumn(1).setCellEditor(editorBotonMenos);
		
		tabla.getColumnModel().getColumn(3).setCellRenderer(renderBotonMas);
		tabla.getColumnModel().getColumn(3).setCellEditor(editorBotonMas);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(400);	//Ancho de las columnas
		tabla.getColumnModel().getColumn(1).setPreferredWidth(5);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(5);
		tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
		tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		//PARA CENTRAR
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		tabla.setDefaultRenderer(Object.class, centerRenderer);
		tabla.setDefaultRenderer(Integer.class, centerRenderer); //Por alguna razon no lo toma sin esto
		
		tabla.setBackground(Color.white);
		tabla.setGridColor(Color.black);
		//this.add(tableContainer, BorderLayout.CENTER);
		c.fill = GridBagConstraints.BOTH;
		//c.anchor = GridBagConstraints.CENTER;
		c.insets = insetTabla;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 3;
		this.add(tableContainer, c);
		
		//----------- CONFIGURACIONES TABLA -----------
		
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.gridwidth = 1;
		
			c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.NONE;	c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
			
		label = new JLabel("Subtotal");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 4;	this.add(label, c);
		
			c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.CENTER; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampoDerecho;
		
		subtotal = new JTextField(); 
		subtotal.setText("REEMPLAZAR");
		subtotal.setFont(fuenteLabelCampo);	subtotal.setBorder(bordeCampo);	subtotal.setEditable(false);
		c.gridx = 3; c.gridy = 4;	subtotal.setMinimumSize(dimensionCamposFinales);	subtotal.setPreferredSize(dimensionCamposFinales);	
		this.add(subtotal, c); 
		
			c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.NONE;	c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;		
			
		label = new JLabel("IVA (21%)");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 5;	this.add(label, c);
		
			c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.CENTER; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampoDerecho;
		
		IVA = new JTextField(); 
		IVA.setText("REEMPLAZAR");
		IVA.setFont(fuenteLabelCampo);	IVA.setBorder(bordeCampo);	IVA.setEditable(false);
		c.gridx = 3; c.gridy = 5;	IVA.setMinimumSize(dimensionCamposFinales);	IVA.setPreferredSize(dimensionCamposFinales);	
		this.add(IVA, c); 
	
			c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.NONE;	c.weightx = pesoXLabel; c.weighty = pesoYLabel; c.insets = insetLabel;	
		
		label = new JLabel("Total a pagar");	label.setFont(fuenteLabelCampo);	c.gridx = 2; c.gridy = 6;	this.add(label, c);
		
			c.anchor = GridBagConstraints.WEST;	c.fill = GridBagConstraints.CENTER; c.weightx = pesoXCampo; c.weighty = pesoYCampo; c.insets = insetCampoDerecho;
		
		totalAPagar = new JTextField(); 
		totalAPagar.setText("REEMPLAZAR");
		totalAPagar.setFont(fuenteLabelCampo);	totalAPagar.setBorder(bordeCampo);	totalAPagar.setEditable(false);	totalAPagar.setBackground(Color.decode("#edf4b1"));
		c.gridx = 3; c.gridy = 6;	totalAPagar.setMinimumSize(dimensionCamposFinales);	totalAPagar.setPreferredSize(dimensionCamposFinales);	
		this.add(totalAPagar, c); 
	}

}
