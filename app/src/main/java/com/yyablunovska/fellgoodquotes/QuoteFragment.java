package com.yyablunovska.fellgoodquotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author yuliia.yablunovska on 1/24/18.
 */

public class QuoteFragment extends Fragment {

    private static final String KEY_QUOTE = "quote";
    private static final String KEY_AUTHOR = "author";
    private static final String SHARE_INTENT_TYPE = "text/plain";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_quote, container, false);

        final TextView quoteView = view.findViewById(R.id.quote_text);
        final TextView authorView = view.findViewById(R.id.author_name);

        final Bundle args = getArguments();
        final String quote = args.getString(KEY_QUOTE);
        final String author = args.getString(KEY_AUTHOR);

        quoteView.setText(quote);
        authorView.setText(String.format("~%s", author));

        final ImageView shareView = view.findViewById(R.id.share);
        shareView.setOnClickListener(v -> shareQuote(quote, author));

        return view;
    }

    public static final QuoteFragment newInstance(@NonNull String quote, String author) {
        final QuoteFragment fragment = new QuoteFragment();
        final Bundle args = new Bundle();
        args.putString(KEY_QUOTE, quote);
        args.putString(KEY_AUTHOR, author);
        fragment.setArguments(args);
        return fragment;
    }

    private void shareQuote(String quote, String author) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType(SHARE_INTENT_TYPE);
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_string, author, quote));
        startActivity(shareIntent);
    }
}
