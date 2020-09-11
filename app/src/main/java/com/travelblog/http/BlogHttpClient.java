package com.travelblog.http;

import android.util.*;

import com.google.gson.*;

import java.io.*;
import java.util.*;

import okhttp3.*;

public final class BlogHttpClient {

    public static final BlogHttpClient INSTANCE = new BlogHttpClient();

    public static final String IMAGE_BASE_URL = "https://res.cloudinary.com/jshaikh/image/upload/";
    private static final String BLOG_ARTICLES_URL = "https://api.npoint.io/65e304a38a3a7013b681";

    private OkHttpClient client;
    private Gson gson;

    private BlogHttpClient() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public List<Blog> loadBlogArticles() {
        Request request = new Request.Builder()
                .get()
                .url(BLOG_ARTICLES_URL)
                .build();

        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String json = responseBody.string();
                BlogData blogData = gson.fromJson(json, BlogData.class);
                if (blogData != null) {
                    return blogData.getData();
                }
            }
        } catch (IOException e) {
            Log.e("BlogHttpClient", "Error loading blog articles", e);
        }
        return null;
    }
}
