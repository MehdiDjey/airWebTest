package fr.airweb.news.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.airweb.news.DetailActivity;
import fr.airweb.news.databinding.ListItemBinding;
import fr.airweb.news.model.Item;
import fr.airweb.news.model.News;
import io.reactivex.annotations.NonNull;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {
    private static final String TAG = MainActivityAdapter.class.getSimpleName();

    private final ArrayList<Item> itemsList = new ArrayList<>();
    private final ArrayList<News> newsItem = new ArrayList<>();
    private Context context;

    public MainActivityAdapter(Context context) {
        Log.d(TAG, "MainActivityAdapter: ");
        this.context = context;
    }

    public void addRepos(Item itemsList) {
        Log.i(TAG, "addRepos: " + itemsList);

        for (int i = 0; i < itemsList.getSize(); i++) {
            newsItem.add(itemsList.getNews().get(i));
        }
        this.notifyItemInserted(newsItem.size() - 1);
        this.notifyDataSetChanged();
    }

    public void clear() {
        newsItem.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return newsItem.size();
    }

    @Override
    public @NonNull
    MainActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binds(newsItem.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + v);
                Log.i(TAG, "onClick: " + newsItem.get(position));
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("news", newsItem.get(position));
                context.startActivity(intent);
            }
        });
     /*   holder.binding.setOnClickListener(v -> {
            Log.d(TAG, "onBindViewHolder: " + reposRepo.get(position));
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("username", reposRepo.get(position).getOwner().getLogin());
            intent.putExtra("repos", reposRepo.get(position).getName());
            context.startActivity(intent);

        });*/

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ListItemBinding binding;

        ViewHolder(ListItemBinding binding) {
            this(binding.getRoot());
            this.binding = binding;

        }

        ViewHolder(View view) {
            super(view);
        }


        void binds(@NonNull News item) {
            Log.i(TAG, "binds: " + item);
            binding.setItems(item);
            binding.executePendingBindings();
        }
    }
}

