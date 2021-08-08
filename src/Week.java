public enum Week {
//    월,화,수,목,금,토,일

    SUNDAY("일"),
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토");

    private String desc;

    Week(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
