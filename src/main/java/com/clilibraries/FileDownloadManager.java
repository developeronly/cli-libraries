package com.clilibraries;

import com.clilibraries.validators.FileDownloaderParameterValidator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloadManager {

    public static final int BUFFER = 1024;
    private final String url;
    private final String location;

    public FileDownloadManager(String url, String location) {
        FileDownloaderParameterValidator.isValid(url, location);
        this.url = url;
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public String getLocation() {
        return location;
    }

    public String extractFileNameFromUrl() {
        return getUrl().substring(getUrl().lastIndexOf("/") + 1, getUrl().length());
    }

    public boolean download() {
        try {
            URL url = new URL(getUrl());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(location + File.separator +
                        extractFileNameFromUrl());
                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();
                return true;
            }
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
