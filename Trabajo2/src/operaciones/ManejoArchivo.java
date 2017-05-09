package operaciones;

import java.awt.Color;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument.Content;

public class ManejoArchivo {

	///////////////////////
	// Metodos
	//////////////////////

	/*********************************************************
	 * 
	 * @param direccion
	 **********************************************************/

	public void crearDirectorio(String direccion) {
		File folder = new File(direccion);
		if (!folder.exists()) {
			folder.mkdir();
		}
		// if (folder.isDirectory()) {System.out.println("Es un directorio"); }
		// if (folder.isFile()) { System.out.println("is file"); }
	}

	/************************************************************
	 * 
	 * @param txtArchivo
	 * @return
	 *************************************************************/

	public String leerArchivo(JTextField txtArchivo) {
		JFileChooser fileChooser = new JFileChooser();
		String dir = "";
		int seleccion = fileChooser.showOpenDialog(txtArchivo);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File fichero = fileChooser.getSelectedFile();
			dir = fichero.toString();
			txtArchivo.setText(dir);
			txtArchivo.setDisabledTextColor(Color.BLACK);
		}
		return dir;
	}

	/*************************************************************************
	 * copia el archivo s en otro directorio siendo este el archivo t
	 * 
	 * @param s
	 * @param t
	 *************************************************************************/

	public void copyFile(File s, File t) {
		try {
			FileChannel in = (new FileInputStream(s)).getChannel();
			FileChannel out = (new FileOutputStream(t)).getChannel();
			in.transferTo(0, s.length(), out);
			in.close();
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/****************************************************************
	 * 
	 * @param direccion
	 *            del archivo seleccionado
	 * @param direccion2
	 *            es la direccion donde se copiar� el archivo
	 *****************************************************************/
	public String add(String direccion, String direccion2) {
		String a = "";
		String nombreD = "";
		String[] p = direccion.split("\\\\");
		a = p[p.length - 1];
		System.out.println(a);
		String extencion = a.substring(a.indexOf("."), a.length());
		File fIn = new File(direccion);
		char[] dir = a.toCharArray();
		for (int i = 0; i < a.indexOf("."); i++) {
			nombreD += dir[i];
		}
		System.out.println(nombreD);
		String name = System.currentTimeMillis() + "";
		File fOut = new File(direccion2 + name + "_" + nombreD + extencion);
		copyFile(fIn, fOut);
		return direccion2 + name + "_" + nombreD + extencion;
	}

	/**********************************************************************************
	 * Metodo que tokeniza un string por espacios retornando un arreglo con las
	 * palabras
	 * 
	 * @param palabras
	 *            string a tokenizar
	 * @return Arreglo con palabras tokenizadas
	 ***********************************************************************************/

	public String[] tokenizarKeys(String palabras) {
		String[] keys = palabras.split(" ");
		return keys;
	}

	/*************************************************************
	 * 
	 * @param path
	 * @return
	 **************************************************************/
	public String[] nextTounderScore(String path) {
		String[] txt = path.split("\n");
		String[] txtR = new String[txt.length];
		String line = "";
		for (int i = 0; i < txt.length; i++) {
			line = charToString(txt[i]);
			txtR[i] = line;
		}
		return txtR;
	}

	/*********************************************************
	 * 
	 * @param path
	 * @return
	 *********************************************************/
	public String charToString(String path) {
		char[] pathToParts = path.toCharArray();
		String nameFile = "";
		for (int i = path.indexOf('_') + 1; i < pathToParts.length; i++) {
			nameFile += pathToParts[i];
		}
		return nameFile;
	}

	/*****************************************************************
	 * Metodo que crea un archivo, si ya existe lo retorna
	 * 
	 * @return el archivo plano
	 ******************************************************************/
	public File crearArchivo(String nombre) {
		File file = new File(nombre + ".txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
			}
		}
		return file;
	}

	/*****************************************************************************
	 * Metodo que devuelve el tamaño del archivo plano , el tamaño son las
	 * líneas
	 * 
	 * @param archivo
	 * @return Líneas que hayan en el archivo plano
	 *******************************************************************************/
	public int size(File archivo) {
		FileReader fr = null;
		BufferedReader br = null;
		String linea = "";
		int cont = 0;
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			while ((linea = br.readLine()) != null) {
				cont++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero
			try {
				if (null != fr) {
					fr.close();

				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cont;
	}

	/**************************************************************
	 * Metodo que lee las lineas del archivo plano
	 * 
	 * @param archivo
	 *            archivo plano a leer
	 * @return string
	 ***************************************************************/
	public String leerFile(File archivo) {// , int index) {
		FileReader fr = null;
		BufferedReader br = null;
		String linea = null;
		String acum = "";
		int cont = 0;

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			// Lectura del fichero
			while ((linea = br.readLine()) != null) {
				// linea += linea+"";
				// System.out.println(linea);
				acum += linea + "\n";
				// cont++;
				// if (cont == index) {

				// }

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero
			try {
				if (null != fr) {
					fr.close();

				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return acum;
	}

	/**
	 * Metodo que añade el nodo al archivo plano
	 * 
	 * @param claseContraseña
	 * @param contraseña
	 * @param flag
	 * @param archivo
	 */
	public void add(String nombre, File archivo) {
		FileWriter fw = null;
		PrintWriter pw = null;
		String linea = "";
		try {
			linea = nombre + "\n";
			fw = new FileWriter(archivo, false);
			fw.append(linea);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fw)
					fw.close();
				if (null != pw) {
					pw.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * Metodo que abre archivos con programas de windows dependiendo de su
	 * extencion
	 * @param path del archivo
	 */
	public void openFile(String path) {
		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File(path);
				Desktop.getDesktop().open(myFile);
			} catch (IOException ex) {
				// no application registered for PDFs
			}
		}
	}
	
	/**
	 * Elimina el archivo que haya en el path
	 * indicado
	 * @param path
	 */
	public void deleteFile(String path){

		File ruta = new File(path);
		if ( ruta.delete() ){
			JOptionPane.showMessageDialog(null, "Se elimino el archivo");
		}else{
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el archivo");
		}

	}

}
