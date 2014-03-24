//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package edu.buaa.vehiclemanagementsystem.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import edu.buaa.vehiclemanagementsystem.R.id;
import edu.buaa.vehiclemanagementsystem.R.layout;
import org.androidannotations.api.SdkVersionHelper;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class TabActivity_
    extends TabActivity
    implements HasViews, OnViewChangedListener
{

    private final OnViewChangedNotifier onViewChangedNotifier_ = new OnViewChangedNotifier();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier previousNotifier = OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier_);
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(previousNotifier);
        setContentView(layout.activity_tabs);
    }

    private void init_(Bundle savedInstanceState) {
        OnViewChangedNotifier.registerOnViewChangedListener(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        onViewChangedNotifier_.notifyViewChanged(this);
    }

    public static TabActivity_.IntentBuilder_ intent(Context context) {
        return new TabActivity_.IntentBuilder_(context);
    }

    public static TabActivity_.IntentBuilder_ intent(Fragment supportFragment) {
        return new TabActivity_.IntentBuilder_(supportFragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (((SdkVersionHelper.getSdkInt()< 5)&&(keyCode == KeyEvent.KEYCODE_BACK))&&(event.getRepeatCount() == 0)) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onViewChanged(HasViews hasViews) {
        tabStatus = ((RadioButton) hasViews.findViewById(id.tab_status));
        tabLocation = ((RadioButton) hasViews.findViewById(id.tab_location));
        tabMore = ((RadioButton) hasViews.findViewById(id.tab_more));
        {
            CompoundButton view = ((CompoundButton) hasViews.findViewById(id.tab_location));
            if (view!= null) {
                view.setOnCheckedChangeListener(new OnCheckedChangeListener() {


                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        TabActivity_.this.onCheckedChanged(buttonView, isChecked);
                    }

                }
                );
            }
        }
        {
            CompoundButton view = ((CompoundButton) hasViews.findViewById(id.tab_status));
            if (view!= null) {
                view.setOnCheckedChangeListener(new OnCheckedChangeListener() {


                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        TabActivity_.this.onCheckedChanged(buttonView, isChecked);
                    }

                }
                );
            }
        }
        {
            CompoundButton view = ((CompoundButton) hasViews.findViewById(id.tab_more));
            if (view!= null) {
                view.setOnCheckedChangeListener(new OnCheckedChangeListener() {


                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        TabActivity_.this.onCheckedChanged(buttonView, isChecked);
                    }

                }
                );
            }
        }
        initFragment();
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;
        private Fragment fragmentSupport_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, TabActivity_.class);
        }

        public IntentBuilder_(Fragment fragment) {
            fragmentSupport_ = fragment;
            context_ = fragment.getActivity();
            intent_ = new Intent(context_, TabActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public TabActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (fragmentSupport_!= null) {
                fragmentSupport_.startActivityForResult(intent_, requestCode);
            } else {
                if (context_ instanceof Activity) {
                    ((Activity) context_).startActivityForResult(intent_, requestCode);
                } else {
                    context_.startActivity(intent_);
                }
            }
        }

    }

}
