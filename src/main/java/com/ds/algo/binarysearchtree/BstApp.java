package com.ds.algo.binarysearchtree;

public class BstApp {

	public static void main(String[] args) {
		int[] averageUtil = {66, 81, 56, 98, 25, 36, 81, 47, 62, 7, 54, 18, 24, 93, 61, 20, 39, 55, 62, 91, 42, 66, 19, 85, 16, 0, 20, 96, 55, 54, 2, 4, 24, 20, 84, 97, 0, 24, 54, 22, 44, 21, 94, 13, 73, 11, 4, 91, 4, 58, 92, 47, 39, 17, 77, 0, 71, 26, 73, 27, 51, 39,
				6, 73, 8, 80, 40, 3, 48, 41, 68, 44, 51, 7, 60 , 27, 91, 42, 15, 15, 13, 89, 5, 54, 62, 15, 28, 43, 59, 85, 34, 10, 14, 63, 43, 14, 60, 41, 89, 65, 77};
		
		int instances = 191;
		int inst_limit = 200000000;
		int index = 0;
		while(index < averageUtil.length ) {
			System.out.println(" Idex:" + index);
			if (averageUtil[index] <25 && instances > 1 ) {
				instances =  (int) Math.ceil((double)instances/2.0);
				index+=10;
				System.out.println("" + instances + " Idex:" + index);
				continue;
			}
			if (averageUtil[index] >60  && instances < inst_limit/2 ) {
				instances = instances*2;
				index+=10;
				System.out.println("" + instances + " Idex:" + index);
				continue;
			}
			index++;
		}
		System.out.println(instances);
	}

}
