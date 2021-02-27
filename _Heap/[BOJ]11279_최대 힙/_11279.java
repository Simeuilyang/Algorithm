import java.io.*;
import java.util.*;
public class _11279 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(sb.length() != 0)	sb.append("\n");
				if(pq.isEmpty())	sb.append("0");
				else	sb.append(pq.poll());
			}else {
				pq.add(num);
			}
		}
		
		System.out.print(sb.toString());
	}

}
