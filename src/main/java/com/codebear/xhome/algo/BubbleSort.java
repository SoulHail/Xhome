package com.codebear.xhome.algo;

import lombok.extern.slf4j.Slf4j;

/**
 * 冒泡排序：
 * <p>
 * 时间复杂度: O(N^2)
 * 空间复杂度: O(1)
 * 稳定性：稳定
 */
@Slf4j
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {78, 21, 32, 17, 96, 21};
        sTob(arr);
        log.info("冒泡排序，从小到大：{}", arr);

        bTos(arr);
        log.info("冒泡排序，从大到小：{}", arr);
    }

    /**
     * 从小到大
     *
     * @param arr
     */
    public static void sTob(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 从大到小
     *
     * @param arr
     */
    public static void bTos(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}
