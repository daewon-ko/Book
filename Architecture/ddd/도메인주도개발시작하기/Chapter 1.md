### 1.1 도메인이란?

- 소프트웨어로 해결하고자 하는 문제영역
- 한 도메인은 다시 하위 도메인으로 나눌 수 있음
- 한 하위 도메인은 다른 하위 도메인과 연동하여 완전한 기능을 제공

### 1.2 도메인 전문가와 개발자 간 지식 공유

- 도메인 모델을 사용하면 여러 관계자들이 동일한 모습으로 도메인을 이해하고 도메인 지식을 공유하는데 도움이 됨
- 도메인 모델
  - 객체로만 모델링 하는 것이 아님
  - 상태 다이어그램 등을 이용해서 ‘전이 상태’ 등을 모델링 할 수 있음
  - 즉, UML으로만 표현하는 것이 아님
  - 기본적으로 도메인 자체를 이해하기 위한 개념모델
  - 개념모델 ≠ 구현모델
    - → 구현의 영역에서는 별도의 모델링 언어가 필요
    - 구현 모델이 최대한 개념모델을 따르도록 할수 는 있음.
    - ex) 객체기반 모델 → 객체 지향 언어를 이용해서 개념모델에 가깝게 구현 가능

### 1.4 도메인 모델 패턴

- 4계층으로 구성
  - **표현 - 응용 - 도메인 - 인프라스트럭쳐**
  - cf) 도메인 모델
    - def 1)도메인 그자체를 다루는 모델
    - def 2) 아키텍처 상의 도메인 계층을 객체 지향기법으로 구현하는 패턴
    - 추후 논의하는 ‘도메인 모델’은 2번을 뜻한다.
- 도메인 계층
  - 도메인의 핵심 규칙을 구현
  - ex) 주문 도메인
    - ‘출고 전에 배송지를 변경할 수 있다.’
    - ‘주문 취소는 배송 전에만 할 수 있다.’
    - 예시코드
    - (생략)
  - 배송지 변경 가능 여부 판단의 위치가 Order, OrderLine이든 중요한 것은 주문과 관련된 중요한 업무 규칙이 도메인 모델에서 구현한다는 점
  - 즉, 핵심 규칙을 구현한 코드는 도메인 모델에만 위치하므로 규칙이 바뀌거나 규칙을 확장해야할때는 다른 코드에 영향을 덜 주고 변경내역을 모델에 반영할 수 있게된다.

### 1.5 도메인 모델 도출

- 도메인 모델링
  - 모델을 구성하는 핵심 구성요소, 규칙, 기능을 찾는 것이 요구사항의 출발

### 1.6 엔티티와 밸류

- 도메인 모델

  - 엔티티 / 밸류로 구분 가능

- 엔티티

  - 식별자를 지님
  - ex) 주문 도메인에서 각 주문은 주문번호를 지니고 있는데, 이 주문번호는 각 주문마다 다름.
    - 따라서 주문번호가 주문의 식별자
    - 주문에서 배송지가 바뀌거나 상태가 바뀌더라도 주문번호는 바뀌지 않고 식별자는 유지됨.
    - 식별자를 활용해 equlas and HashCode 메서드를 구현할 수 있음
  - 도메인 특징과 사용기술에 따라 엔티티 생성 시점과 기술은 다름
    - 특정 규칙에 따라 생성
    - UUID, NanoID와 같은 고유 식별자 생성기
    - 값을 직접입력
    - DB 자동증가 , 시퀀스 같은 일련번호 사용

- 밸류타입

  - 개념적으로 완전한 하나를 표현할때 사용
  - ex) 하단의 ShippingInfo는 개념적으로 다른 2가지 층위가 존재

  ```python
  public class ShippingInfo{
  	// 받는 사람
  	private String receiverName;
  	private String reciverPhoneNumber;
  	// 주소
  	private String shippingAddress;
  	private String shippingAddress1;
  	private String shppingZipCode;
  	...
  }
  ```

  받는사람과 주소를 아래와 같이 2가지 밸류타입으로 작성 가능하고

  ```python
  public class Receiver{
  	private String name;
  	private String phoneNumber;
  	
  	// 생성자 및 getter, setter 생략
  }
  
  public class Address{
  	private String address1;
  	private String address2;
  	private String shippingZipcode;
  	
  	// 생성자 및 getter, setter 생략
  	
  }
  ```

  위와 같이 밸류타입으로 작성하면 아래와 같이 표현가능하다.

  ```python
  public class ShippingInfo{
  	private Receiver receiver;
  	private Address address;
  }
  ```

  - 밸류 객체의 또 다른 장점은 밸류 타입을 위한 기능을 추가 가능
  - ex)

  ```python
  public class Money{
  	private int value;
  	
  	public Money add(Money money){
  	return new Money(this.value + money.value);
  	}
  	
  	public Money multiply(int multiplier){
  		return new Money(value*mutliplier);
  	}
  }
  ```

  - 밸류 객체의 데이터를 변경할떄는 기존 데이터를 변경하기보다는 변경한 데이터를 갖는 새로운 밸류 객체를 생성하는 방식을 선호

    - 위  Money와 같이 변경 기능을 제공하지 않는 객체를 ‘**불변**’이라고 표현

      - 밸류 타입을 불변으로 구현하는 가장 큰 이유는 ‘ 안전한 코드 작성’을 위함
      - what if) OrderLine이라는 class에서 생성시에 Money를 전달해서 생성하는데 Money 클래스가 public으로 setter를 열어놨다면?
      - ex)

      ```python
      Money price = ...;
      OrderLine orderLine = new OrderLIne(.. , price);
      price.setMoney(xxx);
      ```

      → orderLine의 price 값이 잘못 반영되는 문제가 발생

      ```python
      public class OrderLine{
      
      // 필드들..
      
      // 생성자
      public OrderLine(..., Money price){
      	this.price = new Money(price.getValue());
      }
      ```

      → 이와 같이 코드를 작성해줘야함.

      그러나, Money 객체가 만약 불변이라면?

      생성자 내에서 this.price = this.price로 처리해줘도 무방하다!

      - CF) 불변객체는 ‘참조 투명성’과 ‘thread safe’한 특징을 지닌다!

  - ## 두 밸류 객체를 비교할때는 모든 속성이 같지 비교

    ```sql
    public class Receiver{
    	private String name;
    	private String phoneNumber;
    	
    	public boolean equals(Object other){
    		if(other == null) return false;
    		if(this == other) return true;
    		if(!(other instanceOf Receiver) return false;
    		Receiver that = (Receiver)other;
    		return this.name.equals(that.name) && this.phoneNumber.equals(that.phoneNumber);
    	}
    }
    ```

### 1.6.4 엔티티 식별자와 밸류타입

- 엔티티 식별자의 실제 Data → String같은 자료형이 다수
- 식별자를 위한 밸류타입을 사용하면 → 의미를 더 풍부하게 담을 수 있음
  - ex) String money → Money Type 구현
    - Money Class 내부에서 String value;로 필드를 지닌다 해도 도메인의 의미를 더 풍부하게 담을 수 있음
  - ex2) Order Class(Entity)의 식별자로 String Type보다는 OrderNo라는 밸류 타입을 이용하면 해당 필드를 통해 ‘주문번호’임을 알 수 있음

### 1.6.5 도메인 모델에 set메서드 넣지 않기

- set메서드

  - 도메인의 핵심 개념이나 의도를 코드에서 제거
  - 도메인 객체 생성시, 온전하지 않은 상태가 될 수 있음

  ```sql
  Order order = new Order();
  
  order.setXX...
  ...
  
  // 주문자(Orderer)를 설정하지 않은 상태에서 주문 완료 처리 가능
  order.setState(OrderState.PREPARING);
  ```

  - 즉, 도메인 객체가 불완전한 상태로 사용되는 것을 막으려면, 생성시점에 필요한 것을 전달해줘야함
  - 즉, 생성자를 통해 필요한 데이터를 모두 받아야함.

  ```sql
  Order order = new Order(orderer, lines, shippingInfo, OrderState.Preparing);
  ```