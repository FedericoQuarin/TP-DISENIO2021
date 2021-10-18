package main.java.interfaces.nati.frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.interfaces.nati.paneles.PanelGestionarPasajero;

public class FrameGestionarPasajero extends JFrame {

	private JPanel contentPane;

	public FrameGestionarPasajero() {
		super("Sistema Hotel Premier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new PanelGestionarPasajero(this);
		setContentPane(contentPane);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
