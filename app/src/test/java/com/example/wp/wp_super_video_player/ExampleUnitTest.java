package com.example.wp.wp_super_video_player;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void sort1() {
        int[] numbers = new int[]{12, 32, 543, 21, 3, 56};
        bubbleSort(numbers);
        quick(numbers);
        selectedSort(numbers);
    }

    /**
     * 在未排序序列中找到最小的元素,放到排序队列起始位置
     * 再从剩余未排序队列中继续寻找最小元素,然后放到排序队列末尾
     * 直到所有元素排序完毕
     *
     * @param numbers
     */
    private void selectedSort(int[] numbers) {
        int size = numbers.length;
        int temp = 0;
        for (int i = 0; i < size - 1; i++) {
            int k = i;//待确定位置
            //选择出应该在第一个位置的数
            for (int j = size - 1; j > i; j--) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            }
            //交换2个数
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("选择排序\n" + numbers[i] + "  ");
        }
    }

    private void quick(int[] numbers) {
        if (numbers.length > 0) {
            quickSort(numbers, 0, numbers.length - 1);
        }
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("快速排序" + numbers[i] + "  ");
        }
    }

    /**
     * 递归形式的分治排序算法
     *
     * @param numbers
     */
    private void quickSort(int[] numbers, int low, int high) {
        if (low < high) {
            int middle = getMiddle(numbers, low, high);
            quickSort(numbers, low, middle - 1);
            quickSort(numbers, middle + 1, high);
        }
    }

    /**
     * @param numbers 待排序数组
     * @param low     起始位置
     * @param high    结束位置
     * @return 中轴
     */
    private int getMiddle(int[] numbers, int low, int high) {
        int temp = numbers[low];
        while (low < high) {
            while (low < high && numbers[high] > temp) {
                high--;
            }
            numbers[low] = numbers[high];
            while (low < high && numbers[low] < temp) {
                low++;
            }
            numbers[high] = numbers[low];
        }
        numbers[low] = temp;
        return low;
    }

    /**
     * 冒泡排序，比较相邻的两个元素，如果第一个比第二个大就交换
     * 对每一对相邻的元素做相同的工作
     *
     * @param numbers
     */
    private void bubbleSort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {//交换位置
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("冒泡排序 " + numbers[i] + " ");
        }
    }
}