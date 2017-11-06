package com.clilibraries;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.clilibraries.DownloadStatus.COMPLETED;
import static com.clilibraries.DownloadStatus.IDLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class FileDownloadManagerTest {

    private static String location;
    private String largeSizeFileUrl = "http://dynamodb-local.s3-website-us-west-2.amazonaws" +
            ".com/dynamodb_local_2016-05-17.zip";
    private String smallSizeFileUrl = "http://www.sample-videos.com/csv/Sample-Spreadsheet-10-rows.csv";
    private String mediumSizeFileUrl = "http://speedtest.ftp.otenet.gr/files/test10Mb.db";

    @BeforeClass
    public static void init() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource("").getFile());
        File locationToDownloadFile = new File(file.getAbsolutePath() + File.separator + "download");
        locationToDownloadFile.mkdir();
        location = locationToDownloadFile.getAbsolutePath();
    }

    @AfterClass
    public static void destroy() throws IOException {
        File directoryToBeDeleted = new File(location);
        Files.walk(directoryToBeDeleted.toPath()).map(Path::toFile).forEach(File::delete);
        directoryToBeDeleted.delete();
    }

    @Test
    public void fileDownloaderShouldAcceptUrlAndLocationValues() {
        FileDownloadManager fileDownloadManager = new FileDownloadManager(largeSizeFileUrl, location);
        assertTrue(Objects.nonNull(fileDownloadManager));
    }

    @Test
    public void verifyExistenceOfUrlAndLocationValues() {
        FileDownloadManager fileDownloadManager = new FileDownloadManager(largeSizeFileUrl, location);
        assertEquals(largeSizeFileUrl, fileDownloadManager.getUrl());
        assertEquals(location, fileDownloadManager.getLocation());
    }


    @Test
    public void tryDownloadingAFile() throws InterruptedException {
        FileDownloadManager fileDownloadManager = new FileDownloadManager(smallSizeFileUrl, location);
        DownloadStatus beforeDownloadingStatus = fileDownloadManager.getStatus();
        assertEquals(beforeDownloadingStatus, IDLE);
        fileDownloadManager.download();
        while (COMPLETED != fileDownloadManager.getStatus()) {
            Thread.sleep(200);
        }
        DownloadStatus statusAfterStatingDownloading = fileDownloadManager.getStatus();
        assertNotEquals(statusAfterStatingDownloading, IDLE);
    }

    @Test
    public void checkExistenceOfDownloadedFile() throws InterruptedException {
        FileDownloadManager fileDownloadManager = new FileDownloadManager(smallSizeFileUrl, location);
        File downloadedFile = new File(location + File.separator + FileNameExtractor.extractFileNameFromUrl
                (smallSizeFileUrl));
        fileDownloadManager.download();
        while (COMPLETED != fileDownloadManager.getStatus()) {
            Thread.sleep(200);
        }
        assertTrue(downloadedFile.exists());
    }

    @Test
    public void verifyProgressWhileDownloadingAFile() throws InterruptedException {
        FileDownloadManager fileDownloadManager = new FileDownloadManager(mediumSizeFileUrl, location);
        File downloadedFile = new File(location + File.separator +
                FileNameExtractor.extractFileNameFromUrl(mediumSizeFileUrl));
        List<Integer> progress = new ArrayList<>();
        int initialProgress = fileDownloadManager.progress();
        System.out.println("Initial Progress: " + initialProgress);
        progress.add(initialProgress);
        fileDownloadManager.download();
        while (COMPLETED != fileDownloadManager.getStatus()) {
            Thread.sleep(200);
            System.out.println("Progress: " + fileDownloadManager.progress());
            progress.add(fileDownloadManager.progress());
        }
        progress.stream().forEach(System.out::println);
        assertTrue(downloadedFile.exists());
    }

    @Test
    public void verifyPausingDownloadingFile() throws InterruptedException {
        FileDownloadManager fileDownloadManager = new FileDownloadManager(mediumSizeFileUrl, location);
        File downloadedFile = new File(location + File.separator +
                FileNameExtractor.extractFileNameFromUrl(mediumSizeFileUrl));
        fileDownloadManager.download();
        while (IDLE == fileDownloadManager.getStatus()) {
            Thread.sleep(200);
        }
        fileDownloadManager.pause();
        int progressAfterPause = fileDownloadManager.progress();
        for (int index = 0; index < 10; index++) {
            Thread.sleep(500);
            assertEquals(progressAfterPause, fileDownloadManager.progress());
        }
        fileDownloadManager.resume();
        while (COMPLETED != fileDownloadManager.getStatus()) {
            Thread.sleep(1000);
        }
        assertEquals(progressAfterPause, fileDownloadManager.progress());
        assertTrue(downloadedFile.exists());

    }

}
