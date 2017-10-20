package church.android.fellowshipmission.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passage {

    @SerializedName("copyright") @Expose private String copyright;
    @SerializedName("text") @Expose private String text;
    @SerializedName("end_verse_id") @Expose private String endVerseId;
    @SerializedName("version") @Expose private String version;
    @SerializedName("path") @Expose private String path;
    @SerializedName("version_abbreviation") @Expose private String versionAbbreviation;
    @SerializedName("start_verse_id") @Expose private String startVerseId;
    @SerializedName("display") @Expose private String display;

    /**
     * @return The copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * @param copyright The copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return The endVerseId
     */
    public String getEndVerseId() {
        return endVerseId;
    }

    /**
     * @param endVerseId The end_verse_id
     */
    public void setEndVerseId(String endVerseId) {
        this.endVerseId = endVerseId;
    }

    /**
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return The path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path The path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return The versionAbbreviation
     */
    public String getVersionAbbreviation() {
        return versionAbbreviation;
    }

    /**
     * @param versionAbbreviation The version_abbreviation
     */
    public void setVersionAbbreviation(String versionAbbreviation) {
        this.versionAbbreviation = versionAbbreviation;
    }

    /**
     * @return The startVerseId
     */
    public String getStartVerseId() {
        return startVerseId;
    }

    /**
     * @param startVerseId The start_verse_id
     */
    public void setStartVerseId(String startVerseId) {
        this.startVerseId = startVerseId;
    }

    /**
     * @return The display
     */
    public String getDisplay() {
        return display;
    }

    /**
     * @param display The display
     */
    public void setDisplay(String display) {
        this.display = display;
    }
}
