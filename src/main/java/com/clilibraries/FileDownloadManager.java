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

    public void pause() {
        downloader.pause();
    }

    public void resume() {
        downloader.resume();
    }

    public void stop() {
        downloader.stop();
    }

    public void showHelp() {
        System.out.println("Press 'p' to pause the downloading.");
        System.out.println("Press 'r' to resume the downloading.");
        System.out.println("Press 's' to stop the downloading.");
    }

}
