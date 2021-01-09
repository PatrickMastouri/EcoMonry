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
import com.example.splashscreen.Service.UserService;

import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyViewHolder> {
    private Context mContext;
    private List<user> mData;

    public AdapterUsers(Context mContext, List<user> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public AdapterUsers.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.custom_users,parent,false);
        return new AdapterUsers.MyViewHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUsers.MyViewHolder holder, int position) {


        holder.email.setText(mData.get(position).getEmail());
        holder.phone.setText(mData.get(position).getNum_tel());
        holder.adress.setText(mData.get(position).getAdress());
        holder.name.setText(mData.get(position).getNom());
        holder.idUser.setText(String.valueOf(mData.get(position).getId_user()));
        holder.id=mData.get(position).getId_user();


    }


    @Override
    public int getItemCount() { return mData.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email;
        TextView phone;
        TextView adress;
        TextView name;
        TextView idUser;
        Button delete;
        Integer id;


        public MyViewHolder(@NonNull View itemView, Context mContext) {
            super(itemView);

            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            adress = itemView.findViewById(R.id.adress);
            name = itemView.findViewById(R.id.name);
            idUser = itemView.findViewById(R.id.idUser);
            delete = itemView.findViewById(R.id.delete);



            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    Integer xyz=mData.get(pos).getId_user();
                    System.out.println("ONCLICK  : "+xyz+"FROM DATA : "+mData.get(pos).getId_user()+" ADAPTER POSITION : "+getAdapterPosition());
                    UserService.getInstance().Delete(xyz);
                    Intent intent = new Intent(mContext, Admin.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });



        }
    }
}
