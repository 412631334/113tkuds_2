
import java.util.*;

public class M12_MergeKTimeTables {

    static class Entry implements Comparable<Entry> {

        int time;
        int listIdx;
        int elemIdx;

        Entry(int t, int l, int e) {
            time = t;
            listIdx = l;
            elemIdx = e;
        }

        public int compareTo(Entry other) {
            return this.time - other.time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                arr[j] = sc.nextInt();
            }
            lists.add(arr);
        }

        List<Integer> merged = mergeKLists(lists);
        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i) + (i == merged.size() - 1 ? "\n" : " "));
        }
    }

    static List<Integer> mergeKLists(List<int[]> lists) {
        PriorityQueue<Entry> pq = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        // 將每個列表的第一個元素放入堆
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).length > 0) {
                pq.offer(new Entry(lists.get(i)[0], i, 0));
            }
        }

        while (!pq.isEmpty()) {
            Entry e = pq.poll();
            result.add(e.time);
            int[] arr = lists.get(e.listIdx);
            if (e.elemIdx + 1 < arr.length) {
                pq.offer(new Entry(arr[e.elemIdx + 1], e.listIdx, e.elemIdx + 1));
            }
        }

        return result;
    }
}

/*
 * Time Complexity: O(N log K)
 * 說明：
 * - N 為所有列表元素總數，K 為列表數量
 * - 每個元素進出堆一次，堆大小 ≤ K → O(log K)
 * - 所以總時間 O(N log K)
 */
