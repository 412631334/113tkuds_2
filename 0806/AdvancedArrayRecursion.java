
public class AdvancedArrayRecursion {

    // 遞迴快速排序
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // 遞迴合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        mergeHelper(a, 0, b, 0, result, 0);
        return result;
    }

    private static void mergeHelper(int[] a, int i, int[] b, int j, int[] res, int k) {
        if (i == a.length && j == b.length) {
            return;
        }
        if (i == a.length) {
            res[k] = b[j];
            mergeHelper(a, i, b, j + 1, res, k + 1);
        } else if (j == b.length) {
            res[k] = a[i];
            mergeHelper(a, i + 1, b, j, res, k + 1);
        } else if (a[i] < b[j]) {
            res[k] = a[i];
            mergeHelper(a, i + 1, b, j, res, k + 1);
        } else {
            res[k] = b[j];
            mergeHelper(a, i, b, j + 1, res, k + 1);
        }
    }

    // 遞迴尋找第 k 小元素（快速選擇法）
    public static int kthSmallest(int[] arr, int k) {
        return kthSmallestHelper(arr, 0, arr.length - 1, k);
    }

    private static int kthSmallestHelper(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }

        int pivotIndex = partition(arr, left, right);
        int count = pivotIndex - left + 1;

        if (k == count) {
            return arr[pivotIndex]; 
        }else if (k < count) {
            return kthSmallestHelper(arr, left, pivotIndex - 1, k); 
        }else {
            return kthSmallestHelper(arr, pivotIndex + 1, right, k - count);
        }
    }

    // 遞迴檢查子序列和等於目標值
    public static boolean checkSubsequenceSum(int[] arr, int index, int target) {
        if (target == 0) {
            return true;
        }
        if (index == arr.length || target < 0) {
            return false;
        }

        // 選擇當前元素或不選擇
        return checkSubsequenceSum(arr, index + 1, target - arr[index]) || checkSubsequenceSum(arr, index + 1, target);
    }

    // 測試
    public static void main(String[] args) {
        int[] arr1 = {2, 4, 6, 8};
        int[] arr2 = {1, 3, 5, 7};

        System.out.println("原陣列1:");
        printArray(arr1);
        System.out.println("快速排序後:");
        quickSort(arr1, 0, arr1.length - 1);
        printArray(arr1);

        System.out.println("合併排序陣列:");
        int[] merged = mergeSortedArrays(arr1, arr2);
        printArray(merged);

        System.out.println("第3小元素: " + kthSmallest(merged, 3));

        System.out.println("子序列和為14? " + checkSubsequenceSum(arr1, 0, 14));
        System.out.println("子序列和為7? " + checkSubsequenceSum(arr1, 0, 7));
    }

    private static void printArray(int[] arr) {
        for (int v : arr) {
            System.out.print(v + " ");
        }
        System.out.println();
    }
}
