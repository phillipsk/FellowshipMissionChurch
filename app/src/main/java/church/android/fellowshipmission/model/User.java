package church.android.fellowshipmission.model;

import java.util.HashMap;

/**
 * Created by kevinphillips on 9/10/17.
 */

public class User {
    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    String full_name,email,photo;

    public HashMap<String,Object> toMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("full_name",full_name);
        map.put("email",email);
        map.put("photo",photo);

        return map;
    }

}
