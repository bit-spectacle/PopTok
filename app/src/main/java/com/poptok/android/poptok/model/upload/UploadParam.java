package com.poptok.android.poptok.model.upload;

import org.springframework.core.io.FileSystemResource;

/**
 * Created by BIT on 2018-01-26.
 */

public class UploadParam {
    String destination;
    String pk;
    FileSystemResource image;

    public UploadParam(String destination, String pk, FileSystemResource image) {
        this.destination = destination;
        this.pk = pk;
        this.image = image;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public FileSystemResource getImage() {
        return image;
    }

    public void setImage(FileSystemResource image) {
        this.image = image;
    }
}
