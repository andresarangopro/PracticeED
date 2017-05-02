package mundo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import operaciones.BinaryTree;
import operaciones.ManejoArchivo;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class InterfazG extends JFrame {
	ManejoArchivo manejoArchivo = new ManejoArchivo();
	private JPanel contentPane;
	private BinaryTree<String> arbol = new BinaryTree<>();
	private JTextField txtArchivo;
	private JTextField txtPalabrasC;
	private JTextField textField_2;
	private String direccion = "";
	private String direccion2 = "C:\\Nueva carpeta\\";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazG frame = new InterfazG();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazG() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 0, 494, 147);
		contentPane.add(panel);

		txtArchivo = new JTextField();
		txtArchivo.setForeground(SystemColor.inactiveCaptionText);
		txtArchivo.setEnabled(false);
		txtArchivo.setColumns(10);
		txtArchivo.setBounds(58, 10, 381, 28);
		panel.add(txtArchivo);
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				direccion = manejoArchivo.leerArchivo(txtArchivo);
			}

		});
		button.setBounds(449, 10, 35, 28);
		panel.add(button);

		JLabel lblArchivo = new JLabel("Archivo");
		lblArchivo.setBounds(10, 10, 46, 28);
		panel.add(lblArchivo);

		JLabel lblFrases = new JLabel("Frases");
		lblFrases.setBounds(10, 56, 46, 28);
		panel.add(lblFrases);

		txtPalabrasC = new JTextField();
		txtPalabrasC.setColumns(10);
		txtPalabrasC.setBounds(58, 56, 381, 28);
		panel.add(txtPalabrasC);

		JButton btnAdd = new JButton("AGREGAR");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtArchivo.getText().toString().equals("") && txtArchivo.getText() != null){
					if(!txtPalabrasC.getText().toString().equals("") && txtPalabrasC.getText() != null){
						try {
							manejoArchivo.crearDirectorio(direccion2);
							manejoArchivo.add(direccion, direccion2);
							
							String[] palabrasClave = manejoArchivo.tokenizarKeys(txtPalabrasC.getText());
							
							for (int i = 0; i < palabrasClave.length; i++) {
								arbol.insert(palabrasClave[i]);
							}
														
							txtArchivo.setText("");
							txtPalabrasC.setText("");
							JOptionPane.showMessageDialog(null,"Archivo agregado con exito!");
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null,e2);
						}	
					}else{
						JOptionPane.showMessageDialog(null,"Las palabras claves son necesarias");
					}
				}else{
					JOptionPane.showMessageDialog(null,"No ha seleccionado ningun archivo");
				}
				
			}
		});
		btnAdd.setBounds(199, 102, 119, 34);
		panel.add(btnAdd);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 149, 494, 59);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		textField_2 = new JTextField();
		textField_2.setBounds(35, 20, 169, 28);
		panel_1.add(textField_2);
		textField_2.setColumns(10);

		ImageIcon icoBuscar = new ImageIcon("img/search-engine.png");
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
			}
		});
		btnBuscar.setBounds(213, 20, 40, 28);
		btnBuscar.setIcon(icoBuscar);
		panel_1.add(btnBuscar);

		ImageIcon icoTree = new ImageIcon("img/hierarchy-levels(1).png");
		JButton btnTree = new JButton("");
		btnTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GraficadoArbol graficar = new GraficadoArbol(arbol);	
			}
		});
		btnTree.setBounds(350, 11, 63, 37);
		btnTree.setIcon(icoTree);
		panel_1.add(btnTree);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 219, 494, 259);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JList list = new JList();
		panel_2.add(list, BorderLayout.CENTER);
	}

}
