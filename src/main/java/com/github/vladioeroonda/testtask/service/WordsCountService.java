package com.github.vladioeroonda.testtask.service;

import java.util.Map;

public interface WordsCountService {
    Map<String, Integer> analize(String[] wordsArray);
}
