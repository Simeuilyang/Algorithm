import java.io.*;
public class _2239 {
	static int[][] map = new int[9][9];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s;
		
		for(int i=0; i<9; i++) {
			s = br.readLine().split("");
			for(int j=0; j<9; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		solution(0, 0); // 백트래킹 함수
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<9; i++) {
			if(i != 0)	sb.append("\n");
			for(int j=0; j<9; j++) {
				sb.append(map[i][j]);
			}
		}
		
		System.out.print(sb.toString());
	}
	
	private static boolean solution(int x, int y) {
		if(y == 9) {
			x++;
			y = 0;
		}
		if(x == 9)	return true; // 스캔 끝
		
		if(map[x][y] != 0) {
			if(solution(x, y+1))	return true;
		}else { // 빈칸이면
			for(int v=1; v<=9; v++) { // 넣을 값 value
				if(ChkValid(x, y, v)) { // 넣을 수 있다면
					map[x][y] = v;
					if(solution(x, y+1))	return true;
				}
			}
			map[x][y] = 0; // 만약 1부터9까지 모두 유효하지 않을 때 => 다시 되돌려놓음
		}
		
		return false;
	}
	
	private static boolean ChkValid(int x, int y, int v) {
		for(int j=0; j<9; j++) {
			if(map[x][j] == v)	return false;
		}
		
		for(int i=0; i<9; i++) {
			if(map[i][y] == v)	return false;
		}
		
		int sx = (x/3) * 3;
		int sy = (y/3) * 3;
		
		for(int i=sx; i<sx+3; i++) {
			for(int j=sy; j<sy+3; j++) {
				if(map[i][j] == v)	return false;
			}
		}
		
		return true;
	}
}
