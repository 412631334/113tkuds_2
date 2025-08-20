
import java.util.*;

public class M11_HeapSortWithTie {

    static class Student implements Comparable<Student> {

        int score;
        int index;

        Student(int s, int i) {
            score = s;
            index = i;
        }

        public int compareTo(Student other) {
            if (this.score != other.score) {
                return this.score - other.score;
            }
            return this.index - other.index; // 平手用 index 小優先
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Student(sc.nextInt(), i);
        }

        heapSort(arr);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score + (i == n - 1 ? "\n" : " "));
        }
    }

    // Max-Heap 排序 (升序結果)
    static void heapSort(Student[] arr) {
        int n = arr.length;
        // 建堆 (自底向上)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 取出元素到末端
        for (int i = n - 1; i >= 0; i--) {
            Student tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            heapify(arr, i, 0); // i 表示 heap 範圍
        }
    }

    static void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l].compareTo(arr[largest]) > 0) {
            largest = l;
        }
        if (r < n && arr[r].compareTo(arr[largest]) > 0) {
            largest = r;
        }

        if (largest != i) {
            Student tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            heapify(arr, n, largest);
        }
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：
 * - 建堆自底向上 O(n)
 * - 每次取出元素並 heapify O(log n)，共 n 次 → O(n log n)
 */
