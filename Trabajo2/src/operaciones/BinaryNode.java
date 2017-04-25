package operaciones;

/**
 * Hojas del arból en las cuales se almacenan cada uno de los items
 * @author Sebastian Luna R
 *
 * @param <T> Parámetro para definir el tipo del árbol, debe de ser tipo Comparable
 */
public class BinaryNode<T extends Comparable<T>> implements Comparable<BinaryNode<T>> {

	/**
	 * Hijo izquierdo
	 */
	private BinaryNode<T> left;

	/**
	 * Hijo derecho
	 */
	private BinaryNode<T> right;

	/**
	 * Contenido del nodo
	 */
	private T item;

	/**
	 * Constructor de la clase nodo
	 * @param left hijo izquierdo
	 * @param right hijo derecho
	 * @param item Contenido del nodo
	 */
	public BinaryNode(BinaryNode<T> left, BinaryNode<T> right, T item) {
		super();
		this.left = left;
		this.right = right;
		this.item = item;
	}

	//////////////////////////////////////////////
	//////Getters y Setters
	/////////////////////////////////////////////
	/**
	 * Obtiene el hijo izquierdo
	 * @return
	 */
	public BinaryNode<T> getLeft() {
		return left;
	}
	
	/**
	 * Modifica el hijo izquierdo
	 * @param left nuevo hijo izquierdo
	 */
	public void setLeft(BinaryNode<T> left) {
		this.left = left;
	}

	/**
	 * Obtiene hijo derecho
	 * @return right hijo derecho
	 */
	public BinaryNode<T> getRight() {
		return right;
	}

	/**
	 * Modifica el hijo derecho
	 * @param right nuevo hijo derecho 
	 */
	public void setRight(BinaryNode<T> right) {
		this.right = right;
	}
	
	/**
	 * Obtiene contenido del nodo
	 * @return contenido del nodo
	 */
	public T getItem() {
		return item;
	}
	
	/**
	 * Modifica item del nodo
	 * @param item Nuevo contenido
	 */
	public void setItem(T item) {
		this.item = item;
	}

	
	///////////////////////////////////////
	///////Métodos
	///////////////////////////////////////
	/**
	 * Compara un nodo con otro nodo.
	 * devuelve 0 si son iguales, 1 si es mayor y -1 si es menor.
	 */
	@Override
	public int compareTo(BinaryNode<T> o) {

		if (this.item.compareTo(o.getItem()) > 0) {
			return 1;
		} else if (this.item.compareTo(o.getItem()) == 0) {
			return 0;
		} else
			return -1;
	}
	
	/**
	 * Método encargado de imprimir el contenido del nodo
	 */
	@Override
	public String toString() {
		return "" + item.toString() + "";
	}
}
