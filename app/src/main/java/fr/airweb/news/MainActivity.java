package fr.airweb.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import butterknife.BindView;

import butterknife.ButterKnife;
import fr.airweb.news.model.Item;
import fr.airweb.news.viewmodel.ItemViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final CompositeDisposable subscriptions = new CompositeDisposable();
    ItemViewModel itemViewModel;

    @BindView(R.id.listview)
    RecyclerView listView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private Context context;

     MainActivityAdapter mainActivityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        itemViewModel =  ViewModelProviders.of(this).get(ItemViewModel.class);
        inidAdapter();
        subscriptions.add(itemViewModel.loadItem()
                .doOnSubscribe(disposable -> progressBar.setVisibility(View.VISIBLE))
                .doOnTerminate(() -> progressBar.setVisibility(View.GONE))
                .subscribe(this::onResponse, this::onFailure));
    }

    void inidAdapter() {
        Log.d(TAG, "inidAdapter: ");
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this));
        context = this;
        mainActivityAdapter = new MainActivityAdapter(context);
        listView.setAdapter(mainActivityAdapter);
        listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void onFailure(Throwable throwable) {
        Log.i(TAG, "onFailure: "+throwable.getCause());
    }

    private void onResponse(Item item) {
        Log.i(TAG, "onResponse: "+item);
        mainActivityAdapter.addRepos(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "onQueryTextSubmit: " + s);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "onQueryTextChange: " + s);
                //mainActivityAdapter.clear();
                return true;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }
}
