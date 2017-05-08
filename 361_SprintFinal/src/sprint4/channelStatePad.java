package sprint4;
/**
 *  channel enable/disable button on chronotimer
 *     
 */
public class channelStatePad implements pad {
	private channel c;

	public channelStatePad(channel c){
	this.c = c;
	}
	/**
	 * enable or disable the channel
	 * 
	 * @return boolean    
	 */
	@Override
	public void push() {
		// TODO Auto-generated method stub
		if(c.state()) c.disable();
		else c.enable();
	}
	/**
	 * not implemented
	 * @return 0    
	 */
	@Override
	public int push2() {return 0;}





}