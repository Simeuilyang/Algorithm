import java.io.*;
import java.util.*;
public class _1697 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int K = Integer.parseInt(s[1]);
		
		System.out.print(bfs(N, K));
	}
	
	private static int bfs(int N, int K) {
		int res = 0;
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[100001];
		q.add(new int[] {N, 0}); // {현 위치, 시간}
		visited[N] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int pos = now[0];
			int time = now[1];
			
			if(pos == K) {
				res = time;
				break;
			}
			
			if(Valid(pos+1) && !visited[pos+1]) {
				q.add(new int[] {pos+1, time+1});
				visited[pos+1] = true;
			}
			
			if(Valid(pos-1) && !visited[pos-1]) {
				q.add(new int[] {pos-1, time+1});
				visited[pos-1] = true;
			}
			
			if(Valid(pos*2) && !visited[pos*2]) {
				q.add(new int[] {pos*2, time+1});
				visited[pos*2] = true;
			}
		}
		
		return res;
	}
	
	private static boolean Valid(int x) {
		return (x>=0 && x<=100000);
	}
}
