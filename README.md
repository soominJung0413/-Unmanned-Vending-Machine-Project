# 무인자판기
본 프로젝트는 중앙 정보 처리 학원 자바 커리큘럼 진행 후 이루어진 첫번째 학원 프로젝트.

**역할**
* 1000줄 정도의 메인메서드 소스코드를 객체 지향화 하는 작업.

 
 **언어**
 * Java
 
**UI**
* Swing 

**데이터베이스**
* Oracle

<hr>

**Presiontation-Tier**

* Java Swing 을 이용한 UI 구성
* 기본 무인 판매기 구조와 유사하게 품목에 대한 결산 내용이 장바구니에 산출 되도록 구성
* 결제와 영수증 화면을 만들어 사용자 경험 증진 꾀함.
* 서비스 사용 고객인 관리자 페이지를 만들어 서비스 사용고객이 결산 내용을 직관적으로 파악할 수 있도록 함

**Service-Tier**

* 데이터 입력 후 취소, 결제시 input 컴포넌트 초기화 구현, 수량 오류 값을 검증하기위한 NumberFormatExcetion 처리
* JFrame , Assembler 등에 각 이벤트처리, 컴포넌트 조합 로직을 정리하여 유지보수와 확장성 증대 꾀함.
* 각 Swing의 클래스 들이 Component 클래스의 자식인 것을 파악, 다형성을 구현하여 하나의 메서드에서 모든 컴포넌트를 뿌리도록 함
* instanceof 연산자의 채용으로 타입 분기를 만들어 이벤트 처리 분기
* 컴포넌트 관련 이벤트 처리를 하는 메서드를 각 패키지의 Buttons, TextFields 등의 클래스에 묶어 유지보수와 더불어 클래스 의 변수와 함수를 유연하게 사용하도록 함
* UI를 변경할 때마다 EventQueue.invokeLater() 사용, 블로킹이 발생하지 않도록 조치.

**Persistence-Tier**

* ojdbc 를 이용한 DataBase Access, 입력 값이 그대로 DB 쿼리문에 입력되는 것을 막기위해 PreparedStatement 를 적극적으로 사용.(인젝션 등의 보안 이슈)
* static Method 를 통한 오라클 드라이버 로딩으로 불 필요한 메모리 누수 지양

<hr>
<br>

**본 패키지는 Java 패키지 밑에 Version 1 과 Version 2 로 관리되고 있으며, 해당 내용은 하단에 기술됩니다.**

<br>

**직접 맡은 역할에 대한 내용에 대해서는 Version2에 기술되어있습니다.**

<br>
<hr>

# Version 1

팀 구성원의 Java Swing 사용에 불편함이 있다는 의견이 제시되어 Window Builder 사용이 확정.

**설계 방향**

![](https://github.com/soominJung0413/-Unmanned-Vending-Machine-Project/blob/main/projectimage/Version%20%EC%95%84%ED%82%A4%ED%85%8D%EC%B3%90.PNG)

<hr>

**패키지 구성**

![](https://github.com/soominJung0413/-Unmanned-Vending-Machine-Project/blob/main/projectimage/Version%201%20%ED%8C%A8%ED%82%A4%EC%A7%80%20%EA%B5%AC%EC%A1%B0.PNG)

* Value Object, Data Access Object, 외의 비즈니스 로직들은 VendingMachine 클래스에 위치.
<br>
<br>
<br>

# Version 2

팀원들의 작업속도에 따라 소스코드를 객체지향화 하는 것이 담당 업무. Version 2 의 소스코드는 Version 1의 코드들을 객체지향화 한 모습을 가지고 있습니다.

**설계 방향**

![](https://github.com/soominJung0413/-Unmanned-Vending-Machine-Project/blob/main/projectimage/Version2%20%EC%95%84%ED%82%A4%ED%85%8D%EC%B3%90.PNG)

<hr>

**패키지 구성**

![](https://github.com/soominJung0413/-Unmanned-Vending-Machine-Project/blob/main/projectimage/Version%202%20%ED%8C%A8%ED%82%A4%EC%A7%80%20%EA%B5%AC%EC%A1%B0.png)


<hr> 

**객체지향화를 위한 고민과 결과**



<br>
<br>
<br>

# 시연

**Main**

![](https://github.com/soominJung0413/-Unmanned-Vending-Machine-Project/blob/main/projectimage/%EB%A9%94%EC%9D%B8.PNG)

<hr>

**Menu**

![](https://github.com/soominJung0413/-Unmanned-Vending-Machine-Project/blob/main/projectimage/%EB%A9%94%EB%89%B4.PNG)

<hr>

**Payment**

![](https://github.com/soominJung0413/-Unmanned-Vending-Machine-Project/blob/main/projectimage/%EA%B2%B0%EC%A0%9C.PNG)

<hr>

**Payment finished**

![](https://github.com/soominJung0413/-Unmanned-Vending-Machine-Project/blob/main/projectimage/%EC%98%81%EC%88%98%EC%A6%9D.PNG)

<hr>

**Admin Authentication View**

![](https://github.com/soominJung0413/-Unmanned-Vending-Machine-Project/blob/main/projectimage/%EA%B4%80%EB%A6%AC%EC%9E%90%EB%A1%9C%EA%B7%B8%EC%9D%B8.PNG)

<hr>

**Admin View**

![](https://github.com/soominJung0413/-Unmanned-Vending-Machine-Project/blob/main/projectimage/%EA%B4%80%EB%A6%AC%EC%9E%90%ED%99%94%EB%A9%B4.PNG)
