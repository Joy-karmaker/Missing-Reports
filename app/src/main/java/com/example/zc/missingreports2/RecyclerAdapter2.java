package com.example.zc.missingreports2;

/**
 * Created by ZC on 09-Oct-18.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by JOY KARMAKER on 14-Dec-17.
 */
public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder> {

    private List<CustomCardview2> listitems;
    private Context context;

    public RecyclerAdapter2(List<CustomCardview2> listitems, Context context) {
        this.context = context;
        this.listitems = listitems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_custom_cardview2, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CustomCardview2 listItem = listitems.get(position);
      //  String a=listItem.getMatch_percentage();

        holder.unotification.setText(listItem.getName()+" and you have found item match.Click to check");
       // Toast.makeText(RecyclerAdapter2.this,a, Toast.LENGTH_SHORT).show();

        //holder.userpost_id.setText(listItem.getPost_id());
        holder.time.setText(listItem.getTime());
        holder.match_id.setText(listItem.getMatch_percentage()+"% matched");
        //holder.tage.setText("Age: "+listItem.getAge()+" Years");

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView unotification;
        public TextView userpost_id;
        public TextView match_id;
        public TextView time;
        //public TextView tage;

        public ViewHolder(View itemView) {
            super(itemView);

            unotification = (TextView) itemView.findViewById(R.id.unotification);
            //userpost_id = (TextView) itemView.findViewById(R.id.post_id);
            match_id = (TextView) itemView.findViewById(R.id.match_id);
            time = (TextView) itemView.findViewById(R.id.post_id);


        }
    }
}
