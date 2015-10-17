package dp.poj.p1015;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Test {

	public static void main(String[] args) throws IOException {
		Main.f(genInput());
	}
	
	public static InputStream genInput(){
		StringBuilder input = new StringBuilder();
		
		Random rand = new Random();
		
		for(int r = 0; r < 5; r++){//round
			if( r != 0){
				input.append("\n");
			}
			//header
			int n = 150+rand.nextInt(20);
			int m = 15+rand.nextInt(4);
			input.append(n+" "+m+"\n");
			
			for( int l = 0; l < n;l++){
				int d = rand.nextInt(20);
				int p = rand.nextInt(20);
				input.append(d+" "+p+"\n");
			}
		}
		input.append("0 0");
		
		InputStream in =   new  ByteArrayInputStream(input.toString().getBytes());
		return in;
	}
}
