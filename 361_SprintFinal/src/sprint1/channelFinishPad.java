package sprint1;

/**
 *  channel finish button on chronotimer
 *     
 */
public class channelFinishPad implements pad{
	private channel c;

	public channelFinishPad(channel c){
	this.c = c;
	}

	@Override
	public void push() {
		// TODO Auto-generated method stub
		if(c.state()) c.finish();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


}
