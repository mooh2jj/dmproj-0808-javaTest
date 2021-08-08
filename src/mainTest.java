import model.CarFactory;
import model.Member;
import model.Sex;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class mainTest {
    public static void main(String[] args) {
        System.out.println("ssss");

        Week today = null;
        // enum : 열거형 타입 클래스 : 상수들의 묶음
        Calendar calendar = Calendar.getInstance();     // 싱글톤 패턴

        int week = calendar.get(Calendar.DAY_OF_WEEK);      //  일주일의 값을 가져온다.

//        switch (week){
//            case 1:
//                today = Week.일; break;
//            case 2:
//                today = Week.화; break;
//            case 3:
//                today = Week.수; break;
//            case 4:
//                today = Week.목; break;
//            case 5:
//                today = Week.금; break;
//            case 6:
//                today = Week.토; break;
//            case 7:
//                today = Week.월; break;
//
//        }
//
//        System.out.println("today: "+ today);

        // 열거형 = 배열이랑 같다!
        Arrays.stream(Week.values())
                .map(v->v.getDesc())
                .forEach(System.out::println);


        // 싱글톤 패턴 : 이미 만들어진 객체를 공유하기 위해서
//        1) 생성자는 private로 선언
//        2) 클래스 내부에 유일한 private 인스턴스 생성
//        3) 외부에서 유일한 인스턴스를 참조할 수 있는 public 메서드 getInstance()

        // 싱글톤을 배워야 되는 이유 -> 스프링에 bean이 싱글톤 패턴으로 이루어진 -> 메모리를 공유 어노테이션
        // 싱글턴 패턴을 따르는 클래스는 생성자가 여러 차례가 호출되더라도 실제로 생성되는 객체는 one!
        // 최초 생성 이후에 호출된 생성자는 최초의 생성자가 생성한 개체를 리턴한다.
        // DI 컨테이너인 요청을 할 때마다 새로운 객체를 생성한다. 요청이 엄청나게 많은 트래픽 사이트에서는
        // 계속 객체를 생성하게 되면 메모리 낭비가 심하기 때문!
        // 스프링 컨테이너에서 싱글턴 관련 코드는 작성하지 않아도 스프링의 기능으로 빈에다가 객체를 1개 설정
        // appConfig.getBean(Member.class) 하나의 주소를 가리킴

        CarFactory carFactory1 = CarFactory.getInstance();
        CarFactory carFactory2 = CarFactory.getInstance();

        System.out.println(carFactory1);
        System.out.println(carFactory2);
//        model.CarFactory@52af6cff 주소값
//        model.CarFactory@52af6cff

//        Member member1 = new Member();
//        Member member2 = new Member();

//        System.out.println("member1: "+ member1);
//        System.out.println("member2: "+ member2);
//        member1: model.Member@6d8a00e3
//        member2: model.Member@548b7f67

        // Optional => null (NPE)때문에 쓰는 객체다.
        Optional<String> stropt = Arrays.asList("dsg", "kmb").stream().findFirst();
//        System.out.println("stropt: "+ stropt.get());
//        System.out.println("stropt: "+ stropt.orElse("empty"));     // Optional 객체 값이 null 이면
        System.out.println("stropt: "+ stropt.orElseGet(String::new));     // Optional객체값에 상관없이 새 객체로 생성할 때 사용
        System.out.println("stropt: "+ stropt.orElseThrow(IllegalArgumentException::new));

        String str = null;

        Integer int1 = null;    // wrapper 클래스는 null값을 허용

        // of(x) vs ofnullable(o) , ifPresent
//        Optional.of(null).ifPresent(s-> System.out.println(s));     // NPE 발생할 수 있음
        Optional.ofNullable(null).ifPresent(System.out::println);      // NPE 발생하지 않음!
        // null일지 모르는 값이 들어올 때 방어로직으로 많이 사용한다.
        var str1 = Optional.ofNullable(str).orElse("null");
        System.out.println("str1: "+ str1);

        var integer = Optional.ofNullable(int1).orElse(0);
        System.out.println("integer: "+ integer);

//        if (str.isEmpty()) {
//            System.out.println("str은 s=null입니다. ");
//        }
//        1) Optional 은 null 처리때문에 쓰는 객체이다.
//        2) 메서드 정리 : get, orElse, orElseGet, orElseThrow,
//        3) of x ofnullable() ifPresnt() orElse 를 많이 쓴다.

        // stream 연산을 도와주기위한 인터페이스 iterator -> 구분자 를 이용해서 연산을 할려고 했는 데 너무 복잡 -> stream

        // 1) 스트림 : 1단계 : 데이터소스, 2단계 : 중간연산 (filter, map, mapToInt, ...), 3단계 : 최종연산 (forEach, collect, sum, reduce)
        // 2) Conumer(T -> void , forEach) vs Supplier( () -> T )  //  Predicate(T -> boolean , filter) vs Function(T -> R , map, mapToObject)  // Collector
        // 3) :: 메서드 레퍼런스 (Class :: static method)
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6);

        // 짝수만 모아서 제곱해서 나오게 해라 (-> 더해라).
        var sum = intList.stream()
                .filter(i -> i % 2 == 0)
                .map(d -> d * d)
//                .forEach(System.out::println);
                .reduce(0, Integer::sum);   // 4 + 16 + 36

        System.out.println("sum: "+ sum);

        // integer stream = > IntStream, rangeClosed() : 범위를 지정해주는
        var sum1 = IntStream.rangeClosed(1, 100)
                .filter(i -> i % 2 == 0)
                .map(d -> d * d)
//                .forEach(System.out::println);
                .reduce(0, Integer::sum);// 4 + 16 + 36
        System.out.println("sum1: "+ sum1);

        List<Member> memlist = Arrays.asList(
                new Member("dsg", 21, Sex.MALE),
                new Member("kmb", 21, Sex.MALE),
                new Member("lbk", 25, Sex.FEMALE),
                new Member("kkk", 27, Sex.FEMALE),
                new Member("jjj", 34, Sex.MALE)

        );

        List<Member> javalist = Arrays.asList(
                new Member("dsg", 21, Sex.MALE),
                new Member("kmb", 21, Sex.MALE),
                new Member("lbk", 25, Sex.FEMALE)


        );

        List<Member> springlist = Arrays.asList(
                new Member("kkk", 27, Sex.FEMALE),
                new Member("jjj", 34, Sex.MALE)

        );

        // 남자인 사람의 나이의 평균을 구하라.
        var average = memlist.stream()
                .filter(m -> m.getSex() == Sex.MALE)
                .map(m -> m.getAge())
                .mapToDouble(d -> d)
                .average();

        System.out.println("average: "+ average.orElse(0.0));       // Null 인 값이 들어갈 시 해결법 과제 Optional.ofnullable


        // 여자인 사람의 이름을 역순으로 정렬(알파벳순)해라.
        var collect = memlist.stream()
                .filter(m -> m.getSex() == Sex.FEMALE)
                .map(i -> i.getName())
                .sorted()
//                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("collect: "+ collect);


        // 중복 제거해서 가져와라.
        List<Integer> intList2 = Arrays.asList(1, 2, 2, 4, 4, 6, 6, 5, 9, 9, 9);

        intList2.stream()
                .filter(i -> i%2 !=0)
                .distinct()
                .forEach(System.out::println);

        List<List<Member>> students = new ArrayList<>();

        students.add(javalist);
        students.add(springlist);

        // javalist 의 사람 이름을 뽑아라.
        students.stream()
                .flatMap(s->s.stream())
                .map(s->s.getName())
                .forEach(System.out::println);

    }
}
