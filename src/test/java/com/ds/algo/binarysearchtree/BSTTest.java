package com.ds.algo.binarysearchtree;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ds.algo.binarytree.Node;

public class BSTTest {

	@Test
	public void testSearchFound() {
		BST bst = new BST();
		bst.insert(2);

		assertEquals(bst.search(2).getKey(), 2);
	}

	@Test
	public void testSearchNotFound() {
		BST bst = new BST();
		bst.insert(2);

		assertNull(bst.search(1));
	}


	@Test
	public void testSearchNotFoundWhenTreeIsEmpty() {
		BST bst = new BST();

		assertNull(bst.search(1));
	}

	@Test
	public void testSearchFoundWhenMultipleNodesExist() {
		BST bst = new BST();
		bst.insert(1);
		bst.insert(2);
		bst.insert(3);

		assertEquals(bst.search(1).getKey(), 1);
	}

	@Test
	public void testSearchNotFoundWhenMultipleNodesExist() {
		BST bst = new BST();
		bst.insert(1);
		bst.insert(2);
		bst.insert(3);

		assertNull(bst.search(0));
	}

	@Test
	public void testSearchWhenKeyIsNegative() {
		BST bst = new BST();
		bst.insert(1);
		bst.insert(2);
		bst.insert(3);

		assertNull(bst.search(-2));
	}
	
	@Test
	public void testInsertionOrder() {
		BST bst = new BST();
		bst.insert(2);
		bst.insert(1);
		bst.insert(3);

		Node nodeFound = bst.search(2);
		assertNotNull(nodeFound);
		assertEquals(nodeFound.getKey(), 2);
		assertEquals(nodeFound.getLeft().getKey(), 1);
		assertEquals(nodeFound.getRight().getKey(), 3);
	}

}
