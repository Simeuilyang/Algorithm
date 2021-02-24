import java.io.*;
import java.util.*;

public class _10845 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		String[] s;
		Queue<Integer> q = new LinkedList<>();
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			String command = s[0];
			
			switch(command) {
			case "push":
				q.add(Integer.parseInt(s[1]));
				break;
			case "pop":
				if(sb.length() != 0)	sb.append("\n");
				
				if(q.isEmpty())	sb.append("-1");
				else	sb.append(q.poll());
				break;
			case "size":
				if(sb.length() != 0)	sb.append("\n");
				sb.append(q.size());
				break;
			case "empty":
				if(sb.length() != 0)	sb.append("\n");
				
				if(q.isEmpty())	sb.append("1");
				else	sb.append("0");
				break;
			case "front":
				if(sb.length() != 0)	sb.append("\n");
				
				if(q.isEmpty())	sb.append("-1");
				else	sb.append(q.peek());
				break;
			case "back":
				if(sb.length() != 0)	sb.append("\n");
				
				if(q.isEmpty())	sb.append("-1");
				else	sb.append(q.toArray()[q.size()-1]);
				break;
			}
		}
		
		System.out.print(sb.toString());
	}

}
