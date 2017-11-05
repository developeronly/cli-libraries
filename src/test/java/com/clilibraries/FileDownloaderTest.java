package com.clilibraries;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class FileDownloaderTest {

    @Test
    public void fileDownloaderShouldAcceptUrlAndLoactionValues() {
        String url = "dummy url";
        String location = "dummy location";
        FileDownloader fileDownloader = new FileDownloader(url, location);
        Assert.assertTrue(Objects.nonNull(fileDownloader));
    }


}
