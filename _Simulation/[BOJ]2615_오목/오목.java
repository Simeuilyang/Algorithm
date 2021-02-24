import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class _2615 {
	static int[][] dx = {{0, 0}, {-1, 1}, {-1, 1}, {1, -1}};
	static int[][] dy = {{1, -1}, {0, 0}, {1, -1}, {1, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[20][20]; // 1 ~ 19
		
		for(int i=1; i<20; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=1; j<20; j++) {
				map[i][j] = Integer.parseInt(s[j-1]);
			}
		}
		

		Queue<int[]> q = new LinkedList<>();
		for(int i=1; i<20; i++) {
			for(int j=1; j<20; j++) {
				if(map[i][j]!=0) {
					q.add(new int[] {i, j});
					int flag = Checkwin(map, q);
					if(flag == 1) {
						System.exit(0);
					}
				}
			}
		}
		
		System.out.print("0");
	}
	
	private static int Checkwin(int[][] map, Queue<int[]> q) {
		int flag = 0;
		int[] tmp = q.poll(); //처음 확인하려고 가져온 돌
		int x = tmp[0];
		int y = tmp[1];
		int color = map[x][y]; //돌 색깔
		ArrayList<int[]> checkloc = new ArrayList<>();
		
		for(int d=0; d<4; d++) { // 방향따라(가로, 세로, 대각선/, 대각션\)
			checkloc.add(new int[] {x, y});
			
			boolean[][] visited = new boolean[20][20];
			visited[x][y] = true;
			for(int j=0; j<2; j++) {
				int rx = x + dx[d][j];
				int ry = y + dy[d][j];
				if(rx>0 && rx<20 && ry>0 && ry<20 && map[rx][ry]==color && !visited[rx][ry]) {
					q.add(new int[] {rx, ry});
					visited[rx][ry] = true;
					
					checkloc.add(new int[] {rx, ry});
				}
			}
			
			while(q.size() != 0) {
				int[] mtmp = q.poll();
				int _x = mtmp[0];
				int _y = mtmp[1];
				int _color = map[_x][_y];
				
				for(int j=0; j<2; j++) {
					int rx = _x + dx[d][j];
					int ry = _y + dy[d][j];
					if(rx>0 && rx<20 && ry>0 && ry<20 && map[rx][ry]==_color && !visited[rx][ry]) {
						q.add(new int[] {rx, ry});
						visited[rx][ry] = true;
						
						checkloc.add(new int[] {rx, ry});
					}
				}
			}
			
			if(checkloc.size() == 5) {

				Collections.sort(checkloc, new AscendingSort());

				System.out.println(color);
				int[] result = checkloc.get(0);
				System.out.print(result[0] + " " + result[1]);
				flag = 1;
				break;
			}
			checkloc.clear();
		}
		return flag;
	}
	
	static class AscendingSort implements Comparator<int[]>{

		@Override
		public int compare(int[] o1, int[] o2) { // 왼쪽이 크면 1, 오른쪽이 크면 -1, 같으면 0을 return
			if(o1[1] > o2[1]) {
				return 1;
			}else if(o1[1] < o2[1]){
				return -1;
			}else { //행이 같을 때
				if(o1[0] > o2[0])
					return 1;
				else
					return -1;
			}
		}
		
	}
}
