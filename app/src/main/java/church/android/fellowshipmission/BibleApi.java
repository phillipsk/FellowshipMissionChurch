package church.android.fellowshipmission;

import church.android.fellowshipmission.model.ChapterResponse;
import church.android.fellowshipmission.model.PassagesResponse;
import church.android.fellowshipmission.model.SearchResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface BibleApi {

    @GET("v2/books/{book_id}/chapters")
    Observable<ChapterResponse> chapter(@Path("book_id") String bookId);

    //GET https://en.bibles.org/v2/search.js?query=Mahershalalhashbaz
    @GET("v2/search.js")
    Observable<SearchResponse> search(@Query("query") String query);

    //GET https://bibles.org/v2/passages.xml?q[]=john+3:1-5&version=eng-KJVA
    @GET("v2/passages.js")
    Observable<PassagesResponse> passages(@Query("q[]") String query, @Query("version") String version);
}
