/*
 *
 题目描述：
 一个整型数组里除了一个数字之外，其它的数字都出现了两次。请写程序找出这个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 解答：
 异或。异或特点：任何一个数字异或它自己都等于0；异或0都等于它自己。
 对数组每个数字进行异或，最后得到的就是只出现一次（出现两次的已经异或为0）
 
 题目扩展：
 该数组中有两个数字（a，b）出现了一次，其它的数字都出现了两次。请写程序找出这个这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 解答：
 由前面分析得到：数组异或的结果为只出现一次的两个数异或的结果，并且肯定不为0（两数字不同）
 对异或结果求二进制数的第1个不为0的位置k（a，b这两个数第k位肯定一个为0，另一个为1），以k位置为依据，将数组分为两组，一组k位置为1，另一组k位置为0,，那么肯定a，b不在同一组
 分别再对这两组数异或，分别找到a，b

 */

package com.singleproblem;

public class NumsAppearOnce {

	// 求异或，求结果
	public int[] FindNumsAppearOnce(int data[]) {
		int len = data.length, resultExclusiveOR = 0;
		int ans1 = 0, ans2 = 0;// 两个只出现一次的数
		for (int i = 0; i < len; i++) {
			resultExclusiveOR ^= data[i]; // 异或结果
		}
		int k = FindFirstBitIs1(resultExclusiveOR); // 异或结果二进制的k位置不为0
		for (int i = 0; i < len; i++) {
			if (IsBit1(data[i], k))
				ans1 ^= data[i]; // 该组为k位置不为0
			else
				ans2 ^= data[i];// 该组k位置为0
		}
		return new int[] { ans1, ans2 };
	}

	// 求二进制数，第一个不为0的位置indexBit
	private int FindFirstBitIs1(int num) {
		int indexBit = 0;
		while (((num & 1) == 0) && (indexBit < 32)) {
			num = num >> 1; // 移动操作
			++indexBit;
		}
		return indexBit;
	}

	// 分组
	private boolean IsBit1(int num, int k) {
		num = num >> k;
		return ((num & 1) == 1);// k位置不为0返回true，否则返回false
	}

}
