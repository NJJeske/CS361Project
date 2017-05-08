package sprint4;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import com.google.gson.Gson;


public class updateServer {
	
	public updateServer(){
		
	}
	
	public void update(Run r){
		try {
			System.out.println("in the client");

			// Client will connect to this location
			URL site = new URL("http://localhost:8000/sendresults");
			HttpURLConnection conn = (HttpURLConnection) site.openConnection();

			// now create a POST request
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());

			// build a string that contains JSON from console
			String content = "";
			content = getJSON(r);

			// write out string to output buffer for message
			out.writeBytes(content);
			out.flush();
			out.close();

			System.out.println("Done sent to server");

			InputStreamReader inputStr = new InputStreamReader(conn.getInputStream());

			// string to hold the result of reading in the response
			StringBuilder sb = new StringBuilder();

			// read the characters from the request byte by byte and build up
			// the Response
			int nextChar;
			while ((nextChar = inputStr.read()) > -1) {
				sb = sb.append((char) nextChar);
			}
			System.out.println("Return String: " + sb);

		} catch (Exception e) {
			e.printStackTrace();
		
	}
	}
	@SuppressWarnings("unchecked")
	private String  getJSON(Run r) {
		ArrayList em = new ArrayList<>();
		Iterator it = r.getRun().iterator();
		
		while(it.hasNext()){
			em.add(it.next());
		}
			
//		em.add(new Employee("1", "K", "Fritz", "0:10:30"));
//		em.add(new Employee("2", "m", "johnson", "0:15:30"));
//		em.add(new Employee("3", "f", "xiong", "0:20:30"));
//		em.add(new Employee("4", "n", "leski", "0:25:30"));
//		em.add(new Employee("5", "b", "beffa", "0:30:30"));

		
		Gson g = new Gson();
		String json = g.toJson(em);
		System.out.println(json);

		return json;
	}
		
}
