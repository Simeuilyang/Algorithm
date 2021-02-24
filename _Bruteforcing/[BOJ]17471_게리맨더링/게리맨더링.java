import java.io.*;
import java.util.*;

public class _17471 {
	static int[] people;
	static ArrayList<Integer>[] map;
	static int total;
	static int Min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		
		String[] s = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			people[i+1] = Integer.parseInt(s[i]);
			total += people[i+1]; //총 인구수
		}
		
		map = new ArrayList[N+1];
		for(int i=1; i<=N; i++)
			map[i] = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			String[] link = br.readLine().split(" ");
			for(int j=1; j<link.length; j++) {
				map[i].add(Integer.parseInt(link[j]));
			}
		}
				
		for(int i=1; i<=N/2; i++) {
			ArrayList<Integer> A = new ArrayList<>();
			Comb(N, i, 1, A);
		}
		
		if(Min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(Min);
	}
	
	private static boolean Checklink(ArrayList<Integer> Arrlist, boolean[] visited) {
		Queue<Integer> q = new LinkedList<>();
		q.add(Arrlist.get(0));
		
		while(!q.isEmpty()) {
			int now = q.poll();
			visited[Arrlist.indexOf(now)] = true;
			
			for(Integer next : map[now]) {
				if(Arrlist.contains(next)) { // 연결된 구역이 현재 선거구에 속하면
					if(!visited[Arrlist.indexOf(next)]) { // 확인하지 않은 구역이면
						visited[Arrlist.indexOf(next)] = true;
						q.add(next);
					}
				}
			}
		}
		
		for(int i=0; i<visited.length; i++) {
			if(!visited[i]) // 현재 선거구에는 연결되지않은 구역이 있다
				return false;
		}
		
		return true; // 현재 선거구는 모두 연결되어 있다
	}
	
	private static void computeMin(ArrayList<Integer> A, ArrayList<Integer> B, int Acnt, int Bcnt) {
		boolean[] Avisited = new boolean[A.size()];
		boolean[] Bvisited = new boolean[B.size()];
		if(Checklink(A, Avisited) && Checklink(B, Bvisited)) { // 두 선거구 각각 모두 연결되어 있다면
			int diff = Math.abs(Acnt - Bcnt);
			Min = Math.min(Min, diff);
		}
	}
	
	private static void Comb(int N, int r, int start, ArrayList<Integer> A) {
		if(r == 0) {
			ArrayList<Integer> B = new ArrayList<>();
			int Bcnt = 0;
			for(int i=1; i<=N; i++) {
				if(!A.contains(i)) {
					B.add(i);
					Bcnt += people[i];
				}
			}
			
			computeMin(A, B, total-Bcnt, Bcnt); //나눠진 조합 A, B가 각각 연결된 선거구인지 확인
		}else {
			for(int i=start; i<=N; i++) {
				A.add(i);
				Comb(N, r-1, i+1, A);
				A.remove(A.indexOf(i));
			}
		}
	}
}
