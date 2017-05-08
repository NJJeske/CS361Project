package sprint4;

import java.util.LinkedList;
import java.util.Queue;

import java.util.Vector;
/*****
* this class represents a IND run event. in this event, racers 
* queue up for a single race. by convention, the start channel is 1 and 
* finish channel is 2
* 
* methods are explained in interface Run in detail
* 
******/
public class IND implements Run{
	private Vector<competitor> qStart = new Vector<competitor>();
	private Vector<competitor> qRunning = new Vector<competitor>();
	private Vector<competitor> qFinished = new Vector<competitor>();
	private Vector<Integer> registeredNumbers = new Vector<Integer>();

	
	private display display;
	public IND(){}
	public IND(display d){display = d;}
	
	public void DNF()
	{
		if(!qRunning.isEmpty())
		{			
			qFinished.add(qRunning.remove(0));
			qFinished.lastElement().DNF();
		}
	}

	public void addCompetitor(int ID){
		if(registeredNumbers.contains(ID)) return ;
		else
		{
			registeredNumbers.add(ID);
			qStart.add(new competitor(ID));
			return ;
		}
	}

	public void triggered(channel c, time t){
		int chNum = c.getChannelNumber();
		switch(chNum)
		{
			case 1:
				start(t,c);
				break;				
			case 2:
				finish(t,c);
			default:
		}
	}
	                        
	
	public String print(){
		String run="";;;
		
		for (competitor e : qFinished) 
			if(!e.isDNF())
				run +=  e.getID() + " " + e.getElapsed().toString() + '\n';
			else
				run +=  e.getID() + " " + " <DNF>" + '\n';
		
		System.out.println(run);

		
		return run;
	}
	
	public void start( time t, channel c){ 
		if(c.getChannelNumber() % 2 == 1 && c.state())	
		{
			if(!qStart.isEmpty()) 
			{
				competitor w = qStart.remove(0);
				w.setStart(t);
				qRunning.add(w);
			}
		}
	}
	public void finish(time t, channel c)
	{
		if(c.getChannelNumber() % 2 == 0 && c.state())
		{
			if( !qRunning.isEmpty())
			{
				competitor comp = qRunning.remove(0);
				comp.setFinish(t);
				comp.calculateElapsed();
				if(display!= null)
				sendDataToDisplay(comp);
				qFinished.add(comp);
				//System.out.println("racer #" + comp.getID() + " finished with time " + t.getTime());
				
			}
		}
	}
	private void sendDataToDisplay(competitor x)
	{
		if(!qRunning.isEmpty())
			display.sendData(x.getID() + " " + x.getElapsed().toString() + "<R>");
		else
			display.sendData(x.getID() + " " + x.getElapsed().toString() + "<F>");
	}
	public void cancel(){
		if(qRunning.isEmpty())return;
		qStart.add(0,qRunning.remove(0));
	}
	@Override
	public String toString(){
		return "IND";
	}
	@Override
	public Vector<competitor> getRun() {
		// TODO Auto-generated method stub
		return qFinished;
	}
	public competitor getLastCompetitor(){
		return qFinished.lastElement();
	}
	
	public void swap(){
		if(qRunning.size() < 2){
		System.out.println("Not enough active racers");
		return;
		}
		competitor w = qRunning.remove(0),  x = qRunning.remove(0);
		qRunning.add(0,w);
		qRunning.add(0,x);
	}
	public Vector getVector(int x){
		if(x == 1) return qStart;
		else if(x == 2) return qRunning;
		else return qFinished;
	}
}