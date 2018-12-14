package fanruiqi.bwie.com.week3_1214;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SecondActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String title = intent.getStringExtra("title");
        String price = intent.getStringExtra("price");

        ImageView imageView = findViewById(R.id.second_img);
        TextView t_title = findViewById(R.id.second_title);
        TextView t_price = findViewById(R.id.second_price);

        t_title.setText(title);
        t_price.setText(price);
        Glide.with(SecondActivity.this).load(image).into(imageView);

    }
}
