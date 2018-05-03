package selfMng;

public class SimpleTime {

	private int hours;
	private int minutes;
	private int seconds = 0;
	public SimpleTime(String timeStr){
		String[] times = timeStr.split(":");
		this.hours = Integer.parseInt(times[0]);
		this.minutes = Integer.parseInt(times[1]);
	}
	public SimpleTime(int hours, int minutes) {
		this.hours = hours;
		this.minutes = minutes;
		//this.seconds = seconds;
	}
	public SimpleTime(String hour,
			String min) {
		this.hours = Integer.parseInt(hour);
		this.minutes = Integer.parseInt(min);
		
	}
	public SimpleTime(int hRes, int mRes, int sRes) {
		this.hours = hRes;
		this.minutes = mRes;
		this.seconds = sRes;
	}
	public String toString(){
		return  hours + " hours " + minutes + " minutes " + seconds + " seconds "; 
	}
	
	public int getTotalSeconds(){
		return 60 * 60 * this.hours + 60 * this.minutes + this.seconds;
	}
	/*public int getTotalMinutes(){
		return (int) (60 * this.hours + this.minutes);
	}*/
	public static SimpleTime secondsToSimpleTime(int seconds){
		int mResTotal = seconds / 60;
		int sRes = seconds % 60;
		int hRes = mResTotal / 60;
		int mRes = mResTotal % 60;
		
		return new SimpleTime(hRes , mRes, sRes);
	}
	
	public SimpleTime plus(SimpleTime t2){
		int m1 = this.getTotalSeconds();
		int m2 = t2.getTotalSeconds();
		int mRes = m1 + m2;
		
		return secondsToSimpleTime(mRes);
	}
	public SimpleTime minus(SimpleTime t2){
		int m1 = this.getTotalSeconds();
		int m2 = t2.getTotalSeconds();
		int minDiff = (int) (m1 - m2);
		
		return secondsToSimpleTime(minDiff);
	}
	public float dividedBy(SimpleTime t2){
		int m1 = this.getTotalSeconds();
		int m2 = t2.getTotalSeconds();
		return (float)m1/(float)m2;
	}
	
	public static SimpleTime milsecToSimpleTime(long mss) {  
		int days = (int) (mss / (1000 * 60 * 60 * 24));  
	    int hours = (int) ((mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));  
	    int minutes = (int) ((mss % (1000 * 60 * 60)) / (1000 * 60));  
	    int seconds = (int) ((mss % (1000 * 60)) / 1000);  
	    return new SimpleTime(hours,minutes,seconds);
	}  
	
	
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	
	
	
	
}
