package com.clilibraries;

public interface Downloader {
    void start();

    DownloadStatus status();

    int getProgressInPercentage();

    void pause();

    void resume();
}
