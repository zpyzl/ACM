package jianzhiOffer.MinimumInRotatedSortedArray;

public class Solution {
    public int findMin(int[] nums) {
    	
    	return insert3Stubs( nums, 0, nums.length);
    }
    
    private Integer insert3Stubs(int[] nums, int begin, int end){
    	if( nums.length == 1)
    		return nums[0];
    	if( nums.length == 2)
    		return nums[1];
    	if( nums.length == 3){
    		if( nums[0] > nums[1])
    			return nums[1];
    		else if( nums[1] > nums[2])
    			return nums[2];
    		else 
    			return nums[0];
    	}
    	
    	InsertRes res1, res2, res3;
    	res1 = insertStub(nums, nums.length/2/2) ;
    	if( res1.min != null )
    		return res1.min;
    	res2 = insertStub(nums,nums.length/2) ;
    	if( res2.min != null )
    		return res2.min;
    	res3 = insertStub(nums,nums.length*3/4);
		if( res3.min != null )
    		return res3.min;
		
		if( res1.insertPoint < res2.insertPoint && res2.insertPoint < res3.insertPoint){
			Integer r1 = insert3Stubs( nums, 0, nums.length/2/2 );
			if( r1 == null )
				return insert3Stubs( nums, nums.length*3/4, nums.length );
			else 
				return r1; 
		}
		if( res1.insertPoint < res2.insertPoint && res2.insertPoint > res3.insertPoint
				&& res1.insertPoint > res3.insertPoint )
			return insert3Stubs( nums, nums.length/2 , nums.length*3/4 );
		
		if( res1.insertPoint > res2.insertPoint && res2.insertPoint < res3.insertPoint
				&& res1.insertPoint > res3.insertPoint )
			return insert3Stubs( nums, nums.length/2/2 , nums.length/2 );
		
		return null;
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
}

class InsertRes{
	Integer min;
	int insertPoint;
}