import model.CarFactory;
import model.Member;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;

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

        // 싱글톤을 배워야 되는 이유 -> 스프링에 bean이 싱글톤 패턴으로 이루어진 -> 메모리를 공유
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

        Member member1 = new Member();
        Member member2 = new Member();

        System.out.println("member1: "+ member1);
        System.out.println("member2: "+ member2);
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


    }
}
