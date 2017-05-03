package sprint1;
/**
 *  channel enable/disable button on chronotimer
 *     
 */
public class channelStatePad implements pad {
	private channel c;

	public channelStatePad(channel c){
	this.c = c;
	}

	@Override
	public void push() {
		// TODO Auto-generated method stub
		if(c.state()) c.disable();
		else c.enable();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}



}
