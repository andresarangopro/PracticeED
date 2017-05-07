package mundo;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class WindowsListener implements WindowListener{
	
	 InterfazG  Interfazg;	

		//////////////////
		// CONSTRUCTOR
		/////////////////

		WindowsListener(InterfazG interfaz) {
			this.Interfazg = interfaz;

		}
	 	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		int sizeTree= Interfazg.manejoArchivo.size(Interfazg.fileTree);

		if(sizeTree > 0){
			String[] tree = Interfazg.manejoArchivo.leerFile(Interfazg.fileTree).split(" ");//, index)
			String[] array = Interfazg.manejoArchivo.leerFile(Interfazg.fileList).split("\n");
			String[] archivo;
		
				for (int j = 0; j < array.length; j++) {
					archivo = array[j].split(",");
					for (int k = 0; k < archivo.length; k++) {
						Interfazg.arbol.insert(tree[j], archivo[k]);
					}
				}}
		}

	@Override
	public void windowClosing(WindowEvent e) {
		String treeLevelOrder = Interfazg.arbol.levelOrder();
		String [] arrLevelTree = treeLevelOrder.split(" ");
		String listTree = listaNodosForFile(arrLevelTree);
		Interfazg.manejoArchivo.add(treeLevelOrder, Interfazg.fileTree);
		Interfazg.manejoArchivo.add(listTree, Interfazg.fileList);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub		
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public String listaNodosForFile(String[] listNods){
		String result = "";
		for (int i = 0; i < listNods.length; i++) {
			result += Interfazg.arbol.findNode(listNods[i]).elementosForFile()+"\n";
		}
		return result;
	}

}
