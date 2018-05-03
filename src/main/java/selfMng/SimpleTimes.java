package selfMng;

import java.util.ArrayList;

public class SimpleTimes extends ArrayList<SimpleTime>{

	public SimpleTime sum(){
		SimpleTime res = new SimpleTime(0, 0);
		for( SimpleTime duration : this){
			res = res.plus(duration);
		}
		return res;
	}
}
