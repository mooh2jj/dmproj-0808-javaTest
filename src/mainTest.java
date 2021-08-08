import model.CarFactory;
import model.Member;

import java.util.Arrays;
import java.util.Calendar;

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


    }
}
