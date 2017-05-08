package sprint4;

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
	/**
	 * not implemented
	 * @return 0    
	 */
	@Override
	public int push2() {return 0;}


}
