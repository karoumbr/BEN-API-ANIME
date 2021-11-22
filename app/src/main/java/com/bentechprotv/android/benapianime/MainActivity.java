package com.bentechprotv.android.benapianime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bentechprotv.android.benapianime.model.Anime;
import com.bentechprotv.android.benapianime.model.AnimeListViewModel;
import com.bentechprotv.android.benapianime.model.JikanResponse;
import com.bentechprotv.android.benapianime.service.AnimeServiceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Anime> data = new ArrayList<>();
    EditText _txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        _txtName = (EditText) findViewById(R.id.txtName);
        Button _btnGet = (Button) findViewById(R.id.btnGet);
        ListView _lsvAnime = (ListView) findViewById(R.id.lsvAnime);

         AnimeListViewModel listViewModel = new AnimeListViewModel(this,R.layout.users_list_view_layout,data);
        _lsvAnime.setAdapter(listViewModel);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        _btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = _txtName.getText().toString();
                AnimeServiceAPI animeServiceAPI = retrofit.create(AnimeServiceAPI.class);
                Call<JikanResponse> callAnimes = animeServiceAPI.searchAnime(query);
                callAnimes.enqueue(new Callback<JikanResponse>() {
                    @Override
                    public void onResponse(Call<JikanResponse> call, Response<JikanResponse> response) {
                        Log.i("info",call.request().url().toString());
                        if(!response.isSuccessful()){
                            Log.i("indo",String.valueOf(response.code()));
                            return;
                        }
                        JikanResponse jikanResponse = response.body();
                         data.clear();
                        for(Anime anime:jikanResponse.animes){
                            data.add(anime);
                        }
                        if (data.size() > 0){
                            listViewModel.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(),"aucun resultat",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<JikanResponse> call, Throwable t) {
                        Log.e("error",t.getMessage());

                    }
                });
            }
        });


        _lsvAnime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int _id = data.get(position).id;
                String _title = data.get(position).title.replaceAll(" ","_");

                String _Path =  "https://myanimelist.net/anime/" + String.valueOf(_id) + "/" + _title;
                Log.i("info", data.get(position).url);
          //      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(_Path));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.get(position).url));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    _txtName.setText("death");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        _txtName.setText("");

    }
}