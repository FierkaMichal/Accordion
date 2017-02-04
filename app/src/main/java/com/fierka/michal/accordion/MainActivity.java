package com.fierka.michal.accordion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<String> headers;
    private Map<String, List<String>> listMap;
    private Accordion accordion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        initData();

        accordion = new Accordion(this);
        accordion.addData(headers, listMap);

        relativeLayout.addView(accordion);
        setContentView(relativeLayout);
    }

    public void initData() {
        headers = new ArrayList<>();
        listMap = new HashMap<>();

        headers.add("First Group");
        headers.add("Group Two");
        headers.add("Group Three");
        headers.add("Penultimate group");
        headers.add("Last group");

        List<String> children1 = new ArrayList<>();
        children1.add("This is first child of group 1");

        List<String> children2 = new ArrayList<>();
        children2.add("This is first child of group 2");
        children2.add("This is second child of group 2");
        children2.add("This is third child of group 2");
        children2.add("This is fourth child of group 2 ");

        List<String> children3 = new ArrayList<>();
        children3.add("Another child");
        children3.add("Another child of third group");
        children3.add("Another child");
        children3.add("Another child ");

        List<String> children4 = new ArrayList<>();
        children4.add("Penultimate group of children");
        children4.add("Child of group 4");
        children4.add("Child of penultimate group");
        children4.add("Last child of this group");

        List<String> children5 = new ArrayList<>();
        children5.add("First child of this group");
        children5.add("Another child");
        children5.add("Extra child");
        children5.add("Last child ");

        listMap.put(headers.get(0),children1);
        listMap.put(headers.get(1),children2);
        listMap.put(headers.get(2),children3);
        listMap.put(headers.get(3),children4);
        listMap.put(headers.get(4), children5);
    }
}