/*
 *
  Arthur最近搬到了新的别墅，别墅特别大，原先的桌子显得比较小，所以他决定换一张新的桌子。他买了一张特别大的桌子，桌子是由很多条桌腿进行支撑的，可是回到家之后他发现桌子不稳，原来是桌子腿长度不太相同。他想要自己把桌子修理好，所以他决定移除掉一些桌腿来让桌子变得平稳。桌子腿总共有n条腿，第i条腿长度为li，Arthur移除第i桌腿要花费代价为di。假设k条腿桌子平稳的条件:超过一半桌腿能够达到桌腿长度的最大值。例如：一条腿的桌子是平稳的，两条腿的桌子腿一样长时是平稳的。请你帮Arthur计算一下是桌子变平稳的最小总代价。

  输入描述:
  第一行数据是一个整数：n (1≤n≤105)，n表示桌腿总数。
  第二行数据是n个整数：l1, l2, …, ln (1≤li≤105)，表示每条桌腿的长度。
  第三行数据是n个整数：d1, d2, …, dn (1≤di≤200)，表示移除每条桌腿的代价。

  输出描述:输出让桌子变平稳的最小总代价

  输入例子:
  6
  2 2 1 1 3 3
  4 3 5 5 2 1

  输出例子:
  8

  解答：
  首先分别获取桌腿长度对应的数量的集合int[] numCount
  计算 保留的桌腿的最大长度为len（枚举从105到1）时的代价cost
  计算方法：首先需要计算到 小于len且需要删除的桌腿数量cutNum；
  然后将大于len的桌腿代价直接加到cost、且将小于len的代价加入到待删除集合，从小到大排序，取前cutNum个加到cost，最后得到一个cost
  比较cost与minCost大小，小于则更新minCost为cost
 */

package com.singleproblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepairTable {

    public static int getMinCost(int n, int[] tableLen, int[] tablePrice) {

        int minCost = Integer.MAX_VALUE; // 最小代价

        /*numCount代表桌腿长度相应的数量集合*/
        int[] numCount = new int[106];
        for (int x : tableLen) {
            numCount[x] += 1;
        }

        /*待减去桌腿的代价集合*/
        List<Integer> cutList = new ArrayList<>();

        /*从大到小（105到1）分别枚举桌腿最大长度，len代表保留的桌腿的最大长度*/
        for (int len = 105; len >= 1; --len) {

            /*计算小于len的桌腿总数量*/
            int cutNumCount = 0;
            for (int j = len - 1; j >= 1; j--) {
                cutNumCount += numCount[j];
            }

            /*计算小于len且需要删除的桌腿数量*/
            int cutNum;
            if (numCount[len] == 0) cutNum = cutNumCount; // len长度的桌腿数量为0，则需要全部删除
            else if (numCount[len] <= cutNumCount)
                cutNum = cutNumCount - (numCount[len] - 1); // len长度的桌腿数量大于0且小于cutNumCount，则需要删除cutNumCount - (numCount[len] - 1)
            else cutNum = 0; // len长度大于cutNumCount，则不需要删除


            /*计算保留最大长度为len时的最小代价*/
            int cost = 0;
            for (int i = 0; i < tableLen.length; ++i) {
                if (tableLen[i] > len) cost += tablePrice[i]; // 若大于len，则直接删除
                if (tableLen[i] < len) cutList.add(tablePrice[i]); // 若小于len，加入到待删除集合
            }

            /*对待删除代价从小到大排序*/
            Collections.sort(cutList);
            /*取前cutNum个*/
            for (int i = 1; i <= cutNum; ++i) {
                cost += cutList.get(i - 1);
            }

            if (cost < minCost) minCost = cost;

            cutList.clear();
        }
        return minCost;
    }

    public static void main(String[] args) {
        System.out.println(getMinCost(6, new int[]{2, 2, 1, 1, 3, 3, 3}, new int[]{4, 3, 5, 5, 2, 1, 10}));
    }

}
