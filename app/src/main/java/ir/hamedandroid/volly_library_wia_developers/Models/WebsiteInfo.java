package ir.hamedandroid.volly_library_wia_developers.Models;

/**
 * Created by hamed on 3/2/18.
 */

public class WebsiteInfo {

    private String webSite;
    private String title;

    public WebsiteInfo(String webSite, String title) {
        this.webSite = webSite;
        this.title = title;
    }

    public WebsiteInfo() {
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String toString() {
        return webSite + " Is " + title + " Website";
    }
}
