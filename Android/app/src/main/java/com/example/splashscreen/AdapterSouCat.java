package com.example.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreen.Models.*;
import com.example.splashscreen.Service.ReclamationService;
import com.example.splashscreen.Service.SousCategorieService;

import java.util.List;

public class AdapterSouCat extends RecyclerView.Adapter<AdapterSouCat.MyViewHolder> {

    private Context mContext;
    private List<sous_categorie> mData;

    public AdapterSouCat(Context mContext, List<sous_categorie> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public AdapterSouCat.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.custom_soucat, parent, false);
        return new AdapterSouCat.MyViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSouCat.MyViewHolder holder, int position) {

        holder.name.setText(mData.get(position).getName());
       // categorie.setText(id);

        holder.categorie.setText(mData.get(position).getCat_id());
        holder.periode.setText(mData.get(position).getPeriode());
        holder.reminder.setText(mData.get(position).getReminder());
        holder.montant.setText(mData.get(position).getMontant());
        holder.etat.setText(mData.get(position).getEtat());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder  {

        TextView reminder;
        TextView name;
        TextView periode;
        TextView categorie;
        TextView montant;
        TextView etat;


        Button delete,view,edit;


        public MyViewHolder(@NonNull View itemView, Context mContext) {
            super(itemView);


            reminder = itemView.findViewById(R.id.reminder);
            name = itemView.findViewById(R.id.name);
            periode = itemView.findViewById(R.id.periode);
            categorie = itemView.findViewById(R.id.categorie);
            montant = itemView.findViewById(R.id.montant);
            etat = itemView.findViewById(R.id.Etat);

            delete = itemView.findViewById(R.id.delete);
            view = itemView.findViewById(R.id.view);
            edit = itemView.findViewById(R.id.edit);


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    Integer xyz=mData.get(pos).getId_sc();
                    SousCategorieService.getInstance().Delete(xyz);
                    Intent intent = new Intent(mContext, SousCategorie.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

            /*

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    Integer xyz=mData.get(pos).getId_rec();
                    Intent intent = new Intent(mContext,ReclamationDetails.class);
                    intent.putExtra("id",xyz);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

             */


        }
    }
}
