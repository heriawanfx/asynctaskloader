package com.heriawanfx.asynctaskloader;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>  {

    ListView listView ;
    MovieAdapter adapter;
    EditText cariFilm;
    Button buttonCari;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView)findViewById(R.id.listView);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieItems movie = (MovieItems) listItems.get(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.KEY_ITEM, movie);
                startActivityForResult(intent, 0);
            }
        });

        cariFilm = (EditText)findViewById(R.id.edit_kota);
        buttonCari = (Button)findViewById(R.id.btn_kota);

        buttonCari.setOnClickListener(myListener);

        String film = cariFilm.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, film);

        getLoaderManager().initLoader(0, bundle, this);
    }

    //Fungsi ini yang akan menjalankan proses myasynctaskloader
    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {

        String kumpulanFilm = "";
        if (args != null){
            kumpulanFilm = args.getString(EXTRAS_MOVIE);
        }

        return new MyAsyncTaskLoader(this,kumpulanFilm);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {

        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);

    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String film = cariFilm.getText().toString();

            if (TextUtils.isEmpty(film))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, film);
            getLoaderManager().restartLoader(0,bundle,MainActivity.this);
        }
    };
}
