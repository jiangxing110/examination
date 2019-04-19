package com.zhiyun.examination.SortTest;/**
 * @Title: QuickSortTest
 * @ProjectName: examination
 * @Description: TODO
 * @author: jiangxing
 * @date 2019/4/1910:10
 */

import java.util.Arrays;

/**
 * @Classname QuickSortTest
 * @Description TODO
 * @Date 2019/4/19 10:10
 * @Created by jiangxing
 */
public class QuickSortTest {


    public static void main(String[] args) {
        // 测试数据
        int arr[] = { 5, 4, 8, 3, 7, 2, 1, 9, 0, 6 };
        // 执行快排
        quick(arr);
        // 打印数组元素
        System.err.println(Arrays.toString(arr));
    }

    private static void quick(int[] arr) {
        if(arr.length > 0)   //查看数组是否为空
        {
            quickSort(arr, 0, arr.length-1);
        }
    }

    /**
     *
     * @param numbers 带排序数组
     * @param low  开始位置
     * @param high 结束位置
     */
    public static void quickSort(int[] numbers,int low,int high)
    {
        if(low < high)
        {
        int middle = getMiddle(numbers,low,high); //将numbers数组进行一分为二
        quickSort(numbers, low, middle-1);   //对低字段表进行递归排序
        quickSort(numbers, middle+1, high); //对高字段表进行递归排序
        }

    }

    public static int getMiddle(int[] numbers, int low,int high)
    {
        int temp = numbers[low]; //数组的第一个作为中轴
        while(low < high)
        {
            while(low < high && numbers[high] > temp)
            {
                high--;
            }
            numbers[low] = numbers[high];//比中轴小的记录移到低端
            while(low < high && numbers[low] < temp)
            {
                low++;
            }
            numbers[high] = numbers[low] ; //比中轴大的记录移到高端
        }
        numbers[low] = temp ; //中轴记录到尾
        return low ; // 返回中轴的位置
    }
}