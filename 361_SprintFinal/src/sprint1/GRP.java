package sprint1;

import java.util.Collection;
import java.util.Vector;

public class GRP extends runAbstract{

private Vector<competitor> vFinished = new Vector<competitor>();

int i = 0; // finish position
int n = 0; // position of next racer to set ID of
private time startTime;
boolean lock;
 
public GRP(){
 
}

@Override
public void addCompetitor(int ID){
	assign(ID);
}

@Override
public void triggered(channel c, time time) {
if(c.getChannelNumber() == 1){
start(time, c);
}
else if(c.getChannelNumber() == 2){
finish(time, c);
}
}
@Override
public void DNF() {
// TODO Auto-generated method stub
	vFinished.add(new competitor(i));
	vFinished.get(i++).DNF();
}

@Override
public String print() {
	String run = "";
	for (competitor p : vFinished) 
		if(p.isDNF())
			run += "competitor: " + p.getID() + " did not finish" + '\n';
		else
			run += p.getID() + " " + p.getElapsed().getTime() + '\n';
	
     // run += e.getID()  + '\n';
//System.out.println(run);
	return run;
}

@Override
public void cancel() {
// TODO Auto-generated method stub
}
//@Override
public void finish(time t, channel c) {
if(i > 9999){
System.out.println("Too many racers");
return;
}
vFinished.add(new competitor(t, i++));
if(!vFinished.lastElement().isDNF()){
vFinished.lastElement().setStart(startTime);
vFinished.lastElement().calculateElapsed();
}

}

//@Override
public void start(time t, channel c) {
	if(lock == false){
		startTime = new time(t.getMilli());
		lock = true;
	}
}

@Override
public Vector getRun() {
	// TODO Auto-generated method stub
	return vFinished;
}
@Override
public void assign(int ID){
	if (vFinished.get(n+1) == null){
	System.out.println("No more racers");
	return;
	}
	vFinished.get(n++).setID(ID);
}


public competitor getLastCompetitor(){
	if(!vFinished.isEmpty())
	return vFinished.lastElement();
	return null;
}

}