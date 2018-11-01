package com.example.derek.showyoufood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.derek.showyoufood.base.CommonAdapter;
import com.example.derek.showyoufood.base.MultiItemTypeAdapter;
import com.example.derek.showyoufood.base.base.ViewHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class databbb extends AppCompatActivity {
    SQLdm s = new SQLdm();
    SQLiteDatabase db;//
    Cursor cursor;
private RecyclerView rv;
private CommonAdapter<Menu> commonAdapter;
private List<Menu> menuList=new ArrayList<>();
private String name;
private String step;

private  List<Script> scripts=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdatabase);

        inintView();

        db = s.openDatabase(getApplicationContext());

        name=getIntent().getStringExtra("name");
        step=getIntent().getStringExtra("step");
        if (!TextUtils.isEmpty(name)){
            cursor = db.rawQuery("select * from Menu where mname LIKE ?", new String[]{"%"+name+"%"});
            sss();
        }else if (!TextUtils.isEmpty(step)){
            cursor = db.rawQuery("select * from Script where material LIKE ?", new String[]{"%"+step+"%"});
            fff();
        }



    }        //打开数据库输出流

    private void fff() {
        while (cursor.moveToNext()){
            Script menu =new Script();
            menu.setMid(cursor.getString(cursor.getColumnIndex("MID")));
            menu.setMaterial(cursor.getString(cursor.getColumnIndex("material")));
            menu.setScriptId(cursor.getString(cursor.getColumnIndex("ScriptId")));

            menu.setUsage(cursor.getString(cursor.getColumnIndex("Usage")));
            scripts.add(menu);
        }
        cursor.close();

        for (Script s : scripts) {
            cursor = db.rawQuery("select * from Menu where MID = ?", new String[]{s.getMid()});
            while (cursor.moveToNext()){
                Menu menu =new Menu();
                menu.setMid(cursor.getString(cursor.getColumnIndex("MID")));
                menu.setMname(cursor.getString(cursor.getColumnIndex("mname")));
                menu.setScriptId(cursor.getString(cursor.getColumnIndex("ScriptId")));

                menu.setHeadPhoto(cursor.getBlob(cursor.getColumnIndex("headphoto")));
                menu.setStepId(cursor.getString(cursor.getColumnIndex("StepID")));
                menu.setCreateTime(cursor.getString(cursor.getColumnIndex("Createtime")));
                menuList.add(menu);
            }
        }
        if (menuList.size()==0){
            Toast.makeText(this, "暂无搜索道菜谱", Toast.LENGTH_SHORT).show();
        }
        commonAdapter.notifyDataSetChanged();
        cursor.close();
    }

    private void inintView() {
        rv=findViewById(R.id.rv);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutmanager);
        commonAdapter=new CommonAdapter<Menu>(this,R.layout.item_menu,menuList) {
            @Override
            protected void convert(ViewHolder holder, Menu menu, int position) {
                holder.setText(R.id.actv_name,menu.getMname())
                        .setText(R.id.actv_desc,menu.getCreateTime());
                ImageView iv=holder.getView(R.id.iv_coin);
                GlideUtils.load(mContext,menu.getHeadPhoto(),iv);
            }
        };

        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent =new Intent(databbb.this,display.class);
                intent.putExtra("mid",commonAdapter.getDatas().get(position).getMid());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rv.setAdapter(commonAdapter);
    }


    public void sss(){
        while (cursor.moveToNext()){
            Menu menu =new Menu();
            menu.setMid(cursor.getString(cursor.getColumnIndex("MID")));
            menu.setMname(cursor.getString(cursor.getColumnIndex("mname")));
            menu.setScriptId(cursor.getString(cursor.getColumnIndex("ScriptId")));

            menu.setHeadPhoto(cursor.getBlob(cursor.getColumnIndex("headphoto")));
            menu.setStepId(cursor.getString(cursor.getColumnIndex("StepID")));
            menu.setCreateTime(cursor.getString(cursor.getColumnIndex("Createtime")));
            menuList.add(menu);
    }
    if (menuList.size()==0){
        Toast.makeText(this, "暂无搜索道菜谱", Toast.LENGTH_SHORT).show();
    }
        commonAdapter.notifyDataSetChanged();
    cursor.close();

}
}
