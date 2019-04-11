package com.lab_work;

import java.util.List;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.LayoutInflater;
import com.squareup.picasso.Picasso;
import android.support.v4.app.Fragment;

public class MoreInfoFragment extends Fragment {

    private final String PIC_URL = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/";
    private static List<CivilizationItem> items;
    private int pageNumber;

    private TextView helpText;
    private TextView name;
    private ImageView image;

    public static MoreInfoFragment newInstance(int page, ArrayList<CivilizationItem> item) {
        MoreInfoFragment moreInfoFragment = new MoreInfoFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("text", page);
        moreInfoFragment.setArguments(arguments);
        items = item;
        return moreInfoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("text") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_info_view, container, false);

        view.setBackgroundColor(Color.parseColor("#FFFFFF"));
        helpText = (TextView) view.findViewById(R.id.help_text);
        helpText.setText(items.get(pageNumber).getHelptext());
        name = (TextView) view.findViewById(R.id.name);
        name.setText(items.get(pageNumber).getName());
        image = (ImageView) view.findViewById(R.id.image);
        Picasso.with(inflater.getContext())
                .load(PIC_URL + items.get(pageNumber).getGraphic())
                .placeholder(R.drawable.white_pic)
                .error(R.drawable.image)
                .into(image);

        return view;
    }
}