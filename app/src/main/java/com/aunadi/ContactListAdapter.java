package com.aunadi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by edemuck dev on 10.01.2015.
 */
public class ContactListAdapter extends ArrayAdapter<Contact> {


    public ContactListAdapter(Context context, List<Contact> objects) {
        super(context, 0, objects);
    }

    private class ViewHolder {
        private ImageView imageViewPic;
        private TextView textViewName;
        private CheckBox checkBoxIsSelected;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_element_get_contact, null);

            // Views
            viewHolder.imageViewPic = (ImageView) convertView.findViewById(R.id.imageViewContactPic);
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.textViewContactName);
            viewHolder.checkBoxIsSelected = (CheckBox) convertView.findViewById(R.id.checkBoxIsSelected);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact contact = (Contact) getItem(position);
        viewHolder.textViewName.setText(contact.getName());
        viewHolder.checkBoxIsSelected.setSelected(contact.isSelected());

        return convertView;
    }
}
