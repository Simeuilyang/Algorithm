import java.io.*;
import java.util.*;
public class _9466 {
	static int[] want;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String[] s;
		for(int t=0; t<T; t++) {
			if(t != 0)	sb.append("\n");
			
			int n = Integer.parseInt(br.readLine());
			want = new int[n+1];
			s = br.readLine().split(" ");
			for(int i=1; i<n+1; i++) {
				want[i] = Integer.parseInt(s[i-1]);
			}
			
			boolean[] visited = new boolean[n+1];
			int team = 0;
			for(int i=1; i<n+1; i++) {
				if(!visited[i]) {
					Stack<Integer> stack = new Stack<>();
					stack.add(i);
					team += dfs(i, stack, visited);
				}
			}
			
			sb.append(n-team);
		}
		
		System.out.print(sb.toString());
	}
	
	private static int dfs(int v, Stack<Integer> stack, boolean[] visited) {
		if(visited[v]) {
			int cnt = 0;
			boolean flag = false;
			while(!stack.empty()) {
				cnt++;
				if(stack.pop() == v) {
					flag = true;
					break;
				}
			}
			
			if(flag)	return cnt;
			else	return 0;
		}
		
		visited[v] = true;
		stack.add(v);
		return dfs(want[v], stack, visited);
	}
}
