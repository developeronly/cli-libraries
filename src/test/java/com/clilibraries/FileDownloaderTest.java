package com.clilibraries;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileDownloaderTest {

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
        FileDownloader fileDownloader = new FileDownloader(url, location);
        assertTrue(Objects.nonNull(fileDownloader));
    }

    @Test
    public void verifyExistenceOfUrlAndLocationValues() {
        FileDownloader fileDownloader = new FileDownloader(url, location);
        assertEquals(url, fileDownloader.getUrl());
        assertEquals(location, fileDownloader.getLocation());
    }

    @Test
    public void verifyExtractingFileNameFromUrl() {
        FileDownloader fileDownloader = new FileDownloader(url, location);
        assertEquals("dynamodb_local_2016-05-17.zip", fileDownloader.extractFileNameFromUrl());
    }


    @Test
    public void tryDownloadingAFile() {
        String url = "http://www.sample-videos.com/csv/Sample-Spreadsheet-10-rows.csv";
        FileDownloader fileDownloader = new FileDownloader(smallFileUrl, location);
        assertTrue(fileDownloader.download());
    }

    @Test
    public void checkExistenceOfDownloadedFile() {
        FileDownloader fileDownloader = new FileDownloader(smallFileUrl, location);
        File downloadedFile = new File(location + File.separator + fileDownloader.extractFileNameFromUrl());
        assertTrue(fileDownloader.download());
        assertTrue(downloadedFile.exists());
    }

}
