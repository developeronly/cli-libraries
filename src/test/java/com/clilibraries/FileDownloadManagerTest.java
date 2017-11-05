package com.clilibraries;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileDownloadManagerTest {

    private static String location;
    private String url = "http://dynamodb-local.s3-website-us-west-2.amazonaws.com/dynamodb_local_2016-05-17.zip";
    private String smallFileUrl = "http://www.sample-videos.com/csv/Sample-Spreadsheet-10-rows.csv";

    @BeforeClass
    public static void init() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource("").getFile());
        File locationToDownloadFile = new File(file.getAbsolutePath() + File.separator + "download");
        locationToDownloadFile.mkdir();
        location = locationToDownloadFile.getAbsolutePath();
    }

    @AfterClass
    public static void destroy() {
        File file = new File(location);
        file.delete();
    }

    @Test
    public void fileDownloaderShouldAcceptUrlAndLocationValues() {
        FileDownloadManager fileDownloadManager = new FileDownloadManager(url, location);
        assertTrue(Objects.nonNull(fileDownloadManager));
    }

    @Test
    public void verifyExistenceOfUrlAndLocationValues() {
        FileDownloadManager fileDownloadManager = new FileDownloadManager(url, location);
        assertEquals(url, fileDownloadManager.getUrl());
        assertEquals(location, fileDownloadManager.getLocation());
    }

    @Test
    public void verifyExtractingFileNameFromUrl() {
        FileDownloadManager fileDownloadManager = new FileDownloadManager(url, location);
        assertEquals("dynamodb_local_2016-05-17.zip", fileDownloadManager.extractFileNameFromUrl());
    }


    @Test
    public void tryDownloadingAFile() {
        String url = "http://www.sample-videos.com/csv/Sample-Spreadsheet-10-rows.csv";
        FileDownloadManager fileDownloadManager = new FileDownloadManager(smallFileUrl, location);
        assertTrue(fileDownloadManager.download());
    }

    @Test
    public void checkExistenceOfDownloadedFile() {
        FileDownloadManager fileDownloadManager = new FileDownloadManager(smallFileUrl, location);
        File downloadedFile = new File(location + File.separator + fileDownloadManager.extractFileNameFromUrl());
        assertTrue(fileDownloadManager.download());
        assertTrue(downloadedFile.exists());
    }


}
