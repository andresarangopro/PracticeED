package mundo;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import operaciones.BinaryTree;

import java.awt.Font;

import java.awt.Color;

public class GraficadoArbol {

	/**
	 * Cuadro en donde se mostrará el árbol.
	 */
	private JFrame frame;
	
	/**
	 * String que contiene el árbol.
	 */
	private String arbolGraficado;

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
		
		JTextArea ta = new JTextArea(15, 25);
		ta.setForeground(Color.BLACK);
		ta.setEditable(false);
		ta.setText(arbolGraficado);
		ta.setFont(new Font("Monospaced", Font.BOLD, 13));
		JFrame frame = new JFrame();
		frame.getContentPane().add(new JScrollPane(ta));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
