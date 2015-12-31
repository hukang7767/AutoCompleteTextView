package com.android.hukang.autocompletetextview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Friend> list = new ArrayList<Friend>();
    List <Friend > list2 = new ArrayList<>();

    private int totalWords = 0;
    private MyAdapter me;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.tv_test);
        me = new MyAdapter(this,R.layout.list_item);
        listView.setAdapter(me);
        AutoCompleteTextView ac_test = (AutoCompleteTextView) findViewById(R.id.ac_test);
        initData();
        MyAdapter myAdapter = new MyAdapter(this,R.layout.list_item);
        ac_test.setAdapter(myAdapter);
        ac_test.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                list2.clear();
                for (int j = 0;j<list.size();j++){
                    if (list.get(j).name.startsWith(s+"")||list.get(j).name.contains(s)){
                        index = list.get(j).name.indexOf(s + "");
                        list2.add( new Friend(index,list.get(j).name));

                    }
                }
                totalWords += count;
                totalWords -= before;
                me.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void initData(){
        list.add(new Friend(0,"胡康"));
        list.add(new Friend(0,"胡蝶"));
        list.add(new Friend(0,"杨志刚"));
        list.add(new Friend(0,"三人"));
        list.add(new Friend(0,"张三"));
        list.add(new Friend(0,"李四"));
        list.add(new Friend(0,"张三丰"));
    }
    class MyAdapter extends ArrayAdapter{

        public MyAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return list2.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            View view = convertView;
//            if (view == null){
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item,null);
                TextView name = (TextView) view.findViewById(R.id.tv_item);
//                name.setText(list.get(position));

                String str = list2.get(position).name;
                int indext = list2.get(position).indext;
                String string =str.substring(0, indext);
                String center = str.substring(indext,indext+totalWords);
                String endStr = str.substring(indext+totalWords,str.length());
                name.setText(Html.fromHtml(string+"<font color='#ff0000'>"+center+"</font>"+endStr));
//            }
            return view;
        }

    }
}
