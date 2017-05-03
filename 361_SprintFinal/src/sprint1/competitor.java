package sprint1;
/*
 * this class represents the racers in a race/run. they have their owwn
 * start, finish and elapsed time with ID. methods are mainly setters and
 * getters. they will be queued in a Run object.
 */
public class competitor {
	private int ID;
	private time elapsed;
	private  time start, finish;
	private boolean DNF ;
	
	public competitor(int ID){
		this.ID = ID;
		DNF = false;
	}
	public competitor(time finished, int ... ID){
		this.ID = ID[0];
		finish = finished;
		DNF = false;
	}
	public void setID(int ID){
		this.ID = ID;
	}
	public void setStart(time t){
		start = new time(t.getMilli());
	}
	public void setFinish(time t){
		finish = new time(t.getMilli());
	}
	public int getID(){
		return ID;
	}
	public time getFinish(){
		return finish;
	}
	public time getStart(){
		return start;
	}
	public time getElapsed(){
		return elapsed;
	}
	public time calculateElapsed(){
		this.elapsed = new time(finish.getMilli() - start.getMilli());
		return elapsed;
	}
	public boolean isDNF(){
		return DNF;
	}
	public void DNF(){
		DNF = true;
	}
	public String toString(){
		if(!DNF)
		return ID + " " + elapsed.getTime();
		else
			return ID + " " + "DNF";
		

	}
}
