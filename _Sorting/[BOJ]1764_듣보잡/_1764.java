import java.io.*;
import java.util.*;

public class _1764 {
	static ArrayList<String> hearNsee;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		
		String[] hearNsee = new String[N+M];
		for(int i=0; i<hearNsee.length; i++) {
			hearNsee[i] = br.readLine();	
		}
		
		Arrays.sort(hearNsee);
		
		ArrayList<String> result = new ArrayList<>();
		for(int i=0; i<hearNsee.length-1; i++) {
			if(hearNsee[i].equals(hearNsee[i+1])) {
				result.add(hearNsee[++i]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(result.size());
		for(int i=0; i<result.size(); i++) {
			sb.append("\n" + result.get(i));
		}
		
		System.out.print(sb.toString());
	}

}
