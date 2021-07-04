package com.github.vladioeroonda.testtask.service;

import java.io.IOException;

public interface DownloadService {
    void download(String url) throws IOException;
}
