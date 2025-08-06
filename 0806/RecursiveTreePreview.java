
import java.util.ArrayList;
import java.util.List;

public class RecursiveTreePreview {

    // 模擬資料夾（目錄）結構
    static class Folder {

        String name;
        List<Folder> subFolders;
        int fileCount;  // 該資料夾裡的檔案數

        Folder(String name, int fileCount) {
            this.name = name;
            this.fileCount = fileCount;
            this.subFolders = new ArrayList<>();
        }

        void addSubFolder(Folder f) {
            subFolders.add(f);
        }
    }

    // 遞迴計算資料夾總檔案數
    public static int totalFiles(Folder folder) {
        int total = folder.fileCount;
        for (Folder sub : folder.subFolders) {
            total += totalFiles(sub);
        }
        return total;
    }

    // 遞迴列印多層選單結構 (用縮排表現層級)
    public static void printMenu(Folder folder, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(folder.name);
        for (Folder sub : folder.subFolders) {
            printMenu(sub, level + 1);
        }
    }

    // 遞迴展平巢狀陣列 (模擬 List<List<Integer>>，這裡用自訂型態簡化)
    public static List<Integer> flattenNestedList(List<?> nestedList) {
        List<Integer> flatList = new ArrayList<>();
        for (Object elem : nestedList) {
            if (elem instanceof Integer) {
                flatList.add((Integer) elem);
            } else if (elem instanceof List<?>) {
                flatList.addAll(flattenNestedList((List<?>) elem));
            }
        }
        return flatList;
    }

    // 遞迴計算巢狀清單最大深度
    public static int maxDepth(List<?> nestedList) {
        int max = 1;
        for (Object elem : nestedList) {
            if (elem instanceof List<?>) {
                max = Math.max(max, 1 + maxDepth((List<?>) elem));
            }
        }
        return max;
    }

    // 測試
    public static void main(String[] args) {
        // 建立模擬資料夾結構
        Folder root = new Folder("根目錄", 2);
        Folder sub1 = new Folder("子資料夾1", 3);
        Folder sub2 = new Folder("子資料夾2", 1);
        Folder sub1_1 = new Folder("子資料夾1-1", 4);

        root.addSubFolder(sub1);
        root.addSubFolder(sub2);
        sub1.addSubFolder(sub1_1);

        System.out.println("資料夾總檔案數: " + totalFiles(root));

        System.out.println("列印多層選單結構:");
        printMenu(root, 0);

        // 巢狀陣列模擬
        List<Object> nestedList = new ArrayList<>();
        nestedList.add(1);
        nestedList.add(List.of(2, 3));
        nestedList.add(List.of(List.of(4, 5), 6));

        System.out.println("展平巢狀陣列:");
        List<Integer> flat = flattenNestedList(nestedList);
        System.out.println(flat);

        System.out.println("巢狀清單最大深度: " + maxDepth(nestedList));
    }
}
