package algorithm;

public class Merging {
    private static int[] tmp;

    public static void main(String[] args) {
        int[] arr = {9, 5, 2, 7, 12, 4, 3, 1, 11};
        tmp = new int[arr.length];
        for (int i : arr) {
            System.out.print(i + " ");
        }
        merge_sort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void merge_sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        // 找中间点
        int mid = left + (right - left) / 2;//防止整数溢出
        // 递归划分左半区
        merge_sort(arr, left, mid);
        // 递归划分右半区
        merge_sort(arr, mid + 1, right);
        // 标记左半区第一个未排序的元素
        int l_pos = left;
        // 标记右半区第一个未排序的元素
        int r_pos = mid + 1;
        // 临时数组元素的下标
        int pos = left;
        // 合并
        while (l_pos <= mid && r_pos <= right) {
            if (arr[l_pos] < arr[r_pos]) { //左半区第一个剩余元素更小
                tmp[pos++] = arr[l_pos++];
            } else {                       // 右半区第一个剩余元素更小
                tmp[pos++] = arr[r_pos++];
            }
        }
        // 合并左半区剩余的元素
        while (l_pos <= mid) {
            tmp[pos++] = arr[l_pos++];
        }
        // 合并右半区剩余的元素
        while (r_pos <= right) {
            tmp[pos++] = arr[r_pos++];
        }
        // 把临时数组中合并后的元素复制回原来的数组
        for (int i = left; i <= right; i++) {
            arr[i] = tmp[i];
        }
    }
}
