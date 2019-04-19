package com.zhiyun.examination.SortTest;

import java.util.Arrays;

/**
 * @Title: SelectSortTest
 * @ProjectName: examination
 * @Description: TODO
 * @author: jiangxing
 * @date 2019/4/1916:45
 */

public class SelectSortTest {

    public static void main(String[] args) {
        // 测试数据
        int arr[] = { 5, 4, 8, 3, 7, 2, 1, 9, 0, 6 };
        // 执行快排
        selectSort(arr);
        // 打印数组元素
        System.err.println(Arrays.toString(arr));
    }
    /**
     * 选择排序
     * 在未排序序列中找到最小元素，存放到排序序列的起始位置
     * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列起始位置。
     * 以此类推，直到所有元素均排序完毕。
     *
     * @param numbers
     */
    private static void selectSort(int[] numbers) {
        int size = numbers.length;
        int temp;
        for (int i = 0; i < size; i++) {
            int k = i;
            for (int j = size - 1; j >i; j--)  {
                if (numbers[j] < numbers[k]) {
                    k = j; }
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }
}
