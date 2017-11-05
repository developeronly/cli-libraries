package com.clilibraries;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileDownloaderTest {

    @Test
    public void fileDownloaderShouldAcceptUrlAndLocationValues() {
        String url = "http://dynamodb-local.s3-website-us-west-2.amazonaws.com/dynamodb_local_2016-05-17.zip";
        String location = "/";
        FileDownloader fileDownloader = new FileDownloader(url, location);
        assertTrue(Objects.nonNull(fileDownloader));
    }

    @Test
    public void verifyExistenceOfUrlAndLocationValues() {
        String url = "http://dynamodb-local.s3-website-us-west-2.amazonaws.com/dynamodb_local_2016-05-17.zip";
        String location = "/";
        FileDownloader fileDownloader = new FileDownloader(url, location);
        assertEquals(url, fileDownloader.getUrl());
        assertEquals(location, fileDownloader.getLocation());
    }


}
