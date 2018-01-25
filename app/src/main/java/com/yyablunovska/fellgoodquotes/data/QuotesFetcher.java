package com.yyablunovska.fellgoodquotes.data;

import android.util.Log;

import com.android.volley.toolbox.JsonArrayRequest;
import com.yyablunovska.fellgoodquotes.controller.AppController;
import com.yyablunovska.fellgoodquotes.model.Quote;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuliia.yablunovska on 1/23/18.
 */

public class QuotesFetcher {

    private static final String QUOTE_KEY = "quote";
    private static final String NAME_KEY = "name";
    private static final String TAG = QuotesFetcher.class.getSimpleName();

    private final List<Quote> mQuoteList = new ArrayList<>();

    public List<Quote> getQuotes(IQuoteListAsyncResponse callBack) {
        final String url = "https://raw.githubusercontent.com/pdichone/UIUX-Android-Course/master/Quotes.json%20";

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            final JSONObject quoteObject = response.getJSONObject(i);
                            final Quote quote = new Quote();
                            quote.setQuote(quoteObject.getString(QUOTE_KEY));
                            quote.setAuthor(quoteObject.getString(NAME_KEY));
                            mQuoteList.add(quote);
                        } catch (JSONException e) {
                            Log.e(TAG, "Error during request to " + url + ": " + e);
                        }
                    }

                    if (callBack != null) {
                        Log.d("Test", "got quotes: " + mQuoteList.size());
                        callBack.processFinished(mQuoteList);
                    }

                }, error -> Log.d("Test", "some error on query: " + error));
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return mQuoteList;
    }
}
