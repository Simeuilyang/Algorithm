import java.io.*;
import java.util.*;
public class _11404 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] cost = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			Arrays.fill(cost[i], -1);
			cost[i][i] = 0;
		}
		
		String[] s;
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" "); // a->b, 그 비용: c
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			int c = Integer.parseInt(s[2]);
			
			if(cost[a][b] == -1)	cost[a][b] = c;
			else	cost[a][b] = Math.min(cost[a][b], c);
		}
		
		solution(N, M, cost);
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			if(i != 1)	sb.append("\n");
			for(int j=1; j<=N; j++) {
				if(j != 1)	sb.append(" ");
				if(cost[i][j]!=-1)	sb.append(cost[i][j]);
				else	sb.append("0");
			}
		}
		System.out.print(sb.toString());
	}
	
	private static void solution(int N, int M, int[][] cost) {
		for(int v=1; v<=N; v++) { // v:거쳐가는 정점
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(!(cost[i][v]!=-1 && cost[v][j]!=-1))	continue;
					int newDist = cost[i][v] + cost[v][j];
					if(cost[i][j]>newDist || cost[i][j]==-1) {
						cost[i][j] = newDist;
					}
				}
			}
		}
	}
}
