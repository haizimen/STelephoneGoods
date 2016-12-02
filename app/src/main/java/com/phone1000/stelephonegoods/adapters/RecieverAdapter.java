package com.phone1000.stelephonegoods.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.phone1000.stelephonegoods.R;
import com.phone1000.stelephonegoods.SElephant;
import com.phone1000.stelephonegoods.activities.EditTextActivity;
import com.phone1000.stelephonegoods.model.AddressContent;
import com.phone1000.stelephonegoods.model.MyEvent;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by my on 2016/12/1.
 */
public class RecieverAdapter extends RecyclerView.Adapter<RecieverAdapter.ViewHolder> implements View.OnClickListener {

    private static final String TAG = RecieverAdapter.class.getSimpleName();

    private List<AddressContent> data;

    private Context context;

    private LayoutInflater inflater;
    private DbManager db;

    public RecieverAdapter(Context context,List<AddressContent> data) {
        inflater=LayoutInflater.from(context);
        this.context=context;
        if (data != null) {
            this.data=data;
        }else{
            this.data=new ArrayList<>();
        }
    }

    public void updateRes(List<AddressContent> data){
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.address_item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.namePhone.setText("   "+data.get(position).getName()+"   "+data.get(position).getPhoneNum());
        holder.location.setText("   "+data.get(position).getLocation()+"  "+data.get(position).getLocationDetail());
        holder.checked.setChecked(data.get(position).isDefault());
        holder.delete.setTag(position);
        holder.delete.setOnClickListener(this);
        holder.edit.setTag(position);
        holder.edit.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick: "+v.getTag() );
        int postion = (int) v.getTag();
        switch (v.getId()) {
            case R.id.address_delete:
                AddressContent addressContent = data.get(postion);
                data.remove(postion);
                Log.e(TAG, "onClick: "+data.size());
                //notifyDataSetChanged();
                notifyItemRemoved(postion);
                notifyItemRangeChanged(postion,getItemCount());
                db = x.getDb(SElephant.daoConfig);
                try {
                    db.delete(addressContent);
                    if(data.size()==0){
                        EventBus.getDefault().post(new MyEvent());
                    }

                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.address_editor:
                AddressContent addressContent1 = data.get(postion);
                db=x.getDb(SElephant.daoConfig);
                try {
                    db.delete(addressContent1);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(context, EditTextActivity.class);
                intent.putExtra("content",data.get(postion));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              //l   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                break;
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView namePhone;
        TextView location;
        CheckBox checked;
        TextView edit;
        TextView delete;
        public ViewHolder(View itemView) {
            super(itemView);
            namePhone= (TextView) itemView.findViewById(R.id.address_username_phone);
            location= (TextView) itemView.findViewById(R.id.address_location);
            edit= ((TextView) itemView.findViewById(R.id.address_editor));
            delete= (TextView) itemView.findViewById(R.id.address_delete);
            checked= (CheckBox) itemView.findViewById(R.id.address_checkbox);
        }
    }
}
