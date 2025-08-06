
import java.util.*;

public class NumberArrayProcessor {

    // 移除重複元素
    public static int[] removeDuplicates(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }

    // 合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int i = 0, j = 0, k = 0;
        int[] merged = new int[a.length + b.length];
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = b[j++];
            }
        }
        while (i < a.length) {
            merged[k++] = a[i++];
        }
        while (j < b.length) {
            merged[k++] = b[j++];
        }
        return Arrays.copyOf(merged, k);
    }

    // 找出出現頻率最高的元素
    public static int mostFrequent(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int maxCount = 0;
        int result = arr[0];
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }

    // 將陣列分割成兩個相等或近似相等的子陣列
    public static int[][] splitArray(int[] arr) {
        int mid = arr.length / 2;
        int[] part1 = Arrays.copyOfRange(arr, 0, mid);
        int[] part2 = Arrays.copyOfRange(arr, mid, arr.length);
        return new int[][]{part1, part2};
    }

    // 測試
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4, 4, 5};
        int[] arr2 = {2, 3, 6, 7};

        System.out.println("移除重複元素：" + Arrays.toString(removeDuplicates(arr1)));

        System.out.println("合併排序陣列：" + Arrays.toString(mergeSortedArrays(arr1, arr2)));

        System.out.println("出現頻率最高元素：" + mostFrequent(arr1));

        int[][] parts = splitArray(arr1);
        System.out.println("分割後陣列1：" + Arrays.toString(parts[0]));
        System.out.println("分割後陣列2：" + Arrays.toString(parts[1]));
    }
}
