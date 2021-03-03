import java.io.*;
import java.util.*;

public class _2638 {
	static int[][] map;
	static int N, M;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		map = new int[N][M];
		
		int cheeseCnt = 0;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if(map[i][j] == 1) // ġ����
					cheeseCnt++;
			}
		}
		
		System.out.print(ComputeTime(cheeseCnt));
	}
	
	private static void CloneArr(int[][] arr) {
		for(int i=0; i<N; i++) {
			arr[i] = map[i].clone();
		}
	}
	
	private static void ChkOutside(int[][] arr) { // �ٱ� ���� Ȯ��
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0});
		arr[0][0] = -1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(rx>=0 && rx<N && ry>=0 && ry<M && arr[rx][ry]==0) {
					arr[rx][ry] = -1;
					q.add(new int[] {rx, ry});
				}
			}
		}
	}
	
	private static int ComputeTime(int cheeseCnt) {
		int time = 0;
		
		while(cheeseCnt != 0) {
			int[][] thisMap = new int[N][M];
			CloneArr(thisMap);
			ChkOutside(thisMap); // �ٱ� ���� üũ
			
			ArrayList<int[]> melt = new ArrayList<>(); // ���� ġ�� Ȯ��
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(thisMap[i][j] == 1) { // ġ����
						int cnt = 0;
						for(int k=0; k<4; k++) {
							int rx = i + dx[k];
							int ry = j + dy[k];
							
							if(rx>=0 && rx<N && ry>=0 && ry<M && thisMap[rx][ry]==-1) 
								cnt++;
						}
						
						if(cnt >= 2)
							melt.add(new int[] {i, j});
					}
				}
			}
			
			Iterator<int[]> itr = melt.iterator();
			while(itr.hasNext()) {
				int[] now = itr.next();
				int rx = now[0];
				int ry = now[1];
				
				map[rx][ry] = 0; // ���̰�
				cheeseCnt--; // ������ ���δ�
			}
			time++;
		}
		
		return time;
	}	
}
