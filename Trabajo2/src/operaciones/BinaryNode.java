/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import java.util.ArrayList;

/**
 *
 * @author ochoscar
 */
public class BinaryNode<T extends Comparable<T>> implements Comparable<BinaryNode<T>> {
    
    private BinaryNode<T> left;
    
    private BinaryNode<T> right;
    
    private T item;
    
    private ArrayList<String> archivos = new ArrayList<>();
    
    public BinaryNode() {
    }
    
    public BinaryNode(BinaryNode<T> left, BinaryNode<T> right, T item) {
        this.left = left;
        this.right = right;
        this.item = item;
    }
    
    @Override
    public String toString() {
        return item.toString();
    }
    
    @Override
    public int compareTo(BinaryNode<T> o) {
        return this.item.compareTo(o.getItem());
    }
    
    /**
     * @return the left
     */
    public BinaryNode<T> getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public BinaryNode<T> getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(BinaryNode<T> right) {
        this.right = right;
    }

    /**
     * @return the item
     */
    public T getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(T item) {
        this.item = item;
    }

    public int tamano(){
    	return archivos.size();
    }
    
    public ArrayList<String> getArchivos() {
		return archivos;
	}

	/**
     * Agrega objeto al arraylist
     * @param item objeto a ser agregado
     */
    public void agregarANodo(String item){
    	archivos.add(item);
    }
    
   
    
    /**
     * Devuelve una lista con todos los elementos existentes en el nodo;
     * @return String con todos los elementos
     */
    public String elementos(){
    	String elementos = "";
    	
    	for (int i = 0; i < archivos.size(); i++) {
			elementos += archivos.get(i) + "\n";
		}
    	
    	return elementos;    	
    }
    
    /**
     * Devuelve una lista con todos los elementos existentes en el nodo;
     * @return String con todos los elementos
     */
    public String elementosForFile(){
    	String elementos = "";
    	
    	for (int i = 0; i < archivos.size(); i++) {
			if(i+1 < archivos.size()){
				elementos += archivos.get(i) + ",";
			}else{
				elementos += archivos.get(i) ;
			}
		}
    	
    	return elementos;    	
    }

    /**
     * Busca un elemento en el arreglo
     * @param archivo elemento a ser buscado
     * @return true si se encuentra, false en el caso contrario.
     */
    public boolean encotrarEnArreglo(String archivo){
    	System.out.println(this);
    	for (String string : archivos) {
			if(archivo.equals(string)){
				return true;
			}
		}
    	return false;
    	
    }
    
    public void deleteFromArray(String item){
    	
    	ArrayList<String> eliminados = new ArrayList<>();
    	
    	for (int i = 0; i < archivos.size(); i++) {
    		if(archivos.get(i).equals(item)){
    			eliminados.add(archivos.get(i));
    		}
		}
    	archivos.removeAll(eliminados);
    }
    
    public int arraySize(){
    	return archivos.size();
    }
    
    
}
