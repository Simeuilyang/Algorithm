import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class _1753 {
	static int maxvalue = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] d = br.readLine().split(" ");
		int V = Integer.parseInt(d[0]);
		int E = Integer.parseInt(d[1]);
		int start = Integer.parseInt(br.readLine());
		
		ArrayList<Edge>[] adj = new ArrayList[V+1];
		for(int i=1; i<V+1; i++) //adj 초기화
			adj[i] = new ArrayList<>();
		
		for(int i=0; i<E; i++) { //간선 개수만큼
			String[] s = br.readLine().split(" ");
			adj[Integer.parseInt(s[0])].add(new Edge(Integer.parseInt(s[1]), Integer.parseInt(s[2])));
		}
		
		int[] D = new int[V+1];
		
		boolean[] visited = new boolean[V+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		Arrays.fill(D, maxvalue);
		D[start] = 0;
		pq.add(new Edge(start, 0)); //첫번쨰 노드만
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			for(Edge next : adj[now.v]) {
				if(!visited[next.v] && (D[next.v] > (D[now.v] + next.weight))) {
					D[next.v] = D[now.v] + next.weight;
					pq.add(new Edge(next.v, D[next.v]));
				}
			}
			visited[now.v] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<V+1; i++) {
			if(D[i] == maxvalue)
				sb.append("INF\n");
			else
				sb.append(D[i]+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static class Edge implements Comparable<Edge>{
		int v, weight;
		
		Edge(int v, int weight){
			this.v = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}

}
