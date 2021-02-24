import java.util.Arrays;
import java.util.Scanner;

public class _2110 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int C = sc.nextInt();
		int[] house = new int[N];
		
		for(int i=0; i<N; i++) {
			house[i] = sc.nextInt();
		}
		
		Arrays.sort(house);
		int right = house[N-1] - house[0]; // ���� �ִ밪�� ù ���� ������ �� ������ �Ÿ�
		int left = 1; // ���� �ּҰ��� 1 (�� ������ ���� ��ǥ�� ����  �����ϱ�)
		int mid;
		int result = 0;
		
		while(left <= right) {
			mid = (left + right) / 2;
			int wifiN = 1;
			int distance = 0;
			
			for(int i=0; i<N-1; i++) {
				if((distance + (house[i+1]-house[i])) >= mid) {
					wifiN++;
					distance = 0;
				}else {
					distance += (house[i+1]-house[i]);
				}
			}
			
			if(distance >= mid)	
				wifiN++;
			
			if(wifiN < C) { //C������ ���� ��ٸ� �� ���� �ִ� ��! -> ������ ������
				right = mid - 1;
			}else { //C������ ���� ��ٸ� ������ ���� �ٿ����� ! -> ���� �ø���
				//result = mid;
				left = mid + 1;
				result = Math.max(result, mid);
			}
		}
		System.out.println(result);
	}

}
