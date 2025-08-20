
import java.util.*;

public class M01_BuildHeap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();   // "max" 或 "min"
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 自底向上建堆
        if (type.equals("max")) {
            buildHeap(arr, n, true);
        } else {
            buildHeap(arr, n, false);
        }

        // 輸出堆陣列
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i != n - 1) {
                System.out.print(" ");
            }
        }
    }

    // 自底向上建堆
    static void buildHeap(int[] arr, int n, boolean isMax) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, isMax);
        }
    }

    // heapifyDown
    static void heapify(int[] arr, int n, int i, boolean isMax) {
        int target = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (isMax) {
            if (left < n && arr[left] > arr[target]) {
                target = left;
            }
            if (right < n && arr[right] > arr[target]) {
                target = right;
            }
        } else {
            if (left < n && arr[left] < arr[target]) {
                target = left;
            }
            if (right < n && arr[right] < arr[target]) {
                target = right;
            }
        }

        if (target != i) {
            int temp = arr[i];
            arr[i] = arr[target];
            arr[target] = temp;
            heapify(arr, n, target, isMax);
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆，對每個節點呼叫 heapify。
 * 雖然單次 heapify 最壞 O(log n)，但實際上多數節點高度較低，
 * 經過推導，總時間複雜度收斂為 O(n)。
 */
