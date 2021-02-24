import java.io.*;
import java.util.*;

public class _20055 {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		int K = Integer.parseInt(in[1]);
		
		String[] s = br.readLine().split(" ");
		int[] Dura = new int[2*N]; // 벨트 0 ~ 2N-1 까지의 내구도
		for(int i=0; i<2*N; i++) {
			Dura[i] = Integer.parseInt(s[i]);
		}
		
		ArrayList<Integer> box = new ArrayList<>(); //각 박스의 위치
		int step = 0; // 단계
		int ZeroCnt = 0; // 내구성 0인 벨트 개수
		while(ZeroCnt < K) {
			step++;
			
			rotateBelt(Dura, box); // 1) 벨트가 한 칸 회전한다.
			
			ZeroCnt = moveRobot(Dura, box, ZeroCnt); // 2) 로봇 한 칸씩 이동
			
			if(!box.contains(0) && Dura[0]>=1) { // 3) 올라가는 위치에 로봇없다면 하나 올린다.
				box.add(0);
				Dura[0]--;
				if(Dura[0] == 0)
					ZeroCnt++;
			}
		}
		
		System.out.print(step);
	}
	
	private static int moveRobot(int[] Dura, ArrayList<Integer> box, int ZeroCnt) {
		
		for(int i=0; i<box.size(); i++) {
			int next; //이동할 벨트의 index
			if(box.get(i) != 2*N - 1) //마지막 벨트가 아니면
				next = box.get(i)+1; 
			else //마지막벨트에 있으면
				next = 0; // 다음은 0
			
			if(Dura[next]>=1 && !box.contains(next)) { // 이동하려는 칸에 로봇이 없고, 그 칸의 내구도가 1이상 남아있다면
				box.set(i, next); // 박스 한칸 옮겨준다
				Dura[next]--; //이동후 그 자리 내구성 -1
				if(Dura[next] == 0)
					ZeroCnt++;
				
				if(next == N-1) { //만약 그 자리가 내려가는 자리라면 로봇 내린다
					box.remove(i);
					i--;
				}
			}
		}
		
		return ZeroCnt;
	}
	
	private static void rotateBelt(int[] Dura, ArrayList<Integer> box) {
		int last = Dura[2*N - 1]; //마지막 벨트의 내구도 값
		
		for(int i=2*N-2 ; i>=0; i--) {
			Dura[i+1] = Dura[i];
			if(box.contains(i)) {
				if((i+1) == (N-1)) //내리는 자리라면
					box.remove(box.indexOf(i));
				else 
					box.set(box.indexOf(i), (i+1));
			}
		}
		
		Dura[0] = last;
		if(box.contains(2*N-1)) 
			box.set(box.indexOf(2*N-1), 0);
	}
}
