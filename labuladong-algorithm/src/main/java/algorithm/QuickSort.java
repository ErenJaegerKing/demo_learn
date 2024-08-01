package algorithm;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {9, 5, 2, 7, 12, 4, 3, 1, 11};
        System.out.println();
        quick_Sort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quick_Sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = arr[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
                i++;
            }
        }

        int tmp = arr[i];
        arr[i] = arr[high];
        arr[high] = tmp;

        int mid = i;
        quick_Sort(arr, low, mid -1);
        quick_Sort(arr,mid + 1,high);
    }
}
