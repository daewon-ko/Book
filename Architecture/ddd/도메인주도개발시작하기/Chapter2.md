- 표현- 응용 - 도메인 -인프라스트럭처

  - 전형적인 4가지 영역

- 표현영역

  - 웹 어플리케이션 개발시 많이 사용하는 스프링 MVC가 표현영역에 해당
  - HTTP 요청을 응용 영역이 필요로 하는 형식으로 변환해서 전달 및 응용 영역의 응답을 HTTP 응답으로 변환하여 전송

- 응용영역

  - 시스템이 사용자에게 제공해야할 기능을 구현
  - ex) ‘주문 등록’, ‘주문 취소’, ‘상품 상세 조회’ 등
  - 응용영역은 기능 구현을 위해 ‘도메인 모델’을 사용

  ```python
  public class CancleOrderService{
  	
  	@Transactional
  	public void cancelOrder(String orderId){
  		Order order = findOrderById(orderId);
  		if(order == null) // 예외처리
  		order.cancel();
  	}
  
  }
  ```

  - 응용 영역은 로직을 직접실행하기 보다 도메인 모델에 로직 수행을 위임

- 도메인영역

  - 도메인 모델을 구현하고 도메인의 핵심 로직을 구현
  - ex) ‘배송지 변경’, ‘결제 완료’, ‘주문 총액 계산’ 등

- 인프라스트럭처 영역

  - 구현기술에 대한 것을 다룸
    - ex) RDMS 연동, 메시징 큐에 메시지 전송 및 수신하는 기능 구현, 몽고 DB나 REDIS의 데이터 연동
  - 논리적인 개념을 표현하기보다는 실제 구현을 다룸
  - 도메인, 응용, 표현 영역은 구현기술을 사용한 코드를 직접 만들지 않고 인프라스트럭처영역에서 제공하는 기능을 사용해서 필요한 기능을 개발
    - ex) DB모듈을 사용하지 DB모듈의 기능을 직접 개발하지 X, 메일 발송필요시 SMTP 연동 모듈을 사용

### 계층구조

- 표현 → 응용 → 도메인 → 인프라

  - 도메인의 복잡도와 상황에 따라서 도메인 영역은 있기도 하고 없기도 하지만 대게는 위의 구조를 따른다.
  - 계층구조는 상위 계층에서 하위 계층으로의 의존만 존재 (**즉, 의존성이 역류하면 안됨**)

- 상위 계층이 하위계층에 의존하고, 의존성이 역류하지 않는다면 계층구조의 유연성 적용은 가능

  - ex) 응용영역에서 도메인 영역뿐 아니라 인프라 영역까지 의존한다 등

- ## But, **표현 ,응용, 도메인 계층이 ‘인프라’에 종속되고 ‘강결합’된다는 문제**가 발생

  ```python
  public class DroolsRuleEngline{
  	private KieContainer kcontainer;
  	// 생성자
  	
  	public void evaluate(String sessionName, List<?> facts){
  		KieSession ksession = Kconatiner.newKieSession(sessionName);
  		try{
  			facts.forEach(x ->kSession.insert(x));
  			kSession.fireAllRules();
  		}finally{
  			kSession.dispose();
  		}
  		
  	
  	}
  }
  
  public class CalculateDiscountService{
  	private DroolsRuleEngine ruleEngine;
  	
  	// 생성자
  	
  	public Money calculateDiscount(List<OrderLine> orderLines, String customerId){
  		Customer customer = findCustomer(customerId):
  		
  		MutableMoney money = new MutableMoney(0);
  		List<?>facts = Arryas.asList(customer, money);
  		facts.addAll(orderLines);
  		ruleEngine.evalute("discountCalculation", facts);
  		return money.toImmutableMoney();
  	
  	}
  
  }
  ```

  - 위 방식은 2가지 문제가 존재

  1. CalculateDiscountService만 테스트하기 어려움
     1. RuleEngine이 완벽하게 동작해야함. 즉 해당 클래스와 관련된 설정을 모두 완성한 이후에 CalculateDiscountService의 정상 동작 여부를 확인 가능
  2. 구현 방식을 변경하기 어려움
     1. ‘discountCalculation’문자열은 Drools의 세션이름을 의미
     2. 따라서 Drools의 세션이름을 변경하면 CalculateDiscountService까지 변경이 전파됨

- 즉, 인프라스트럭처에 의존하면 ‘테스트가 어렵고’, ‘기능 확장의 어려움’이 발생

### DIP

- 의존관계역전

- CalculateDiscountService

  - 고수준 모듈
    - 의미있는 단일 기능을 제공하는 모듈로 , 위 내용에서 CalculateDiscountService는 ‘가격 할인 계산’이라는 기능을 제공
  - 저수준모듈
    - 하위기능을 실제로 구현한 것
      - 고객정보를 구하고 / 룰을 실행하는것 각각이 ‘저수준 모듈’
  - 고수준 모듈이 제대로 동작하려면 저수준 모듈을 사용해야함.

- DIP는 저수준 모듈이 고수준 모듈에 의존하게끔 변경

  - ‘추상화된 인터페이스’를 활용하여 저수준 모듈이 고수준모듈에 의존하도록 변경
  - 

  ```python
  public interface RuleDiscounter{
  	Money applyRules(Customer customer, List<OrderLine> orderLines);
  }
  
  public class CalculateDiscountService{
  	private RuleDiscounter ruleDiscounter;
  	
  	//생성자
  	
  	public Money calculateDiscount(List<OrderLine> orderLines, String customerId){
  		Customer customer = findCustomer(customerId);
  		return ruleDiscounter.applyRules(customer, orderLines);
  	}
  
  }
  ```

  - CalculateDiscountService에서는 Drools에 의존하는 코드가 없음
    - 단지, RuleDiscounter가 룰을 적용한다는 사실만 알고 있음.

- 즉, 마치 전략패턴처럼 CalculateDiscountService는 RuleDiscounter에 의존, DroolsRuleDiscounter(구체)는 RuleDiscounter(인터페이스)에 의존한다.

- DIP를 적용하면 인프라영역에 의존할때 발생했던 ‘구현 교체의 어려움’과 ‘테스트가 어려움’의 문제를 해소할 수 있다.

- 

```python
// 사용할 저수준 객체 생성
RuleDiscounter ruleDiscounter = new DroolsDiscounter();
// 생성자 방식 주입
CalculateDiscountService disService = new CalculateDiscountService(rulesDisCounter);

// 사용할 저수준 객체 변경
RuleDiscounter ruleDiscounter = new SimpeRuleDiscounter();
// 생성자 방식 주입
CalculateDiscountService disService = new CalculateDiscountService(rulesDisCounter);
```

- 또한 다음과 같이 저수준 모듈(Customer를 찾는것)을 별도로 구현할 수 있음
- 

```python
public class CalculateDiscountService{
	private RuleDiscounter ruleDiscounter;
	private CustomerRepository customerRepository;
	
	//생성자
	
	public Money calculateDiscount(List<OrderLine> orderLines, String customerId){
		Customer customer = findCusumer();
		return ruleDiscounter.applyRules(customer, orderLines);
	}

	private Customer findCustomer(String customerId){
		Customer customer = customerRepository.findById(customerId);
		if(customer == null) throws ~
		return customer;
	}
}
```