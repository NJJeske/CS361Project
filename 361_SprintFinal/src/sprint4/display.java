package sprint4;

public class display {
	
	private chronotimer t ;
	private ChronoGUI gui;
	
	/********
	 * display constructor
	 *
	 */
	public display(chronotimer t)
	{
		this.t = t;
		gui = new ChronoGUI(t);
	}
	/********
	 * 
	 * sends data to the GUI display from chronotimer
	 *
	 *@return string
	 *******/
	public void sendData(String data)
	{
		gui.displayText(data);
	}
}
