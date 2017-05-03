package sprint1;
/**
 *  channel start button on chronotimer
 *     
 */
public class channelStartPad implements pad{
	private channel c;

	public channelStartPad(channel c){
	this.c = c;
	}

	@Override
	public void push() {
		// TODO Auto-generated method stub
		if(c.state()) c.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}



}
