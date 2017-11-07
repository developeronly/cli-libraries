package com.clilibraries;

import me.tongfei.progressbar.ProgressBar;

import static com.clilibraries.DownloadStatus.COMPLETED;

public class App {

    public static final int MAX = 100;
    public static final int TOTAL_NUMBER_OF_PARAMETERS = 2;

    public static FileDownloadManager getManager(String[] arguments) {
        String url = arguments[0];
        String location = arguments[1];
        return new FileDownloadManager(url, location);
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != TOTAL_NUMBER_OF_PARAMETERS) {
            System.out.println("Please enter valid inputs.");
            return;
        }
        FileDownloadManager fileDownloadManager = getManager(args);
        fileDownloadManager.showHelp();

        ProgressBar pb = new ProgressBar("Download", MAX);
        pb.start();

        fileDownloadManager.download();
        pb.step();

        while (COMPLETED != fileDownloadManager.getStatus()) {
            pb.stepTo(fileDownloadManager.progress());
            Thread.sleep(1000);
        }
        pb.stepTo(MAX);
        pb.stop();
        System.out.println();
        System.out.println("Downloading completed.");

    }
}
