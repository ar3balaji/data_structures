package com.ds.algo.array;

public abstract class Array {
	
	private int[] container;
	private int size;
	private int numberOfElements;
	
	Array(int size) {
		container = new int[size];
		this.size = size;
	}
	
	public int size() {
		return numberOfElements;
	}
	
	public int insert(int item) {
		int result = -1;
		if (numberOfElements < size && numberOfElements >= 0) {
			container[numberOfElements++] = item;
			result = numberOfElements - 1;
		}
		return result;
	}
	
	public int find(int item) {
		int result = -1;
		for (int i = 0; i < numberOfElements; i++) {
			if (container[i] == item) {
				result = i;
			}
		}
		return result;
	}

	public int delete(int item) {
		int result = -1;
		int itemIndex = find(item);
		if (itemIndex > -1) {
			for (int i = itemIndex; i < numberOfElements; i++) {
				if (i == numberOfElements - 1) {
					container[i] = 0;
					continue;
				}
				container[i] = container[i + 1];
			}
			numberOfElements--;
			result = 0;
		}
		return result;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < numberOfElements; i++) {
			result += container[i];
			if(i != numberOfElements - 1) {
				result += ",";
			}
		}
		return result;
	}

}
