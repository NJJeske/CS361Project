package sprint4;


public class keyPad {
	private pad[] pad;

	public keyPad(){
		pad = new pad[13];
		for(int i = 1 ; i <= 9 ; i++)		
			pad[i] = new numPad(i);
		
		pad[10] = new numPad(0);
		pad[11] = new miscBtn('#');
		pad[12] = new miscBtn('*');
		
	}


	public String push(String whichBtn) {
		
		switch(whichBtn)
		{
		case "0": return pad[10].push2() + "";
		case "1": return pad[1].push2() + "";
		case "2": return pad[2].push2() + "";
		case "3": return pad[3].push2() + "";
		case "4": return pad[4].push2() + "";
		case "5": return pad[5].push2() + "";
		case "6": return pad[6].push2() + "";
		case "7": return pad[7].push2() + "";
		case "8": return pad[8].push2() + "";
		case "9": return pad[9].push2() + "";
		case "*": return pad[11].push2() + "";
		case "#": return pad[12].push2() + "";
			default: return "error";
		}
		
	}


	
}
