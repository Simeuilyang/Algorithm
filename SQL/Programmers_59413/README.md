# [Programmers]/[59413] 입양 시각 구하기(2)

## *- GROUP BY -*

```mysql
SET @hour := -1;
SELECT (@hour := @hour +1) as HOUR, (SELECT COUNT(*)
                                     FROM ANIMAL_OUTS
                                     WHERE HOUR(DATETIME) = @hour) as COUNT
FROM ANIMAL_OUTS
WHERE @hour < 23;
```

* 실제 존재하는 데이터의 입양 시간은 7시부터 19시까지만 존재

  => 문제에서는 0시부터 23시까지의 입양시간을 묻고 있으므로, 존재하지 않는 시간대는 따로 만들어 줘야 한다.

* `SET`을 통해 `hour`라는 변수를 선언해주고,

  `@hour := @hour + 1 as HOUR`을 통해 `hour`변수가 `1`씩 증가하도록 만들어 준다.

* `count`는 서브쿼리를 사용.

  변수 `hour`의 값과 같은 `HOUR(DATETIME)`을 갖는 데이터의 개수를 구하도록 한다.

  * 이 쿼리문의 결과를 `@hour < 23`이라는 조건을 `WHERE`절에 걸어주어서 `hour`변수가 `0`부터 `23`까지 바뀔 수 있도록 한다.

