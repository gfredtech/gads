package me.gfred.gads.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.gfred.gads.R;
import me.gfred.gads.model.HoursEntry;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.ViewHolder> {

    private final List<HoursEntry> entries;
    private final LayoutInflater layoutInflater;

    public HoursAdapter(Context context, List<HoursEntry> entries) {
        this.entries = entries;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HoursAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cv_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursAdapter.ViewHolder holder, int position) {
        holder.bindTo(entries.get(position));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        TextView entryName;
        TextView entryDetail;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            entryName = itemView.findViewById(R.id.entry_name);
            entryDetail = itemView.findViewById(R.id.entry_detail);
            imageView = itemView.findViewById(R.id.iv_badge);
        }

        public void bindTo(HoursEntry hoursEntry) {
            entryName.setText(hoursEntry.getName());
            String hoursCountryText = context.getString(R.string.sample_string, hoursEntry.getHours(), "learning hours", hoursEntry.getCountry());
            entryDetail.setText(hoursCountryText);
            Picasso.get().load(hoursEntry.getBadgeUrl()).into(imageView);
        }
    }
}
