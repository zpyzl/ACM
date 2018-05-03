package selfMng;

import java.io.Console;
import java.io.IOException;

public class ScheduleLearnAndFree {
	private static String bellDisk = "I";
	
	private void scheduleLearnAndFree(Console console){
		System.out.println("bell disk not I: ? y/n");
		if( "y".equalsIgnoreCase(console.readLine()) ){
			System.out.println("bell disk:");
			bellDisk = console.readLine();
		}
	}
	private void learnBell(){
		playBell("ImYours_clip.mp3");
	}
	private void freeBell(){
		playBell("PokerFace_clip.mp3");
	}
	private void playBell(String bellName){
		try {
			Runtime.getRuntime().exec(
					"C:\\Program Files (x86)\\Tencent\\QQPlayer\\QQPlayer.exe "
			+ bellDisk+":\\workspace2\\"+bellName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
