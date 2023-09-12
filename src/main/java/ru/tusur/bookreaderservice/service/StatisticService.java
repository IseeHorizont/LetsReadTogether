package ru.tusur.bookreaderservice.service;

public interface StatisticService {

    Long getAllRegisteredClientAmount();

    Long getAllActiveClientAmount();

    Long getAllEventAmount();

    Long getDeletedEventAmount();
}
