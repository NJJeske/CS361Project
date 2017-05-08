package sprint4;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


/*
 * this class represents a 'race' or a 'run' used by chronotimer
 * it has several queues:
 * 		the qStart queue is responsible for the competitors that are
 * 		queued to race, but the channel hasn't triggered yet. 
 * 		the qRunning queue is responsible for competitors who are currently
 * 		running the race.
 * 		the qFinished queue is responsible for competitors who have finished the 
 * 		race
 */
import java.util.Collection;

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
	/**
	 * toString
	 */
	public String toString();
	/**
	 * issue a DNF or did not finish for the  competitor at the head of queue
	 */
	public void DNF();
	/**
	 * print the race results on paper tab/console/magnetic tape
	 */
	public String print();
	/**
	 * cancel the current racer and add to front of queue
	 */
	public void cancel();
	/**
	 * handle a trigger event at channel c on time time
	 */
	public void triggered(channel c, time time);
	/**
	 * force finish using push pad
	 * @param (channel, time)
	 */
	public void finish(time t, channel channel);
	/**
	 * force start using push pad
	 *  @param (channel, time)
	 */
	public void start(time t, channel channel);
	/**
	 * get competitors who finished
	 *  @param (channel, time)
	 */
	public Collection<competitor> getRun();
	/**
	 * add competitor to current run with ID
	 *  @param int
	 * @return collection
	 */
	public void addCompetitor(int ID);
	
}

