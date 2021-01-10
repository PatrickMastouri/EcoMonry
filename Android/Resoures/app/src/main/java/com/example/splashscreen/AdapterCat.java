package com.example.splashscreen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splashscreen.Models.CategorieCallback;
import com.example.splashscreen.Models.categorie;
import com.example.splashscreen.Service.CategorieService;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;

public class AdapterCat extends RecyclerView.Adapter<AdapterCat.MyViewHolder> implements Filterable {

    private Context mContext;
    private List <categorie> mData;
    private List <categorie> exampleListFull;

    public AdapterCat(Context mContext, List<categorie> mData) {
        this.mContext = mContext;
        this.mData = mData;
        exampleListFull = new ArrayList<>(mData);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.customer_cat,parent,false);
        return new MyViewHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //holder.user_id.setText(mData.get(position).getUser_user());
        holder.totale.setText(mData.get(position).getTolate());
        holder.catname.setText(mData.get(position).getCat_Nome());
        holder.cat_id.setText(String.valueOf(mData.get(position).getCat_id()));
        holder.id=mData.get(position).getCat_id();


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView user_id;
        TextView totale;
        TextView catname;
        TextView cat_id;
        Button Edit,Delete,view;
        Integer id;
        Context c;
        public MyViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            user_id = itemView.findViewById(R.id.user_id);
            totale = itemView.findViewById(R.id.totale);
            catname = itemView.findViewById(R.id.catname);
            cat_id = itemView.findViewById(R.id.cat_id);

            Edit = itemView.findViewById(R.id.edit);
            Delete = itemView.findViewById(R.id.delete);
            view = itemView.findViewById(R.id.view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    String xyz=mData.get(pos).getCat_Nome();
                    Integer abs =mData.get(pos).getCat_id();
                    Intent intent = new Intent(context, SousCategorie.class);
                    System.out.println("ONCLICK  : "+xyz+"FROM DATA : "+mData.get(pos).getCat_id()+" ADAPTER POSITION : "+getAdapterPosition());
                    intent.putExtra("cat_nome",xyz);
                    intent.putExtra("Cat_id",abs);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });

            Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                     AlertDialog.Builder builder = new AlertDialog.Builder(context);
                     builder.setTitle("Confirmation !").
                        setMessage("You sure, that you want to delete this category?");
                      builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                    int pos=getAdapterPosition();
                    Integer xyz=mData.get(pos).getCat_id();
                     CategorieService.getInstance().Delete(xyz);
                     Toast.makeText(context, "your categoryhas been delete successfully", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(context, home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   context.startActivity(intent);
                }
            });
            builder.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder.create();
            alert11.show();

                }
            });

            Edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int pos=getAdapterPosition();
                        Integer xyz=mData.get(pos).getCat_id();
                        Intent intent = new Intent(context, CategorieEdit.class);
                        //System.out.println("ONCLICK  : "+xyz+"FROM DATA : "+mData.get(pos).getCat_id()+" ADAPTER POSITION : "+getAdapterPosition());
                        intent.putExtra("id",xyz);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos=getAdapterPosition();
                    Integer xyz=mData.get(pos).getCat_id();
                    Intent intent = new Intent(context, CategoritDelails.class);
                    intent.putExtra("id",xyz);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });

        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<categorie> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (categorie item : exampleListFull) {
                    if (item.getCat_Nome().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            mData.clear();
            mData.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };
}
