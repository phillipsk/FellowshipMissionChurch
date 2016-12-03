package io.techministry.android.fellowshipmissionchurch;

import io.techministry.android.fellowshipmissionchurch.model.ChapterResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BibleApi {

    @GET("v2/books/{book_id}/chapters.js")
    Call<ChapterResponse> chapter(@Path("book_id") String bookId);
}
