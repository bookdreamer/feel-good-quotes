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

    public List<Quote> getQuotes(String requestTag, IQuoteListAsyncResponse callBack) {
        final String url = "https://raw.githubusercontent.com/bookdreamer/feel-good-quotes/master/Quotes.json";

        final JsonArrayRequest quotesJsonRequest = new JsonArrayRequest(url,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            final JSONObject quoteObject = response.getJSONObject(i);
                            final Quote quote = new Quote();
                            quote.setQuote(quoteObject.getString(QUOTE_KEY));
                            quote.setAuthor(quoteObject.getString(NAME_KEY));
                            mQuoteList.add(quote);
                        } catch (JSONException e) {
                            Log.e(TAG, "Error during parsing JSON received from " + url + ": " + e);
                        }
                    }

                    if (callBack != null) {
                        Log.d(TAG, "Got quotes: " + mQuoteList.size());
                        callBack.processFinished(mQuoteList);
                    }

                }, error -> Log.d(TAG, "Some error appeared on query url " + url + ": " + error));
        quotesJsonRequest.setTag(requestTag);
        AppController.getInstance().addToRequestQueue(quotesJsonRequest);

        return mQuoteList;
    }
}
