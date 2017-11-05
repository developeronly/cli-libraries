package com.clilibraries;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownloader {

    private final String url;
    private final String location;

    public FileDownloader(String url, String location) {
        if (isUrlBlank(url))
            throw new IllegalArgumentException("URL can't be empty. Please provide valid URL.");
        if (isUrlMalformed(url))
            throw new IllegalArgumentException("Please provide valid URL");
        if (isLocationBlank(location))
            throw new IllegalArgumentException("File location can't be empty. Please provide location to save file.");
        if (!isLocationExists(location))
            throw new IllegalArgumentException("Please provide valid location to save file.");

        this.url = url;
        this.location = location;
    }

    private boolean isLocationExists(String locationToSaveFile) {
        File file = new File(locationToSaveFile);
        if (file.exists()) return true;
        if (file.isDirectory()) return true;
        return false;
    }

    private boolean isLocationBlank(String locationToSaveFile) {
        return StringUtils.isBlank(locationToSaveFile);
    }

    private boolean isUrlMalformed(String urlOfFile) {
        if (!isUrlBlank(urlOfFile)) {
            try {
                URL url = new URL(urlOfFile);
            } catch (MalformedURLException e) {
                return true;
            }
        }
        return false;
    }

    private boolean isUrlBlank(String urlOfFile) {
        return StringUtils.isBlank(urlOfFile);
    }

    public String getUrl() {
        return url;
    }

    public String getLocation() {
        return location;
    }

}
