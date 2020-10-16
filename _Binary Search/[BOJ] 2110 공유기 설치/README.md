# [BOJ]/[2110] 공유기 설치

## *- Binary Search -*

* 찾아야 하는 것(`int result`): **가장 인접한 두 공유기 사이의 거리의 최댓값**

  ​													( = 최대한 골고루 설치해야 함)

* 집의 좌표 `N`의 범위가 (2 ≤ N ≤ 200,000) 이고, 공유기 개수`C`의 범위가 (2 ≤ C ≤ N) 이므로, 각 집의 간격을 찾아서 인접한 공유기의 간격을 찾으려고 하면 오래 걸린다.

  => *이분 탐색* 으로 찾기.

* `int right = housep[N-1] - house[0]`

  : `result`가 될 수 있는 값의 범위의 최댓값 ( = 처음 집과 마지막 집 사이의 거리)

* `int left = 1`

  : `result`가 될 수 있는 값의 범위의 최솟값 ( = 각 집들이 같은 좌표는 갖지 않음. 최소는 1)

* `left <= right`가 성립한다면 아래 과정 반복

  * `int mid = (left + right) / 2` 로 설정

  * 처음 집부터 마지막 집까지 사이 간격을 따져본다.

    * 공유기를 설치한 집과 현재 집까지의 간격이 `mid`보다 크거나 같으면 공유기 설치개수를 늘려주고  현재 집부터 다시 간격을 구한다.

      ```java
      int wifiN = 1; // 공유기 개수
      int distance = 0; // 집 사이 간격
      for(int i=0; i<N-1; i++) {
      	if((distance + (house[i+1]-house[i])) >= mid) {
      		wifiN++; //공유기 개수 늘려주고
      		distance = 0; //공유기 사이 간격 초기화
      	}else { //mid를 넘지 않으면 공유기 사이 간격을 더해준다.
      		distance += (house[i+1]-house[i]);
      	}
      }
      ```

    * 이후 마지막 `distance`가 `mid`보다 크면 공유기를 하나 더 설치해준다.

      ```java
      if(distance >= mid)	
      	wifiN++;
      ```

    * 현재 간격으로 공유기를 설치했을 때, 이 개수(`wifiN`)가 `C`보다 작다면 공유기를 더 쓸 수 있는 것 ! => 공유기 간격을 좁힌다.

      이 개수(`wifiN`)가 `C`보다 크다면 사용한 공유기 개수를 줄여야 한다 ! => 간격을 넓힌다.

      이 때의 가능한 `mid`값의 최대값을 `result`에 저장한다.

      ```java
      if(wifiN < C) { 
      	right = mid - 1;
      }else { 
      	left = mid + 1;
      	result = Math.max(result, mid);
      }
      ```

  ## :speaking_head:

  이분 탐색 문제는 푸는 방법은 대부분 비슷한 것 같다.

  `left`와 `right`를 어떤 조건일 때 바꿔주어야 하는지 잘 확인하자 !

