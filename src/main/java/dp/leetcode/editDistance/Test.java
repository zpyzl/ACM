package dp.leetcode.editDistance;

public class Test {

	public static void main(String[] args) {
		Solution sol = new Solution();
		
		int res = sol.minDistance("peek", "stepss");
		System.out.println(res);
		
		int res1 = sol.minDistance("", "");
		System.out.println(res1);
		
		int res2 = sol.minDistance("asf", "");
		System.out.println(res2);
		
		int res3 = sol.minDistance("", "dfe");
		System.out.println(res3);
		
		int res4 = sol.minDistance("tbb", "dfe");
		System.out.println(res4);
		
		int res5 = sol.minDistance("a", "a");
		System.out.println(res5);
	}
}
