# [BOJ]/[18870] 좌표 압축

## *- Sorting -*

#### Logic

1. 우선, 입력받은 좌표들을 그 입력 순서와 함께 저장하고, 좌표를 기준으로 오름차순으로 정렬한다.

2. 해당하는 좌표의 index보다 작은 index에 있는 좌표들의 개수가 좌표 압축의 결과값이므로 그것을  result배열에 순서와 함께 저장한다. 이때는 다시 순서를 기준으로 오름차순으로 정렬한다.
3. 순서를 기준으로 오름차순으로 정렬된 배열의 결과값을 출력한다.

#### Simulation

* `N`의 범위가 `1 <= N <= 1,000,000`이므로 java에 있는 `Arrays.sort()`를 사용하면 시간초과가 날 것같아 heap sort를 이용하였다.

  * heap sort는 `PriorityQueue`를 이용하여 구현하였다.

  * `PriorityQueue`는 두 개를 생성하였는데, 

    * 하나는 (순서, 좌표값)이 저장되어 있는 객체에서 좌표값을 우선순위로 하는 것

      => `PriorityQueue<FirstLoc> firstloc`

    * 다른 하나는 (순서, 결과값)이 저장되어 있는 객체에서 순서를 우선순위로 하는 것이다.

      => `PriorityQueue<FirstOrder> firstorder`

* 먼저, 입력받은 좌표들을 `firstloc`에 `add()`한다.

* `firstloc`에서 하나씩 `poll()`하면서 그 값을 `firstorder`에 결과값과 함께 `add()`한다.

  * 이때, 같은 좌표가 있을 수 있으므로 `Map<Integer, Integer> map`에 <좌표값, 결과값>을 저장하였다. 그래서 `map`에 해당하는 좌표값이 있다면 그 결과값을 그대로 `firstorder`에 `add()`해주었다.
  * 또, 결과값은 처음에는 그냥 현재의 (index-1)을 넣어주었는데, 이렇게 되면 같은 숫자가 있을 경우에 결과값이 다르게 나오게 된다. 따라서 좌표 압축의 결과값을 `smallCnt`로 지정하였고, `map`에 없는 좌표값을 넣을때마다 `+1`해주었다.

* 모든 과정을 끝낸 후, `firstorder`를 `poll()`하면서 결과값 배열에 넣어주고 이를 출력한다.