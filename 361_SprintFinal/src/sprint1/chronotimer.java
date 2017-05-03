package sprint1;

import java.util.ArrayList;
/**
 *  chronotimer 
 *     
 */
public class chronotimer {
	private time sysTime;
	
	private boolean powerState = false;
	private boolean runState;
	private updateServer client;
	
	private Run run;
	private String event;
	ArrayList<Run> allRuns = new ArrayList<Run>();
	
	private ChronoGUI GUI;
	channel[] ch;
	
	/*
	 * Start 			1 3 5 7
	 * enable/disable	1 3 5 7
	 * Finish			2 4 6 8
	 * enable/disable	2 4 6 8
	 */
	 pad[][] channelPads = new pad[2][9];
	 pad powerPad ;

	
	public chronotimer()
	{
		GUI = new ChronoGUI(this);
		client = new updateServer();
		run =   new IND(); // default event
		runState = true;				// default state (there are no new runs)
		sysTime = new time();		// default time
	//initializE channels
		ch = new channel[10];			
		for(int i = 1 ; i <= 8 ; i++)
			ch[i] = new channel(this, i, (sensor)null);
		
		ch[9] = new USBChannel(this,9,null);
		ch[9].enable();
	//initialize channelPads
			for(int j = 1; j <= 8 ; j++)
			{
				if(j%2 == 0)
				{
					channelPads[0][j] = new channelFinishPad(ch[j]);
					channelPads[1][j] = new channelStatePad(ch[j]);
				}
				else
				{
					channelPads[0][j] = new channelStartPad(ch[j]);
					channelPads[1][j] = new channelStatePad(ch[j]);
				}
			}
	//initialize powerPad
		powerPad = new powerPad(this);
	}
	/*****
	* it will return the current run(race) on the timer 
	* if an event is triggered on an enabled channel and no run is currently in place,
	* it will also create a new run.
	******/
	public Run getRun(){
		return run;
	}
	protected boolean powerState(){
		return powerState;
	}
	/*****
	* turn the system on
	* 
	******/
	protected void turnOn(){
		powerState=true;
		
	}
	/*****
	* turn the system off
	* 
	******/
	protected void turnOff(){
		powerState = false;
	}
	/*****
	* it will create a new run, only one run can be active at a time,
	* as indicated by run state
	* 
	******/
	public void createRun(){
		if(!runState)
		{
			if(event.equals("IND")) 	run = new IND();
			if(event.equals("PARAIND"))	run = new PARAIND();
			if(event.equals("GRP"))		run = new GRP();
			
			runState = true;
		}
	}
	/*****
	* it will end the current run 
	* 
	******/
	public void endRun(){
			//client.update(run);

		if(runState)
		{
			allRuns.add(run);
			run = null;
			runState=false;
		}
	}
	public void CONN(int channelNumber, sensor s){
		ch[channelNumber].setSensor(s);
	}
	/*****
	* print the current run
	* 
	******/
	public String print(){
		return run.print();
	}
	/*****
	* set system to initial state
	* 
	******/
	public void pushResetButton(){
		event =  "IND"; // default event
		runState = false;				// default state (there are no new runs)
		run = null;
		allRuns.clear();
		sysTime = new time();		// default time

	}
	/*****
	* set the systemTime
	* 
	******/
//	public void setTime( int timeInMilli){
//		time.SetTime(timeInMilli);
//	}
	/*****
	* issue a cancel on a competitor. more details in class Run
	* 
	******/
	public void cancel(time t){
		run.cancel();
	}
	/*****
	* issue a DNF
	* 
	******/
	public void DNF(){
		run.DNF();
	}
	/*****
	* issue a trigger event
	* 
	******/
	protected void signal(channel c) {
		// TODO Auto-generated method stub
		sysTime.update();
		run.triggered(c, sysTime);
		if(run instanceof IND){
			competitor x = ((IND) run).getCompetitorWhoTriggered();
			if(c.getChannelNumber()%2 == 0 && x != null && !((IND)run).isLast() && !x.isDNF())
				GUI.displayText(x.getID() + " " + x.getElapsed().getTime() + "<R>");
			else if(((IND)run).isLast() && x != null){
				GUI.displayText(x.getID() + " " + x.getElapsed().getTime() + "<F>");
				((IND)run).setCompetitorWhoTriggered();
			}
		}
		else if(run instanceof PARAIND){
		//todo
			competitor a = ((PARAIND) run).getA();
			competitor b = ((PARAIND) run).getB();
			if(a != null) if(a.getFinish() != null){ GUI.displayText(a.getID() + " " + a.getElapsed().getTime() + "<R>");  ((PARAIND) run).setA();}
				if(b != null) if(b.getFinish() != null){
					GUI.displayText(b.getID() + " " + b.getElapsed().getTime() + "<R>"); ((PARAIND) run).setB();}
		}
		else if(run instanceof GRP){
			//todo
			competitor x = ((GRP) run).getLastCompetitor();
			if(x != null)
				GUI.displayText(x.getID() + " " + x.getElapsed().getTime() + "<R>");
			}
	}
//	/*****
//	* set the system time 
//	* 
//	******/
//	public void setTime(time t) {
//		// TODO Auto-generated method stub
//		this.sysTime = t;
//	}
	/*****
	* export specified run number to USB as JSON
	* 
	******/
	public void export(int runNumber){
		
		USBChannel u = (USBChannel) ch[9];
		u.writeToUSB(allRuns.get(runNumber), runNumber);
	}
	/*****
	* set the type of event {IND, PARAIND}
	* 
	******/
	public void setEvent(String event)
	{
		this.event = event;
	}
	
	public void NUM(int ID){
		run.addCompetitor(ID);
		GUI.displayText(""+ID + " <" + sysTime.getTime()+">");
	}
	public time getTime(){
		return sysTime;
	}
}
