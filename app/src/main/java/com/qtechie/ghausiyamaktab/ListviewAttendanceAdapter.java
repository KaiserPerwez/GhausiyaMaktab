package com.qtechie.ghausiyamaktab;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.qtechie.ghausiyamaktab.Constant.FIRST_COLUMN;
import static com.qtechie.ghausiyamaktab.Constant.SECOND_COLUMN;

/**
 * Created by Kaiser on 02-07-2017.
 */

public class ListviewAttendanceAdapter extends BaseAdapter {

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
           // holder.txtThird = (TextView) convertView.findViewById(R.id.ThirdText);
            //holder.txtFourth = (TextView) convertView.findViewById(R.id.FourthText);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap map = list.get(position);
        holder.txtFirst.setText(map.get(FIRST_COLUMN).toString());
        holder.txtSecond.setText(map.get(SECOND_COLUMN).toString());
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
class Constant {
    public static final String FIRST_COLUMN = "First";
    public static final String SECOND_COLUMN = "Second";
    public static final String THIRD_COLUMN = "Third";
    public static final String FOURTH_COLUMN = "Fourth";
}