package com.clilibraries;

public class FileNameExtractor {

    private FileNameExtractor() {
    }

    public static String extractFileNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1, url.length());
    }

}
