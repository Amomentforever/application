package problem_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Scores {
	
	public static void main(String[] args){
		int best = 0;
		try {
			BufferedReader bff = new BufferedReader(new FileReader("scores.txt"));
			BufferedWriter brr = new BufferedWriter(new FileWriter("grades.txt"));
			HashMap<String,Integer> hm = new HashMap<String, Integer>();

			String scores = bff.readLine();
			
			while(scores!=null){
				StringTokenizer stk = new StringTokenizer(scores," ");
				while(stk.hasMoreTokens()){
					String s = stk.nextToken()+" "+stk.nextToken();
					int n = Integer.parseInt(stk.nextToken());
					if(n>best)best=n;
					hm.put(s, n);

				}
				scores = bff.readLine();
			}
			
			int i =1;
			for(Map.Entry<String, Integer> entry : hm.entrySet()){
				String name = entry.getKey();
				Integer value = entry.getValue();
				if(value>=best-10){
					name =i+")"+name+" - \"A\"";
					brr.write(name+"\n");
					++i;
				}
				else if(value>=best-20){
					name = i+")"+name+" - \"B\"";
					brr.write(name+"\n");
					++i;
				}
				else if(value>=best-30){
					name = i+")"+name+" - \"C\"";
					brr.write(name+"\n");
					++i;
				}
				else if(value>=best-40){
					name = i+")"+name+" - \"D\"";
					brr.write(name+"\n");
					++i;
				}
				else {
					name = i+")"+name+" - \"F\"";
					brr.write(name+"\n");
					++i;
				}
			}
			
			
			bff.close();
			brr.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
