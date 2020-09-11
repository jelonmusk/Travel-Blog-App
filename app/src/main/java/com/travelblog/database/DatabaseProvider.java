package com.travelblog.database;

import android.content.*;

import androidx.room.*;

public class DatabaseProvider {

    private static volatile com.travelblog.database.AppDatabase instance;

    public static com.travelblog.database.AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (DatabaseProvider.class) {
                if (instance == null) {
                    instance = Room
                            .databaseBuilder(context, com.travelblog.database.AppDatabase.class, "blog-database")
                            .build();
                }
            }
        }
        return instance;
    }
}