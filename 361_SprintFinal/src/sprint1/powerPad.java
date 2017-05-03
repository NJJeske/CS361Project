package sprint1;

public class powerPad implements pad {
	private chronotimer timer;
	public powerPad(chronotimer timer){
		this.timer = timer;
	}
	@Override
	public void push() {
		// TODO Auto-generated method stub
		if(!timer.powerState()) timer.turnOn();
		else timer.turnOff();
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


}
