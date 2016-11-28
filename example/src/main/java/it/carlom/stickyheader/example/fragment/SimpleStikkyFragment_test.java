package it.carlom.stickyheader.example.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import it.carlom.stickyheader.example.R;
import it.carlom.stickyheader.example.Utils;
import it.carlom.stikkyheader.core.RootLayout;
import it.carlom.stikkyheader.core.StikkyHeaderBuilder;

public class SimpleStikkyFragment_test extends Fragment {

    private ListView mListView;

    private TextView mText;

    private RootLayout mRoot;
    private ViewGroup mHeaderView;

    public SimpleStikkyFragment_test() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simplelistview_test, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRoot = (RootLayout) view.findViewById(R.id.layout_container);
        mListView = (ListView) view.findViewById(R.id.listview);
        mText = (TextView) view.findViewById(R.id.text);
        mHeaderView = (ViewGroup) view.findViewById(R.id.header);
        mRoot.addClickCandidates(mText);
        mRoot.setScrollableView(mListView);
        mRoot.setHeaderView(mHeaderView);

        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "text clicked", Toast.LENGTH_LONG).show();
            }
        });
        mText.setEnabled(false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        StikkyHeaderBuilder.stickTo(mListView)
                .setHeader(R.id.header, (ViewGroup) getView())
                .minHeightHeader(0)
                .allowTouchBehindHeader(true)
                .build();

        Utils.populateListView(mListView);
    }
}
