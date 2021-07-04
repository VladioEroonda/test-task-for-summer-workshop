package com.github.vladioeroonda.testtask.service.impl;

import com.github.vladioeroonda.testtask.service.WordsCountService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class WordsCountServiceImpl implements WordsCountService {
    @Override
    public Map<String, Integer> analize(String[] wordsArray) {

        Map<String, Integer> result = new HashMap<>();
        Arrays.sort(wordsArray); //Сортируем в нат порядке, чтоб легче искать

        int tempCounter = 1;

        for (int i = 0; i < wordsArray.length; i++) {
            if (wordsArray[i].length() > 0) {
                if (i == wordsArray.length - 1) {
                    result.put(wordsArray[i], tempCounter);
                    break;
                }

                if (wordsArray[i].equals(wordsArray[i + 1])) {
                    ++tempCounter;
                } else {
                    result.put(wordsArray[i], tempCounter);
                    tempCounter = 1;
                }
            }
        }

        return result;
    }
}
