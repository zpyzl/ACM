package selfMng;

import java.util.Date;
import java.util.Scanner;

public class InputMonitor implements Runnable{
	private Scanner in = new Scanner(System.in);
	private Challenge challenge;
	
	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	@Override
	public void run() {
		String breakStr = null;
		while((breakStr = in.nextLine()) != null){
			SimpleTime entDuration = null;
			if( breakStr.equals("l")){//input entertain length
				System.out.println("entertain time:");
				String entTimeStr = in.nextLine();
				entDuration = new SimpleTime(entTimeStr);
				challenge.dealWithEntertain( entDuration);
			}
			if( breakStr.equals("b")){//input entertain begin
				System.out.println("Entertain begins now.");
				challenge.setEntBegin(new Date().getTime());
				challenge.setStatus(2);
			}
			if( breakStr.equals("e")){//input entertain end
				System.out.println("Entertain ends now.");
				
				entDuration = challenge.showEntDuration();
				challenge.dealWithEntertain( entDuration);
			}
			if( breakStr.equals("p")){
				challenge.printDayLearning();
			}
		}
	}
	
}
