package com.clilibraries;

import com.clilibraries.validators.FileDownloaderParameterValidator;

public class FileDownloader {

    private final String url;
    private final String location;

    public FileDownloader(String url, String location) {
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

}
