package sprint4;

/**
 * This class represents channel ports on the timer.
 * each channel can be enabled/disabled and can be hooked up to a sensor
 * 
 * @since          sprint 2 
 */
public class channel {
	
	private boolean state = false;
	private chronotimer timer;
	private int channelNumber;
	private sensor eye;

	public channel(chronotimer timer, int channelNumber, sensor s){
		this.timer = timer;
		this.channelNumber = channelNumber;
		eye = s;
	}
	/**
	 * enables a channel 
	 * 
	 * @param none    
	 * @return nothing    
	 */
	public void enable(){
		state = true;
		System.out.println("enable: " + channelNumber);
	}
	/**
	 * disables a channel 
	 * 
	 * @param none    
	 * @return nothing    
	 */
	public void disable(){
		state = false;
		System.out.println("disable: " + channelNumber);

	}
	/**
	 * returns true if enables and false if disbaled
	 * 
	 * @return boolean    
	 */
	public boolean state(){
		return state ;
	}
	/**
	 * returns channel Number
	 * 
	 * @return int    
	 */
	public int getChannelNumber(){
		return channelNumber;
	}
	/**
	 * hook up a sensor
	 * 
	 * @param sensor    
	 */
	public void setSensor(sensor s){
		eye = s;
		eye.plug(this);
	}
	
	public void start(){
		timer.signal(this);
	}
	public void finish(){
		timer.signal(this);
	}
	/**
	 * returns sensor that is hooked up to this channel
	 * 
	 * @param    
	 * @return  sensor
	 */
	public sensor getSensor(){
		return eye;
	}
	/**
	 * signal chronotimer that this channel has been triggered
	 * 
	 * @param   
	 * @return     
	 */
	protected void trig() {
		// TODO Auto-generated method stub
		if(eye != null)
		if(eye.isHooked() && state == true)
			timer.signal(this);
	}
	
	public void disconnectSensor(){
		if(eye != null)
		eye.unplug(this);
		eye = null;
	}
	public boolean hasSensor(){
		return eye != null;
	}
	
}
