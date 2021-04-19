import java.io.*;
import java.util.*;
public class _12851 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int S = Integer.parseInt(s[0]); // 수빈
		int E = Integer.parseInt(s[1]); // 동생
		
		int[] res = solution(S, E);
		System.out.print(res[0] + "\n" + res[1]);
	}

	private static int[] solution(int S, int E) {
		int MinTime = Integer.MAX_VALUE;
		int cnt = 1;
		
		int[] Time = new int[100001];
		Arrays.fill(Time, Integer.MAX_VALUE);
		Time[S] = 0;
		Queue<int[]> q = new LinkedList<>(); // 위치, 시간
		q.add(new int[] {S, 0});
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int time = now[1];
			
			if(x == E) {
				if(MinTime > time) {
					MinTime = time;
					cnt = 1;
				}else if(MinTime == time)	cnt++;
				continue;
			}
			
			if(Valid(x+1) && Time[x+1]>=(time+1)) {
				q.add(new int[] {x+1, time+1});
				if((x+1)!=E && Time[x+1]>(time+1))	Time[x+1] = time+1;
			}
			
			
			if(Valid(x-1) && Time[x-1]>=(time+1)) {
				q.add(new int[] {x-1, time+1});
				if((x-1)!=E && Time[x-1]>(time+1))	Time[x-1] = time+1;
			}
			
			if(Valid(x*2) && Time[x*2]>=(time+1)) {
				q.add(new int[] {x*2, time+1});
				if((x*2)!=E && Time[x*2]>(time+1))	Time[x*2] = time+1;
			}
		}
		
		return new int[] {MinTime, cnt};
	}
	
	private static boolean Valid(int x) {
		return (x>=0 && x<=100000);
	}
}
