package com.application.app.myresturants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.app.myresturants.dialog.ConfirmDealFragment;
import com.application.app.myresturants.models.DealModel;
import com.application.app.myresturants.models.DealsModel;
import com.application.app.myresturants.models.OrderModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class DealListAdapter extends RecyclerView.Adapter<DealListAdapter.ViewHolder>{
    private ArrayList<DealsModel> listdata;
private Context context;
    // RecyclerView recyclerView;
    public DealListAdapter(ArrayList<DealsModel> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.layout_deal_list_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DealsModel myListData = listdata.get(position);
       /* holder.tvOrderNum.setText(listdata.get(position).getOrderNumber());
        holder.tvCustomer.setText(listdata.get(position).getOrderedBy());
        holder.tvProduct.setText(listdata.get(position).getProduct());
        holder.tvStatus.setText(listdata.get(position).getStatus());*/
holder.desc.setText(myListData.getPrice());
holder.product.setText(myListData.getDescription());
        Glide.with(context).load(myListData.getImage_url()).centerCrop().into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentActivity activity = (FragmentActivity)(context);
                FragmentManager fm = activity.getSupportFragmentManager();
                ConfirmDealFragment alertDialog = new ConfirmDealFragment().newInstance(1,myListData);
                alertDialog.show(fm, "fragment_alert");

           //     PlaceholderFragment.newInstance(position + 1,restautantModel);

           /*     FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                DialogFragment dialogFragment = new ConfirmDealFragment();
                dialogFragment.show(ft, "dialog");*/
              // Toast.makeText(view.getContext(),"click on item: "+position ,Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView desc,product;
        public ConstraintLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);


       /*     this.tvOrderNum = (TextView) itemView.findViewById(R.id.order_num);
            this.tvProduct = (TextView) itemView.findViewById(R.id.product);
            this.tvCustomer = (TextView) itemView.findViewById(R.id.order_by);
            this.tvStatus = (TextView) itemView.findViewById(R.id.status);*/

            imageView = itemView.findViewById(R.id.imageView2);
            product= itemView.findViewById(R.id.product);
            desc = itemView.findViewById(R.id.desc);
            linearLayout = (ConstraintLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}
