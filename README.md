# SpringJPA_1
인프런 영한님의 실전 스프링부트 + JPA활용편의 프로젝트입니다.

### <a href="https://deadwhale.notion.site/SpringBoot-JPA-1-1a5957776eba4b21879b378e97876720">노션 정리 노트</a>

---


# What i Learn This Project?
- JPA 설계
- 도메인 분석 / 설계

---

### Dependencies
- Spring Web
- Thymeleaf
- Spring Data JPA
- H2 Database
- Lombok


---
## 도메인 분석 설계

회원
- 가입 신청
- 목록 조회

상품
- 등록
- 수정
- 조회

주문
- 상품 주문
- 주문 내역 조회
- 주문 취소

기타 요구 사항
- 상품의 재고관리가 필요
- 상품의 종류는 3종류 ( 도서 , 음반 , 영화)
- 상품을 카테고리로 구분할 수 있다
- 상품 주문시 배송 정보를 입력할 수 있다.

----
## 테이블 설계

> 아이템 폴더 하위에 아이템 인터페이스를 상속받아 각각의 구현체들을 구성 했다.
- Item ( Interface )
- Album
- Book
- Movie
> 아이템의 카테고리를 관리하는 Entity
- Category
 >고객 정보와 주소를 담당하는 Entity
- Member
  - Address
>주문정보를 관리하는 주문 Entity 와 하위 상세 Entity
- Order
  - OrderItem
  - OrderStatus (Enum Class)
>주문 정보와 연관된 배송 정보 Entity
- Delivery
  - DeliveryStatus (Enum Class)

``` 
각각의 관계를 테이블로는 여러번 다뤄 봤지만 
```
<br>
- 다대다 관계는 쓰면 안된다.
- 양뱡향 연관관계도 가급적 권고되지 않는다.
- 싱글 테이블 전략

