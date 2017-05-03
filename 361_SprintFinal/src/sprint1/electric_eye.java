package sprint1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*****
* one of the types of sensors that can be hooked up to a channel
* if tripped, it will send a signal to chronotimer via thru channel port
* 
******/
public class electric_eye implements sensor{

	private channel c;
	private Thread t;
	private boolean state;
	
	public electric_eye(){
//		t = new Thread(this);
		state = false;
		
	}
	
	public void trigger() {
//		t.interrupt();
		if(c!=null)
		c.trig();

	}
	
	public boolean isHooked(){
		return c != null;
	}

	@Override
	public void plug(channel c) {
		// TODO Auto-generated method stub
		this.c = c;
		state = true;
//		t.start();
	}
	public void unplug(channel c) {
		// TODO Auto-generated method stub
		this.c = null;
		state = false;
//		try {
//            t.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
//		    while(true) {
//	            try {
//	            	while(true){
//	                Thread.sleep(5000);
//	                if(!state) return;
//	            	}
//	            } catch (InterruptedException e) {
//	            	if(c != null)
//	            		c.trig();
//	                System.out.println("triggered");
//
//	            	}
//     
//	}

}

}
