//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package edu.buaa.vehiclemanagementsystem.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import com.amap.api.maps2d.SupportMapFragment;
import edu.buaa.vehiclemanagementsystem.R.layout;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LocusFragment_
    extends LocusFragment
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();
    private View contentView_;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
    }

    public View findViewById(int id) {
        if (contentView_ == null) {
            return null;
        }
        return contentView_.findViewById(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.fragment_locus, container, false);
        }
        return contentView_;
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static LocusFragment_.FragmentBuilder_ builder() {
        return new LocusFragment_.FragmentBuilder_();
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        seekbar = ((SeekBar) hasViews.findViewById(edu.buaa.vehiclemanagementsystem.R.id.seek_bar));
        btnPlay = ((Button) hasViews.findViewById(edu.buaa.vehiclemanagementsystem.R.id.btn_play));
        fragmentMap = ((SupportMapFragment) findSupportFragmentById(edu.buaa.vehiclemanagementsystem.R.id.map));
        {
            View view = hasViews.findViewById(edu.buaa.vehiclemanagementsystem.R.id.btn_play);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        LocusFragment_.this.replay();
                    }

                }
                );
            }
        }
        init();
    }

    private Fragment findSupportFragmentById(int id) {
        if (!(getActivity() instanceof FragmentActivity)) {
            return null;
        }
        FragmentActivity activity_ = ((FragmentActivity) getActivity());
        return activity_.getSupportFragmentManager().findFragmentById(id);
    }

    public static class FragmentBuilder_ {

        private Bundle args_;

        private FragmentBuilder_() {
            args_ = new Bundle();
        }

        public LocusFragment build() {
            LocusFragment_ fragment_ = new LocusFragment_();
            fragment_.setArguments(args_);
            return fragment_;
        }

    }

}
