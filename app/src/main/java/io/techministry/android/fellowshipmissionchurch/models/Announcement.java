package io.techministry.android.fellowshipmissionchurch.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Akinsete on 2/19/17.
 */

public class Announcement {
    String photo;
    String title;
    String description;
    String type;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String picture) {
        this.photo = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("photo", photo);
        result.put("title", title);
        result.put("timestamp", ServerValue.TIMESTAMP);
        result.put("description", description);
        result.put("type", type);
        return result;
    }

}
