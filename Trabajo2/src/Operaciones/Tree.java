package Operaciones;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>> {

	private BinaryNode<T> raiz;

	public Tree(BinaryNode<T> raiz) {
		super();
		this.raiz = raiz;
	}

	public Tree() {
		super();
	}

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

	public int countLeaf() {
		return countLeaf(raiz, 0);
	}

	private int countLeaf(BinaryNode<T> n, int contador) {

		if (isLeaf(n)) {
			return 1;
		}
		if (n.getLeft() != null) {
			contador += countLeaf(n.getLeft(), 0);
		}
		if (n.getRight() != null) {
			contador += countLeaf(n.getRight(), 0);
		}

		return contador;
	}

	public int hight() {
		return hight(raiz, 1, 1);
	}

	private int hight(BinaryNode<T> n, int alturaL, int alturaR) {

		if (n.getLeft() != null) {	
			alturaL = hight(n.getLeft(), alturaL + 1, 0);
		}

		if (n.getRight() != null) {
			alturaR = hight(n.getRight(), 0, alturaR + 1);
		}

		if (alturaR > alturaL) {
			return alturaR;
		} else {
			return alturaL;
		}
	}

	public boolean contains(BinaryNode<T> n) {

		return contains(raiz, n);
	}

	private boolean contains(BinaryNode<T> n, BinaryNode<T> n2) {

		boolean izq = false;
		boolean der = false;

		if (n.equals(n2)) {
			return true;
		} else {

			if (n.getLeft() != null)
				izq = contains(n.getLeft(), n2);

			if (n.getRight() != null)
				der = contains(n.getRight(), n2);
		}

		if (izq == true) {
			return izq;
		} else {
			return der;
		}
	}

	public String cutTree() {
		cutTree(raiz);
		return inOrder();
	}

	private void cutTree(BinaryNode<T> n) {

		if (n.getLeft() != null)
			if (isLeaf(n.getLeft())) {
				n.setLeft(null);
			} else
				cutTree(n.getLeft());

		if (n.getRight() != null)
			if (isLeaf(n.getRight())) {
				n.setRight(null);
			} else
				cutTree(n.getRight());
	}

	public BinaryNode<T> higher() {
		return higher(raiz, raiz, raiz);
	}

	private BinaryNode<T> higher(BinaryNode<T> n, BinaryNode<T> mayorL, BinaryNode<T> mayorR) {

		BinaryNode<T> mayorTempR = null;
		BinaryNode<T> mayorTempL = null;

		if (n.getLeft() != null) {
			if (n.getLeft().compareTo(n) == 1) {
				mayorTempL = n.getLeft();
			} else {
				mayorTempL = n;
			}
			if (mayorTempL.compareTo(mayorL) == 1) {
				mayorL = mayorTempL;
			}
		}

		if (n.getRight() != null) {
			if (n.getRight().compareTo(n) == 1) {
				mayorTempR = n.getRight();
			} else {
				mayorTempR = n;
			}
			if (mayorTempR.compareTo(mayorR) == 1) {
				mayorR = mayorTempR;
			}
		}

		if (n.getLeft() != null)
			mayorL = higher(n.getLeft(), mayorL, mayorR);

		if (n.getRight() != null)
			mayorR = higher(n.getRight(), mayorL, mayorR);

		if (mayorL.compareTo(mayorR) == 1) {
			return mayorL;
		} else {
			return mayorR;
		}
	}

	public String swap() {
		swap(raiz);
		return preOrder();
	}

	private void swap(BinaryNode<T> n) {
		
		BinaryNode<T> temp = new BinaryNode<T>(n.getLeft(), n.getRight(), n.getItem());
		n.setRight(n.getLeft());
		n.setLeft(temp.getRight());
		
		if(n.getLeft() != null){
			swap(n.getLeft());
		}
		if(n.getRight() != null){
			swap(n.getRight());
		}

	}

	public boolean isOrdered(){
		return isOrdered(raiz);	
	}
	
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

	public ArrayList<BinaryNode<T>> toArray(){
		return toArray(raiz);
	}
	
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

	public BinaryNode<T> insert(BinaryNode<T> n){
		return insert(raiz, n);
	}
	
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

	public boolean contains2(BinaryNode<T> n){
		return contains2(raiz, n);
	}
	
	private boolean contains2(BinaryNode<T> n, BinaryNode<T> n2){
		
		if(!(isLeaf(n))){
			if(n.compareTo(n2) == 0){
				return true;
			}else if(n2.compareTo(n) < 0){
				if(n.getLeft() != null){
					return contains2(n.getLeft(), n2);
				}else{
					return false;
				}
			}else{
				if(n.getRight() != null){
					return contains2(n.getRight(), n2);
				}else{
					return false;
				}
			}
		}else if(n2.compareTo(n) != 0){
			return false;
		}		
		return true;
	}

	public void compareSearch(BinaryNode<T> n) throws Exception{
		
		Long l1 = System.currentTimeMillis();
		Thread.sleep(4000);
		contains2(n);
		Long l2 = System.currentTimeMillis();
		long t = l2-l1;
		System.out.println("Tiempo busqueda"+(t));
		
		ArrayList<BinaryNode<T>> arreglo = toArray();
		
		Long l3 = System.currentTimeMillis();
		Thread.sleep(4000);
		arreglo.contains(n);
		
		Long l4 = System.currentTimeMillis();
		long t2 = l4-l3;
		System.out.println("Tiempo de búsqueda"+(t2));
	}
	
	public void compareInsert(BinaryNode<T> n) throws Exception{
		
		Long l1 = System.currentTimeMillis();
		Thread.sleep(4000);
		insert(n);
		Long l2 = System.currentTimeMillis();
		long t = l2-l1;
		System.out.println("Tiempo busqueda"+(t));
		
		ArrayList<BinaryNode<T>> arreglo = toArray();
		
		Long l3 = System.currentTimeMillis();
		Thread.sleep(4000);		
		arreglo.add(n);
		Long l4 = System.currentTimeMillis();
		long t2 = l4-l3;
		System.out.println("Tiempo de búsqueda"+(t2));
	}
	
}
