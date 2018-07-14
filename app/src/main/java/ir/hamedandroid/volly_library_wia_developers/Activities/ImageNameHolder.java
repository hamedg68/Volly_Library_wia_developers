package ir.hamedandroid.volly_library_wia_developers.Activities;

/**
 * Created by hamed on 6/23/18.
 */

public class ImageNameHolder {

    private String url;
    private String picId;
    private int tempId;
    private boolean isUploaded;

    public ImageNameHolder(int id, String url) {
        this.tempId = id;
        this.url = url;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTempId() {
        return tempId;
    }

    public void setTempId(int tempId) {
        this.tempId = tempId;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }
}
