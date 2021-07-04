package com.github.vladioeroonda.testtask.service.impl;

import com.github.vladioeroonda.testtask.exception.DirectoryCreationException;
import com.github.vladioeroonda.testtask.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class DownloadServiceImpl implements DownloadService {

    @Value("${savingfile.name}")
    private String savingName;
    @Value("${savefolder.path}")
    private String folderPath;


    @Override
    public void download(String url) throws IOException{
        URL target = new URL(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(target.openStream()));

        File saveFolder = createDirectory();
        String pageFilePath =  saveFolder.getPath();
        File savedPageFile = new File(
                pageFilePath
                        + File.separator
                        + savingName
        );

        log.debug("Downloaded page file will be saved as: " + savedPageFile.getPath());

        BufferedWriter writer = new BufferedWriter(new FileWriter(savedPageFile));

        String tempString;

        while ((tempString = reader.readLine()) != null) {
            writer.write(tempString);
        }

        log.debug(savedPageFile.exists() ? "File was successfully created" : "File was not created");

        log.info("File size: " + savedPageFile.length() + " bytes");

        reader.close();
        writer.close();
    }

    private File createDirectory() {

        String originalFolderName = generateNameForFolder();

        log.debug("Generated name for folder: " + originalFolderName);

        File saveDirectory = new File(folderPath + File.separator + originalFolderName);
        boolean isDirectoryCreated = saveDirectory.mkdirs();

        log.debug(isDirectoryCreated? "Created directory path: " + saveDirectory : "Directory was not created");

        if (!isDirectoryCreated) {
            log.error("Directory was not created");
            throw new DirectoryCreationException("Directory was not created");
        }

        return saveDirectory;
    }

    private String generateNameForFolder(){
        LocalDateTime thatMoment = LocalDateTime.now();
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return thatMoment.format(formatter);
    }
}
