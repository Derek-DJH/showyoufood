package com.example.derek.showyoufood;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.widget.ImageView;

public class display extends AppCompatActivity {
private ImageView iv_coin,iv_coin1;
private AppCompatTextView actv_name,actv_desc,actv_1,actv_2,actv_3;
private String mid;
    SQLdm s = new SQLdm();
    SQLiteDatabase db;//
    Cursor cursor;
    private Menu menu=new Menu();
    private Script script=new Script();
    private Step step=new Step();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        inintView();
    }

    private void inintView() {
        db = s.openDatabase(getApplicationContext());
        iv_coin=findViewById(R.id.iv_coin) ;
        iv_coin1=findViewById(R.id.iv_coin1) ;
        actv_name=findViewById(R.id.actv_name) ;
        actv_desc=findViewById(R.id.actv_desc) ;
        actv_1=findViewById(R.id.actv_1) ;
        actv_2=findViewById(R.id.actv_2) ;
        actv_3=findViewById(R.id.actv_3) ;

        mid=getIntent().getStringExtra("mid");
        if (!TextUtils.isEmpty(mid)){ //查找菜单
            cursor = db.rawQuery("select * from Menu where MID = ?", new String[]{mid});

            while (cursor.moveToNext()){
                menu.setMid(cursor.getString(cursor.getColumnIndex("MID")));
                menu.setMname(cursor.getString(cursor.getColumnIndex("mname")));
                menu.setScriptId(cursor.getString(cursor.getColumnIndex("ScriptId")));

                menu.setHeadPhoto(cursor.getBlob(cursor.getColumnIndex("headphoto")));
                menu.setStepId(cursor.getString(cursor.getColumnIndex("StepID")));
                menu.setCreateTime(cursor.getString(cursor.getColumnIndex("Createtime")));
            }
            cursor.close();
            cursor = db.rawQuery("select * from Script where MID = ?", new String[]{mid});

            while (cursor.moveToNext()){//查找食材
                script.setMid(cursor.getString(cursor.getColumnIndex("MID")));
                script.setMaterial(cursor.getString(cursor.getColumnIndex("material")));
                script.setScriptId(cursor.getString(cursor.getColumnIndex("ScriptId")));

                script.setUsage(cursor.getString(cursor.getColumnIndex("Usage")));
            }
            cursor.close();
            cursor = db.rawQuery("select * from Step where MID = ?", new String[]{mid});
            while (cursor.moveToNext()){//查找食材
                step.setMID(cursor.getString(cursor.getColumnIndex("MID")));
                step.setSid(cursor.getString(cursor.getColumnIndex("SID")));
                step.setStep(cursor.getString(cursor.getColumnIndex("Step")));

                step.setSteppthoto(cursor.getBlob(cursor.getColumnIndex("Steppthoto")));
            }
            cursor.close();
            GlideUtils.load(this,menu.getHeadPhoto(),iv_coin);
            GlideUtils.load(this,step.getSteppthoto(),iv_coin1);
            actv_name.setText(menu.getMname());
            actv_desc.setText(menu.getCreateTime());
            actv_1.setText(script.getMaterial());
            actv_2.setText(script.getUsage());
            actv_3.setText(step.getStep());
        }
    }
}
