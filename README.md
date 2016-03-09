# AKImageDownloaderDemo
It's a demo project for a sample library AKImageDownloader, its an Android library to download images.

# Installation and Usage
  
  1) Import akimagedownloader-release.aar
  
  2) Add dependency: compile project(':akimagedownloader')
  
  3) Implement OnDownload interface and its methods: onImageDownloadComplete() and onDownloadError()
  
  4)create an instance of downloader class(new ImageDownloader(DemoActivity.this).startDownload(Url);).
