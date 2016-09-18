package minyi.leetcode;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Test");
        System.out.println(Integer.BYTES);

        System.out.println("======");
        Solution solution = new Solution();
//
//        //================219======================
//        int[] nums = {1,2,3,4,5,6,7,8,4};
//        boolean res = solution.containsNearbyDuplicate(nums, 5);
//        System.out.println(res);
//
//        //================189======================
//        int[] nums2 = {1,2,3,4,5,6,7,8};
//        solution.rotate(nums2, 3);
//        System.out.println(Arrays.toString(nums2));
//        //================119======================
//        System.out.println(Arrays.toString(solution.getRow(3).toArray()));
//        //================231======================
//        System.out.println(solution.isPowerOfTwo(536870912));
//        //================165======================
//        System.out.println(solution.compareVersion("1.0.1", "1.0"));
//        solution.test();
//        System.out.println(solution.solution_binary_period(100000000));
        System.out.println(solution.solution_long_bit_count(100000000,100000000));
    }
}
