package io.techministry.android.fellowshipmissionchurch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @SerializedName("passages")
    @Expose
    private List<Passage> passages = new ArrayList<>();
    @SerializedName("type")
    @Expose
    private String type;

    /**
     *
     * @return
     * The passages
     */
    public List<Passage> getPassages() {
        return passages;
    }

    /**
     *
     * @param passages
     * The passages
     */
    public void setPassages(List<Passage> passages) {
        this.passages = passages;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

}