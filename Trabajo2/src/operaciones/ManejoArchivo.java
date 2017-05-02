package operaciones;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument.Content;

public class ManejoArchivo {
	
	///////////////////////
	//Metodos
	//////////////////////	
	
	/*********************************************************
	 * 
	 * @param direccion
	 **********************************************************/
	
	public void crearDirectorio(String direccion){
		File folder = new File(direccion);
		if (!folder.exists()) { 
			folder.mkdir();
		}		
		//if (folder.isDirectory()) {System.out.println("Es un directorio"); }		
		//if (folder.isFile()) { System.out.println("is file"); }
	}
	
	/************************************************************
	 * 
	 * @param txtArchivo
	 * @return
	 *************************************************************/
	
	public String leerArchivo(JTextField txtArchivo){		
		JFileChooser fileChooser = new JFileChooser();
		String dir = "";
		int seleccion = fileChooser.showOpenDialog(txtArchivo);	
		if (seleccion == JFileChooser.APPROVE_OPTION){
		   File fichero = fileChooser.getSelectedFile();
		   dir = fichero.toString();
		   txtArchivo.setText(dir);		
		   txtArchivo.setDisabledTextColor(Color.BLACK);
		}	
		return dir;	
	}
	
	/*************************************************************************
	 * copia el archivo s en otro directorio siendo este el archivo t
	 * @param s
	 * @param t
	 *************************************************************************/
	
    public void copyFile(File s, File t)
    {
        try{
              FileChannel in = (new FileInputStream(s)).getChannel();
              FileChannel out = (new FileOutputStream(t)).getChannel();
              in.transferTo(0, s.length(), out);
              in.close();
              out.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    /****************************************************************
     * 
     * @param direccion del archivo seleccionado
     * @param direccion2 es la direccion donde se copiar� el archivo
     *****************************************************************/
    public String add(String direccion, String direccion2){
    	String a = "";
    	String nombreD = "";
    	String[] p = direccion.split("\\\\");
		a = p[p.length-1];	
		System.out.println(a);
		String extencion = a.substring(a.indexOf("."), a.length());
		File fIn = new File(direccion);			
		char[] dir = a.toCharArray();
		for (int i = 0; i < a.indexOf("."); i++) {
			nombreD += dir[i];
		}
		System.out.println(nombreD);
		String name = System.currentTimeMillis()+"";
		File fOut = new File(direccion2+name+"_"+nombreD+extencion);
		copyFile(fIn, fOut);
		return direccion2+name+"_"+nombreD+extencion;
    }
    
    /*******************************************************
     * Metodo que tokeniza un string por espacios 
     * retornando un arreglo con las palabras
     * @param palabras string a tokenizar
     * @return Arreglo con palabras tokenizadas
     *********************************************************/
    
    public String[] tokenizarKeys(String palabras){
    	String[] keys = palabras.split(" ");
    	return keys;
    }
}
