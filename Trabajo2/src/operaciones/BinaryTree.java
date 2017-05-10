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

	/**
	 * Inserta un objeto en un nodo, en caso de que el nodo no exista, lo crea.
	 * @param item Etiqueta la cuál es el nombre del nodo
	 * @param archivo Archivo a ser insertado en el nodo
	 */
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

	/**
	 * Agrega un archivo a un nodo
	 * @param nodo Nombre del nodo
	 * @param archivo Dirección del archivo a ser agregado
	 */
	private void agregarALista(BinaryNode<T> nodo, String archivo) {
		nodo.agregarANodo(archivo);
	}

	/**
	 * Encuentra un nodo.
	 * @param item Nodo a ser encontrado.
	 * @return true si lo encuentra, false en el caso contrario.
	 */
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

	/**
	 * Encuentra un nodo.
	 * @param item Nodo a ser encontrado.
	 * @return Nodo si es encontrado, null en el caso contrario.
	 */
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

	/**
	 * Elimina un nodo del arbol
	 * @param item Nodo a ser borrado
	 * @return true si es eliminado, false en el caso contrario.
	 */
	private boolean delete(T item) {
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

	/**
	 * Obtiene el sucesro del un nodo
	 * @param node Nodo a obtener sucesores
	 * @return sucesor del nodo
	 */
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
				if (successorParent != node) {
					successorParent.setLeft(successor.getRight());
				}
				if (successor != node.getRight()) {
					successor.setRight(node.getRight());
				}
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
	 * Cambia la raiz del árbol
	 * @param root nodo a ser la nueva raiz
	 */
	
	public void setRoot(BinaryNode<T> root) {
		this.root = root;
	}

	/**
	 * Imprime el arbol en un String
	 * @return String de árbol o "Arbol vacío" en caso de no tener nigún item.
	 */
	public String displayTree() {
		if (root != null) {
			return this.toString(new StringBuilder(), true, new StringBuilder(), root).toString();
		} else {
			return "Árbol vacío";
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
	 * Busca un nodo con una etiqueta
	 * @param item nodo a ser encontrado
	 * @return ""No se encuentran elementos con esta etiqueta" o los elementos del nodo en caso contrario
	 */
	public String buscarPalabra(T item) {

		BinaryNode<T> comparar = findNode(item);

		if (comparar == null)
			return "No se encuentran elementos con esta etiqueta";
		else {
			return comparar.elementos();
		}
	}

	/**
	 * Elmina los nodos que no tengan archivos en su interio
	 * @param node nodo a comprobar si tiene archivos
	 */
	private void eliminarNodos(BinaryNode<T> node) {
		if(node == null){
			return;
		}
		if (node.tamano() == 0) {
			delete(node.getItem());
			eliminarNodos(root);
			return;
		} else {
			if (node.getLeft() != null) {
				eliminarNodos(node.getLeft());
			}
			if (node.getRight() != null) {
				eliminarNodos(node.getRight());
			}
		}
	}

	/**
	 * Recorre el arbol en level order
	 * @return String con los elementos el lvlorder
	 */
	public String levelOrder() {
		if (root != null) {
			return levelOrder(root);
		}
		return "";
	}



	/**
	 * Recorree el arbol en lvlorder
	 * @param raiz raiz del arbol
	 * @return String con todos los elementos del árbol
	 */
	public String levelOrder(BinaryNode<T> raiz) {
		String result = "";
		Queue<BinaryNode<T>> q = new LinkedList<BinaryNode<T>>();
		q.add(this.getRoot());
		while (!(q.isEmpty())) {
			BinaryNode<T> node = q.remove();
			result += node.getItem() + " ";
			if (node.getLeft() != null) {
				q.add(node.getLeft());
			}
			if (node.getRight() != null) {
				q.add(node.getRight());
			}
		}
		return result;
	}

	/**
	 * Elimina un archivo de todas sus apariciones en el árbol
	 * @param archivo Archivo a ser eliminado
	 */
	public void eliminarALvl(String archivo) {
		eliminarALvl(root, archivo);
		eliminarNodos(root);
	}

	/**
	 * Elimina un archivo de todas sus apariciones
	 * @param raiz primer nodo del arbol
	 * @param archivo archivo a ser eliminado
	 * @return String con los elementos del arbol
	 */
	private String eliminarALvl(BinaryNode<T> raiz, String archivo) {
		String result = "";
		Queue<BinaryNode<T>> q = new LinkedList<BinaryNode<T>>();
		q.add(this.getRoot());

		while (!(q.isEmpty())) {
			BinaryNode<T> node = q.remove();
			if (node.encotrarEnArreglo(archivo)) {
				node.deleteFromArray(archivo);
			}
			result += node.getItem() + " ";
			if (node.getLeft() != null) {
				q.add(node.getLeft());

			}
			if (node.getRight() != null) {
				q.add(node.getRight());

			}
		}
		return result;
	}

}
