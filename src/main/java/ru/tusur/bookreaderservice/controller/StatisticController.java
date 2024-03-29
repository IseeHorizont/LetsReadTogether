package ru.tusur.bookreaderservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tusur.bookreaderservice.service.StatisticService;
import ru.tusur.bookreaderservice.service.TagService;


@Slf4j
@Validated
@RestController
@RequestMapping(value = "/api/v1/statistic/")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping(value = "allusers")
    public Long getAllRegisteredClientAmount() {
        return statisticService.getAllRegisteredClientAmount();
    }

    @GetMapping(value = "activeusers")
    public Long getAllActiveClientAmount() {
        return statisticService.getAllActiveClientAmount();
    }

    @GetMapping(value = "allevents")
    public Long getAllEventAmount() {
        return statisticService.getAllEventAmount();
    }

    @GetMapping(value = "deletedevents")
    public Long getDeletedEventAmount() {
        return statisticService.getDeletedEventAmount();
    }
}
