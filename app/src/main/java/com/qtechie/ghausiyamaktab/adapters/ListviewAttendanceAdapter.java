package com.qtechie.ghausiyamaktab.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qtechie.ghausiyamaktab.R;
import com.qtechie.ghausiyamaktab.activity.HomeActivity;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kaiser on 02-07-2017.
 */

public class ListviewAttendanceAdapter extends BaseAdapter {
    public static final String FIRST_COLUMN = "First";
    public static final String SECOND_COLUMN = "Second";
    public static final String THIRD_COLUMN = "Third";
    public static final String FOURTH_COLUMN = "Fourth";

    public ArrayList<HashMap> list;
    Activity activity;

    public ListviewAttendanceAdapter(HomeActivity activity, ArrayList<HashMap> list) {
        super();
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;
        TextView txt_name_copy;
        //TextView txtThird;
        //TextView txtFourth;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.attendance_list_item, null);
            holder = new ViewHolder();
            holder.txtFirst = (TextView) convertView.findViewById(R.id.txt_roll);
            holder.txtSecond = (TextView) convertView.findViewById(R.id.txt_name);
            holder.txt_name_copy= (TextView) convertView.findViewById(R.id.txt_name_copy);
           // holder.txtThird = (TextView) convertView.findViewById(R.id.ThirdText);
            //holder.txtFourth = (TextView) convertView.findViewById(R.id.FourthText);
            // get our folding cell
            final FoldingCell fc = (FoldingCell) convertView.findViewById(R.id.folding_cell);
            // attach click listener to folding cell
            fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fc.toggle(false);
                }
            });
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap map = list.get(position);
        holder.txtFirst.setText(map.get(FIRST_COLUMN).toString());
        holder.txtSecond.setText(map.get(SECOND_COLUMN).toString());
        holder.txt_name_copy.setText("Name: "+map.get(SECOND_COLUMN).toString());
        //holder.txtThird.setText(map.get(THIRD_COLUMN));
        //holder.txtFourth.setText(map.get(FOURTH_COLUMN));

        return convertView;
    }

}
class AttendanceModelClass{
    public String sl_no;//serial num
    public String name;
    //public CheckBox checkBox;
}