### 55번 switch 표현식

```java
// default를 빠트리면 코드 자체가 컴파일 되지 않음
// 

private static Player createPlayer(PlayerTypes plyerType){
	switch(playerType){
		case TENNIS : 
			return new TennisPlayer();
		case FOOTBAL :
			return new FootBallPlayer();
		case SNOOKER :
			return new SnnokerPlyaer();
		
		default :
			throw new IllegalArgumentException("Invalid Types"+playerType);	
	}

}
```

- JDK 12부터 switch 표현식 가능해짐
  - switch 표현식이 결과로 평가됨
  - 즉, 단순히 위와 같은 명령문의 구조가 아님
  - break를 쓸 필요가 없음

```java
private static Player createPlayer(PlayerTypes playerType){
	return switch(playerType){
		case TENNIS -> new TennisPlayer();
		case FOOTBAL -> new FootBalPlayer();
		
		...
		// default는 애초에 없어도 됨
	
	}
	
}
```

- 그러나, switch 표현식에도 콜론과 break문을 사용할 수 있음

```java
private static Player createPlayer(PlayerType playerType){
	return switch(playerType){
		case TENNIS :
			break new TennisPlayer();
		case FOOTBAL :
			break new FootbalPlayer();
			
		...
	
	}
}
```

- switch 표현식에서는 반드시 내부에서 Return을 해서는 안됨