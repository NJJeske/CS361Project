package sprint1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Timer;

import org.omg.PortableServer.THREAD_POLICY_ID;

public class simulator {
	public static void main(String[] args) throws InterruptedException
	{
		ArrayList<String> list = new ArrayList<>();
		chronotimer chrono = new chronotimer();
		time SystemTime = new time();
		Queue<competitor> q = new LinkedList<competitor>();

		electric_eye s1 = new electric_eye();
		electric_eye s2 = new electric_eye();
		electric_eye s3 = new electric_eye();
		electric_eye s4 = new electric_eye();
		
		Scanner scan;
		
		//read commands from file and store them in temporary array
		try {
			scan = new Scanner(new File("GRPTEST"));
			String s;
			while(scan.hasNextLine()){
				s = scan.nextLine();
				list.add(s);
			}
			scan.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		String[] cmd, time;
		
		
		//read in commands from the temporary array and simulate accordingly
		while(!list.isEmpty()){
			//Thread.sleep(1000);
			
			cmd = list.remove(0).split("\\s+");
			time = cmd[0].split("[:.]");
			//time t = new time(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]), Integer.parseInt(time[3]));
			time t = new time();

		switch(cmd[1])
			{
			case "POWER":
				chrono.powerPad.push();
				break;
			case "NUM":
				int ID = Integer.parseInt(cmd[2]);
				chrono.NUM(ID);
				break;
			case "TOG":
				//System.out.print(cmd[2]);

				chrono.channelPads[1][Integer.parseInt(cmd[2])].push();
				break;
				
			case "CONN":
				
				switch(Integer.parseInt(cmd[3])){
				case 1:
					//chrono.setTime(t);
					chrono.CONN(Integer.parseInt(cmd[3]), s1);
					break;
				case 2:
					chrono.CONN(Integer.parseInt(cmd[3]), s2);
					break;
				case 3:
					chrono.CONN(Integer.parseInt(cmd[3]), s3);
					break;
				case 4:
					chrono.CONN(Integer.parseInt(cmd[3]), s4);
					break;
			}
				break;
			case "TRIG":

				int channel = Integer.parseInt(cmd[2]);

				switch(channel){
					case 1:
						s1.trigger();
						break;
					case 2:
						s2.trigger();
						break;
					case 3:
						s3.trigger();
						break;
					case 4:
						s4.trigger();
						break;
				}
				break;
			case "START":
				chrono.getRun().start(t, chrono.ch[Integer.parseInt(cmd[2])]);
				break;
			case "FINISH":
				chrono.getRun().finish(t, chrono.ch[Integer.parseInt(cmd[2])]);
				break;
			case "EVENT":
				String event = cmd[2];
					chrono.setEvent(event);
				break;
			case "NEWRUN":
				chrono.createRun();
				break;
			case "ENDRUN":
				chrono.endRun();
				break;
			case "PRINT":
				chrono.print();
				break;
			case "RESET":
				chrono.pushResetButton();
				break;
			case "TIME":
				String[] t2;
				t2 = cmd[2].split("[:.]");
				//chrono.setTime(Integer.parseInt(t2[0]),Integer.parseInt(t2[1]),Integer.parseInt(t2[2]),Integer.parseInt(t2[3]));
				break;
			case "CANCEL":
					chrono.cancel(t);
				break;
			case "DNF":
				chrono.DNF();
				break;
			case "EXPORT":
				chrono.export(Integer.parseInt(cmd[2]));
				break;
			case "EXIT":
				return;
			default:
				break;
			}
		}
	}
}
