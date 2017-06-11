package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nissan on 5/24/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceId ;

    public  WordAdapter (Activity context, ArrayList<Word> words, int colorResourceId){
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super (context,0,words);
        mColorResourceId = colorResourceId;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        //It will inflate the list_item.xml view. It has the format/structure of each item
        //that's supposed to appear in the view
        View listItemView = convertView; //convertView has the recycled view
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false);
        }

        //Change the listItemView item background colours
        listItemView.findViewById(R.id.text_container).setBackgroundResource(mColorResourceId);


        // Get the {@link Word} object located at this position in the list
        Word word = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        // Get the miwok word from the word object and
        // set this text on the miwok TextView
        miwokTextView.setText(word.getMiwokTranslation());

        //Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);

        // Get the miwok word from the word object and
        // set this text on the miwok TextView
        defaultTextView.setText(word.getDefaultTranslation());


        //Find the ImageView in list_item.XML
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);

        //Check if an image exists in the word object, if not, remove the view. Else,
        //Get the resource ID from the word object
        //and set that image to the ImageView.
//        if (word.getmImageResourceId() == 0){
        if (!word.hasImage()){
            imageView.setVisibility(View.GONE);
        }
        else {
            imageView.setImageResource(word.getImageResourceId());
            imageView.setVisibility(View.VISIBLE); //Need to define explicitly in case views get reused.
        }
        return listItemView; //returns the listItemView. It is ONE view that is populated with the words.
    }
}
