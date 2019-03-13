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

import java.util.List;

/**
 * Created by JOY KARMAKER on 14-Dec-17.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<CustomCardview> listitems;
    private Context context;

    public RecyclerAdapter(List<CustomCardview> listitems, Context context) {
        this.context = context;
        this.listitems = listitems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_custom_cardview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CustomCardview listItem = listitems.get(position);

        //MissingActivity mobj = new MissingActivity();


        holder.tname.setText("Name: "+listItem.getName());
        holder.tpname.setText("Mr. "+listItem.getPerson()+" has "+listItem.getOption()+" a person.");
        holder.tage.setText("Age: "+listItem.getAge()+" Years");
        holder.tgender.setText("Gender: "+listItem.getGender());
        holder.theight.setText("Height: "+listItem.getHeight()+" ft");
        holder.tphone.setText("Phone: "+listItem.getPhone());
        holder.tskin.setText("Skin Color: "+listItem.getSkin());
        holder.tlocation.setText("Missing/Found Location: "+listItem.getLocation());

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tname;
        public TextView tpname;
        public TextView tage;
        public TextView tgender;
        public TextView theight;
        public TextView tphone;
        public TextView tskin;
        public TextView tlocation;


        public ViewHolder(View itemView) {
            super(itemView);

            tname = (TextView) itemView.findViewById(R.id.cname);
            tpname = (TextView) itemView.findViewById(R.id.person);
            tage = (TextView) itemView.findViewById(R.id.cage);
            tgender = (TextView) itemView.findViewById(R.id.resultgender);
            theight = (TextView) itemView.findViewById(R.id.cheight);
            tphone = (TextView) itemView.findViewById(R.id.cphone);
            tskin = (TextView) itemView.findViewById(R.id.cskin);
            tlocation = (TextView) itemView.findViewById(R.id.clocation);
        }
    }
}
