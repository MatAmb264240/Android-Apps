package com.example.myapplication.Listeners;

import com.example.myapplication.Search.Search;
import com.example.myapplication.Search.SearchResponse;

public interface OnSearchApiListener {
    void onResponse(SearchResponse response);
    void onError(String message);
}
