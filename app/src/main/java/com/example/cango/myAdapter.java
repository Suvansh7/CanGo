package com.example.cango;

import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;


public class myAdapter extends FirebaseRecyclerAdapter <Student,myAdapter.myviewholder> {
    Context context;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public myAdapter(@NonNull FirebaseRecyclerOptions<Student> options, Context context) {
        super(options) ;
        this.context = context;

    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Student model) {

        String no = model.getPhoneNo();

        holder.nt.setText(model.getName());
        holder.rt.setText(model.getReason());
        Glide.with(holder.ab.getContext()).load(model.getImageUrl()).into(holder.ab);
        holder.nt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "logout using google", Toast.LENGTH_SHORT).show();
                Intent extraIntent  = new Intent(context,allowdeny.class);
                extraIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                extraIntent.putExtra("keyNo", no);
                context.startActivity(extraIntent);
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView nt ,rt;
        CircleImageView ab;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            rt = (TextView)itemView.findViewById(R.id.rolltext);
            nt = (TextView)itemView.findViewById(R.id.nametext);
            ab = (CircleImageView)itemView.findViewById(R.id.img1);

        }
    }

}
