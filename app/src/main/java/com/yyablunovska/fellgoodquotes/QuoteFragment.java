package com.yyablunovska.fellgoodquotes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author yuliia.yablunovska on 1/24/18.
 */

public class QuoteFragment extends Fragment {

    private static final String KEY_QUOTE = "quote";
    private static final String KEY_AUTHOR = "author";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_quote, container, false);

        final TextView quoteView = view.findViewById(R.id.quote_text);
        final TextView authorView = view.findViewById(R.id.author_name);
        final CardView cardView = view.findViewById(R.id.card_view);

        final Bundle args = getArguments();
        if (args != null) {
            final String quote = args.getString(KEY_QUOTE);
            final String author = args.getString(KEY_AUTHOR);

            quoteView.setText(quote);
            authorView.setText("~" + author);
        }

        return view;
    }

    public static final QuoteFragment newInstance(String quote, String author) {
        final QuoteFragment fragment = new QuoteFragment();
        final Bundle args = new Bundle();
        args.putString(KEY_QUOTE, quote);
        args.putString(KEY_AUTHOR, author);
        fragment.setArguments(args);
        return fragment;
    }
}
