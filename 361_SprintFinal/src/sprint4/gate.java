package sprint4;

import java.awt.event.ActionEvent;
/*****
* implemented
* 
******/
public class gate implements sensor{


	private channel c;
	private Thread t;
	private boolean state;

	public gate(){}
		
		/**
		 * interrupt the thread indicating that this sensor has triggered
		 * 
		 */
		@Override
		public void trigger() {
			t.interrupt();
//			if(c!=null)
//			c.trig();

		}
		/**
		 * checks if this sensor is connected to any channel ports
		 * 
		 * 
		 * @return boolean
		 * 
		 */
		public boolean isHooked(){
			return c != null;
		}
		/**
		 * plug this sensor into a channel 
		 * 
		 */
		@Override
		public void plug(channel c) {
			// TODO Auto-generated method stub
			this.c = c;
			state = true;
			t.start();
		}
		/**
		 * unplug this sensor form a channel
		 * 
		 */
		public void unplug(channel c) {
			// TODO Auto-generated method stub
			this.c = null;
			state = false;
	        //System.out.println("disconnected on " + c.getChannelNumber());

		}
		/**
		 * loops until this sensor is unplugged. continuously sleeps for 5 seconds and
		 * waits for interrupt calls. if interrupted, signals the chronotimer to trigger
		 * thru this channel
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			    while(state) {
		            try {
		            	while(state){
		                Thread.sleep(5000);
		                System.out.println("runnung on " + c.getChannelNumber());
		            	}
		                System.out.println("test");
		            } catch (InterruptedException e) {
		                System.out.println("interrupted on " + c.getChannelNumber());

		                c.trig();

		            	}
			    }
	            System.out.println("disconnected on " + c.getChannelNumber());

	}


}
