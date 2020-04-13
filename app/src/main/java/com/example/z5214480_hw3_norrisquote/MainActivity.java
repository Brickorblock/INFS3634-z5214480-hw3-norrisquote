package com.example.z5214480_hw3_norrisquote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z5214480_hw3_norrisquote.entities.QuoteResponse;
import com.example.z5214480_hw3_norrisquote.entities.QuoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView quoteText;
    ImageView chuckButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate elements
        quoteText = findViewById(R.id.quoteText);
        chuckButton = findViewById(R.id.chuckButton);

        chuckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set to a filler text while the quote is loading
                quoteText.setText("Fetching quote...");
                setQuote();
            }
        });

        setQuote();

    }

    //calls ChuckNorris API and sets the quote to textField
    public void setQuote(){
        //set up Retrofit
        Retrofit.Builder builder =new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/jokes/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        QuoteService service = retrofit.create(QuoteService.class);
        Call<QuoteResponse> call = service.getQuote("dev");

        // use enqueue to handle execution
        call.enqueue(new Callback<QuoteResponse>() {
            @Override
            public void onResponse(Call<QuoteResponse> call, Response<QuoteResponse> response) {
                QuoteResponse quote = response.body();
                quoteText.setText(quote.getValue());
            }

            @Override
            public void onFailure(Call<QuoteResponse> call, Throwable t) {
                String msg = "Failed to retrieve quote.";

                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
