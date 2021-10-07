package com.example.login;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BillAdapter extends BaseAdapter {
    private Context ctx;
    private List<Bill> bills;

    public BillAdapter(Context ctx, List<Bill> bills) {
        this.ctx = ctx;
        this.bills = bills;
    }

    @Override
    public int getCount() {
        return bills.size();
    }

    @Override
    public Object getItem(int i) {
        return bills.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Bill b = (Bill) getItem(i);
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        View v = inflater.inflate(R.layout.item_layout, viewGroup,false);

        ImageView imgv=v.findViewById(R.id.imageView);
        TextView tvFactura=v.findViewById(R.id.textViewFactura);
        TextView tvData=v.findViewById(R.id.textViewData);
        TextView tvDataS=v.findViewById(R.id.textViewDataS);
        TextView suma=v.findViewById(R.id.textViewSuma);

        tvFactura.setText(b.getFurnizor());
        tvData.setText(b.getDataScadenta());
        tvDataS.setText(b.getDataScadenta());
        suma.setText(""+b.getSuma());


        return v;
    }

    public void remove(int position){
        if (position >= 0 && bills.size() < position && bills.get(position) != null) {
            bills.remove(position);
            this.notifyDataSetChanged();
        }
    }
}
