package sprint1;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


/*
 * this class represents a 'race' or a 'run' used by chronotimer
 * it has several queues:
 * 		the qStart queue is responsible for the competitors that are
 * 		queued to race, but the channel hasnt triggered yet. 
 * 		the qRunning queue is responsible for competitors who are currently
 * 		running the race.
 * 		the qFinished queue is responsible for competitors who have finished the 
 * 		race
 */
interface Run {

	public String toString();
	public void DNF();
	public String print();
	public void cancel();
	public void triggered(channel c, time time);
	public void finish(time t, channel channel);
	public void start(time t, channel channel);
	public Collection getRun();
	public void addCompetitor(int ID);
	
}
