package com.clilibraries;

import com.clilibraries.validators.FileDownloaderParameterValidator;

public class FileDownloadManager {

    private final String url;
    private final String location;
    private final Downloader downloader;

    public FileDownloadManager(String url, String location) {
        FileDownloaderParameterValidator.isValid(url, location);
        this.url = url;
        this.location = location;
        this.downloader = new FileDownloader(url, location);
    }

    public String getUrl() {
        return url;
    }

    public String getLocation() {
        return location;
    }

    public void download() {
        downloader.start();
    }


    public DownloadStatus getStatus() {
        return downloader.status();
    }

    public int progress() {
        return downloader.getProgressInPercentage();
    }
}
