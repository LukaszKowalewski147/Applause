package com.example.applause;

public class JSONClap <T> {

    private final SessionType sessionType;
    private final T avgParameter;
    private T maxParameter;

    public JSONClap(SessionType sessionType, T avgParameter, T maxParameter) {
        this.sessionType = sessionType;
        this.avgParameter = avgParameter;
        this.maxParameter = maxParameter;
    }

    public JSONClap(SessionType sessionType, T avgParameter) {
        this.sessionType = sessionType;
        this.avgParameter = avgParameter;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public T getAvgParameter() {
        return avgParameter;
    }

    public T getMaxParameter() {
        return maxParameter;
    }
}
