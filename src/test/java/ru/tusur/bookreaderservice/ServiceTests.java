package ru.tusur.bookreaderservice;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import ru.tusur.bookreaderservice.service.impl.ClientServiceTest;
import ru.tusur.bookreaderservice.service.impl.EventRatingServiceTest;
import ru.tusur.bookreaderservice.service.impl.EventServiceTest;

@Suite
@SuiteDisplayName("Service layer tests")
@SelectClasses({ClientServiceTest.class,
                EventServiceTest.class,
                EventRatingServiceTest.class})
public class ServiceTests {
}
