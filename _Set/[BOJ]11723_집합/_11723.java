import java.io.*;
import java.util.*;

public class _11723 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		String[] s;
		Set<Integer> set = new HashSet<>();
		int num;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			String command = s[0];
			
			switch(command) {
			case "add":
				set.add(Integer.parseInt(s[1]));
				break;
			case "remove":
				set.remove(Integer.parseInt(s[1]));
				break;
			case "check":
				if(sb.length() != 0)	sb.append("\n");
				
				num = Integer.parseInt(s[1]);
				if(set.contains(num))	sb.append("1");
				else	sb.append("0");
				break;
			case "toggle":
				num = Integer.parseInt(s[1]);
				
				if(set.contains(num))	set.remove(num);
				else 	set.add(num);
				break;
			case "all":
				for(int j=1; j<=20; j++)	set.add(j);
				break;
			case "empty":
				set.clear();
				break;
			}
		}
		System.out.print(sb.toString());
	}

}
