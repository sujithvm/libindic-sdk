package org.silpa.sdk.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sujith on 10/6/14.
 */
public class FortuneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fortune_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
