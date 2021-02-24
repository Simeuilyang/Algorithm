import java.io.*;
import java.util.*;

public class _10828 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuffer sb = new StringBuffer();
		Stack<Integer> stack = new Stack<>();
		String[] s;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			String command = s[0];
			
			switch(command) {
			case "push":
				stack.add(Integer.parseInt(s[1]));
				break;
			case "pop":
				if(sb.length() != 0)	sb.append("\n");
				
				if(stack.isEmpty())	sb.append("-1");
				else	sb.append(stack.pop());
				break;
			case "size":
				if(sb.length() != 0)	sb.append("\n");
				sb.append(stack.size());
				break;
			case "empty":
				if(sb.length() != 0)	sb.append("\n");
				if(stack.isEmpty())	sb.append("1");
				else	sb.append("0");
				break;
			case "top":
				if(sb.length() != 0)	sb.append("\n");
				
				if(stack.isEmpty())	sb.append("-1");
				else	sb.append(stack.peek());
				break;
			}
		}
		
		System.out.print(sb.toString());
	}

}
