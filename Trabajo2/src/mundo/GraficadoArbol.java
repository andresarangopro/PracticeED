package mundo;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import operaciones.BinaryTree;

import java.awt.Font;

import javax.swing.JFormattedTextField;

public class GraficadoArbol {

	private JFrame frame;
	
	private String arbolGraficado;
	private final JTextArea textArea = new JTextArea();

	/**
	 * Create the application.
	 */
	public GraficadoArbol(BinaryTree<String> arbol) {
		arbolGraficado = arbol.displayTree();
		initialize();		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);

		
		textArea.setText(arbolGraficado);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea.setEditable(false);
		frame.getContentPane().add(new JScrollPane(textArea));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
