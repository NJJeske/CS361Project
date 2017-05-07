package sprint1;

import java.util.Collection;
import java.util.Vector;

public class PARAGRP implements Run{
	private Vector<competitor> vStart = new Vector<competitor>();
	private Vector<competitor> vFinished = new Vector<competitor>();
	private time startTime;
	private boolean DNF = false;
	
	@Override
	public void DNF() {
		DNF = true;
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancel() {
		for(int i=0; i< vStart.size(); i++){
			vStart.get(i).DNF();
			vFinished.add(vStart.remove(i));
		}
	}

	@Override
	public void triggered(channel c, time t) {
		if(startTime == null)
			start(t, c);
		else
			finish(t, c);
	}

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

	@Override
	public void start(time t, channel channel) {
		if(channel.getChannelNumber() != 1)
			System.out.println("Illegal Trigger");
		else {
			startTime = t;
		}
		
	}

	@Override
	public Vector<competitor> getRun() {
		return vFinished;
	}

	@Override
	public void addCompetitor(int ID) {
		if(vStart.size() > 7) 
			System.out.println("Lanes full");
		else
			vStart.add(new competitor(ID));
	}

}
