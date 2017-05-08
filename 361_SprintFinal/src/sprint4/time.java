package sprint4;


/**
 * Time class that handles complex time management 
 * 
 */
public class time {
	private long time;
	
	public time(){
		time = System.currentTimeMillis();
	}
	public time(long milli){
		time = milli;
	}
	
	/**
	 * update the time using local system time in milli
	 * 
	 */
	public void update(){
		time = System.currentTimeMillis();
	}
	/**
	 * HH:MM:SS.milli
	 * @return string
	 * 
	 */
	public String toString(){
		return getHr() + ":" + getMin() + ":" +getSec() + "." + (getMilli()- (getHr()*(1000*60*60) + getMin()*(1000*60) + getSec()*1000))%999;
	}
	/**
	 * get Hr
	 * @return long
	 * 
	 */
	public long getHr(){
		return (( time / (1000*60*60)) % 24);
	}
	/**
	 * get min
	 * @return long
	 * 
	 */
	public long getMin(){
		return  (( time / (1000*60)) % 60);
	}
	/**
	 * get sec
	 * @return long
	 * 
	 */
	public long getSec(){
		return  (time / 1000) % 60 ;
	}
	/**
	 * get milli
	 * @return long
	 * 
	 */
	public long getMilli(){
		return time ;
	}
	/**
	 * set the time 
	 * @param long
	 * 
	 */
	public void SetTime( long timeInMilli){
		time = timeInMilli;
	}
}

