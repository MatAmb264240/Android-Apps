package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Info.InfoResponse;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView moviePoster;
    private TextView movieTitle, movieYear, moviePlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        moviePoster = findViewById(R.id.movie_poster);
        movieTitle = findViewById(R.id.movie_title);
        movieYear = findViewById(R.id.movie_year);
        moviePlot = findViewById(R.id.movie_plot);

        InfoResponse movie = (InfoResponse) getIntent().getSerializableExtra("movie");

        if (movie != null) {
            movieTitle.setText(movie.getTitle());
            movieYear.setText(movie.getYear());
            moviePlot.setText(movie.getPlot()); // Assuming getPlot() returns the plot
            Picasso.get().load(movie.getPoster()).into(moviePoster);
        }
    }
}
