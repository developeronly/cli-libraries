package com.clilibraries;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileDownloaderTest {

    @Test
    public void fileDownloaderShouldAcceptUrlAndLocationValues() {
        String url = "http://dynamodb-local.s3-website-us-west-2.amazonaws.com/dynamodb_local_2016-05-17.zip";
        String location = "/home/dheeraj/Downloads";
        FileDownloader fileDownloader = new FileDownloader(url, location);
        assertTrue(Objects.nonNull(fileDownloader));
    }

    @Test
    public void verifyExistenceOfUrlAndLocationValues() {
        String url = "http://dynamodb-local.s3-website-us-west-2.amazonaws.com/dynamodb_local_2016-05-17.zip";
        String location = "/home/dheeraj/Downloads";
        FileDownloader fileDownloader = new FileDownloader(url, location);
        assertEquals(url, fileDownloader.getUrl());
        assertEquals(location, fileDownloader.getLocation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyWhetherUrlIsEmptyOrNot() {
        String url = "";
        String location = "/home/dheeraj/Downloads";
        FileDownloader fileDownloader = new FileDownloader(url, location);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyWhetherUrlIsValidOrNot() {
        String url = "dummy url";
        String location = "/home/dheeraj/Downloads";
        FileDownloader fileDownloader = new FileDownloader(url, location);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyWhetherLocationIsEmptyOrNot() {
        String url = "http://dynamodb-local.s3-website-us-west-2.amazonaws.com/dynamodb_local_2016-05-17.zip";
        String location = "";
        FileDownloader fileDownloader = new FileDownloader(url, location);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyWhetherLocationExistsOrNot() {
        String url = "http://dynamodb-local.s3-website-us-west-2.amazonaws.com/dynamodb_local_2016-05-17.zip";
        String location = "dummy url";
        FileDownloader fileDownloader = new FileDownloader(url, location);
    }

}
