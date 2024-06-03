package com.example.taketickets.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taketickets.MySupportClasses.MovieTrailer;
import com.example.taketickets.R;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {

    private List<MovieTrailer> trailers;

    public SimpleAdapter(List<MovieTrailer> trailers) {
        this.trailers = trailers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trailer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieTrailer movieTrailer = trailers.get(position);
        holder.webView.setWebViewClient(new WebViewClient());
        holder.webView.getSettings().setJavaScriptEnabled(true);
        holder.webView.loadUrl(movieTrailer.uri.toString());
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        WebView webView;

        ViewHolder(View view) {
            super(view);
            webView = view.findViewById(R.id.webViewRecycler);
        }
    }
}
