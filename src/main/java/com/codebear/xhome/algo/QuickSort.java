package com.codebear.xhome.algo;

import lombok.extern.slf4j.Slf4j;

/**
 * 快速排序
 */
@Slf4j
public class QuickSort {
    public static void main(String[] args) {

        int[] arr = {4,2,35,9,7,8,1,5,0,4,3};
        quickSort(arr, 0, arr.length - 1);
        log.info("快速排序{}",arr);
    }

    /**
     * 从小到大
     * @param arr
     * @param left
     * @param right
     */
    private static  void quickSort(int[] arr, int left, int right){
        if (left >= right) {    // 必须加
            return;
        }

        int temp = arr[left]; // 以左边的元素为基准元素
        int i = left, j = right; // i,j为两个游标
        while (i < j) {
            while (i < j && arr[j] >= temp){ // 右边先走
                j--;
            }
            while (i < j && arr[i] <= temp) {
                i++;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        arr[left] = arr[i]; // 注意，这一步必须要，填上最左边的坑
        arr[i] = temp; // 基准元素就位
        quickSort(arr, left, i - 1);    // 递归操作左边部分
        quickSort(arr, i + 1, right);   // 递归操作右边部分
    }

    /**
     * 交换两个元素
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
