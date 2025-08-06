
import java.util.Arrays;

public class SelectionSortImplementation {

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
                swaps++;
            }
            System.out.println("第 " + (i + 1) + " 輪排序結果：" + Arrays.toString(arr));
        }
        System.out.println("總比較次數：" + comparisons);
        System.out.println("總交換次數：" + swaps);
    }

    // 氣泡排序做比較
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;
        int[] copy = Arrays.copyOf(arr, n);

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                comparisons++;
                if (copy[j] > copy[j + 1]) {
                    int temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                    swaps++;
                }
            }
        }
        System.out.println("氣泡排序結果：" + Arrays.toString(copy));
        System.out.println("氣泡排序比較次數：" + comparisons);
        System.out.println("氣泡排序交換次數：" + swaps);
    }

    // 測試
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("選擇排序開始");
        selectionSort(arr);
        System.out.println();

        System.out.println("氣泡排序開始");
        bubbleSort(arr);
    }
}
