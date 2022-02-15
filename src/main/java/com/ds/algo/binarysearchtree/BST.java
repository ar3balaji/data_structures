package com.ds.algo.binarysearchtree;

import com.ds.algo.binarytree.Node;

public class BST {
	
	private Node root;

	public void display() {
		System.out.println("Binary search tree contents!");
		
		if (root == null) {
			System.out.println("Binary search tree is empty");
			return;
		}

		displayInOrder(root);
	}

	private void displayInOrder(Node currentNode) {
		if (currentNode == null) {
			return;
		}
		displayInOrder(currentNode.getLeft());
		System.out.print(" " + currentNode.getKey() + " ");
		displayInOrder(currentNode.getRight());
	}

	public void insert(int key) {
		if (root == null) {
			root = new Node(key, null, null);
		} else {
			Node current = root;
			Node parent = null;
			boolean isLeft = false;

			while (current != null && current.getKey() != key) {
				parent = current;
				if (key < current.getKey()) {
					current = current.getLeft();
					isLeft = true;
				} else if (key > current.getKey()) {
					current = current.getRight();
					isLeft = false;
				}
			}

			if (isLeft) {
				parent.setLeft(new Node(key, null, null));
			} else {
				parent.setRight(new Node(key, null, null));
			}
		}
	}

	public Node search(int key) {
		if (root == null) {
			return null;
		} else {
			Node current = root;
			while (current != null) {
				if (current.getKey() == key) {
					return current;
				}
				if (key < current.getKey()) {
					current = current.getLeft();
				} else {
					current = current.getRight();
				}
			}
			return null;
		}
	}

	public void delete(int key) {

	}
}
