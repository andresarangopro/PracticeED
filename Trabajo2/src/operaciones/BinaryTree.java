/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import java.util.Stack;

/**
 *
 * @author ochoscar
 */
public class BinaryTree<T extends Comparable<T>> {
    
    private BinaryNode<T> root;
    
    public BinaryTree() {}
    
    public void insert(T item) {
        BinaryNode<T> comparar = findNode(item);
    	if(comparar == null){
        	BinaryNode<T> newNode = new BinaryNode<>(null, null, item);
            
            if(root == null) {
                root = newNode;
                return;
            }
            
            BinaryNode<T> current = root;
            BinaryNode<T> parent = root;
            boolean isLeft = false;
            while(current != null) {
                parent = current;
                if(current.compareTo(newNode) > 0) {
                    current = current.getLeft();
                    isLeft = true;
                } else {
                    current = current.getRight();
                    isLeft = false;
                }
            }
            if(isLeft) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
            }
    	}else{
    		comparar.agregarANodo(item);
    	}
    }
    
    public boolean findItem(T item) {
        BinaryNode<T> current = root;
        
        while(current != null) {
            if(current.getItem().compareTo(item) == 0) {
                return true;
            } else if(current.getItem().compareTo(item) > 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return false;
    }
    
    private BinaryNode<T> findNode(T item) {
        BinaryNode<T> current = root;
        
        while(current != null) {
            if(current.getItem().compareTo(item) == 0) {
                return current;
            } else if(current.getItem().compareTo(item) > 0) {
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
        
        while(current != null) {
            if(current.getItem().compareTo(item) == 0) {
                break;
            } else if(current.getItem().compareTo(item) > 0) {
                parent = current;
                current = current.getLeft();
                isLeft = true;
            } else {
                parent = current;
                current = current.getRight();
                isLeft = false;
            }
            if(current == null) {
                return false;
            }
        }
        
        // Caso 1: No se tienen hijos
        if(current.getLeft() == null && current.getRight() == null) {
            if(current == root) {
                root = null;
            } else if(isLeft) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if(current.getLeft() != null && current.getRight() == null) {
            // Caso 2: se tiene un hijo izquierdo
            if(current == root) {
                root = current.getLeft();
            } else if(isLeft) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }
        } else if(current.getLeft() == null && current.getRight() != null) {
            // Caso 2: se tiene un hijo derecho
            if(current == root) {
                root = current.getRight();
            } else if(isLeft) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
        } else {
            // Caso 3: se tienen dos hijos
            BinaryNode<T> successor = getSuccessor(current);
            if(current == root) {
                root = successor;
            } else if(isLeft) {
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
        
        while(current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeft();
            
            if(current == null) {
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
     * @param root the root to set
     */
    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }
    
    /**
     * Displays the tree in a String
     * @return
     */
	public String displayTree(){
		if(root != null){
			return this.toString(new StringBuilder(), true, new StringBuilder(), root).toString();
		}else{
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
	    if(node.getRight() !=null) {
	        toString(new StringBuilder().append(prefix).append(esIzquierdo ? "│   " : "    "), false, sb, node.getRight());
	    }
	    sb.append(prefix).append(esIzquierdo ? "└── " : "┌── ").append(node.getItem().toString()).append("\n");
	    if(node.getLeft() != null) {
	    	toString(new StringBuilder().append(prefix).append(esIzquierdo ? "    " : "│   "), true, sb, node.getLeft());
	    }
	    return sb;
	}
    
}
