/*
 * - 이분탐색 -
 * 무엇을 탐색할 것인가? 가 중요 !!
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class _2343 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]); // 레슨의 수
		int M = Integer.parseInt(s[1]); // 블루레이의 개수
		int[] lesson = new int[N];
		String[] d = br.readLine().split(" ");
		int left = 1;  //블루레이의 크기가 될 수 있는 값 중 최소 값
		int right = 0; //블루레이의 크기가 될 수 있는 값 중 최대 값 => 레슨 길이의 합
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
				if(lesson[i] > mid) { //블루레이의 길이보다 레슨의 길이가 길면 -> 블루레이 길이 늘려야 함!
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
			
			if(bCnt <= M) { // 블루레이의 개수가 M보다 작거나 같으면 -> 블루레이 길이 줄여볼 수 있다 (블루레이 더 쓸 수 있으니까)
				right = mid -1;
			}else { //블루레이 개수가 M보다 크면 -> 블루레이 길이 늘려야 함  (블루레이 덜 써야되니까)
				left = mid + 1;
			}
		}
			
		System.out.println(left);
		
	}

}
