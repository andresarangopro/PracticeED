package operaciones;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Clase arbol que almacena los nodos binarios
 * @author Sebastian Luna R
 *
 * @param <T> Item, debe de implementar Comparable
 */
public class Tree<T extends Comparable<T>> {

	/**
	 * Primer nodo del �rbol (Raiz).
	 */
	private BinaryNode<T> raiz;

	/**
	 * Constructor de la clase Tree
	 * @param raiz primer nodo del �rbol
	 */
	public Tree(BinaryNode<T> raiz) {
		super();
		this.raiz = raiz;
	}

	/**
	 * Constructor de la clase Tree
	 */
	public Tree() {
		super();
	}

	/**
	 * M�todo que compara si un nodo es hoja del �rbol o no, 
	 * @param n nodo a comparar
	 * @return devuelve true si es hoja, false en el caso contrario 
	 */
	private boolean isLeaf(BinaryNode<T> n) {
		return n.getLeft() == null && n.getRight() == null;
	}
	
	public String preOrder() {
		return preOrder(raiz);
	}

	private String preOrder(BinaryNode<T> n) {

		String result = "";

		result += n.toString();

		if (n.getLeft() != null)
			result += this.preOrder(n.getLeft());
		if (n.getRight() != null)
			result += this.preOrder(n.getRight());

		return result;
	}

	public String posOrder() {
		return posOrder(raiz);
	}

	private String posOrder(BinaryNode<T> n) {

		String result = "";

		if (n.getLeft() != null)
			result += this.preOrder(n.getLeft());
		if (n.getRight() != null)
			result += this.preOrder(n.getRight());

		result += n.toString();

		return result;
	}

	public String inOrder() {
		return inOrder(raiz);
	}

	private String inOrder(BinaryNode<T> n) {

		String result = "";

		if (n.getLeft() != null)
			result += this.preOrder(n.getLeft());

		result += n.toString();

		if (n.getRight() != null)
			result += this.preOrder(n.getRight());

		return result;
	}

	/**
	 * M�todo que indica si un �rbol est� ordenado
	 * @return true si lo est�, flase en el caso contrario
	 */
	public boolean isOrdered(){
		return isOrdered(raiz);	
	}
	
	/**
	 * M�todo que indica si un �rbol est� ordenado
	 * @param n raiz del �rbol
	 * @return true si lo est�, false en el caso contrario
	 */
	private boolean isOrdered(BinaryNode<T> n){
		
		if(!isLeaf(n)){
			boolean compararR = false;
			boolean compararL = false;
			if(n.getLeft() != null){
				if(n.compareTo(n.getLeft()) >= 0){
					compararL = isOrdered(n.getLeft());
				}else{
					compararL = false;
				}
			}
			if(n.getRight() != null){
				if(n.compareTo(n.getRight()) >= 0){
					compararR = isOrdered(n.getRight());
				}else{
					compararR = false;
				}
			}
			
			if(compararL && compararR){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	/**
	 * Convierte un �rbol en Arraylist
	 * @return ArrayList del �rbol
	 */
	public ArrayList<BinaryNode<T>> toArray(){
		return toArray(raiz);
	}
	
	/**
	 * Convierte un �rbol en Arraylist.
	 * @param n Raiz del �rbol
	 * @return ArrayList del arbol
	 */
	private ArrayList<BinaryNode<T>> toArray(BinaryNode<T> n){
		
		ArrayList<BinaryNode<T>> array = new ArrayList<>();
		
		Queue<BinaryNode<T>> cola = new LinkedList<BinaryNode<T>>();

		cola.add(n);

		
		while(!(cola.isEmpty())){
			BinaryNode<T> nodo = cola.remove();
			array.add(nodo);
			if(nodo.getLeft() != null){
				cola.add(nodo.getLeft());
			}
			if(nodo.getRight() != null){
				cola.add(nodo.getRight());
			}
		}
		
		
		return array;
	}

	/**
	 * Inserta ordenadamente en el �rbol
	 * @param n Nodo a insertar
	 * @return Nodo insertado
	 */
	public BinaryNode<T> insert(BinaryNode<T> n){
		return insert(raiz, n);
	}
	
	/**
	 * M�todo usado para insertar ordenadamente en un �rbol
	 * @param n nodo a ser comparado para insertar
	 * @param n2 nodo a insertar
	 * @return nodo insertado
	 */
	private BinaryNode<T> insert(BinaryNode<T> n, BinaryNode<T> n2){
		
		if(n2.compareTo(n) < 0){
			if(n.getLeft() == null){
				n.setLeft(n2);
			}else{
				insert(n.getLeft(), n2);
			}
		}else{
			if(n.getRight() == null){
				n.setRight(n2);
			}else{
				insert(n.getRight(), n2);
			}
		}	
		return n2;
	}

	/**
	 * Indica si un nodo est� en el �rbol
	 * @param n Nodo a ser comparado en el �rbol
	 * @return True si lo encuentra, false en el caso contrario
	 */
	public boolean contains(BinaryNode<T> n){
		return contains(raiz, n);
	}
	
	/**
	 * Compara si un nodo est� contenido en un �rbol
	 * @param n Nodo que es comparado
	 * @param n2 Nodo a saber si est� en el �rbol
	 * @return True si est�, false en el caso contrario
	 */
	private boolean contains(BinaryNode<T> n, BinaryNode<T> n2){
		
		if(!(isLeaf(n))){
			if(n.compareTo(n2) == 0){
				return true;
			}else if(n2.compareTo(n) < 0){
				if(n.getLeft() != null){
					return contains(n.getLeft(), n2);
				}else{
					return false;
				}
			}else{
				if(n.getRight() != null){
					return contains(n.getRight(), n2);
				}else{
					return false;
				}
			}
		}else if(n2.compareTo(n) != 0){
			return false;
		}		
		return true;
	}

	public String imprimirArbol(){
		return this.toString(new StringBuilder(), true, new StringBuilder(), raiz).toString();
	}

	private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb, BinaryNode<T> node) {
	    if(node.getRight() !=null) {
	        toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, node.getRight());
	    }
	    sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.getItem().toString()).append("\n");
	    if(node.getLeft() != null) {
	    	toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, node.getLeft());
	    }
	    return sb;
	}

}
