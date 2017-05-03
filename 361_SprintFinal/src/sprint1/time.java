package sprint1;

/*
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
	
	public void update(){
		time = System.currentTimeMillis();
	}
	public String getTime(){
		return getHr() + ":" + getMin() + ":" +getSec() + "." + (getMilli()- (getHr()*(1000*60*60) + getMin()*(1000*60) + getSec()*1000));
	}
	public long getHr(){
		return (( time / (1000*60*60)) % 24);
	}
	public long getMin(){
		return  (( time / (1000*60)) % 60);
	}
	public long getSec(){
		return  (time / 1000) % 60 ;
	}
	public long getMilli(){
		return time ;
	}
	public void SetTime( long timeInMilli){
		time = timeInMilli;
	}
}
