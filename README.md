[![Build Status](https://travis-ci.org/developeronly/cli-libraries.svg?branch=develop)](https://travis-ci.org/developeronly/cli-libraries)

cli-libraries - For downloading files from internet

- [DESCRIPTION](#description)
- [INSTALLATION](#installation)

# DESCRIPTION
**cli-libraries** is a command-line program to download files from internet. It requires the JDK, version 8, and it is not platform specific. It should work on your Unix box, on Windows or on Mac OS X. It is released to the public domain, which means you can modify it, redistribute it or use it however you like.

    youtube-dl [URL] [Location]

# INSTALLATION

To install it you will need to have:

 - `Maven Build Tool`
 - `Java / JDK 8`

Clone the project, get inside it.
For generating a jar you will need to hit following command

    mvn clean install
    mvn package

It will take some time, to minimize time you can skip the test cases by hitting following commands

    mvn clean install -DskipTests
    mvn package -DskipTests


Your build will get created inside target directory with name

    file-downloader-1.0-jar-with-dependencies.jar

To run this jar you will need to pass two arguments

    First argument : File URL from where you want to download a file
    Second argument : Location where you want to save downloaded file

You can use following downloading links:

    100kb: http://speedtest.ftp.otenet.gr/files/test100k.db
    10Mb: http://speedtest.ftp.otenet.gr/files/test10Mb.db
    100Mb: http://speedtest.ftp.otenet.gr/files/test100Mb.db

You can run this generated jar using following command with valid arguments:

    java -jar path/to/the/generated/jar/file fileUrl location

The file will get downloaded and on successful download console will print the message likewise.
