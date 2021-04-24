# [BOJ]/[20056] 마법사 상어와 파이어볼

## - simulation -

**전역 변수**

```java
ArrayList<FireBall> fireballs; // 파이어볼 정보 리스트
```

* `class FireBall` : 파이어볼의 위치좌표(`x, y`), 질량(`m`), 속력(`s`), 방향(`d`)가 저장됨.

## solution

```java
ArrayList<Integer>[][] map = new ArrayList[N+1][N+1]
```

* 파이어볼의 이동 후의 위치에 해당 파이어볼의 인덱스를 저장할 배열

</br>

* 각 파이어볼을 이동시키고, 그 위치를 `map`에 저장한다. 

`void MoveFireBalls(int N, ArrayList<Integer>[][] map)`

1. 파이어볼의 위치를 각 속력과 방향에 따라 이동해야하는 칸만큼 더해준다.

   ```java
   int rx = now.x + s * dx[now.d];
   int ry = now.y + s * dy[now.d];
   ```

2. 만약 위의 `rx, ry`값이 범위내에 속하지 않으면, 맞게 처리해준다.

   ```java
   if(rx > N)	rx %= N;
   if(rx < 1)	rx = N - (Math.abs(rx)%N);
   ```

   * `N`보다 크다면 `%N`
   * `1`보다 작다면 `N - (Math.abs(rx)%N)`

   :star:이 때, 첫 번째 조건 처리를 해준 후 두 번째 조건도 확인해주어야한다.

   > 만약 첫 번째 조건에서 **`N`으로 나누어떨어진다면 `rx`값은 `0`이 되므로 알맞지 않은 좌표값!** 따라서 두 번째 조건인 `1`보다 작을 경우도 처리해주어야 알맞은 값이 계산된다.

3. 이동시킨 위치의 `map`리스트에 현재 파이어볼의 인덱스를 추가해준다.

   > 이 때, `map`을 모두 초기화하지 않고 만약 파이어볼을 추가해야할 경우에만 초기화해주어 인덱스를 추가하도록 한다.

</br>

`void ChkSameLoc(int N, ArrayList<Integer>[][] map)`

* 모든 위치를 확인하면서, 파이어볼이 여러개 존재하는 위치라면 그 파이어볼들을 합쳐 4개의 파이어볼로 나눠준다.

```java
ArrayList<Integer> remove = new ArrayList<>(); // 합쳐져서 소멸시켜야 하는 파이어볼의 인덱스
```

1. `void SumAndSpread(ArrayList<Integer> BallList, ArrayList<Integer> remove, int N, int x, int y)`

   `BallList`에 있는 인덱스에 해당하는 파이어볼들의 질량, 속력 합을 구해 문제의 조건에 맞게 설정해준다.

   새로 생성될 파이어볼들의 방향은 첫번째 파이어볼의 방향의 홀짝을 구해둔 후, 그 뒤의 파이어볼의 방향의 홀짝이 첫번째와 다르다면 `1, 3, 5, 7`로, 모두 같다면 `2, 4, 6, 8`로 설정한다.

2. 파이어볼 2개 이상 있는 위치를 모두 확인했다면, `remove`리스트를 오름차순으로 정렬한다.

3. `remove`를 스캔하며 합쳐진 파이어볼들을 소멸시킨다.

   ```java
   int remCnt = 0; // 삭제된 파이어볼의 개수
   for(Integer r : remove) {
   	fireballs.remove(r-remCnt); // remove를 오름차순 정렬한 이유.
   	remCnt++;
   }
   ```

   * 오름차순 정렬되어있어 작은 인덱스부터 삭제되기 때문에 인덱스를 맞춰주기 위해 삭제된 개수를 빼준 인덱스의 파이어볼을 삭제한다.

</br>

위 과정을 `K`번 반복. 그 후 남은 파이어볼의 질량 합을 구해 출력한다.

</br>

## :speaking_head:

> **:timer_clock: *1시간 10분***

알고리즘 스터디를 하면서 풀어봤던 문제인데 그 때 놓쳤던 부분을 똑같이 놓쳤다 ㅜ 내일은 이러지 않도록 !!

**`%` 연산을 할 때 나누어 떨어지는 경우(`%`시 `0`이 되는 경우)도 잘 고려해주자 !!!**