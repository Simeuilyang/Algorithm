import java.io.*;
import java.util.*;

public class _10814 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Member> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			pq.add(new Member(Integer.parseInt(s[0]), s[1], i));
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<N; i++) {
			Member m = pq.poll();
			if(i != (N-1))
				sb.append(m.age + " " + m.name + "\n");
			else
				sb.append(m.age + " " + m.name);
		}
		System.out.print(sb.toString());
	}
	
	private static class Member implements Comparable<Member>{
		int age;
		String name;
		int order;
		
		Member(int age, String name, int order){
			this.age = age;
			this.name = name;
			this.order = order;
		}
		
		@Override
		public int compareTo(Member m) {
			if(this.age > m.age)
				return 1;
			else if(this.age < m.age)
				return -1;
			else { // 나이가 같으면 가입순
				return (this.order >= m.order)? 1 : -1;
			}
		}
	}
}
