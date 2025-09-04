
public class lt_27_RemoveElement {

    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }

    // ====== Driver ======
    public static void main(String[] args) {
        lt_27_RemoveElement solution = new lt_27_RemoveElement();
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        int len = solution.removeElement(nums, val);
        System.out.println("Length: " + len);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
