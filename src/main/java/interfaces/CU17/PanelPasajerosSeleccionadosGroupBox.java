package main.java.interfaces.CU17;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import main.java.interfaces.clasesExtra.ModeloPasajerosSeleccionadosOcuparHabitacion;
import main.java.interfaces.clasesExtra.RenderParaTablas;

public class PanelPasajerosSeleccionadosGroupBox extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JTable tabla;
	private ModeloPasajerosSeleccionadosOcuparHabitacion miModelo;
	private RenderParaTablas renderTabla;
	
	@SuppressWarnings({ "unused", "rawtypes" })
	private Vector filaSeleccionada = null;
	@SuppressWarnings("unused")
	private Integer nroFilaSeleccionada;
	private JScrollPane tableContainer;
	
	private Insets insetTabla = new Insets(5, 20, 5, 20);

	private Font fuenteGroupBox = new Font("SourceSansPro", Font.PLAIN, 18);	
	
	public PanelPasajerosSeleccionadosGroupBox() {
		
		this.setBackground(Color.white);
		
		this.setBorder(new TitledBorder (new LineBorder (Color.black, 1), " Resultados de b�squeda", 0, 0, fuenteGroupBox));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		miModelo = new ModeloPasajerosSeleccionadosOcuparHabitacion();
		
		miModelo.cargarPasajeros();
		
		tabla = new JTable(miModelo);
		tableContainer = new JScrollPane(tabla);
		
		renderTabla = new RenderParaTablas(tabla.getDefaultRenderer(Object.class), false);
		
		tabla.getTableHeader().setDefaultRenderer(renderTabla);
		
		tabla.getTableHeader().setReorderingAllowed(false); //Para que no se muevan las columnas
		
		tabla.setRowSelectionAllowed(true);
		tabla.setColumnSelectionAllowed(false);
		
		tabla.setFocusable(false); //Para que no seleccione una sola columna
		
		tabla.getTableHeader().setResizingAllowed(false);	//Para que no puedas cambiar el tama�o de las columnas
		
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabla.setAutoCreateRowSorter(true);	//Para que se ordenen
		
		tabla.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {				
				filaSeleccionada = miModelo.getDataVector().elementAt(tabla.getSelectedRow());
				nroFilaSeleccionada = tabla.getSelectedRow();
			}
		});
		
		//PARA CENTRAR
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tabla.setDefaultRenderer(Object.class, centerRenderer);
		
		tabla.setBackground(Color.white);
		tabla.setGridColor(Color.black);
		tabla.setBorder(new LineBorder(Color.BLACK));
		
		//this.add(tableContainer, BorderLayout.CENTER);
		c.fill = GridBagConstraints.BOTH;
		//c.anchor = GridBagConstraints.CENTER;
		c.insets = insetTabla;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		this.add(tableContainer, c);
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.gridwidth = 1;
		
	}
}
