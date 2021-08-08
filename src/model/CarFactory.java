package model;

public class CarFactory {
//        1) 생성자는 private로 선언
//        2) 클래스 내부에 유일한 private 인스턴스 생성 , static
//        3) 외부에서 유일한 인스턴스를 참조할 수 있는 public 메서드 getInstance() static

    private CarFactory() {

    }

    private static CarFactory instance = new CarFactory();

    public static CarFactory getInstance() {
        if (instance == null) {
            instance = new CarFactory();
        }
        return instance;
    }
}
