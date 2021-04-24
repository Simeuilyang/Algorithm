import java.io.*;
import java.util.*;
public class _20056_2 {
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static ArrayList<FireBall> fireballs = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		 int N = Integer.parseInt(s[0]);
		 int M = Integer.parseInt(s[1]);
		 int K = Integer.parseInt(s[2]);
		 
		 for(int i=0; i<M; i++) {
			 s = br.readLine().split(" ");
			 fireballs.add(new FireBall(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4])));
		 }
		 
		 System.out.print(solution(N, M, K));
	}	
	
	private static int solution(int N, int M, int K) {
		int res = 0;
		while(K != 0) {
			ArrayList<Integer>[][] map = new ArrayList[N+1][N+1];
			MoveFireBalls(N, map); // ���̾ �̵� & �� ��ġ�� ����
			ChkSameLoc(N, map);
			K--;
		}
		
		for(FireBall f : fireballs)	res += f.m;
		
		return res;
	}
	
	private static void ChkSameLoc(int N, ArrayList<Integer>[][] map) { // ���� ��ġ�� �ִ� ���̾ ��ġ�� ������
		ArrayList<Integer> remove = new ArrayList<>(); // �Ҹ�Ǵ� ���̾�� �ε���
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j]!=null && map[i][j].size()>1) { // ���̾�� ������ �ִ� ��ġ���
					SumAndSpread(map[i][j], remove, N, i, j);
				}
			}
		}
		
		int remCnt = 0;
		Collections.sort(remove);
		for(Integer r : remove) {
			fireballs.remove(r-remCnt);
			remCnt++;
		}
	}
	
	private static void SumAndSpread(ArrayList<Integer> BallList, ArrayList<Integer> remove, int N, int x, int y) {
		int cnt = BallList.size(); // ������ ���̾�� ����
		int idx = BallList.get(0);
		remove.add(idx);
		FireBall first = fireballs.get(idx); // ù��° ���̾
		int Msum = first.m;
		int Ssum = first.s;
		int firstD = first.d%2; // 0�̸� ¦�� // 1�̸� Ȧ��
		boolean flag = true;
		
		for(int i=1; i<cnt; i++) {
			idx = BallList.get(i);
			FireBall now = fireballs.get(idx);
			remove.add(idx);
			Msum += now.m;
			Ssum += now.s;
			if(flag && firstD!=(now.d%2))	flag = false; // ù��° ���̾�� Ȧ¦�� �ٸ��� flag�� false
		}
		
		if(Msum/5 == 0)	return; // ���� 0�̸� �Ҹ�
		
		int d;
		if(flag)	d = 0; // 0 2 4 6
		else	d = 1; // 1 3 5 7
		for(int i=0; i<4; i++) { // ���ο� ���̾ 4���� �߰���
			fireballs.add(new FireBall(x, y, Msum/5, Ssum/cnt, d));
			d += 2;
		}
	}
	
	private static void MoveFireBalls(int N, ArrayList<Integer>[][] map) { // ���̾ �̵�
		int ballN = fireballs.size();
		for(int i=0; i<ballN; i++) {
			FireBall now = fireballs.get(i);
			int s = now.s;
			int rx = now.x + s * dx[now.d];
			int ry = now.y + s * dy[now.d];
			
			if(rx > N)	rx %= N;
			if(rx < 1)	rx = N - (Math.abs(rx)%N);
			
			if(ry > N)	ry %= N;
			if(ry < 1)	ry = N - (Math.abs(ry)%N);
			
			now.x = rx;
			now.y = ry;
			if(map[rx][ry] == null)	map[rx][ry] = new ArrayList<>();
			map[rx][ry].add(i);
		}
	}
	
	private static class FireBall{
		private int x;
		private int y;
		private int m; 
		private int s;
		private int d;
		
		FireBall(int x, int y, int m, int s, int d){
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

}
