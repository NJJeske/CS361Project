package sprint4;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class PARAIND implements Run {
	
	private Queue<competitor> pStart = new LinkedList<competitor>();
	private Queue<competitor> pRunning = new LinkedList<competitor>();
	private Queue<competitor> pFinished = new LinkedList<competitor>();
	
	private competitor a,b;
	private display d ;
	public PARAIND(){	
	}
	public PARAIND(display d){
		this.d = d;
	}
	/*  Next competitor is marked DNF
	 */
	public void DNF(){
		competitor x;
		if(pRunning.size() > 1) {x = pRunning.remove(); x.DNF(); pFinished.add(x);}
	}
	
	/*  Add competitor to starting Queue with ID
	 */
	public void addCompetitor(int ID){
		pStart.add(new competitor(ID));
	}
	
	/*  Trigger channel c with time t
	 *  Odd Channels start, Even channels finish
	 */
	public void triggered(channel c, time t){
		int chNum = c.getChannelNumber();
		switch(chNum)
		{
			case 1:
			case 3:
				start(t,c);
				break;				
			case 2:
			case 4:
				finish(t,c);
				break;
			default:
		}
	}
	
	/*   Generate and return string with each racer's ID and finish time
	 */
	public String print(){
		String run="";
		
		for (competitor p : pFinished) 
			if(p.isDNF())
				run += "competitor: " + p.getID() + " did not finish" + '\n';
			else
				run += p.getID() + " " + p.getElapsed().toString() + '\n';
		
		return run;
	}
	
	@Override
	public String toString(){
		return "PARAIND";
	}
	
	/*  Cancel current Run
	 */
	@Override
	public void cancel() {
		if(pRunning.size() > 2)
		{
			competitor toCancel1= pRunning.remove(), toCancel2 = pRunning.remove() ;
			Queue<competitor> temp = new LinkedList<competitor>();
			temp.add(toCancel1); temp.add(toCancel2);
			
			for(competitor old : pStart)
				temp.add(old);
			pStart = temp;
		}
	}
	
	/*  Finish with specified time and channel. Channel number should be Even
	 */
	@Override
	public void finish(time t, channel channel) {
		if(!pRunning.isEmpty())
			if(channel.getChannelNumber() == 2){ a = pRunning.remove(); a.setFinish(t); a.calculateElapsed(); sendDataToDisplay(a); pFinished.add(a); }
			else if(channel.getChannelNumber() == 4){ b = pRunning.remove(); b.setFinish(t); b.calculateElapsed(); sendDataToDisplay(b); pFinished.add(b); }
	}
	/*  Start with given time and channel.  Channel number should be ODD
	 */
	@Override
	public void start(time t, channel channel) {
		if(channel.getChannelNumber() == 1){ a = pStart.remove(); a.setStart(t); pRunning.add(a);}
		else if(channel.getChannelNumber() == 3){ b = pStart.remove(); b.setStart(t); pRunning.add(b);}
		else { System.out.println("Invalid start channel");}
	}
	
	/*  Return Queue of finished competitors
	 */
	@Override
	public Queue<competitor> getRun() {
		// TODO Auto-generated method stub
		return pFinished;
	}
	public competitor getA(){
		return a;
	}
	public competitor getB(){
		return b;
	}
	private void sendDataToDisplay(competitor x)
	{
		if(!pRunning.isEmpty())
			d.sendData(x.getID() + " " + x.getElapsed().toString() + "<R>");
		else
			d.sendData(x.getID() + " " + x.getElapsed().toString() + "<F>");
	}
}
