package org.jdev.service.CashProxy;

import java.time.Instant;

public class TimeStamp<T> {
    private Instant timestamp;
    private T data;

    public TimeStamp(T data) {
        this.timestamp = Instant.now();
        this.data = data;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Метод для обновления временной метки
    public void refreshTimestamp() {
        this.timestamp = Instant.now();
    }
}