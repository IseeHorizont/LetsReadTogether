package ru.tusur.bookreaderservice.service;

import java.util.List;

public interface TagService {

    List<String> getMostPopularEventTagsByLimit(long tagsLimit);
}
