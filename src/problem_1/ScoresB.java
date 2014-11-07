package problem_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.Comparator;

import java.util.StringTokenizer;
import java.util.Vector;

public class ScoresB {
	
	static class Compare implements Comparator<Integer>{
		public int compare(Integer o1, Integer o2) {
			if(o1>o2)return 1;
			else if(o1<o2) return -1;
			else return 0;
		}
	}
	public static void main(String[] args) {
		int sum = 0;
		int avg ;
		int max,min;
			try {
			BufferedReader bff = new BufferedReader(new FileReader("scores.txt"));
			Vector<Integer> vec = new Vector<Integer>();
			
			String scores = bff.readLine();
			
			while(scores!=null){
				StringTokenizer stk = new StringTokenizer(scores," ");
				while(stk.hasMoreTokens()){
					String s = stk.nextToken()+" "+stk.nextToken();
					int n = Integer.parseInt(stk.nextToken());
					sum+=n;
					vec.add(n);
				}
				scores = bff.readLine();
			}
			
			avg=sum/vec.size()+sum%vec.size();                  ///////////////////////AVERAGE
			
			Collections.sort(vec, new ScoresB.Compare());
			max=vec.get(vec.size()-1);                          /////////////MAX
			min = vec.get(0);                                   //////////////MIN
			
			
			
			RandomAccessFile rand = new RandomAccessFile(new File("grades.txt"),"rw");
			rand.seek(rand.length());
			String score = "";
			score+="Average - "+avg+"\n";
			score+="Minimum - "+min+"\n";
			score+="Maximum - "+max+"\n";
			rand.writeBytes(score);
			rand.close();
			

			bff.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}


