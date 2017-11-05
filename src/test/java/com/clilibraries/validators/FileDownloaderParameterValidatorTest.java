package com.clilibraries.validators;

import com.clilibraries.FileDownloader;
import org.junit.Test;

public class FileDownloaderParameterValidatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void verifyWhetherUrlIsEmptyOrNot() {
        String url = "";
        String location = "/";
        FileDownloader fileDownloader = new FileDownloader(url, location);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyWhetherUrlIsValidOrNot() {
        String url = "dummy url";
        String location = "/";
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
