package Operaciones;

public class BinaryNode<T extends Comparable<T>> implements Comparable<BinaryNode<T>> {

	private BinaryNode<T> left;

	private BinaryNode<T> right;

	private T item;

	public BinaryNode(BinaryNode<T> left, BinaryNode<T> right, T item) {
		super();
		this.left = left;
		this.right = right;
		this.item = item;
	}

	@Override
	public String toString() {
		return "" + item.toString() + "";
	}

	public BinaryNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryNode<T> left) {
		this.left = left;
	}

	public BinaryNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryNode<T> right) {
		this.right = right;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	@Override
	public int compareTo(BinaryNode<T> o) {

		if (this.item.compareTo(o.getItem()) > 0) {
			return 1;
		} else if (this.item.compareTo(o.getItem()) == 0) {
			return 0;
		} else
			return -1;
	}
}
