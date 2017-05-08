package sprint4;

/**
 *  object representing a finish push button on the system
 *     
 */
public class channelFinishPad implements pad{
	private channel c;

	public channelFinishPad(channel c){
	this.c = c;
	}
	/**
	 * if system is on, trigger a finish event
	 * 
	 * @return boolean    
	 */
	@Override
	public void push() {
		// TODO Auto-generated method stub
		if(c.state()) c.finish();
	}
	/**
	 * not implemented
	 * @return 0    
	 */
	@Override
	public int push2() {return 0;}



}
