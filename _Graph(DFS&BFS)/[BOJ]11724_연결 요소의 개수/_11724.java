import java.io.*;
import java.util.*;

public class _11724 {
	static ArrayList<Integer>[] g;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		
		g = new ArrayList[N+1];
		for(int i=1; i<N+1; i++)
			g[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			
			g[u].add(v);
			g[v].add(u);
		}
		
		System.out.print(ChkCount(N));
	}
	
	private static void dfs(int v, boolean[] visited) {
		Iterator<Integer> itr = g[v].iterator();
		while(itr.hasNext()) {
			int u = itr.next();
			if(!visited[u]) {
				visited[u] = true;
				dfs(u, visited);
			}
		}
	}
	
	private static int ChkCount(int N) {
		int cnt = 0;
		boolean[] visited = new boolean[N+1];
		
		for(int i=1; i<N+1; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i, visited);
				cnt++;
			}
		}
		
		return cnt;
	}
}
