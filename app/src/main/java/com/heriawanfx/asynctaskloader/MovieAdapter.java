package com.heriawanfx.asynctaskloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 25-01-2018.
 */

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items){
        mData = items;
        notifyDataSetChanged();
    }
    public void addItem(final MovieItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }
    public void clearData(){
        mData.clear();
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }
    @Override
    public MovieItems getItem(int position) {
        return mData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.movie_items, null);
            holder.title= (TextView)convertView.findViewById(R.id.title);
            holder.releaseDate = (TextView) convertView.findViewById(R.id.releaseDate);
            holder.posterPath = (ImageView) convertView.findViewById(R.id.posterPath);
            holder.overview = (TextView) convertView.findViewById(R.id.overview);
            holder.voteAverage = (TextView) convertView.findViewById(R.id.voteAverage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(mData.get(position).getTitle());
        holder.releaseDate.setText(mData.get(position).getReleaseDate());
        holder.overview.setText(mData.get(position).getOverview());
        holder.voteAverage.setText(mData.get(position).getVoteAverage().toString());
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w185/" + mData.get(position).getPosterPath())
                .resize(200, 250)
                .placeholder(android.R.color.darker_gray)
                .into(holder.posterPath);
        return convertView;
    }
    private static class ViewHolder {
        TextView title;
        TextView releaseDate;
        ImageView posterPath;
        TextView overview;
        TextView voteAverage;
    }
}