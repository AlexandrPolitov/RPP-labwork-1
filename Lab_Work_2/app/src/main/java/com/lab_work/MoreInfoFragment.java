package com.lab_work;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MoreInfoFragment extends DialogFragment {

    public static final String TAG = "MoreInfoFragmentTag";

    private TextView helpText;
    private TextView name;
    private ImageView image;
    private String id_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_info_fragment, container, false);

        helpText = (TextView) view.findViewById(R.id.help_text);
        name = (TextView) view.findViewById(R.id.name);
        name.setText(id_name);
        image = (ImageView) view.findViewById(R.id.image);
        image.setImageResource(R.drawable.books);

        return view;
    }

    void setName (String name) {
        this.id_name = name;
    }
}
