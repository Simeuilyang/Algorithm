# [BOJ]/[11404] 플로이드

## *- Floyd Warshall -*

> 플로이드 와샬 알고리즘은 '**거쳐가는 정점**'을 기준으로 최단 거리를 구한다.

```java
int[][] cost;
```

* `cost[a][b]` : `a`에서 `b`로 가는 거리의 최소 비용
  * `cost[i][i]` 는 자기 자신이므로 `0`으로 초기화하고, 나머지는 무한대의 의미로 `-1`로 초기화한다.

* 입력을 받으면서 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있으므로, 그 노선 중 최소의 비용으로 `cost`값에 저장한다. (최단 거리를 구하는 것이므로)

## solution

```java
for(int v=1; v<=N; v++){
	for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++){
            ...
        }
    }
}
```

* `v` : 거쳐가는 정점

* `i`에서 `j`로 가는 거리를 확인한다.

  `i->j`거리보다 (`i->v`거리+`v->j`거리)가 더 짧다면 `cost[i][j]`를 그 비용으로 갱신.

  * 이때, `i->v`와 `v->j`의 비용이 `-1`이 아닐 때만 확인. (즉, `i`에서 `v`와 `v`에서 `j`로 가는 각각의 길이 있을 때만 확인)
  * 또, `cost[i][j]`가 `-1` , 즉 무한대일 때 또한 거쳐가는 비용으로 갱신해준다. (원래는 갈 수 없었던 경우. `v`를 거친다면 갈 수 있는 경우.)

모든 정점을 거쳤을 때의 처리를 해준 후, `cost`배열의 값이 `-1`이면 갈 수 없는 것으로 `0`을, 아니라면 `cost`값을 출력해준다.



