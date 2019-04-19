package com.zhiyun.examination.SortTest;

import java.util.Arrays;

/**
 * @Title: bubbleSortTest
 * @ProjectName: examination
 * @Description: 冒泡排序
 * @author: jiangxing
 * @date 2019/4/1914:02
 */

public class bubbleSortTest {


    public static void main(String[] args) {
        // 测试数据
        int arr[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        //冒泡
        bubbleSort(arr);

        quick(arr);
        System.err.println(Arrays.toString(arr));
    }

    private static void quick(int[] arr) {
        if (arr.length > 0)   //查看数组是否为空
        {
            quickSort(arr, 0, arr.length - 1);
        }
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int middle = getMiddle(arr, low, high); //将numbers数组进行一分为二
            quickSort(arr, low, middle - 1);
            quickSort(arr, middle + 1, high);
        }
    }

    //int arr[] = { 5, 4, 8, 3, 7, 2, 1, 9, 0, 6 };
    //int crr[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private static int getMiddle(int[] arr, int low, int high) {
        int temp = arr[low];
        while (low < high) {
            while (arr[high] > temp && low < high) {
                high--;
            }
            arr[low] = arr[high];//比中轴小的记录移到低端
            while (arr[high] < temp && low < high) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp; //中轴记录到尾
        return low; // 返回中轴的位置
    }

    private static void bubbleSort(int[] a) {
        int temp;
        int size = a.length;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }
}
