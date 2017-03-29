package com.singleproblem;

/*给定入栈序列和出栈序列，判断给定的出栈序列是否正确*/
public class StackPop {

    public static boolean IsLegal(int[] pushNum, int[] popNum) {
        int popNumLength = popNum.length;
        int pushNumLength = pushNum.length;
        if (pushNumLength != popNumLength) return false;
        int[] tempStack = new int[pushNumLength];
        int top = 0;
        int pushNumPosition = 0, popNumPosition = 0;
        while (popNumPosition < popNumLength) {
            if (top > 0 && popNum[popNumPosition] == pushNum[top - 1]) {
                top = top - 1;
                popNumPosition++;
            } else if (pushNumPosition < pushNumLength && popNum[popNumPosition] == pushNum[pushNumPosition]) {
                pushNumPosition++;
                popNumPosition++;
            } else if (pushNumPosition < pushNumLength && popNum[popNumPosition] != pushNum[pushNumPosition]) {
                tempStack[top++] = pushNum[pushNumPosition];
                pushNumPosition++;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(IsLegal(new int[]{6, 5, 4, 3, 2, 1}, new int[]{3, 4, 6, 5, 2, 1}));
    }
}
