package com.clilibraries;

import static com.clilibraries.DownloadStatus.COMPLETED;

public class App {

    public static FileDownloadManager getManager(String[] arguments) {
        String url = arguments[0];
        String location = arguments[1];
        return new FileDownloadManager(url, location);
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            System.out.println("Please enter valid inputs.");
            return;
        }
        FileDownloadManager fileDownloadManager = getManager(args);
        fileDownloadManager.showHelp();
        int currentProgress = 0;
        int previousProgress = 0;
        fileDownloadManager.download();

        System.out.print(currentProgress + " ");
        while (COMPLETED != fileDownloadManager.getStatus()) {
            currentProgress = fileDownloadManager.progress();
            if (currentProgress != previousProgress) {
                System.out.print(currentProgress + " ");
                previousProgress = currentProgress;
            }
            Thread.sleep(1000);
        }
    }
}
