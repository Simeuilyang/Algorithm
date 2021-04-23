import java.io.*;
public class _12100 {
	static int Max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		String[] s;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		solution(N, map, 5);
		System.out.print(Max);
	}
	
	private static int MaxOfMap(int[][] map, int N) {
		int max = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				max = Math.max(max, map[i][j]);
			}
		}
		return max;
	}
	
	private static void solution(int N, int[][] map, int cnt) {
		if(cnt == 0) {
			int max = MaxOfMap(map, N);
			Max = Math.max(Max, max);
			return;
		}
		
		for(int k=0; k<4; k++) { // 1:상 2:우 3:하 4:좌
			int[][] tmp = mapClone(map, N);
			if(k==0)	MoveUp(tmp, N);
			else if(k==1)	MoveRight(tmp, N);
			else if(k==2)	MoveDown(tmp, N);
			else	MoveLeft(tmp, N);
			
			solution(N, tmp, cnt-1);
		}
	}
	
	private static void MoveLeft(int[][] map, int N) {
		for(int i=0; i<N; i++) { // 행마다 확인
			for(int j=0; j<N; j++) {
				if(map[i][j] == 0) {
					int k;
					for(k=j+1; k<N; k++)	if(map[i][k] != 0)	break;
					if(k == N)	break;
					else {
						map[i][j] = map[i][k];
						map[i][k] = 0;
					}
				}
				int k;
				for(k=j+1; k<N; k++)	if(map[i][k] != 0)	break;
				if(k == N)	break;
				else if(map[i][j] == map[i][k]) { // 다음 수가 현재 수와 같다면
					map[i][j] *= 2;
					map[i][k] = 0;
				}
			}
		}
	}
	
	private static void MoveRight(int[][] map, int N) {
		for(int i=0; i<N; i++) { // 행마다 확인
			for(int j=N-1; j>=0; j--) { // (N-1)번 열부터
				if(map[i][j] == 0) {
					int k;
					for(k=j-1; k>=0 ; k--)	if(map[i][k] != 0)	break;
					if(k == -1)	break; // 그 다음 숫자가 없는 것
					else {
						map[i][j] = map[i][k]; // 다음 숫자
						map[i][k] = 0;
					}
				}
				int k;
				for(k=j-1; k>=0 ; k--)	if(map[i][k] != 0)	break; // 다음 수
				if(k == -1)	break;
				else if(map[i][j] == map[i][k]) { // 다음 수가 현재 수와 같다면
					map[i][j] *= 2;
					map[i][k] = 0;
				}
			}
		}
	}
	
	private static void MoveDown(int[][] map, int N) {
		for(int j=0; j<N; j++) { // 열마다 확인
			for(int i=N-1; i>=0 ; i--) { // (N-1)번행부터
				if(map[i][j] == 0) {
					int k;
					for(k=i-1; k>=0; k--)	if(map[k][j] != 0)	break;
					if(k == -1)	break;
					else {
						map[i][j] = map[k][j];
						map[k][j] = 0;
					}
				}
				int k;
				for(k=i-1; k>=0; k--)	if(map[k][j] != 0)	break;
				if(k == -1)	break; // 다음 수 없는 것
				else if(map[i][j] == map[k][j]) {
					map[i][j] *= 2;
					map[k][j] = 0;
				}
			}
		}
	}
	
	private static void MoveUp(int[][] map, int N) {
		for(int j=0; j<N; j++) { // 열마다 확인
			for(int i=0; i<N; i++) { // 0번 행부터
				if(map[i][j] == 0) {
					int k;
					for(k=i+1; k<N; k++)	if(map[k][j] != 0)	break;
					if(k == N)	break; // 그 다음 숫자가 없는 것
					else {
						map[i][j] = map[k][j]; // 아니라면 다음 숫자를 가져온다
						map[k][j] = 0;
					}
				}
				int k;
				for(k=i+1; k<N; k++)	if(map[k][j] != 0)	break; // 다음 수 찾아서
				if(k == N)	break; // 다음 수 없는 것
				else if(map[i][j]==map[k][j]) {
					map[i][j] *= 2;
					map[k][j] = 0;
				}
			}
		}
	}
	
	private static int[][] mapClone(int[][] A, int N) {
		int[][] tmp = new int[N][N];
		for(int i=0; i<N; i++)	tmp[i] = A[i].clone();
		return tmp;
	}
}
