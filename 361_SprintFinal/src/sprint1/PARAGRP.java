package sprint1;

import java.util.Collection;
import java.util.Vector;

public class PARAGRP implements Run{
	private Vector<competitor> vStart = new Vector<competitor>();
	private Vector<competitor> vFinished = new Vector<competitor>();
	private time startTime;
	private boolean DNF = false;
	
	/*  Set DNF flag to apply to next finishing competitor
	 */
	@Override
	public void DNF() {	
		DNF = true;
	}
	
	/*  For each finished (or DNF) competitor,
	 *  add their racer ID and finish time to a string
	 */
	@Override
	public String print() {
		String run = "";
		for (competitor p : vFinished) 
			if(p.isDNF())
				run += "competitor: " + p.getID() + " did not finish" + '\n';
			else
				run += p.getID() + " " + p.getElapsed().getTime() + '\n';
	}
	
	/*  End the run.  All unfinished racer are marked as DNF
	 */
	@Override
	public void cancel() {
		for(int i=0; i< vStart.size(); i++){
			vStart.get(i).DNF();
			vFinished.add(vStart.remove(i));
		}
	}

	/*  Trigger channel c with time t
	 */
	@Override
	public void triggered(channel c, time t) {
		if(startTime == null)
			start(t, c);
		else
			finish(t, c);
	}
	
	/*  Finish specified channel with specified time.
	 *  Precondintion: Run must have a start time
	 */
	@Override
	public void finish(time t, channel channel) {
		int ind = channel.getChannelNumber() -1;
		if((vStart.size() < ind) || startTime == null)
			System.out.println("Invalid trigger");
		else{
			vFinished.add(ind, vStart.remove(ind));
			if(DNF)
				vFinished.get(ind).DNF();
			else	
				vFinished.get(ind).setFinish(t);
		}
		DNF = false;
	}
	
	/*  Start event.  Should be triggered on channel 1.
	 */
	@Override
	public void start(time t, channel channel) {
		if(channel.getChannelNumber() != 1)
			System.out.println("Illegal Trigger");
		else {
			startTime = t;
		}
		
	}
	
	/*  Return vector of finished racers
	 */
	@Override
	public Vector<competitor> getRun() {
		return vFinished;
	}
	
	/*  Add competitor to start with given ID
	 *  Max capacity: 8 competitors
	 */
	@Override
	public void addCompetitor(int ID) {
		if(vStart.size() > 7) 
			System.out.println("Lanes full");
		else
			vStart.add(new competitor(ID));
	}

}
