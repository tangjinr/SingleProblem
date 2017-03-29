/*
 *
 题目描述：
 给定入栈序列和出栈序列，判断给定的出栈序列是否合理
 
 解答：
 popNumPosition：出栈序列待判断位置
 pushNumPosition：入栈序列待判断位置
 pushNumLength：入栈序列长度
 popNumLength：出栈序列长度
 1、判断出栈序列的popNumPosition（popNumPosition从0开始）位置的元素   是否等于   栈顶元素（最开始为空栈，所以此时不比较，直接跳到第2步）（1）
 若（1）相等，则删除栈顶元素，继续判断出栈序列的下一个元素popNumPosition+1，回到第1步
 若（1）不相等，执行第2步
 2、判断入栈序列检验位置pushNumPosition（从0开始 ） 是否小于　入栈序列长度pushNumLength，入栈序列是否检验完（2）
 若（2）不小于，则直接返回false
 若（2）小于，执行第3步
 3、判断 popNumPosition位置的元素   是否等于    pushNumPosition（从0开始）位置的元素（3）
 若（3）相等，继续判断出栈序列的下一个元素popNumPosition+1，同时pushNumPosition+1，回到第1步
 若（3）不相等，将pushNum[pushNumPosition]这个元素压栈，同时pushNumPosition+1，回到第1步
 终止条件为：popNumPosition < popNumLength，也就是出栈序列是否检验完了，这时可以直接返回true

 */

package com.singleproblem;

public class StackPop {

	public static boolean IsLegal(int[] pushNum, int[] popNum) {
		int popNumLength = popNum.length; // 出栈序列长度
		int pushNumLength = pushNum.length; // 入栈序列长度
		if (pushNumLength != popNumLength) // 出栈序列长度不等于入栈序列长度，直接返回false
			return false;
		int[] tempStack = new int[pushNumLength]; // 数组模拟栈，保存需要进栈的元素
		int top = 0;
		int pushNumPosition = 0, popNumPosition = 0;
		/* 检验出栈序列 */
		while (popNumPosition < popNumLength) { // 终止条件：出栈序列元素检验完了
			if (top > 0 && popNum[popNumPosition] == pushNum[top - 1]) { // 出栈序列的popNumPosition（popNumPosition从0开始）位置的元素是否等于栈顶元素
				top = top - 1; // 栈顶元素出栈
				popNumPosition++; // 检验出栈序列后一个元素
			} else if (pushNumPosition < pushNumLength && popNum[popNumPosition] == pushNum[pushNumPosition]) {
				pushNumPosition++; // 检验入栈序列后一个元素
				popNumPosition++; // 检验出栈序列后一个元素
			} else if (pushNumPosition < pushNumLength && popNum[popNumPosition] != pushNum[pushNumPosition]) {
				tempStack[top++] = pushNum[pushNumPosition]; // 入栈操作
				pushNumPosition++; // 检验出栈序列后一个元素
			} else {
				return false; // 不和栈顶元素相同，且出栈序列已经没有元素可对照检验，则该位置的元素不满足出栈要求，返回false
			}
		}
		return true;  // 出栈序列元素检验完了，返回true
	}

	public static void main(String[] args) {
		System.out.println(IsLegal(new int[] { 6, 5, 4, 3, 2, 1 }, new int[] { 3, 4, 6, 5, 2, 1 }));
	}
}
