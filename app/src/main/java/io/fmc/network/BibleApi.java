package io.fmc.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BibleApi {

    // https://api.scripture.api.bible/v1/bibles/de4e12af7f28f599-01/books/RUT/chapters
    @GET("/v1/bibles/{bible_id}/books/RUT/chapters")
    Observable<BooksResponse> getBibleBooks(@Query("bible_id") String bibleId);

}
