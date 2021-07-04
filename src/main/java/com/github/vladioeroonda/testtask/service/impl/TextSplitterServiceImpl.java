package com.github.vladioeroonda.testtask.service.impl;

import com.github.vladioeroonda.testtask.service.TextSplitterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TextSplitterServiceImpl implements TextSplitterService {

    @Value("${splitter.symbols}")
    private String delimiter;

    @Override
    public String[] split(String text) {
            return text.split(delimiter);
    }
}
