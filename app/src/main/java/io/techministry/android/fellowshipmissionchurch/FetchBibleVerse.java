package io.techministry.android.fellowshipmissionchurch;


import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class FetchBibleVerse extends AsyncTask<Void, Void, Void>{

    private final String LOG_TAG = FetchBibleVerse.class.getSimpleName();

    @Override
//    TODO: Change from Void to String to pass a paramter from the main class
    protected Void doInBackground(Void... params) {


            HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String bibleJSONStr = null;

        String format = "js";

//        Test variables until API urlConnection works properly
        String testBook = "2Tim";
        String testChapter = "1";
        String versionParam = "KJV";

        try{
//          This is what we want
//        https://2CYcT1Y1RfQBN1pUtPwO8tfZRy0JIPJOx9d7R2YW@bibles.org/v2/chapters/eng-KJV:2Tim.1/verses.js

            final String API_KEY = BuildConfig.API_KEY;
            final String VERSION_PARAM = "eng-";
            final String BOOK_PARAM = ":";
            final String CHAPTER_PARAM = ".";
            final String ENDING = "/";
            final String BIBLE_BASE_URL =
                    "https://" + API_KEY + "@bibles.org/v2/chapters/";

            Uri builtUri = Uri.parse(BIBLE_BASE_URL).buildUpon()
                    .appendQueryParameter(VERSION_PARAM,versionParam)
                    .appendQueryParameter(BOOK_PARAM,testBook)
                    .appendQueryParameter(CHAPTER_PARAM,testChapter)
                    .appendQueryParameter(ENDING,"verses.js")
                    .build();

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null){
//                Nothing to do.
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null){
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0){
//                Stream was empty. No point in parsing.
                return null;
            }
            bibleJSONStr = buffer.toString();
            Log.v(LOG_TAG, bibleJSONStr);

        } catch (ProtocolException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error", e);
        }
        finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (final IOException e){
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return null;

//        2CYcT1Y1RfQBN1pUtPwO8tfZRy0JIPJOx9d7R2YW
//        passages.js?q[]=john+3:1-5&version=eng-KJVA
//        /eng-GNTD/Gen/1.js
//        https://2CYcT1Y1RfQBN1pUtPwO8tfZRy0JIPJOx9d7R2YW@bibles.org/v2/search.js?query=john+3:16&version=eng-GNTD
//        https://2CYcT1Y1RfQBN1pUtPwO8tfZRy0JIPJOx9d7R2YW@bibles.org/v2/search.js?query=2Tim+1:7&version=eng-KJV
//        https://2CYcT1Y1RfQBN1pUtPwO8tfZRy0JIPJOx9d7R2YW@bibles.org/v2/chapters/eng-KJV:2Tim/verses.js
//        https://2CYcT1Y1RfQBN1pUtPwO8tfZRy0JIPJOx9d7R2YW@bibles.org/v2/chapters/eng-KJVA:1Cor.2/verses.js
//        https://2CYcT1Y1RfQBN1pUtPwO8tfZRy0JIPJOx9d7R2YW@bibles.org/v2/search.js?query=2Tim+1:7&version=eng-NASB
//        https://2CYcT1Y1RfQBN1pUtPwO8tfZRy0JIPJOx9d7R2YW@bibles.org/v2/versions.js
//        https://2CYcT1Y1RfQBN1pUtPwO8tfZRy0JIPJOx9d7R2YW@bibles.org/v2/versions/eng-KJV/books.js
//        curl -u 2CYcT1Y1RfQBN1pUtPwO8tfZRy0JIPJOx9d7R2YW:X -k https://en.bibles.org/v2/versions/eng-GNTD.xml

    }

}
