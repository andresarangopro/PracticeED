package mundo;

import java.awt.EventQueue;

import javax.swing.JFrame;

import operaciones.BinaryTree;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GraficadoArbol {

	private JFrame frame;
	
	private String arbolGraficado;

	/**
	 * Create the application.
	 */
	public GraficadoArbol(BinaryTree<String> arbol) {
		arbolGraficado = arbol.displayTree();
		initialize();
		frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblArbol = new JLabel("Arbol");
		lblArbol.setVerticalAlignment(SwingConstants.TOP);
		lblArbol.setBounds(0, 0, 434, 261);
		lblArbol.setText(arbolGraficado);
		frame.getContentPane().add(lblArbol);
		
	}
}
