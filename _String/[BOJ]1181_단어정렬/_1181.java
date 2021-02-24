import java.io.*;
import java.util.*;

public class _1181 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<String> chk = new ArrayList<>();
		PriorityQueue<Str> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			if(!chk.contains(s)) { // 없다면
				chk.add(s);
				pq.add(new Str(s, s.length()));
			}
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(pq.poll().str);
		while(!pq.isEmpty()) {
			sb.append("\n"+pq.poll().str);
		}
		
		System.out.print(sb.toString());
	}

	private static class Str implements Comparable<Str>{
		String str;
		int len;
		
		Str(String str, int len){
			this.str = str;
			this.len = len;
		}
		
		@Override
		public int compareTo(Str s) {
			if(this.len > s.len)
				return 1;
			else if(this.len < s.len)
				return -1;
			else { // 길이가 같으면
				return this.str.compareTo(s.str);
				/*
				 *  두 문자열이 사전 순으로 같다면 0을 반환
				 * 대상 문자열이 매개변수로 받은 문자열보다 사전 순으로 앞선 경우 "음수"를 반환
				 * 대상 문자열이 매개변수로 받은 문자열보다 사전 순으로 뒤질 경우 "양수"를 반환 
				 *
				 */
			}
		}
	}
}
