package com.example.friendscontract;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.nio.file.Files.find;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>implements Filterable
{
    Context context;
    List<Student> dataList;
    //for SearchView
    List<Student> copyDataList;

    public CustomAdapter(Context context, List<Student> dataList) {
        this.context = context;
        this.dataList = dataList;

        //for searchView//dataList's copy
        copyDataList = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.recycle_demo,parent,false);

        //the objectMyViewHolder constructor

        MyViewHolder myViewholder=new MyViewHolder(view);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.nameTextView.setText(dataList.get(position).getName());
        holder.locationTextView.setText(dataList.get(position).getLocation());
        holder.phoneNumberTextView.setText(dataList.get(position).getPhoneNumber());

        holder.personImage.setImageResource(dataList.get(position).getPersonImage());
        holder.callImage.setImageResource(dataList.get(position).getCallIcon());
        holder.messageImage.setImageResource(dataList.get(position).getMessageIcon());



        holder.personImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //set onClickListener
        holder.callImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber=dataList.get(position).phoneNumber;

                String s="tel:"+phoneNumber;
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(s));
                context.startActivity(intent);
            }
        });
        holder.messageImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                String number = dataList.get(position).getPhoneNumber();
                intent.setData(Uri.parse("sms:"+ number));
                String myMessage="Hello! How are You ?";
                intent.putExtra("Sms Body",myMessage);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.personImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Main2Activity.class);
                context.startActivity(intent);
            }
        });


        holder.phoneNumberTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent1 = new Intent(context,Main2Activity.class);
                context.startActivity(intent1);

                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public Filter getFilter() {
        return filterData;
    }

    Filter filterData=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Student> filterList=new ArrayList<>();//for filter data keeping
            if (charSequence==null||charSequence.length()==0){
                filterList.addAll(copyDataList);
            }
            else{
                String value=charSequence.toString().toLowerCase().trim();
                for (Student student:copyDataList){
                    if (student.getName().toLowerCase().trim().contains(value)){
                        filterList.add(student);
                    }

                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataList.clear();
            dataList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView nameTextView,locationTextView,phoneNumberTextView;
        ImageView personImage,callImage,messageImage;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //recycle demo layout id find
            nameTextView=itemView.findViewById(R.id.name);
            locationTextView=itemView.findViewById(R.id.locationId);
            phoneNumberTextView=itemView.findViewById(R.id.number);
            personImage=itemView.findViewById(R.id.profile_image);
            callImage=itemView.findViewById(R.id.call_image_id);
            messageImage=itemView.findViewById(R.id.sms_image_id);


            }
    }
}
