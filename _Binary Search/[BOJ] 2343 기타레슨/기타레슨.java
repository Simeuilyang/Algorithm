/*
 * - �̺�Ž�� -
 * ������ Ž���� ���ΰ�? �� �߿� !!
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class _2343 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]); // ������ ��
		int M = Integer.parseInt(s[1]); // ��緹���� ����
		int[] lesson = new int[N];
		String[] d = br.readLine().split(" ");
		int left = 1;  //��緹���� ũ�Ⱑ �� �� �ִ� �� �� �ּ� ��
		int right = 0; //��緹���� ũ�Ⱑ �� �� �ִ� �� �� �ִ� �� => ���� ������ ��
		int mid = 0;
		
		
		for(int i=0; i<N; i++) {
			lesson[i] = Integer.parseInt(d[i]);
			right += lesson[i];
		}
		while(left <= right) {
			mid = (left + right) / 2;
			int tmpsum = 0;
			int bCnt = 1;
			int flag = 0;
			for(int i=0; i<N; i++) {
				if(lesson[i] > mid) { //��緹���� ���̺��� ������ ���̰� ��� -> ��緹�� ���� �÷��� ��!
					left = mid + 1;
					flag = 1;
					break;
				}
				
				if((tmpsum+lesson[i]) > mid) {
					tmpsum = lesson[i];
					bCnt++;
				}else { //tmpsum+lesson[i] <= mid
					tmpsum += lesson[i];
				}
			}
			if(flag == 1)	continue;
			
			if(bCnt <= M) { // ��緹���� ������ M���� �۰ų� ������ -> ��緹�� ���� �ٿ��� �� �ִ� (��緹�� �� �� �� �����ϱ�)
				right = mid -1;
			}else { //��緹�� ������ M���� ũ�� -> ��緹�� ���� �÷��� ��  (��緹�� �� ��ߵǴϱ�)
				left = mid + 1;
			}
		}
			
		System.out.println(left);
		
	}

}
