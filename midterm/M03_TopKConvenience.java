
import java.util.*;

public class M03_TopKConvenience {

    static class Item {

        String name;
        int qty;

        Item(String n, int q) {
            name = n;
            qty = q;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            items.add(new Item(name, qty));
        }

        // Min-Heap (維護 Top-K)
        PriorityQueue<Item> pq = new PriorityQueue<>(K, (a, b) -> {
            if (a.qty != b.qty) {
                return a.qty - b.qty; // 小的優先出堆

            }
            return a.name.compareTo(b.name); // 字典序確保穩定性
        });

        for (Item it : items) {
            pq.offer(it);
            if (pq.size() > K) {
                pq.poll();
            }
        }

        // 把結果倒出來，依 qty 降序、字典序排序
        List<Item> result = new ArrayList<>(pq);
        result.sort((a, b) -> {
            if (b.qty != a.qty) {
                return b.qty - a.qty;
            }
            return a.name.compareTo(b.name);
        });

        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
    }
}

/*
 * Time Complexity: O(n log K)
 * 說明：每插入一個元素最多進行一次堆操作 O(log K)，總共 n 次 → O(n log K)。
 * 由於 K << n（K ≤ 50），效率遠高於排序 O(n log n)。
 */
