package church.android.fellowshipmission.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("fums_js_include") @Expose private String fumsJsInclude;
    @SerializedName("fums_noscript") @Expose private String fumsNoscript;
    @SerializedName("fums_js") @Expose private String fumsJs;
    @SerializedName("fums_tid") @Expose private String fumsTid;
    @SerializedName("fums") @Expose private String fums;

    /**
     * @return The fumsJsInclude
     */
    public String getFumsJsInclude() {
        return fumsJsInclude;
    }

    /**
     * @param fumsJsInclude The fums_js_include
     */
    public void setFumsJsInclude(String fumsJsInclude) {
        this.fumsJsInclude = fumsJsInclude;
    }

    /**
     * @return The fumsNoscript
     */
    public String getFumsNoscript() {
        return fumsNoscript;
    }

    /**
     * @param fumsNoscript The fums_noscript
     */
    public void setFumsNoscript(String fumsNoscript) {
        this.fumsNoscript = fumsNoscript;
    }

    /**
     * @return The fumsJs
     */
    public String getFumsJs() {
        return fumsJs;
    }

    /**
     * @param fumsJs The fums_js
     */
    public void setFumsJs(String fumsJs) {
        this.fumsJs = fumsJs;
    }

    /**
     * @return The fumsTid
     */
    public String getFumsTid() {
        return fumsTid;
    }

    /**
     * @param fumsTid The fums_tid
     */
    public void setFumsTid(String fumsTid) {
        this.fumsTid = fumsTid;
    }

    /**
     * @return The fums
     */
    public String getFums() {
        return fums;
    }

    /**
     * @param fums The fums
     */
    public void setFums(String fums) {
        this.fums = fums;
    }
}