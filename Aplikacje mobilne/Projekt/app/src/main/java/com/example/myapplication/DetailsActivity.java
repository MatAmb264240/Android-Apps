package com.example.myapplication;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.RateRecylerAdapter;
import com.example.myapplication.Info.InfoResponse;
import com.example.myapplication.Listeners.OnDetailsApiListener;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    TextView textView_movie_plot, textView_movie_name, textView_movie_released, textView_movie_runtime, textView_movie_actors;
    ImageView imageView_movie_poster;

    RecyclerView recycler_movie_ratings;

    RateRecylerAdapter adapter;

    RequestManager manager;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textView_movie_plot=findViewById(R.id.textView_movie_plot);
        textView_movie_name=findViewById(R.id.textView_movie_name);
        textView_movie_released=findViewById(R.id.textView_movie_released);
        textView_movie_runtime=findViewById(R.id.textView_movie_runtime);
        textView_movie_actors=findViewById(R.id.textView_movie_actors);
        imageView_movie_poster=findViewById(R.id.imageView_movie_poster);
        recycler_movie_ratings=findViewById(R.id.recycler_movie_ratings);

        manager = new RequestManager(this);
        String movie_id = getIntent().getStringExtra("data");

        dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait...");
        dialog.show();

        manager.searchMovieDetails(listener, movie_id);


    }
    private OnDetailsApiListener listener = new OnDetailsApiListener() {
        @Override
        public void onResponse(InfoResponse response) {
            dialog.dismiss();
            if(response.equals(null))
            {
                Toast.makeText(DetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                return;
            }
            showResults(response);
        }
        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(DetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
    };
    private void showResults(InfoResponse response) {
        textView_movie_name.setText(response.getTitle());
        textView_movie_released.setText("Year released:" +  response.getYear());
        textView_movie_runtime.setText("Length: " + response.getRuntime());
        textView_movie_actors.setText("Actors: " + response.getActors());
        textView_movie_plot.setText(response.getPlot());


        try {
            Picasso.get().load(response.getPoster()).into(imageView_movie_poster);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        recycler_movie_ratings.setHasFixedSize(true);
        recycler_movie_ratings.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new RateRecylerAdapter(this, response.getRatings());

        recycler_movie_ratings.setAdapter(adapter);

    }
}