import java.io.*;
import java.util.*;

public class _10866 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] s;
		StringBuffer sb = new StringBuffer();
		ArrayList<Integer> deque = new ArrayList<>();
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			String command = s[0];
			
			switch(command) {
			case "push_front":
				deque.add(0, Integer.parseInt(s[1]));
				break;
				
			case "push_back":
				deque.add(Integer.parseInt(s[1]));
				break;
				
			case "pop_front":
				if(sb.length() != 0)	sb.append("\n");
				if(deque.isEmpty())
					sb.append("-1");
				else {
					sb.append(deque.get(0));
					deque.remove(0);
				}
				break;
				
			case "pop_back":
				if(sb.length() != 0)	sb.append("\n");
				if(deque.isEmpty())
					sb.append("-1");
				else {
					sb.append(deque.get(deque.size()-1));
					deque.remove(deque.size()-1);
				}
				break;
				
			case "size":
				if(sb.length() != 0)	sb.append("\n");
				sb.append(deque.size());
				break;
				
			case "empty":
				if(sb.length() != 0)	sb.append("\n");
				if(deque.isEmpty())		sb.append("1");
				else	sb.append("0");
				break;
				
			case "front":
				if(sb.length() != 0)	sb.append("\n");
				if(deque.isEmpty())	sb.append("-1");
				else	sb.append(deque.get(0));
				break;
				
			case "back":
				if(sb.length() != 0)	sb.append("\n");
				if(deque.isEmpty())	sb.append("-1");
				else	sb.append(deque.get(deque.size()-1));
				break;
			}
		}
		System.out.print(sb.toString());
	}

}
