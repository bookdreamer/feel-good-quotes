package com.yyablunovska.fellgoodquotes.data;

import com.yyablunovska.fellgoodquotes.model.Quote;

import java.util.List;

/**
 * @author yuliia.yablunovska on 1/24/18.
 */

public interface IQuoteListAsyncResponse {
    void processFinished(List<Quote> quoteList);
}
