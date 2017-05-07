/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ochoscar
 */
public class BinaryTree<T extends Comparable<T>> {

	private BinaryNode<T> root;

	public BinaryTree() {
	}

	public void insert(T item, String archivo) {
		BinaryNode<T> comparar = findNode(item);
		if (comparar == null) {
			BinaryNode<T> newNode = new BinaryNode<>(null, null, item);

			if (root == null) {
				root = newNode;
				agregarALista(newNode, archivo);
				return;
			}

			BinaryNode<T> current = root;
			BinaryNode<T> parent = root;
			boolean isLeft = false;
			while (current != null) {
				parent = current;
				if (current.compareTo(newNode) > 0) {
					current = current.getLeft();
					isLeft = true;
				} else {
					current = current.getRight();
					isLeft = false;
				}
			}
			if (isLeft) {
				parent.setLeft(newNode);
			} else {
				parent.setRight(newNode);
			}
			agregarALista(newNode, archivo);
		} else {
			agregarALista(comparar, archivo);
		}
	}

	
	private void agregarALista(BinaryNode<T> nodo, String archivo) {
		nodo.agregarANodo(archivo);
	}

	public boolean findItem(T item) {
		BinaryNode<T> current = root;

		while (current != null) {
			if (current.getItem().compareTo(item) == 0) {
				return true;
			} else if (current.getItem().compareTo(item) > 0) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		return false;
	}

	public BinaryNode<T> findNode(T item) {
		BinaryNode<T> current = root;

		while (current != null) {
			if (current.getItem().compareTo(item) == 0) {
				return current;
			} else if (current.getItem().compareTo(item) > 0) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		return null;
	}

	public boolean delete(T item) {
		// Encontrar el nodo a eliminar y su padre
		BinaryNode<T> current = root;
		BinaryNode<T> parent = current;
		boolean isLeft = false;

		while (current != null) {
			if (current.getItem().compareTo(item) == 0) {
				break;
			} else if (current.getItem().compareTo(item) > 0) {
				parent = current;
				current = current.getLeft();
				isLeft = true;
			} else {
				parent = current;
				current = current.getRight();
				isLeft = false;
			}
			if (current == null) {
				return false;
			}
		}

		// Caso 1: No se tienen hijos
		if (current.getLeft() == null && current.getRight() == null) {
			if (current == root) {
				root = null;
			} else if (isLeft) {
				parent.setLeft(null);
			} else {
				parent.setRight(null);
			}
		} else if (current.getLeft() != null && current.getRight() == null) {
			// Caso 2: se tiene un hijo izquierdo
			if (current == root) {
				root = current.getLeft();
			} else if (isLeft) {
				parent.setLeft(current.getLeft());
			} else {
				parent.setRight(current.getLeft());
			}
		} else if (current.getLeft() == null && current.getRight() != null) {
			// Caso 2: se tiene un hijo derecho
			if (current == root) {
				root = current.getRight();
			} else if (isLeft) {
				parent.setLeft(current.getRight());
			} else {
				parent.setRight(current.getRight());
			}
		} else {
			// Caso 3: se tienen dos hijos
			BinaryNode<T> successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeft) {
				parent.setLeft(successor);
			} else {
				parent.setRight(successor);
			}
			successor.setLeft(current.getLeft());
		}

		return true;
	}

	private BinaryNode<T> getSuccessor(BinaryNode<T> node) {
		BinaryNode<T> successorParent = node;
		BinaryNode<T> successor = node;
		BinaryNode<T> current = successor.getRight();

		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.getLeft();

			if (current == null) {
				// hace las conexiones
				successorParent.setLeft(successor.getRight());
				successor.setRight(node.getRight());
			}
		}

		return successor;
	}

	/**
	 * @return the root
	 */
	public BinaryNode<T> getRoot() {
		return root;
	}

	/**
	 * @param root
	 *            the root to set
	 */
	public void setRoot(BinaryNode<T> root) {
		this.root = root;
	}

	/**
	 * Displays the tree in a String
	 * 
	 * @return
	 */
	public String displayTree() {
		if (root != null) {
			return this.toString(new StringBuilder(), true, new StringBuilder(), root).toString();
		} else {
			return "Empty tree";
		}
	}

	/**
	 * 
	 * @param prefix
	 * @param esIzquierdo
	 * @param sb
	 * @param node
	 * @return
	 */
	private StringBuilder toString(StringBuilder prefix, boolean esIzquierdo, StringBuilder sb, BinaryNode<T> node) {
		System.out.println(node.getItem() + ": " + node.elementos());
		if (node.getRight() != null) {
			toString(new StringBuilder().append(prefix).append(esIzquierdo ? "│   " : "    "), false, sb,
					node.getRight());
		}
		sb.append(prefix).append(esIzquierdo ? "└── " : "┌── ").append(node.getItem().toString()).append("\n");
		if (node.getLeft() != null) {
			toString(new StringBuilder().append(prefix).append(esIzquierdo ? "    " : "│   "), true, sb,
					node.getLeft());
		}
		return sb;
	}

	/**
	 * Busca en el árbol la palabra clave a encontrar
	 * 
	 * @param item   palabra a buscar
	 * @return lista de elementos en el nodo.
	 */
	public String buscarPalabra(T item) {

		BinaryNode<T> comparar = findNode(item);

		if (comparar == null)
			return "No se encuentran elementos con esta etiqueta";
		else {
			return comparar.elementos();
		}
	}

	private void eliminarNodos(BinaryNode<T> node){
		if(node.arraySize() == 0){
			delete(node.getItem());
			eliminarNodos(root);
			return;
		}else{
			if(node.getLeft() != null){
				eliminarNodos(node.getLeft());
			}
			if(node.getRight() != null){
				eliminarNodos(node.getRight());
			}
		}
	}
	
	public String levelOrder() {
		if(root != null){
			return levelOrder(root);
		}
		return "";
	}
	
	/****************************
	 * 
	 * @param raiz
	 * @return
	 ****************************/

	public String levelOrder(BinaryNode<T> raiz) {
		 String result = "";
		 	Queue<BinaryNode<T>> q = new LinkedList<BinaryNode<T>>();
		    q.add (this.getRoot());
		    while (!(q.isEmpty())) {
		     BinaryNode<T> node = q.remove ();
		     result += node.getItem()+" ";
		     if (node.getLeft()!= null) {
		       q.add(node.getLeft());
		     }
		     if (node.getRight() != null) {
		       q.add(node.getRight());
		     }
		   }
		   return result;
	}
	
	
	public void eliminarALvl(String archivo) {
		eliminarALvl(root, archivo);
		eliminarNodos(root);
	}


	/****************************
	 * 
	 * @param raiz
	 * @return
	 ****************************/

	private String eliminarALvl(BinaryNode<T> raiz,String archivo) {
		 String result = "";
		 	Queue<BinaryNode<T>> q = new LinkedList<BinaryNode<T>>();
		    q.add (this.getRoot());
		    
		    while (!(q.isEmpty())) {
		     BinaryNode<T> node = q.remove ();
		 	if (node.encotrarEnArreglo(archivo)) {
				node.deleteFromArray(archivo);
			}
		     result += node.getItem()+" ";		    
		     if (node.getLeft()!= null) {
		       q.add(node.getLeft());
		       
		     }
		     if (node.getRight() != null) {
		       q.add(node.getRight());
		   
		     }
		   }
		   return result;
	}
	


}
