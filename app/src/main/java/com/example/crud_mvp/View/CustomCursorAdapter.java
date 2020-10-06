package com.example.crud_mvp.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.crud_mvp.BD.DBHelper;
import com.example.crud_mvp.R;

public class CustomCursorAdapter extends CursorAdapter {

    private LayoutInflater layoutInflater;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CustomCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View view = layoutInflater.inflate(R.layout.row_student, viewGroup, false);
        MyHolder holder = new MyHolder();

        holder.listId = (TextView) view.findViewById(R.id.listId);
        holder.listName = (TextView) view.findViewById(R.id.listName);
        holder.listLastName = (TextView) view.findViewById(R.id.listLastName);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        MyHolder holder = (MyHolder) view.getTag();
        holder.listId.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_id)));
        holder.listName.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_name)));
        holder.listLastName.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_lastName)));


    }

    class MyHolder {
        TextView listId;
        TextView listName;
        TextView listLastName;
        TextView listStudentCode;
    }

}
