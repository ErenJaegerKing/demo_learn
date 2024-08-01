package test;

public class test1 {

    public static void main(String[] args) {

    }
}

//    public boolean isValid(String s) {
//        Stack<Character> left = new Stack<>();
//        for(char c : s.toCharArray()) {
//            if (c == '(' || c == '{' || c == '[') {
//                left.push(c);
//            } else {
//                if (left.isEmpty() || pipei(c) != left.pop()) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//    char pipei(char c) {
//        if (c == ')') {
//            return '(';
//        } else if (c == '}') {
//            return '{';
//        } else {
//            return '[';
//        }
//    }

//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        pq.add(2);
//        pq.add(5);
//        pq.add(1);
//        System.out.println(pq.peek());
//        int[] nums = {1,23,2,34,5,34};
//        System.out.println(Arrays.toString(nums));
//        int k = 2;
//        for (int n : nums) {
//            pq.offer(n);
//            if (pq.size() > k) {
//                pq.poll();
//            }
//        }
//        System.out.println(pq.peek());
//        System.out.println(pq.peek());
//        System.out.println(pq.peek());
//        HashMap<Integer,Integer> map = new HashMap();
//        for (int v : nums) {
//            map.put(v,map.getOrDefault(v,0) + 1);
//        }
//        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0] - a[0]);
//        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
//            pq.offer(new int[]{entry.getValue(), entry.getKey()});
//        }
//        int[] res = new int[k];
//        for (int i = 0;i < k;i++) {
//            if (pq.peek() != null) {
//                res[i] = pq.peek()[1];
//            }
//        }

