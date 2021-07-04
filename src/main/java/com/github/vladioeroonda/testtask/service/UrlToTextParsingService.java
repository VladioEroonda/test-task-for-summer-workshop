package com.github.vladioeroonda.testtask.service;

import java.io.IOException;

public interface UrlToTextParsingService {
    String parse(String url) throws IOException;
}
