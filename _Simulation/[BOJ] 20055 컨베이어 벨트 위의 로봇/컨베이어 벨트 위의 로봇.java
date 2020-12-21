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
		int[] Dura = new int[2*N]; // ��Ʈ 0 ~ 2N-1 ������ ������
		for(int i=0; i<2*N; i++) {
			Dura[i] = Integer.parseInt(s[i]);
		}
		
		ArrayList<Integer> box = new ArrayList<>(); //�� �ڽ��� ��ġ
		int step = 0; // �ܰ�
		int ZeroCnt = 0; // ������ 0�� ��Ʈ ����
		while(ZeroCnt < K) {
			step++;
			
			rotateBelt(Dura, box); // 1) ��Ʈ�� �� ĭ ȸ���Ѵ�.
			
			ZeroCnt = moveRobot(Dura, box, ZeroCnt); // 2) �κ� �� ĭ�� �̵�
			
			if(!box.contains(0) && Dura[0]>=1) { // 3) �ö󰡴� ��ġ�� �κ����ٸ� �ϳ� �ø���.
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
			int next; //�̵��� ��Ʈ�� index
			if(box.get(i) != 2*N - 1) //������ ��Ʈ�� �ƴϸ�
				next = box.get(i)+1; 
			else //��������Ʈ�� ������
				next = 0; // ������ 0
			
			if(Dura[next]>=1 && !box.contains(next)) { // �̵��Ϸ��� ĭ�� �κ��� ����, �� ĭ�� �������� 1�̻� �����ִٸ�
				box.set(i, next); // �ڽ� ��ĭ �Ű��ش�
				Dura[next]--; //�̵��� �� �ڸ� ������ -1
				if(Dura[next] == 0)
					ZeroCnt++;
				
				if(next == N-1) { //���� �� �ڸ��� �������� �ڸ���� �κ� ������
					box.remove(i);
					i--;
				}
			}
		}
		
		return ZeroCnt;
	}
	
	private static void rotateBelt(int[] Dura, ArrayList<Integer> box) {
		int last = Dura[2*N - 1]; //������ ��Ʈ�� ������ ��
		
		for(int i=2*N-2 ; i>=0; i--) {
			Dura[i+1] = Dura[i];
			if(box.contains(i)) {
				if((i+1) == (N-1)) //������ �ڸ����
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
