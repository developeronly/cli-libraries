package com.clilibraries.validators;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownloaderParameterValidator {

    private FileDownloaderParameterValidator() {
    }

    public static void isValid(String urlToDownloadFileFrom, String locationToSaveDownloadedFie) {
        System.out.println("Validating parameters. Please wait...");
        validateUrl(urlToDownloadFileFrom);
        validateLocation(locationToSaveDownloadedFie);
        System.out.println("All Good. Downloading will start in a moment.");
    }

    private static void validateLocation(String locationToSaveDownloadedFie) {
        if (isLocationBlank(locationToSaveDownloadedFie))
            throw new IllegalArgumentException("File location can't be empty. Please provide location to save file.");
        if (!isLocationExists(locationToSaveDownloadedFie))
            throw new IllegalArgumentException("Please provide valid location to save file.");
    }

    private static void validateUrl(String urlToDownloadFileFrom) {
        if (isUrlBlank(urlToDownloadFileFrom))
            throw new IllegalArgumentException("URL can't be empty. Please provide valid URL.");
        if (isUrlMalformed(urlToDownloadFileFrom))
            throw new IllegalArgumentException("Please provide valid URL");
    }

    private static boolean isLocationExists(String locationToSaveDownloadedFie) {
        File file = new File(locationToSaveDownloadedFie);
        if (file.exists()) return true;
        if (file.isDirectory()) return true;
        return false;
    }

    private static boolean isLocationBlank(String locationToSaveDownloadedFie) {
        return StringUtils.isBlank(locationToSaveDownloadedFie);
    }

    private static boolean isUrlMalformed(String urlToDownloadFileFrom) {
        if (!isUrlBlank(urlToDownloadFileFrom)) {
            try {
                URL url = new URL(urlToDownloadFileFrom);
            } catch (MalformedURLException e) {
                return true;
            }
        }
        return false;
    }

    private static boolean isUrlBlank(String urlToDownloadFileFrom) {
        return StringUtils.isBlank(urlToDownloadFileFrom);
    }


}
