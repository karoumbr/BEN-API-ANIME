package com.bentechprotv.android.benapianime.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bentechprotv.android.benapianime.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnimeListViewModel extends ArrayAdapter<Anime> {
    private List<Anime> animes;
    private int resource;

    public AnimeListViewModel(@NonNull Context context, int resource, List<Anime> data) {
        super(context, resource,data);
        this.animes = data;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        CircleImageView imageViewAvatar = (CircleImageView) listViewItem.findViewById(R.id.imageViewAnime);
        TextView txtTitle = listViewItem.findViewById(R.id.txtTitle);
        TextView txtType = listViewItem.findViewById(R.id.txtType);
        TextView txtScore = listViewItem.findViewById(R.id.txtScore);
        txtTitle.setText(animes.get(position).title);
        txtType.setText(animes.get(position).type);
        txtScore.setText(String.valueOf(animes.get(position).score));
        try {
            URL url = new URL(animes.get(position).imageUrl);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
            imageViewAvatar.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return  listViewItem ;

    }
}
