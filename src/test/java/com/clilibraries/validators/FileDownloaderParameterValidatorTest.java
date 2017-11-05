package com.clilibraries.validators;

import com.clilibraries.FileDownloadManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

public class FileDownloaderParameterValidatorTest {

    private static String location;
    private String url = "http://dynamodb-local.s3-website-us-west-2.amazonaws.com/dynamodb_local_2016-05-17.zip";

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

    @Test(expected = IllegalArgumentException.class)
    public void verifyWhetherUrlIsEmptyOrNot() {
        String url = "";
        FileDownloadManager fileDownloadManager = new FileDownloadManager(url, location);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyWhetherUrlIsValidOrNot() {
        String url = "dummy url";
        FileDownloadManager fileDownloadManager = new FileDownloadManager(url, location);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyWhetherLocationIsEmptyOrNot() {
        String location = "";
        FileDownloadManager fileDownloadManager = new FileDownloadManager(url, location);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verifyWhetherLocationExistsOrNot() {
        String location = "dummy url";
        FileDownloadManager fileDownloadManager = new FileDownloadManager(url, location);
    }

}
