package com.sz.common;

public enum DownloadStatus {
    Remote_File_Noexist,
    Local_Bigger_Remote,
    Download_From_Break_Success,
    Download_From_Break_Failed,
    Download_New_Success,
    Download_New_Failed;

    private DownloadStatus() {
    }
}