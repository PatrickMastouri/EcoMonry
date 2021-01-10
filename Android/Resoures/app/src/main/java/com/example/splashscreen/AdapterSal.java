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

import com.example.splashscreen.Models.categorie;
import com.example.splashscreen.Models.salaire;
import com.example.splashscreen.Service.SalaireService;

import java.util.List;

public class AdapterSal extends RecyclerView.Adapter<AdapterSal.MyViewHolder> {

    private Context mContext;
    private List<salaire> mData;

    public AdapterSal(Context mContext, List<salaire> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public AdapterSal.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.custom_sal,parent,false);
        return new AdapterSal.MyViewHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSal.MyViewHolder holder, int position) {

//        holder.id_user.setText(String.valueOf(mData.get(position).getId_user()));
        holder.salaire.setText(mData.get(position).getSalaire());
        System.out.println("inside view holder : "+mData.get(position).getPayment().toString());
   holder.payment.setText(mData.get(position).getPayment().toString());
       // holder.sal_id.setText(String.valueOf(mData.get(position).getId_sal()));
        holder.id=mData.get(position).getId_sal();


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

//        TextView sal_id;
        TextView salaire;
        TextView payment;
        Button Edit,Delete;
        Integer id;
        Context c;
        public MyViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
           payment = itemView.findViewById(R.id.payment);
            salaire = itemView.findViewById(R.id.salaire);
            Edit = itemView.findViewById(R.id.edit);
            Delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    Integer xyz=mData.get(pos).getId_sal();
                    Intent intent = new Intent(context, CategoritDelails.class);
                    System.out.println("ONCLICK  : "+xyz+"FROM DATA : "+mData.get(pos).getId_sal()+" ADAPTER POSITION : "+getAdapterPosition());
                    intent.putExtra("id",xyz);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });

            Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    Integer xyz=mData.get(pos).getId_sal();
                    System.out.println("ONCLICK  : "+xyz+"FROM DATA : "+mData.get(pos).getId_sal()+" ADAPTER POSITION : "+getAdapterPosition());
                    SalaireService.getInstance().Delete(xyz);
                    Intent intent = new Intent(context, home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

            Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos=getAdapterPosition();
                    Integer xyz=mData.get(pos).getId_sal();
                    Intent intent = new Intent(context, EditSalaire.class);
                    //System.out.println("ONCLICK  : "+xyz+"FROM DATA : "+mData.get(pos).getCat_id()+" ADAPTER POSITION : "+getAdapterPosition());
                    intent.putExtra("id",xyz);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);



                }
            });





        }
    }


}
