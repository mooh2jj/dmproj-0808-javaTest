package model;

public enum Sex {
    MALE("남자"),
    FEMALE("여자");

    private final String desc;

    Sex(String desc) {
        this.desc = desc;
    }

    public String getSex() {
        return desc;
    }
}
