package com.clilibraries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.clilibraries.DownloadStatus.COMPLETED;
import static com.clilibraries.DownloadStatus.DOWNLOADING;
import static com.clilibraries.DownloadStatus.IDLE;

public class FileDownloader implements Downloader {

    public static final int BUFFER = 1024;

    private final String url;
    private final String location;

    private DownloadStatus currentStatus;

    public FileDownloader(String url, String location) {
        this.url = url;
        this.location = location;
        this.currentStatus = IDLE;
    }

    @Override
    public void start() {
        try {
            currentStatus = DOWNLOADING;
            URL url = new URL(getUrl());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(location + File.separator +
                        FileNameExtractor.extractFileNameFromUrl(getUrl()));
                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        currentStatus = COMPLETED;
    }

    @Override
    public DownloadStatus status() {
        return currentStatus;
    }

    public String getUrl() {
        return url;
    }

    public String getLocation() {
        return location;
    }
}
