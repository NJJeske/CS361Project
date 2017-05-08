package sprint4;

public class printer {
	private boolean state;
	public printer(){
		
	}
	public void powerOn(){
		if(!state) state = true;
	}
	public void powerOff(){
		if(state) state = false;
	}
	public String print(Run r){
		return r.toString();
	}
	public boolean state(){
		return state;
	}
}
