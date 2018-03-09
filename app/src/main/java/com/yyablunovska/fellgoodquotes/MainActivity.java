package com.yyablunovska.fellgoodquotes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yyablunovska.fellgoodquotes.controller.AppController;
import com.yyablunovska.fellgoodquotes.data.QuotePagerAdapter;
import com.yyablunovska.fellgoodquotes.data.QuotesFetcher;
import com.yyablunovska.fellgoodquotes.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    ViewPager mPager;
    QuotePagerAdapter mQuotePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.view_pager);
        mQuotePagerAdapter = new QuotePagerAdapter(getSupportFragmentManager(), getFragments());
        mPager.setAdapter(mQuotePagerAdapter);
    }

    private List<Fragment> getFragments() {
        final List<Fragment> fragmentList = new ArrayList<>();

        new QuotesFetcher().getQuotes(TAG, quoteList -> {
            for (int i = 0; i < quoteList.size(); i++) {
                final Quote quote = quoteList.get(i);
                final QuoteFragment quoteFragment = QuoteFragment.newInstance(quote.getQuote(), quote.getAuthor());
                fragmentList.add(quoteFragment);
            }
            mQuotePagerAdapter.notifyDataSetChanged();
        });

        return fragmentList;
    }

    @Override
    protected void onStop() {
        super.onStop();

        AppController.getInstance().getRequestQueue().cancelAll(TAG);
    }
}
