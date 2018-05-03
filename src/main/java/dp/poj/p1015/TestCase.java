package dp.poj.p1015;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class TestCase {

	public static void main(String[] args) throws IOException {
		Main.f(genInput());
	}
	
	public static InputStream genInput(){
		StringBuilder input = new StringBuilder();
		
		Random rand = new Random();
		
		for(int r = 0; r < 9999; r++){//round
			if( r != 0){
				input.append("\n");
			}
			//header
			int m = 1+rand.nextInt(20);
			int n = m+rand.nextInt(181);
			input.append(n+" "+m+"\n");
			
			for( int l = 0; l < n;l++){
				int d = rand.nextInt(21);
				int p = rand.nextInt(21);
				input.append(d+" "+p+"\n");
			}
		}
		input.append("0 0");
		
		InputStream in =   new  ByteArrayInputStream(input.toString().getBytes());
		return in;
	}
}
