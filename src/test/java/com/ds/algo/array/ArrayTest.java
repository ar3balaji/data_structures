package com.ds.algo.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayTest {
	
	@Test
	public void testInsert() {
		UnorderedArray obj = new UnorderedArray(10);
		obj.insert(0);
		obj.insert(1);
		assertEquals(obj.size(), 2);
	}
	
	@Test
	public void testFind() {
		UnorderedArray obj = new UnorderedArray(10);
		obj.insert(0);
		obj.insert(1);
		assertEquals(obj.find(1), 1);
	}
	
	@Test
	public void testItemNotExist() {
		UnorderedArray obj = new UnorderedArray(10);
		obj.insert(0);
		obj.insert(1);
		assertEquals(obj.find(3), -1);
	}
	
	@Test
	public void testDelete() {
		UnorderedArray obj = new UnorderedArray(10);
		obj.insert(0);
		obj.insert(1);
		obj.delete(1);
		assertEquals(obj.size(), 1);
	}
	
	@Test
	public void testDeleteItemNotExist() {
		UnorderedArray obj = new UnorderedArray(10);
		assertEquals(obj.delete(4), -1);
	}
	
	@Test
	public void testDisplay() {
		UnorderedArray obj = new UnorderedArray(10);
		obj.insert(0);
		obj.insert(1);
		assertEquals(obj.toString(), "0,1");
	}
}
