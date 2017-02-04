package com.fierka.michal.accordion;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by Michał on 2017-01-18.
 */

public class Accordion extends ExpandableListView {

    static String TAG = "tag";

    private AccordionAdapter accordionAdapter;
    private List<String> headers;
    private Map<String, List<String>> listMap;

    private static int previousGroup = -1;

    public Accordion(Context context, List<String> headers, Map<String, List<String>> listMap) {
        super(context);
        accordionAdapter = new AccordionAdapter(context, headers, listMap);
        this.setAdapter(accordionAdapter);
        onChildClick(context);
        onExpandAndCollapseListener();
    }

    public Accordion(final Context context) {
        super(context);
        onChildClick(context);
        onExpandAndCollapseListener();
    }

    public void onExpandAndCollapseListener() {
        this.setOnGroupCollapseListener(new MyOnGroupCollapseListener());
        this.setOnGroupExpandListener(new MyOnGroupExpandListener());
    }

    private void onChildClick(final Context context) {
        this.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                int position = expandableListView.getPositionForView(view);
                String text = String.valueOf(expandableListView.getItemAtPosition(position));
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
                return true;
            }
        });
    }


    public void addData(List<String> headers, Map<String, List<String>> listMap) {
        this.headers = headers;
        this.listMap = listMap;
        accordionAdapter = new AccordionAdapter(this.getContext(), headers, listMap);
        this.setAdapter(accordionAdapter);
    }


    private class MyOnGroupExpandListener implements OnGroupExpandListener {
        @Override
        public void onGroupExpand(int i) {
            if ((previousGroup != -1) && (i != previousGroup)) {
                collapseGroup(previousGroup);
            }
            previousGroup = i;

            /* EWENTUALNIE
            int groups = accordionAdapter.getGroupCount();

            for(int j = 0  ; j < groups ; j++) {
                if(j != i)
                collapseGroup(j);
            }*/

            expandAnimation();
            Log.i(TAG, "otwarto " + i + " karte");
        }

        private void expandAnimation() {
            Context context = getContext();
            int expand = R.anim.layout_animation;
            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, expand);
            setLayoutAnimation(controller);
        }
    }

    private class MyOnGroupCollapseListener implements OnGroupCollapseListener {
        @Override
        public void onGroupCollapse(int i) {
            collapseAnimation();
            Log.i(TAG, "zamknieto " + i + " karte");
        }
    }

        private void collapseAnimation() {
            Context context = getContext();
            int collapse = R.anim.layout_animation_close;
            LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, collapse);
            setLayoutAnimation(controller);
         }
}
