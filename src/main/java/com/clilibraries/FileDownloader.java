package com.clilibraries;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownloader {

    private final String url;
    private final String location;

    public FileDownloader(String url, String location) {
        isValidParameters(url, location);
        this.url = url;
        this.location = location;
    }

    private void isValidParameters(String urlToDownloadFileFrom, String locationToSaveDownloadedFie) {
        validateUrl(urlToDownloadFileFrom);
        validateLocation(locationToSaveDownloadedFie);
    }

    private void validateLocation(String locationToSaveDownloadedFie) {
        if (isLocationBlank(locationToSaveDownloadedFie))
            throw new IllegalArgumentException("File location can't be empty. Please provide location to save file.");
        if (!isLocationExists(locationToSaveDownloadedFie))
            throw new IllegalArgumentException("Please provide valid location to save file.");
    }

    private void validateUrl(String urlToDownloadFileFrom) {
        if (isUrlBlank(urlToDownloadFileFrom))
            throw new IllegalArgumentException("URL can't be empty. Please provide valid URL.");
        if (isUrlMalformed(urlToDownloadFileFrom))
            throw new IllegalArgumentException("Please provide valid URL");
    }

    private boolean isLocationExists(String locationToSaveDownloadedFie) {
        File file = new File(locationToSaveDownloadedFie);
        if (file.exists()) return true;
        if (file.isDirectory()) return true;
        return false;
    }

    private boolean isLocationBlank(String locationToSaveDownloadedFie) {
        return StringUtils.isBlank(locationToSaveDownloadedFie);
    }

    private boolean isUrlMalformed(String urlToDownloadFileFrom) {
        if (!isUrlBlank(urlToDownloadFileFrom)) {
            try {
                URL url = new URL(urlToDownloadFileFrom);
            } catch (MalformedURLException e) {
                return true;
            }
        }
        return false;
    }

    private boolean isUrlBlank(String urlToDownloadFileFrom) {
        return StringUtils.isBlank(urlToDownloadFileFrom);
    }

    public String getUrl() {
        return url;
    }

    public String getLocation() {
        return location;
    }

}
