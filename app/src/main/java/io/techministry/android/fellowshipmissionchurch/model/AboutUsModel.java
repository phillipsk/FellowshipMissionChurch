package io.techministry.android.fellowshipmissionchurch.model;

/**
 * Created by kevinphillips on 6/20/17.
 */

public class AboutUsModel {
    private String title;
    private String details;

    public AboutUsModel(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
