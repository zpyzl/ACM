package selfMng;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Challenge {
	private long learningBegin = new Date().getTime();
	private SimpleTimes durations = new SimpleTimes();
	private SimpleTime longestSegment;
	private String longestSegmentStr;
	private SimpleTime longestDayLearning;
	private String longestDayLearningStr;
	private SimpleTime dayBegin;
	private SimpleTime dayEnd;
	private SimpleTime dayLength;
	private NumberFormat nt = NumberFormat.getPercentInstance();
	private int status = 1;//1 for learning, 2 for entertain
	private long entBegin;
	private long msgInterval;
	
	public long getEntBegin() {
		return entBegin;
	}
	public void setEntBegin(long entBegin) {
		this.entBegin = entBegin;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Challenge(){
		loadProperties();
		startInputMonitor();
	}
	public void loadProperties(){
		//Strprop.getProperty("ifEclipse");
		//= System.console();
		Properties prop = new Properties();//���Լ��϶���   
		InputStream fis;
		try {
			fis = this.getClass().getResourceAsStream("/Challenge.properties");
			prop.load(fis);//�������ļ���װ�ص�Properties������  
		} catch (Exception e) {
			e.printStackTrace();
		}   
		longestSegment = new SimpleTime(prop.getProperty("longestSegment"));
		longestSegmentStr = 					
			"The longest segment record is "+longestSegment;

		longestDayLearning = new SimpleTime(prop.getProperty("longestDayLearning"));
		longestDayLearningStr = 					
			"The longest day learning record is "+longestDayLearning;
		
		dayBegin = new SimpleTime(prop.getProperty("dayBegin"));
		dayEnd = new SimpleTime(prop.getProperty("dayEnd"));
		dayLength = dayEnd.minus(dayBegin);
		msgInterval = Long.parseLong(prop.getProperty("msgInterval"));
		nt.setMinimumFractionDigits(Integer.parseInt(prop.getProperty("fractionDigits")));
	}
	
	private void startInputMonitor(){
		InputMonitor inputMonitor = new InputMonitor();
		inputMonitor.setChallenge(this);
		new Thread(inputMonitor).start();
	}
	public static void main(String[] args) {
		
		try {
			Challenge challe = new Challenge();
			challe.challengeNoEntertain();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public SimpleTime showEntDuration(){
		return showDuration(this.entBegin, "ENTERTAIN has last for ");
	}
	public static SimpleTime showDuration(final long beginTime, String msg){
		SimpleTime duration = calculateDurationUntilNow( beginTime);
		System.out.println(
				msg + duration +"\n");
		return duration;
	}
	
	private static SimpleTime calculateDurationUntilNow(final long beginTime){
		long currTime = new Date().getTime();
		long durationLong = currTime - beginTime;
		return SimpleTime.milsecToSimpleTime(durationLong);
	}
	
	private void challengeNoEntertain() throws InterruptedException, ExecutionException{
		while(true){
			if( status == 1 ){
				showDuration(learningBegin, longestSegmentStr+"\nNO ENTERTAIN has last for     ");
				printDayLearning();
			}else if( status == 2){
				showEntDuration();
			}
			TimeUnit.SECONDS.sleep(msgInterval);
		}
	}
	
	public void dealWithEntertain( SimpleTime entDuration){
		if( entDuration == null ){
			return;
		}
		setStatus(1);
		SimpleTime actualDuration = calculateDurationUntilNow( learningBegin).minus(entDuration);
		durations.add(actualDuration);
		System.out.println(
				longestSegmentStr+
				"\nThis pure NO ENTERTAIN segment finally lasts for \n"+
				"              "
				+ actualDuration
				+"\n");

		printDayLearning();
		
		resetBeginTime();
	}
	private void resetBeginTime(){
		learningBegin = new Date().getTime();
	}

	private SimpleTime getCurrentLearningDuration(){
		return this.durations.sum().plus( calculateDurationUntilNow( learningBegin) );
	}
	public void printDayLearning(){
		SimpleTime sum = getCurrentLearningDuration();
		/*SimpleTime currDuration = calculateDurationUntilNow( this.learningBegin);
		sum = sum.plus(currDuration);*/
		System.out.println("Until now the total learning time is "+sum);

		float percentF = sum.dividedBy(longestDayLearning);
		System.out.println("You have finished " + nt.format(percentF)
				+ " of the longest day learning record!");
		
		//how much today has passed
		Date now = new Date();
		SimpleTime nowSt = new SimpleTime(now.getHours() , now.getMinutes());
		SimpleTime passed = nowSt.minus(dayBegin);
		float passedF = passed.dividedBy(dayLength);
		System.out.println("Today has passed  "+nt.format(passedF)+"\n");
	}
	
	
	
	
	
}
