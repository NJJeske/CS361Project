package sprint1;

import java.util.LinkedList;
import java.util.Queue;
/*****
* this class represents a IND run event. in this event, racers 
* queue up for a single race. by convention, the start channel is 1 and 
* finish channel is 2
* 
* methods are explained in interface Run in detail
* 
******/
public class IND implements Run{
	private Queue<competitor> qStart = new LinkedList<competitor>();
	private Queue<competitor> qRunning = new LinkedList<competitor>();
	private Queue<competitor> qFinished = new LinkedList<competitor>();
	private competitor whoTriggered;
	private int counter=0;
	
	
public IND(){}
	
	public void DNF() {
		if(!qRunning.isEmpty())
		{
			competitor c = qRunning.remove();
			c.DNF();
			qFinished.add(c);
		}
	}
	
	/*  Add competitor to start Queue with specified ID
	 */
	public void addCompetitor(int ID){
		qStart.add(new competitor(ID));
		counter++;
	}
	
	/*  Trigger channel c with time t
	 *  Channel 1 is a start, Channel 2 is a finish
	 */ 
	public void triggered(channel c, time t) {
		int chNum = c.getChannelNumber();
		switch(chNum)
		{
			case 1:
				start(t,c);
				break;				
			case 2:
				finish(t,c);
				break;
			default:
		}
	}
	
	/*  Create string with all Competitiors and their finish (or DNF) times 
	 *  Return generated string
	 */
	public String print(){
		String run="";
		
		for (competitor e : qFinished) 
			if(!e.isDNF())
	       run +=  e.getID() + " " + e.getElapsed().getTime() + '\n';
			else
			 run +=  e.getID() + " " + " <DNF>" + '\n';
		
		System.out.println(run);

		return run;
	}
	
	/*  Start with given channel and time.  Start should be on Odd Channel number
	 */
	public void start( time t, channel c){ 
		if(c.getChannelNumber() % 2 == 1 && c.state())	
		{
			if(!qStart.isEmpty()) 
			{
				competitor w = qStart.remove();
				w.setStart(t);
				qRunning.add(w);
				whoTriggered = w;
			}
		}
	}
	
	/*   Finish with given channel and time. Finish should be on Even channel number
	 */
	public void finish(time t, channel c)
	{
		if(c.getChannelNumber() % 2 == 0 && c.state())
		{
			if( !qRunning.isEmpty())
			{
				competitor comp = qRunning.remove();
				comp.setFinish(t);
				comp.calculateElapsed();
				qFinished.add(comp);
				whoTriggered = comp;
				counter--;
				//System.out.println("racer #" + comp.getID() + " finished with time " + t.getTime());		
			}
		}
	}
	
	/*  Cancel the current run
	 */
	public void cancel(){
		if(!qStart.isEmpty())return;
		
		qStart.add(qRunning.remove());

		for(int i = 0 ; i <qStart.size()-1 ; i++)
			qStart.add(qStart.remove());
		for(competitor e : qStart)
			System.out.println("asdas" + e.getID());
		qStart.peek().setStart(null);
		System.out.println(qStart.peek().getID() + " cancelled");
	}
	
	@Override
	public String toString(){
		return "IND";
	}
	
	/*  Return Queue of finished competitors
	 */
	@Override
	public Queue<competitor> getRun() {
		return qFinished;
	}
	
	public competitor getCompetitorWhoTriggered(){
		return whoTriggered;
	}
	
	public void setCompetitorWhoTriggered(){
		 whoTriggered = null;
	}
	
	public boolean isLast(){
		return counter == 0;
	}
	
	/*  Swap position of the next 2 competitors to finish
	 */
	public void swap(){
		if(qRunning.size() < 2){
		System.out.println("Not enough active racers");
		return;
		}
		Queue<competitor> qTemp = new LinkedList<competitor>();
		competitor c = qRunning.remove();
		qTemp.add(qRunning.remove());
		qTemp.add(c);
		 
		for(int i=0; i< qRunning.size(); ++i){
		qTemp.add(qRunning.remove());
		}
		qRunning = qTemp;
		}
	
}
