import java.io.*;
import java.util.*;

public class _11866 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int K = Integer.parseInt(s[1]);
		
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++)	q.add(i);
		
		StringBuffer sb = new StringBuffer();
		sb.append("<");
		while(!q.isEmpty()) {
			int num;
			for(int i=0; i<K-1; i++) {
				num = q.poll();
				q.add(num);
			}
			
			num = q.poll();
			if(sb.length() != 1)	sb.append(", " + num);
			else	sb.append(num);
		}
		sb.append(">");
		System.out.print(sb.toString());
	}

}
