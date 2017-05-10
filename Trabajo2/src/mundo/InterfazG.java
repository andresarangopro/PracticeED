package mundo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import operaciones.BinaryNode;
import operaciones.BinaryTree;
import operaciones.ManejoArchivo;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.MatteBorder;

public class InterfazG extends JFrame {

	public ManejoArchivo manejoArchivo = new ManejoArchivo();
	private JPanel contentPane;
	public BinaryTree<String> arbol = new BinaryTree<>();
	private JTextField txtArchivo;
	private JTextField txtPalabrasC;
	private JTextField TBpalabraABuscar;
	private String direccion = "";
	private final String direccion2 = "C:\\Nueva carpeta\\";
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JList<String> list = new JList<>(model);
	public WindowsListener wl;
	private String search = "";
	public File fileTree = manejoArchivo.crearArchivo("fileTree");
	public File fileList = manejoArchivo.crearArchivo("fileList");

	/*****************************
	 * Launch the application.
	 *****************************/
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
		wl = new WindowsListener(this);
		this.addWindowListener(wl);
		// wl.windowClosed();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 0, 546, 147);
		contentPane.add(panel);

		txtArchivo = new JTextField();
		txtArchivo.setForeground(SystemColor.inactiveCaptionText);
		txtArchivo.setEnabled(false);
		txtArchivo.setColumns(10);
		txtArchivo.setBounds(58, 10, 418, 28);
		panel.add(txtArchivo);
		JButton button = new JButton("");
		ImageIcon icoB = new ImageIcon("img/documento.png");
		button.setIcon(icoB);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				direccion = manejoArchivo.leerArchivo(txtArchivo);
			}

		});
		button.setBounds(486, 10, 35, 28);
		panel.add(button);

		JLabel lblArchivo = new JLabel("Archivo");
		lblArchivo.setBounds(10, 10, 46, 28);
		panel.add(lblArchivo);

		JLabel lblFrases = new JLabel("Frases");
		lblFrases.setBounds(10, 56, 46, 28);
		panel.add(lblFrases);

		txtPalabrasC = new JTextField();
		txtPalabrasC.setColumns(10);
		txtPalabrasC.setBounds(58, 56, 418, 28);
		panel.add(txtPalabrasC);

		JButton btnAdd = new JButton("AGREGAR");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!txtArchivo.getText().toString().equals("") && txtArchivo.getText() != null) {
					if (!txtPalabrasC.getText().toString().equals("") && txtPalabrasC.getText() != null) {
						try {
							manejoArchivo.crearDirectorio(direccion2);
							String nombre = manejoArchivo.add(direccion, direccion2);
							String[] palabrasClave = manejoArchivo.tokenizarKeys(txtPalabrasC.getText());
							for (int i = 0; i < palabrasClave.length; i++) {
								arbol.insert(palabrasClave[i], nombre);
							}

							txtArchivo.setText("");
							txtPalabrasC.setText("");
							JOptionPane.showMessageDialog(null, "Archivo agregado con exito!");
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Las palabras claves son necesarias");
					}
				} else {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ningun archivo");
				}

			}
		});
		btnAdd.setBounds(199, 102, 119, 34);
		panel.add(btnAdd);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 149, 546, 59);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		TBpalabraABuscar = new JTextField();
		TBpalabraABuscar.setBounds(35, 20, 169, 28);
		panel_1.add(TBpalabraABuscar);
		TBpalabraABuscar.setColumns(10);

		ImageIcon icoBuscar = new ImageIcon("img/search-engine.png");
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Cambiar a Jtable esto
				model.clear();
				list.setModel(model);
				if (arbol.buscarPalabra(TBpalabraABuscar.getText())
						.equalsIgnoreCase("No se encuentran elementos con esta etiqueta")) {
					JOptionPane.showMessageDialog(null, arbol.buscarPalabra(TBpalabraABuscar.getText()));
				} else {
					search = TBpalabraABuscar.getText();
					actualizarList();
				}
				list.setModel(model);
			}
		});
		btnBuscar.setBounds(213, 20, 40, 28);
		btnBuscar.setIcon(icoBuscar);
		panel_1.add(btnBuscar);

		ImageIcon icoTree = new ImageIcon("img/hierarchy-levels(1).png");
		JButton btnTree = new JButton("");
		btnTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// System.out.println(arbol.levelOrder()+"");
				GraficadoArbol graficar = new GraficadoArbol(arbol);
			}
		});
		btnTree.setBounds(381, 0, 71, 59);
		btnTree.setIcon(icoTree);
		panel_1.add(btnTree);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 219, 494, 259);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		list.setBorder(new CompoundBorder(
				new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), null), null));
		panel_2.add(list, BorderLayout.CENTER);

		ImageIcon icoOpen = new ImageIcon("img/carpeta.png");
		JButton btnOpen = new JButton("");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = list.getSelectedIndex();
				if (pos >= 0) {

					String wordKey = arbol.findNode(search).elementosForFile();
					String[] ar = wordKey.split(",");
					manejoArchivo.openFile(ar[pos]);
				}
			}
		});
		btnOpen.setBounds(514, 219, 42, 39);
		btnOpen.setIcon(icoOpen);
		contentPane.add(btnOpen);

		ImageIcon icoEdit = new ImageIcon("img/lapiz.png");
		JButton btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int pos = list.getSelectedIndex();
				if (pos >= 0) {
					String name = JOptionPane.showInputDialog("", "Ingrese nuevas palabras: ");
					String wordKey = arbol.findNode(search).elementosForFile();
					String[] ar = wordKey.split(",");
					arbol.eliminarALvl(ar[pos]);

					String[] palabras = name.split(" ");
					for (int j = 0; j < palabras.length; j++) {
						arbol.insert(palabras[j], ar[pos]);
					}					
				}				
			}
		});
		btnEditar.setBounds(514, 269, 42, 39);
		btnEditar.setIcon(icoEdit);
		contentPane.add(btnEditar);

		ImageIcon icoDelete = new ImageIcon("img/basura.png");
		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = list.getSelectedIndex();
				if (pos >= 0) {
					String wordKey = arbol.findNode(search).elementosForFile();
					String[] ar = wordKey.split(",");
					System.out.println(arbol.findNode(search).encotrarEnArreglo(ar[pos])+" "+arbol.getRoot().getItem().toString());
					arbol.eliminarALvl(ar[pos]);
					manejoArchivo.deleteFile(ar[pos]);
					System.out.println(search);
						
				}				
				actualizarList();
			}
		});
		btnEliminar.setBounds(514, 319, 42, 39);
		btnEliminar.setIcon(icoDelete);
		contentPane.add(btnEliminar);
	}
	
	public void actualizarList(){
		String wordKey = arbol.buscarPalabra(TBpalabraABuscar.getText());
		String [] ar = manejoArchivo.nextTounderScore(wordKey);
		model.clear();
		for (int i = 0; i < ar.length; i++) {
			model.addElement(ar[i]);
		}
	}
}
