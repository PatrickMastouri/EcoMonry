package com.example.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreen.Models.*;
import com.example.splashscreen.Service.CategorieService;
import com.example.splashscreen.Service.ReclamationService;
import com.example.splashscreen.Service.UserService;

import java.util.List;

public class AdapterRec extends RecyclerView.Adapter<AdapterRec.MyViewHolder> {
    private Context mContext;
    private List<reclamtion> mData;

    public AdapterRec(Context mContext, List<reclamtion> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public AdapterRec.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.custom_reclamation, parent, false);
        return new AdapterRec.MyViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRec.MyViewHolder holder, int position) {

        holder.des.setText(mData.get(position).getDescription());
        holder.Sujet.setText(mData.get(position).getSujet());
        //holder.User.setText(String.valueOf(mData.get(position).getId_user()));
        holder.idRec.setText(String.valueOf(mData.get(position).getId_rec()));
        holder.id=mData.get(position).getId_rec();

    }

    @Override
    public int getItemCount() { return mData.size(); }


    public class MyViewHolder  extends RecyclerView.ViewHolder  {

        TextView des;
        TextView Sujet;
        TextView User;
        TextView idRec;
        Integer id;
        Button delete,show;


        public MyViewHolder(@NonNull View itemView, Context mContext) {
            super(itemView);


            idRec = itemView.findViewById(R.id.idRec);
            Sujet = itemView.findViewById(R.id.Sujet);
            des = itemView.findViewById(R.id.description);
           // User = itemView.findViewById(R.id.User);
            delete = itemView.findViewById(R.id.delete);
            show = itemView.findViewById(R.id.show);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    Integer xyz=mData.get(pos).getId_rec();
                    System.out.println("ONCLICK  : "+xyz+"FROM DATA : "+mData.get(pos).getId_rec()+" ADAPTER POSITION : "+getAdapterPosition());
                   ReclamationService.getInstance().Delete(xyz);
                    Intent intent = new Intent(mContext, Reclamation.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

            show.setOnClickListener(new View.OnClickListener() {
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


        }
    }
}
