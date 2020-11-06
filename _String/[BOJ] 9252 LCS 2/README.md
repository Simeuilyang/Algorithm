# [BOJ]/[9252] LCS 2

## *- String, DP -*

* LCS: 최장 공통 부분 수열

  ​		주어진 수열 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제.

  ​		:star:주의할 점

  > LCS에는 공통 부분 문자열(Longest Common Substring)과 공통 부분 수열(Longest Common Subsequence)이 존재한다.
  >
  > 예) ABCDHEF	BCDEF
  >
  > 공통부분 문자열: BCD (연속해야 함)
  >
  > 공통 부분 수열: BCDEF (연속하지 않아도 됨. but 문자끼리 앞뒤 순서는 일정해야함)

  </br>

* LCS(Xi, Yj)는 다음과 같이 구한다.

  X, Y는 주어진 각 문자열.

  i, j는 각 문자열의 인덱스.

  1. `i=0` 이거나 `j=0` 이라면, `LCS[i][j] = 0`

     : 계산하기 편리하도록 배열의 첫 행, 첫 열에는 `0`을 넣어준다.

  2. `Xi = Yj`라면, `LCS[i][j] = LCS[i-1][j-1] + 1`

     : 현재 인덱스의 각 문자가 같다면 이전 인덱스까지의 문자열끼리의 최장 공통 부분 수열 길이에 `+1`한 값이 현재 문자열로 만드는 최장 공통 부분 수열의 길이이다.

  3. `Xi != Yj`라면, `max(LCS[i][j-1], LCS[i-1][j])`

     : 현재 인덱스의 각 문자가 다르다면 그 문자를 뺀 문자열끼리의 최장 공통 부분 수열 길이가 현재의 최장 공통 부분 수열의 길이가 된다.

     </br>

* 출력은 다음과 같은 원리이다.

  ![image](https://user-images.githubusercontent.com/33208360/96445427-d4ae3a80-124a-11eb-9c0c-fea2e7337e23.png)

```java
int[] find = new int[] {s2.length, s1.length};
while(find[0]!=0 && find[1]!=0) {
	if(LCS[find[0]][find[1]-1] == LCS[find[0]][find[1]]) {
		find[1] -= 1;
	}else if(LCS[find[0]-1][find[1]] == LCS[find[0]][find[1]]) {
		find[0] -= 1;
	}else if(LCS[find[0]][find[1]-1]!=LCS[find[0]][find[1]] && LCS[find[0]-1][find[1]] != LCS[find[0]][find[1]]) {
				t.add(s2[find[0]-1]);
				find[0] -= 1;
				find[1] -= 1;				
	}
}
		
for(int i=t.size()-1; i>=0; i--) {
	System.out.print(t.get(i));
}
```

</br>

## :speaking_head:

* 코딩테스트를 몇번 쳐보니까 다른 자주 나오는 알고리즘도 어렵지만, 문자열 처리도 은근 어려웠다. 문자열 문제도 많이 풀어보도록 해야겠다!!
* LCS를 처음 들어봐서 문제를 읽어도 제대로 이해하지 못했다. 처음엔 '공통 부분 문자열' 방식으로 풀어서 틀렸었는데, 다시 LCS를 찾아보고 공부하면서 알 수 있었다 !