package com.ds.algo.heap;

import com.ds.algo.binarytree.Node;

public class BinaryHeap {
	Node root;
	private Node leftMost;
	
	BinaryHeap(Integer key) {
		if (key != null) {
			root = new Node(key, null, null);
			leftMost = root;
		}
	}
	
	public Integer max() {
		if(root == null) {
			return null;
		}
		return root.getKey();
	}
	
	public Integer extractMax() {
		return null;
	}
	
	public void insert(Integer key) {
		
	}
	
	public void remove(Integer key) {
	}
	
	public void changePriority(Integer i, Integer p) {
	}
}
