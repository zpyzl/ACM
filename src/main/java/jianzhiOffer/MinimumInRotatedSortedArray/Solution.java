package jianzhiOffer.MinimumInRotatedSortedArray;

import junit.framework.Assert;

import org.junit.Test;

public class Solution {
    public int findMin(int[] nums) {
    	
    	return insert3Stubs( nums, 0, nums.length);
    }
    
    private Integer insert3Stubs(int[] nums, int begin, int end){
    	int len = end - begin;
    	
    	if( len == 1)
    		return nums[begin];
    	if( len == 2){
    		return nums[begin] < nums[begin+1] ? nums[begin] : nums[begin+1];
    	}else if( len == 3){
    		if( nums[begin] > nums[begin+1])
    			return nums[begin+1];
    		else if( nums[begin+1] > nums[begin+2])
    			return nums[begin+2];
    		else 
    			return nums[begin];
    	}
    	
    	InsertRes res1, res2, res3;
    	res1 = insertStub(nums, begin+len/2/2) ;
    	if( res1.min != null )
    		return res1.min;
    	res2 = insertStub(nums,begin+len/2) ;
    	if( res2.min != null )
    		return res2.min;
    	res3 = insertStub(nums,begin+len*3/4);
		if( res3.min != null )
    		return res3.min;
		
		if( res1.insertPoint < res2.insertPoint && res2.insertPoint < res3.insertPoint){
			Integer r1 = insert3Stubs( nums, begin, begin+len/2/2+1 );
			if( r1 == null )
				return insert3Stubs( nums, begin+len*3/4, end );
			else 
				return r1; 
		}
		if( res1.insertPoint < res2.insertPoint && res2.insertPoint > res3.insertPoint
				&& res1.insertPoint > res3.insertPoint ){
			return insert3Stubs( nums, begin+len/2 , begin+len*3/4+1 );
		}else if( res1.insertPoint > res2.insertPoint && res2.insertPoint < res3.insertPoint
				&& res1.insertPoint > res3.insertPoint ){
			return insert3Stubs( nums, begin+len/2/2 , begin+len/2+1 );
		}else{
			return nums[begin];
		}
    }

	private InsertRes insertStub(int[] nums, int i) {
		InsertRes res = new InsertRes();
		if( (i+1) < nums.length && nums[i] > nums[i+1] ){
			res.min = nums[i+1];
			return res;
		}else{
			res.insertPoint = nums[i];
			return res;
		}
	}
	
	@Test
	public void test1(){
		int[] a = {1,2,3,4,5};
		Solution s = new Solution();
		Assert.assertEquals(1, s.findMin(a));
	}
	@Test
	public void test2(){
		int[] a = {4,5,1,2,3};
		Solution s = new Solution();
		Assert.assertEquals(1, s.findMin(a));
	}
	@Test
	public void test3(){
		int[] a = {3,4,5,6,7,1,2};
		Solution s = new Solution();
		Assert.assertEquals(1, s.findMin(a));
	}
	@Test
	public void test4(){
		int[] a = {5,1,2,3,4};
		Solution s = new Solution();
		Assert.assertEquals(1, s.findMin(a));
	}
	
}

class InsertRes{
	Integer min;
	int insertPoint;
}