package com.github.vladioeroonda.testtask.service.impl;

import com.github.vladioeroonda.testtask.exception.DownloadException;
import com.github.vladioeroonda.testtask.exception.ParseException;
import com.github.vladioeroonda.testtask.model.TargetPage;
import com.github.vladioeroonda.testtask.service.DownloadService;
import com.github.vladioeroonda.testtask.service.TargetPageService;
import com.github.vladioeroonda.testtask.service.TextSplitterService;
import com.github.vladioeroonda.testtask.service.UrlToTextParsingService;
import com.github.vladioeroonda.testtask.service.WordsCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class TargetPageServiceImpl implements TargetPageService {

    private final DownloadService downloadService;
    private final TextSplitterService textSplitterService;
    private final UrlToTextParsingService urlToTextParsingService;
    private final WordsCountService wordsCountService;

    public TargetPageServiceImpl(DownloadService downloadService,
                                 TextSplitterService textSplitterService,
                                 UrlToTextParsingService urlToTextParsingService,
                                 WordsCountService wordsCountService) {
        this.downloadService = downloadService;
        this.textSplitterService = textSplitterService;
        this.urlToTextParsingService = urlToTextParsingService;
        this.wordsCountService = wordsCountService;
    }

    @Override
    public TargetPage getResult(String url) {

        try {
            downloadService.download(url);
        } catch (IOException e){
            String errorMessage = "Failed to download from" + url;
            log.error(e.getMessage());
            throw new DownloadException(errorMessage, e);
        }

        String pureText="";
        try {
            pureText = urlToTextParsingService.parse(url);
        } catch (IOException e){
            String errorMessage = "Failed to parse from" + url;
            log.error(e.getMessage());
            throw new ParseException(errorMessage, e);
        }

        String[] wordsArray = textSplitterService.split(pureText);

        Map<String, Integer> analized = wordsCountService.analize(wordsArray);
        log.debug("Size of map:" + analized.size());

        TargetPage result = new TargetPage();
        result.setCountedWords(analized);

        return result;
    }
}
