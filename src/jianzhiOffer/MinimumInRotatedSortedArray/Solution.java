package jianzhiOffer.MinimumInRotatedSortedArray;

public class Solution {
    public int findMin(int[] nums) {
    	
    	return insert3Stubs( nums, 0, nums.length);
    }
    
    private Integer insert3Stubs(int[] nums, int begin, int end){
    	int len = end - begin;
    	
    	if( len == 1)
    		return nums[begin];
    	if( len == 2)
    		return nums[begin+1];
    	if( len == 3){
    		if( nums[begin] > nums[begin+1])
    			return nums[begin+1];
    		else if( nums[begin+1] > nums[begin+2])
    			return nums[begin+2];
    		else 
    			return nums[begin];
    	}
    	
    	InsertRes res1, res2, res3;
    	res1 = insertStub(nums, len/2/2) ;
    	if( res1.min != null )
    		return res1.min;
    	res2 = insertStub(nums,len/2) ;
    	if( res2.min != null )
    		return res2.min;
    	res3 = insertStub(nums,len*3/4);
		if( res3.min != null )
    		return res3.min;
		
		if( res1.insertPoint < res2.insertPoint && res2.insertPoint < res3.insertPoint){
			Integer r1 = insert3Stubs( nums, begin, len/2/2 );
			if( r1 == null )
				return insert3Stubs( nums, len*3/4, len );
			else 
				return r1; 
		}
		if( res1.insertPoint < res2.insertPoint && res2.insertPoint > res3.insertPoint
				&& res1.insertPoint > res3.insertPoint )
			return insert3Stubs( nums, len/2 , len*3/4 );
		
		if( res1.insertPoint > res2.insertPoint && res2.insertPoint < res3.insertPoint
				&& res1.insertPoint > res3.insertPoint )
			return insert3Stubs( nums, len/2/2 , len/2 );
		
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