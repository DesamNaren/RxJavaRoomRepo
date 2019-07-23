package com.example.roomdb;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class OTAdapter extends RecyclerView.Adapter<OTAdapter.OTHolder> {

    private List<OtItem> otItems;
    private Context context;
    private otInterface otInterface;

    public OTAdapter(Context context, List<OtItem> otItems) {
        this.context=context;
        this.otItems = otItems;
        try{
            otInterface = (otInterface) context;
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    public OTAdapter() {

    }

    @NonNull
    @Override
    public OTHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ot_item, viewGroup,false);
        return new OTHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OTHolder otHolder, final int i) {
        otHolder.empID.setText(otItems.get(i).getEmpId());
        otHolder.des.setText(otItems.get(i).getDesignation());
        otHolder.post.setText(otItems.get(i).getPostType());
        otHolder.ots.setText(otItems.get(i).getOts());

        otHolder.updateOt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otInterface.onOtItemUpdate(otItems.get(i));
            }
        });

        otHolder.deleteOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otInterface.onOtItemDelete(otItems.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return otItems != null ? otItems.size() : 0;
    }

    class OTHolder extends RecyclerView.ViewHolder {
        private TextView empID, des, post, ots;
        private Button updateOt;
        private Button deleteOT;

        OTHolder(@NonNull View itemView) {
            super(itemView);
            empID = itemView.findViewById(R.id.empID);
            des = itemView.findViewById(R.id.des);
            post = itemView.findViewById(R.id.post);
            ots = itemView.findViewById(R.id.ots);
            updateOt = itemView.findViewById(R.id.updateOt);
            deleteOT = itemView.findViewById(R.id.deleteOT);
        }
    }
}
