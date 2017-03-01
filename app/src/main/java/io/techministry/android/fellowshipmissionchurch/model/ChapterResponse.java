package io.techministry.android.fellowshipmissionchurch.model;

import java.util.List;

public class ChapterResponse {

    List<Chapter> chapters;

    public ChapterResponse(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }
}
