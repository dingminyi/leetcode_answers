package minyi.leetcode;

import java.util.*;

/**
 * Created by minyidin on 7/4/2016.
 */
public class Solution {
    //////////////////////////////////////////////////////
    public boolean isIsomorphic(String s, String t) {
        return getPattern(s).equals(getPattern(t));
    }

    public String getPattern(String s){
        char[] array = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        int index = 0;
        for(char c:array){
            if(map.containsKey(c)){
                int v = map.get(c);
                sb.append(v);
            }else{
                map.put(c,index);
                sb.append(index);
                index++;
            }
        }
        return sb.toString();
    }
    /////////////////////////////////////////////////////////////
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<Integer>();
        while(set.add(n)){
            int target = n;
            int sum = 0;
            while(target>0){
                int remain = target % 10;
                sum = sum + remain * remain;
                target = target / 10;
            }
            n = sum;
            if(sum == 1){
                return true;
            }
        }
        return false;
    }
    //////////////////////////////////////////////////////////////////
    public String getHint(String secret, String guess) {
        HashMap<Character,Integer> secretMap = new HashMap<Character,Integer>();
        int bullNum = 0;

        for(int i = 0; i<secret.length();i++){
            char c = secret.charAt(i);
            if(guess.charAt(i) == c) bullNum++;
            if(!secretMap.containsKey(c)){
                secretMap.put(c,1);
            }else{
                secretMap.put(c,secretMap.get(c) + 1);
            }
        }
        // count cow
        int tempCount = 0;
        for(int i = 0; i<guess.length();i++){
            char c = guess.charAt(i);
            if(secretMap.containsKey(c)){
                if(secretMap.get(c) > 1){
                    secretMap.put(c,secretMap.get(c) - 1);
                    tempCount++;
                }else{
                    secretMap.remove(c);
                    tempCount++;
                }
            }
        }
        return bullNum + "A" + (tempCount - bullNum) + "B";
    }
    /////////////////////////////////////////////////////////////////
    public boolean wordPattern(String pattern, String str) {
        if(pattern.trim().length() == 0 || str.trim().length() == 0) return false;
        String p1 = getPattern2(pattern.split(""));
        String p2 = getPattern2(str.split(" "));
        return p1.equals(p2);
    }
    public String getPattern2(String[] stringArray){
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        StringBuffer sb = new StringBuffer();
        int currentFlag = 0;
        for(String str:stringArray){
            if(map.containsKey(str)){
                sb.append(map.get(str));
            }else{
                map.put(str,currentFlag);
                sb.append(currentFlag);
                currentFlag++;
            }
        }
        return sb.toString();

    }
    /////////////////////////////////////////
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,ArrayList> map = new HashMap<Integer,ArrayList>();
        for(int i=0;i<nums.length;i++){
            if(	map.containsKey(nums[i])){
                ArrayList<Integer> list = map.get(nums[i]);
                for(int idx: list){
                    if (Math.abs(idx - i) <= k){
                        return true;
                    }
                }
                list.add(i);
            }else{
                ArrayList list = new ArrayList<Integer>();
                list.add(i);
                map.put(nums[i],list);
            }
        }
        return false;
    }
    /////////////////////////////////////////
    public void rotate(int[] nums, int k) {
        for(int i=0; i<k; i++){
            for(int idx=0; idx<nums.length-1; idx++){
                int temp = nums[nums.length-1-idx-1];
                nums[nums.length-1-idx-1] = nums[nums.length-1-idx];
                nums[nums.length-1-idx] = temp;
            }
        }
    }

//    public void rotate(int[] nums, int k) {
//        k %= nums.length;
//        reverse(nums, 0, nums.length - 1);
//        reverse(nums, 0, k - 1);
//        reverse(nums, k, nums.length - 1);
//    }
//
//    public void reverse(int[] nums, int start, int end) {
//        while (start < end) {
//            int temp = nums[start];
//            nums[start] = nums[end];
//            nums[end] = temp;
//            start++;
//            end--;
//        }
//    }

    /////////////////////////////////////////
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0;
        int p2 = 0;
        ArrayList<Integer> res = new ArrayList<Integer>();
        while(p1 <= (m-1) && p2 <= (n-1) ){
            if(nums1[p1] <= nums2[p2]){
                res.add(nums1[p1]);
                p1++;
            }else{
                res.add(nums2[p2]);
                p2++;
            }
        }

        if(p1>m-1){
            for(;p2<=n-1;p2++){
                res.add(nums2[p2]);
            }
        }

        if(p2>n-1){
            for(;p1<=m-1;p1++){
                res.add(nums1[p1]);
            }
        }

        int i = 0;
        for(int v : res){
            nums1[i] = v;
            i++;
        }

    }
    //////////////////////////////////////////
    public int majorityElement(int[] nums) {
        int k = (int)Math.floor(nums.length / 2);
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i: nums){
            if(map.containsKey(i)){
                int v = map.get(i);
                v++;
                map.put(i,v);
                if(v > k){
                    return i;
                }
            }else{
                map.put(i,1);
                if(1 > k){
                    return i;
                }
            }
        }
        return -1;
    }
    /////////////////////////////////////////////
    public List<Integer> getRow(int rowIndex) {
        List<Integer> l = new ArrayList<Integer>();
        l.add(1);
        if(rowIndex == 0){
            return l;
        }
        for(int i=0;i<rowIndex;i++){
            l.add(0,0);
            l.add(0);
            int last_size = l.size();
            for (int j=0;j<last_size-1;j++){
                l.add(l.get(0)+l.get(1));
                l.remove(0);
            }
            l.remove(0);
        }
        return l;
    }
    //////////////////////////////////////////////
    public int countPrimes(int n) {
        int num = 0;
        for(int i = 1 ;i< n;i++){
            if (isPrime(i)){
                num++;
            }
        }
        return num;
    }

    private boolean isPrime(int n){
        if (n == 1){
            return false;
        }
        double sn = Math.sqrt(n);
        for(int i = 2;i<=sn;i++){
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }
    ////////////////////////////////////////
    public boolean isValidSudoku(char[][] board) {
        int[] rowFlag = new int[9];
        int[] columnFlag = new int[9];
        int[] blockFlag = new int[9];
        for(int row = 0;row < 9;row++){
            for(int column = 0;column < 9;column++){
                if(board[row][column] != '.'){
                    int value = board[row][column] - '1';
                    int bitMap = (1 << value);
                    if((bitMap & rowFlag[row]) >= 1 || (bitMap & columnFlag[column]) >= 1 || (bitMap & blockFlag[row / 3 * 3 + column / 3]) >= 1 ){
                        return false;
                    }else{
                        rowFlag[row] = bitMap + rowFlag[row];
                        columnFlag[column] = bitMap + columnFlag[column];
                        blockFlag[row / 3 * 3 + column / 3] = bitMap + blockFlag[row / 3 * 3 + column / 3];
                    }
                }
            }
        }
        return true;
    }
    ///////////////////////////////////////////
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<Integer>();
        HashSet<Integer> res = new HashSet<Integer>();
        for(int i=0;i < nums1.length;i++){
            set.add(nums1[i]);
        }
        for(int i=0;i < nums2.length;i++){
            if(set.contains(nums2[i])){
                res.add(nums2[i]);
            }
        }
        Iterator<Integer> iter = res.iterator();
        int[] resArray = new int[res.size()];
        for(int i = 0;iter.hasNext();i++){
            resArray[i] = iter.next();
        }
        return resArray;
    }
    /////////////////////////////////////////////
    public boolean isAnagram(String s, String t) {
        HashMap<Integer,Integer> mapS = getStringHashMap(s);
        HashMap<Integer,Integer> mapT = getStringHashMap(t);
        return mapS.equals(mapT);
    }

    public HashMap<Integer,Integer> getStringHashMap(String s){
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i =0;i<s.length();i++){
            int codePoint = s.codePointAt(i);
            if(map.containsKey(codePoint)){
                map.put(codePoint, map.get(codePoint) + 1);
            }else{
                map.put(codePoint, 1);
            }
        }
        return map;
    }
    /////////////////////////////////////////////////
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> set = new HashMap<Integer,Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i=0;i < nums1.length;i++){
            if(set.containsKey(nums1[i])){
                set.put(nums1[i],set.get(nums1[i])+1);
            }else{
                set.put(nums1[i],1);
            }

        }
        for(int i=0;i < nums2.length;i++){
            if(set.containsKey(nums2[i])){
                res.add(nums2[i]);
                if(set.get(nums2[i]) > 1){
                    set.put(nums2[i],set.get(nums2[i])-1);
                }else{
                    set.remove(nums2[i]);
                }
            }
        }
        Iterator<Integer> iter = res.iterator();
        int[] resArray = new int[res.size()];
        for(int i = 0;iter.hasNext();i++){
            resArray[i] = iter.next();
        }
        return resArray;
    }
    /////////////////////////////////////////////
//    Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public boolean isPalindrome(ListNode head) {
        int length = getLinkedListLength(head);
        if(length<2){
            return true;
        }
        int left_step = 0;
        int right_step = 0;
        if(length%2==0){
            right_step = length /2;
            left_step = right_step -1;
        }else{
            right_step = length / 2 +1 ;
            left_step = length / 2 -1;
        }

        ListNode rightNode = linkedListTraverse(head, right_step);
        ListNode leftNode = linkedListReverse(head, left_step);

        ListNode rNode = rightNode;
        ListNode lNode = leftNode;
        for(int i = 0;i<length/2;i++){
            if(lNode.val == rNode.val){
                lNode = lNode.next;
                rNode = rNode.next;
            }else{
                return false;
            }
        }
        return true;
    }

    public int getLinkedListLength(ListNode head){
        ListNode currentNode = head;
        int length = 0;
        while(currentNode != null){
            length++;
            currentNode = currentNode.next;
        }
        return length;
    }

    public ListNode linkedListReverse(ListNode head, int step){
        ListNode target = head;
        ListNode post = head.next;

        for(int step_count = 0;step_count < step;step_count++){
            if(target.next != null){
                ListNode pre = target;
                target = post;
                post = target.next;
                target.next = pre;
            }else{
                return null;
            }
        }
        head.next = null;
        return target;
    }

    public ListNode linkedListTraverse(ListNode head, int step){
        ListNode target = head;
        for(int step_count = 0;step_count < step;step_count++){
            if(target.next != null){
                target = target.next;
            }else{
                return null;
            }
        }
        return target;
    }
    /////////////////////////////////////////////
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head1 = l1;
        ListNode head2 = l2;
        ListNode head = head1;
        while(head1!=null && head2!= null){
            head1 = stickNode(head1,head2);
            if(head1!=null){
                head2 = stickNode(head2,head1);
            }

        }
        return head;
    }

    public ListNode mergeSortedTwoLists(ListNode l1, ListNode l2) {
        ListNode head1 = l1;
        ListNode head2 = l2;
        ListNode head = head1;
        ListNode tail = null ;
        if(head1 == null){
            head = head2;
            return head;
        }else if(head2 == null){
            head = head1;
            return head;
        }else if(head1.val < head2.val){
            head = head1;
            head1 = head1.next;
        }else{
            head = head2;
            head2 = head2.next;
        }
        tail = head;
        while(head1!=null && head2!= null){
            if(head1.val < head2.val){
                stickNode(tail,head1);
                tail = head1;
                head1 = head1.next;
            }else{
                stickNode(tail,head2);
                tail = head2;
                head2 = head2.next;
            }
        }
        if(head2 == null){
            stickNode(tail,head1);
        }else{
            stickNode(tail,head2);
        }
        return head;
    }

    public ListNode stickNode(ListNode target, ListNode newNext){
        ListNode oldNext = target.next;
        target.next = newNext;
        return oldNext;
    }
    /////////////////////////////////////////////
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = getLinkedListLength(head);
        int preIdx = length -n -1;
        ListNode pre = head;
        if(preIdx == -1){
            return pre.next;
        }
        for(int i = 0;i<preIdx;i++){
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return head;
    }
    /////////////////////////////////////////////
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<ListNode>();
        ListNode current = head;
        if(current != null){
            while(current.next != null){
                if(set.add(current) == false){
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }
    public boolean hasCycle2(ListNode head) {
        if(head==null) return false;
        ListNode walker = head;
        ListNode runner = head;
        while(runner.next!=null && runner.next.next!=null) {
            walker = walker.next;
            runner = runner.next.next;
            if(walker==runner) return true;
        }
        return false;
    }
    /////////////////////////////////////////////
    public ListNode deleteDuplicates(ListNode head) {
        ListNode tail = head;
        ListNode pointer = null;
        while(tail != null){
            pointer = tail.next;
            while(pointer != null && pointer.val == tail.val){
                pointer = pointer.next;
            }
            tail.next = pointer;
            tail = pointer;
        }
        return head;
    }
    /////////////////////////////////////////////
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        ListNode p = head;
        while(p != null){
            if(p.val != val){
                tail.next = p;
                tail = p;
            }
            p = p.next;
        }
        tail.next = null;
        return dummyHead.next;
    }
    /////////////////////////////////////////////
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode lnA = headA;
        ListNode lnB = headB;
        if(lnA == null || lnB == null) return null;

        if(getLastNode(lnA).val != getLastNode(lnB).val) return null;

        while(true){
            if(lnA.val == lnB.val){
                return lnA;
            }
            if (lnA.next != null){
                lnA = lnA.next;
            }else{
                lnA = headB;
            }
            if (lnB.next != null){
                lnB = lnB.next;
            }else {
                lnB = headA;
            }
        }
    }

    public ListNode getLastNode(ListNode head){
        ListNode node = head;
        ListNode res = head;
        while(node!=null){
            res = node;
            node = node.next;
        }
        return res;
    }
    /////////////////////////////////////////////
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode p = head;
        ListNode tail = new ListNode(-1);
        ListNode tempHead = tail;
        while(p!=null && p.next != null){
            ListNode temp = p.next.next;
            p.next.next = p;
            tail.next = p.next;
            p.next = temp;

            tail = p;
            p = temp;
        }
        return tempHead.next;
    }
    /////////////////////////////////////////////
    public boolean isPowerOfTwo(int n) {
        double res = Math.log10(n)/Math.log10(2);
        if(res == (int)res){
            return true;
        }else{
            return false;
        }
    }
    /////////////////////////////////////////////
    public int myAtoi(String str) {
        str = str.trim();
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[+-]{0,1}[0-9]+");
        java.util.regex.Matcher matcher = pattern.matcher(str);
        int sum = 0;
        if(matcher.find()){
            String res = matcher.group(0);
            int sign = 1;
            if(res.charAt(0) == '+' || res.charAt(0) == '-'){
                if(res.charAt(0) == '-'){
                    sign = -1;
                }
                res = res.substring(1);
            }
            char[] array = res.toCharArray();
            for(int i = 0;i< array.length; i++){
                if (sum > Integer.MAX_VALUE/10 || ((sum == Integer.MAX_VALUE/10) && (sign==1) && (array[i] - '0')>7)
                        || ((sum == Integer.MAX_VALUE/10) && (sign==-1) && (array[i] - '0')>8)) {
                    if (sign == 1){
                        return Integer.MAX_VALUE;
                    }else{
                        return Integer.MIN_VALUE;
                    }
                }
                sum = sum*10 + (array[i] - '0');
            }
            return sign * sum;
        }else{
            return 0;
        }
    }
    /////////////////////////////////////////////
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        int origin = x;
        int sum = 0;
        while(x != 0){
            sum = sum * 10 + x % 10;
            x = x / 10;
        }
        return sum == origin;
    }
    /////////////////////////////////////////////
    public boolean isPowerOfThree(int n) {
        if(n<=0) return false;
        while(n%3 == 0){
            n = n/3;
        }
        if(n == 1){
            return true;
        }else{
            return false;
        }
    }
    public boolean isPowerOfThree2(int n) {
        int maxPowerOfThree = (int)Math.pow(3, (int)(Math.log(Integer.MAX_VALUE) / Math.log(3)));
        return n>0 && maxPowerOfThree%n==0;
    }
    /////////////////////////////////////////////
    public int romanToInt(String s) {
        HashMap<Character,Integer> romanMap = new HashMap<>();
        romanMap.put('I',1);
        romanMap.put('V',5);
        romanMap.put('X',10);
        romanMap.put('L',50);
        romanMap.put('C',100);
        romanMap.put('D',500);
        romanMap.put('M',1000);
        int sum = 0;
        char[] array = s.toCharArray();
        for(int i = 0; i< array.length; i++){
            if(i != array.length - 1
                    && (array[i] == 'I' || array[i] == 'X' || array[i] == 'C')
                    && romanMap.get(array[i])<romanMap.get(array[i+1])
                    ){
                sum = sum + romanMap.get(array[i+1]) - romanMap.get(array[i]);
                i++;
            }else{
                sum += romanMap.get(array[i]);
            }

        }
        return sum;
    }
    /////////////////////////////////////////////
    public boolean isUgly(int num) {
        if(num == 0) return false;
        while(true){
            if(num%2==0){
                num = num /2;
            }else if(num%3==0){
                num = num /3;
            }else if(num%5==0){
                num = num /5;
            }else{
                break;
            }
        }
        if(num == 1){
            return true;
        }else{
            return false;
        }
    }
    /////////////////////////////////////////////
    public int reverse(int x) {
        if(x==Integer.MIN_VALUE) return 0;

        int sign = (x>=0? 1:-1);
        x = Math.abs(x);
        java.util.concurrent.LinkedBlockingQueue<Integer> queue = new java.util.concurrent.LinkedBlockingQueue<>();
        while(x != 0){
            queue.offer(x%10);
            x /= 10;
        }
        int res = 0;
        while(queue.peek() != null){
            if(res > Integer.MAX_VALUE/10){
                return 0;
            }else if(res == Integer.MAX_VALUE/10){
                if(sign == 1 && queue.peek() <= 7){
                    res = res * 10 + queue.poll();
                    return res;
                }else if(sign == -1 && queue.peek() <= 8){
                    res = sign*res * 10 + sign*queue.poll();
                    return res;
                }
            }
            res = res * 10 + queue.poll();
        }
        return sign * res;
    }
    /////////////////////////////////////////////
    public String addBinary(String a, String b) {
        StringBuffer sb = new StringBuffer();
        char[] arrayA = a.toCharArray();
        char[] arrayB = b.toCharArray();
        int maxLength = Math.max(arrayA.length, arrayB.length);

        int carry = 0;
        for(int i = 0; i < maxLength; i++){
            int bitA = retriveNumInArray(arrayA, arrayA.length - 1 - i);
            int bitB = retriveNumInArray(arrayB, arrayB.length - 1 - i);
            System.out.println("====");
            System.out.println(bitA);
            System.out.println(bitB);
            System.out.println(carry);
            int sum = (bitA + bitB + carry) % 2;
            carry = (bitA + bitB + carry) >= 2 ? 1:0;
            System.out.println("---");
            System.out.println(sum);
            System.out.println(carry);
            System.out.println("====");
            sb.insert(0,sum);
        }
        if(carry == 1){
            sb.insert(0,carry);
        }
        return sb.toString();
    }

//    public String addBinary(String a, String b) {
//        char[] arrayA = a.toCharArray();
//        char[] arrayB = b.toCharArray();
//        int resNum = binaryCharArrayToInt(arrayA) + binaryCharArrayToInt(arrayB);
//        return intToBinaryString(resNum);
//    }

    public int retriveNumInArray(char[] array, int idx){
        int length = array.length;
        if(idx >=0 && idx < length){
            return array[idx] - '0';
        }else{
            return 0;
        }
    }

    public int binaryCharArrayToInt(char[] array){
        int sum = 0;
        for(int i = 0; i < array.length; i++ ){
            sum = sum * 2 + (array[i]- '0');
        }
        return sum;
    }

    public String intToBinaryString(int n){
        if(n == 0) return "0";
        StringBuffer sb = new StringBuffer();
        while(n != 0){
            sb.insert(0,n % 2);
            n /= 2;
        }
        return sb.toString();
    }
    /////////////////////////////////////////////
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int commonArea = computeCommonArea(A,B,C,D,E,F,G,H);
        return (C - A) * (D - B) + (G - E)*(H - F) - commonArea;
    }
    public int computeCommonArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int width = 0;
        int height = 0;
        if(C <= E || G <= A || H <= B || D <= F){
            return 0;
        }

        // Get width
        if(C <= G){
            if(A>= E){
                width = C - A;
            }else{
                width = C - E;
            }
        }else{
            if(A >= E){
                width = G - A;
            }else{
                width = G - E;
            }
        }

        //Get Height
        if(H <= D){
            if(F>= B){
                height = H - F;
            }else{
                height = H - B;
            }
        }else{
            if(F >= B){
                height = D - F;
            }else{
                height = D - B;
            }
        }
        return height * width;
    }
    /////////////////////////////////////////////
    public int titleToNumber(String s) {
        char[] array = s.toCharArray();
        int sum = 0;
        for(int i = 0; i < array.length ; i++){
            int point = array[i] - 'A' + 1;
            sum = sum * 26 + point;
        }
        return sum;
    }
    /////////////////////////////////////////////
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        char[] array = s.toCharArray();
        int p1 = 0;
        int p2 = array.length - 1;
        while(p2 > p1){
            if(!((array[p1] >= 'a' && array[p1] <= 'z') || (array[p1] >= '0' && array[p1] <= '9'))){
                p1++;
                continue;
            }
            if(!((array[p2] >= 'a' && array[p2] <= 'z') || (array[p2] >= '0' && array[p2] <= '9'))){
                p2--;
                continue;
            }
            if(array[p1] != array[p2]){
                return false;
            }
            p1++;
            p2--;
        }
        return true;
    }
    /////////////////////////////////////////////
    public int strStr(String haystack, String needle) {
        for(int i = 0; i <= haystack.length() - needle.length(); i++){
            int j = 0;
            for(;j < needle.length(); j++){
                if(haystack.charAt(i+j) != needle.charAt(j)){
                    break;
                }
            }
            if (j == needle.length()){
                return i;
            }
        }
        return -1;
    }
    /////////////////////////////////////////////
    public String zigZagConvert(String s, int numRows) {
        StringBuilder[] sb = new StringBuilder[numRows];
        for(int i = 0; i< numRows; i++){
            sb[i] = new StringBuilder();
        }
        int idx  = 0;
        while(idx < s.length()){
            for(int i=0; i< numRows && idx < s.length() ; i++){
                sb[i].append(s.charAt(idx++));
            }
            for(int i=numRows-2; i > 0 && idx < s.length(); i--){
                sb[i].append(s.charAt(idx++));
            }
        }
        StringBuilder resSb = new StringBuilder();
        for(int i = 0;i < numRows; i++){
            resSb.append(sb[i].toString());
        }

        return resSb.toString();
    }
    /////////////////////////////////////////////
    public int compareVersion(String version1, String version2) {
        String[] versionArray1 = version1.split("\\.");
        String[] versionArray2 = version2.split("\\.");
        if(versionArray1.length > versionArray2.length){
            for (int i = 0; i < versionArray1.length - versionArray2.length; i++){
                version2 = version2 + ".0";
            }
            versionArray2 = version2.split("\\.");
        }

        if(versionArray2.length > versionArray1.length){
            for (int i = 0; i < versionArray2.length - versionArray1.length; i++){
                version1 = version1 + ".0";
            }
            versionArray1 = version1.split("\\.");
        }
        for(int i = 0; i < Math.min(versionArray1.length, versionArray2.length); i++){
            int diff = Integer.valueOf(versionArray1[i]) - Integer.valueOf(versionArray2[i]);
            if( diff > 0){
                return 1;
            }else if (diff < 0){
                return -1;
            }
        }
        return 0;
    }
    /////////////////////////////////////////////
    public String countAndSay(int n) {
        if(n == 1){
            return "1";
        }
        String res = countAndSay(n-1);
        StringBuilder sb = new StringBuilder();
        char preChar = ' ';
        int count = 0;
        for(int i = 0; i < res.length(); i++){

            if(res.charAt(i) != preChar){
                sb.append(count);
                sb.append(preChar);
                preChar = (res.charAt(i));
                count = 1;
            }else {
                count++;
            }

        }
        if(count != 0){
            sb.append(count);
            sb.append(preChar);
        }
        sb.delete(0, 2);
        return sb.toString();
    }
    /////////////////////////////////////////////
    public boolean isParenthesesValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] sArray = s.toCharArray();
        for(int i = 0; i < sArray.length; i++){
            if(sArray[i] == '(' || sArray[i] == '[' || sArray[i] == '{'){
                stack.push(sArray[i]);
            }else if(sArray[i] == ')'){
                if (stack.empty() || stack.pop() != '('){
                    return false;
                }
            }else if(sArray[i] == ']'){
                if (stack.empty() || stack.pop() != '['){
                    return false;
                }
            }else if(sArray[i] == '}'){
                if (stack.empty() || stack.pop() != '{'){
                    return false;
                }
            }
        }
        return stack.empty();
    }
    /////////////////////////////////////////////
    public int lengthOfLastWord(String s) {
        s = s + " ";
        int lengthLastWord = 0;
        int idxLastSpace = -1;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){

                if(i - idxLastSpace - 1 > 0){
                    lengthLastWord = i - idxLastSpace - 1;
                }
                idxLastSpace = i;
                continue;
            }
        }
        return lengthLastWord;
    }
    /////////////////////////////////////////////
    public boolean canConstruct1(String ransomNote, String magazine) {
        if( ransomNote.length() == 0 ){
            return true;
        }
        for(int i = 0; i < magazine.length() - ransomNote.length() + 1; i++){
            for(int j = 0; j < ransomNote.length();j++){
                if(ransomNote.charAt(j) != magazine.charAt(i+j)){
                    break;
                }
                if (j == ransomNote.length() - 1){
                    return true;
                }
            }
        }
        return false;
    }
    /////////////////////////////////////////////
    public boolean canConstruct(String ransomNote, String magazine) {
        if( ransomNote.length() == 0 ){
            return true;
        }
        HashMap<Character,Integer> mMap = new HashMap<>();
        for(int i = 0; i < magazine.length(); i++){
            char c = magazine.charAt(i);
            if(mMap.containsKey(c)){
                mMap.put(c, mMap.get(c) + 1);
            }else{
                mMap.put(c, 1);
            }
        }
        for(int i = 0; i < ransomNote.length(); i++){
            char c = ransomNote.charAt(i);
            if(mMap.containsKey(c)){
                mMap.put(c, mMap.get(c) - 1);
                if(mMap.get(c) == 0){
                    mMap.remove(c);
                }
            }else{
                return false;
            }
        }
        return true;
    }
    /////////////////////////////////////////////
    public int solution_saddle_point(int[][] A) {
        if(A.length<3 || A[0].length<3){
            return 0;
        }

        int count = 0;

        for (int row = 1; row < A.length-1; row++){
            for (int column = 1; column < A[0].length-1; column++){
                int point = A[row][column];
                if(point< A[row-1][column] && point < A[row+1][column] && point > A[row][column+1] && point > A[row][column-1]){
                    count++;
                    continue;
                }
                if(point > A[row-1][column] && point > A[row+1][column] && point < A[row][column+1] && point < A[row][column-1]){
                    count++;
                    continue;
                }
            }
        }

        return count;
    }
    /////////////////////////////////////////////
    public int solution_contiguous_group(int[] A) {
        // write your code in Java SE 8
        if(A.length == 1){
            return 0;
        }
        int[] sorted_A = A.clone();
        insertSort(sorted_A);
        int pre_i = trim_min(A,sorted_A);
        int post_i = trim_max(A,sorted_A);
        if(pre_i == -1 || post_i==-1){
            return 0;
        }
        return post_i - pre_i +1;
    }

    public int trim_min(int[] a,int[] sorted_A){
        int[] a_sorted = sorted_A;
        for(int i = 0 ;i<a.length;i++){
            if (a[i] != a_sorted[i]){
                return i;
            }
        }
        return -1;
    }

    public int trim_max(int[] a,int[] sorted_A){
        int[] a_sorted = sorted_A;
        for(int i = a.length-1;i >= 0;i--){
            if (a[i] != a_sorted[i]){
                return i;
            }
        }
        return -1;
    }

    public static int[] insertSort(int[] args){
        for(int i=1;i<args.length;i++){
            for(int j=i;j>0;j--){
                if (args[j]<args[j-1]){
                    int temp=args[j-1];
                    args[j-1]=args[j];
                    args[j]=temp;
                }else break;
            }
        }
        return args;
    }
    /////////////////////////////////////////////
    int solution_binary_period(int n) {
        int[] d = new int[30];
        int l = 0;
        int p;
        while (n > 0) {
            d[l] = n % 2;
            n /= 2;
            l++;
        }
        for (int i = 0; i<l;i++) {
            System.out.print(d[i]);
        }
        System.out.println("\nlength:"+l);

        for (p = 1; p < l; ++p) {
            int i;
            boolean ok = true;
            for (i = 0; i < l - p; ++i) {
                if (d[i] != d[i + p]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return p;
            }
        }
        return -1;
    }
    /////////////////////////////////////////////
//    public int solution_adjancent_indices(int[] A) {
//        // write your code in Java SE 8
//        HashMap<Integer,Integer> indices_map = new HashMap<>();
//        for(int i = 0;i< A.length;i++){
//            if(!indices_map.containsKey(A[i])){
//                indices_map.put(A[i],i);
//            }
//        }
//        int[] sortedA = A.clone();
//        Arrays.sort(sortedA);
//        int length = sortedA.length;
//        int max_distance = -1;
//        for(int i=1;i<length;i++){
//            if(sortedA[length - i] != sortedA[length - i - 1]){
//                max_distance = Math.max(max_distance,
//                        Math.abs(indices_map.get(sortedA[length - i]) - indices_map.get(sortedA[length - i - 1])));
//            }
//        }
//        return max_distance;
//    }
    public int solution_adjancent_indices(int[] A) {
        // write your code in Java SE 8
        HashMap<Integer,MinMax> indices_map = new HashMap<>();
        for(int i = 0;i< A.length;i++){
            if(!indices_map.containsKey(A[i])){
                MinMax mm = new MinMax(i);
                indices_map.put(A[i],mm);
            }else{
                MinMax mm = indices_map.get(A[i]);
                mm.setMax(i);
            }
        }
        int[] sortedA = A.clone();
        Arrays.sort(sortedA);
        int length = sortedA.length;
        int max_distance = -1;
        for(int i=1;i<length;i++){
            if(sortedA[length - i] != sortedA[length - i - 1]){
                max_distance = Math.max(max_distance,
                        Math.abs(indices_map.get(sortedA[length - i]).getMax() - indices_map.get(sortedA[length - i - 1]).getMin()));
                max_distance = Math.max(max_distance,
                        Math.abs(indices_map.get(sortedA[length - i - 1]).getMax() - indices_map.get(sortedA[length - i]).getMin()));
            }
        }
        return max_distance;
    }

    class MinMax{
        private int min = 0;
        private int max = -1;

        public MinMax(int min) {
            this.min = min;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max > 0?max:min;
        }

    }
    /////////////////////////////////////////////
    public int solution_long_bit_count(int A, int B) {
        // write your code in Java SE 8
        long n = (long)A * (long)B;
        return Long.bitCount(n);
    }

    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////
    /////////////////////////////////////////////

    //////////////////// TEST ///////////////////
    public void test() {
        int a = 0;
        int b = 8 & 8;
        System.out.println(b);
    }
}
