package com.github.vladioeroonda.testtask.service.impl;

import com.github.vladioeroonda.testtask.exception.ParseException;
import com.github.vladioeroonda.testtask.service.UrlToTextParsingService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class UrlToTextParsingServiceImpl implements UrlToTextParsingService {
    @Override
    public String parse(String url) throws IOException {

        Document document = Jsoup.connect(url).get();

        if (document == null) {
            log.error("Unable to parse by url: " + url);
            throw new ParseException("Unable to parse by url: " + url);
        }

        return document.body().text();
    }
}
