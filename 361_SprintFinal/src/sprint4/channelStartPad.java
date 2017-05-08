package sprint4;
/**
 *  channel start button on chronotimer
 *     
 */
public class channelStartPad implements pad{
	private channel c;

	public channelStartPad(channel c){
	this.c = c;
	}
	/**
	 * if system is on, trigger a start event
	 * 
	 * @return boolean    
	 */
	@Override
	public void push() {
		// TODO Auto-generated method stub
		if(c.state()) c.start();
	}
	/**
	 * not implemented
	 * @return 0    
	 */
	@Override
	public int push2() {return 0;}





}

