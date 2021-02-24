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
		int right = house[N-1] - house[0]; // 범위 최대값은 첫 집과 마지막 집 사이의 거리
		int left = 1; // 범위 최소값은 1 (각 집들이 같은 좌표는 갖지  않으니까)
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
			
			if(wifiN < C) { //C개보다 적게 썼다면 더 쓸수 있는 것! -> 간격을 좁힌다
				right = mid - 1;
			}else { //C개보다 많이 썼다면 공유기 개수 줄여야함 ! -> 간격 늘린다
				//result = mid;
				left = mid + 1;
				result = Math.max(result, mid);
			}
		}
		System.out.println(result);
	}

}
