package fr.airweb.news;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.airweb.news.model.News;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.description)
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        News news = getIntent().getParcelableExtra("news");
        title.setText(news.getTitle());
        description.setText(news.getContent());
        Picasso.get().load(news.getPicture()).resize(1000, 1000).centerCrop().into(imageView);
    }
}
