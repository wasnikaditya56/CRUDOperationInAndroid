package com.negaveez.crudoperationinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity
{
    EditText name, password,first, second;
    Button save, delete, get, submit, secondget;
    String Name, Password, First, Second;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder,SQLiteDataBaseQueryHolder1;
    Button EnterData;
    Boolean EditTextEmptyHold;
    String deviceStartNEndTime1,deviceStartNEndTimeCount;
    //--------------- Dynamic Table Layout -----------------
    TableLayout layout;
    TableRow[] tr;
    TextView[] txt;
    TextView [] txt1;
    TableRow[] tableRow;
    TextView[] edt_refNo,edt_partyName;
     int item_Count=0, timeCount;
    //-------------- All ArrayList --------------------
    List<String> NameList = new ArrayList<String>();
    List<String> PasswordList = new ArrayList<String>();
    List<String> IDList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);

        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);
        get = findViewById(R.id.get);
        submit = findViewById(R.id.second_save);
        secondget = findViewById(R.id.secondget);
        layout = (TableLayout)findViewById(R.id.layout_r_payble_receivable1);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                CheckEditTextStatus();
                InsertDataIntoSQLiteDatabase();
                EmptyEditTextAfterDataInsert();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                InsertDataSubmit();

            }
        });
        secondget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                secondGet();
            }
        });


        delete();
        getData();


    }

    private void secondGet()
    {

        DBController db = new DBController(MainActivity.this);
        String getDeviceSetupAddRule = db.getDataSecond();
        System.out.println("My databse ::::: "+getDeviceSetupAddRule);

        String[] StartTime1 = getDeviceSetupAddRule.split("#:#:#");
        deviceStartNEndTime1 = StartTime1[0];
        deviceStartNEndTimeCount = StartTime1[1];
        timeCount = Integer.parseInt(deviceStartNEndTimeCount);
        System.out.println("deviceStartNEndTime1::::113 "+deviceStartNEndTime1);
        System.out.println("deviceStartNEndTimeCount::::144 "+deviceStartNEndTimeCount);


    }

    private void InsertDataSubmit()
    {
        First = first.getText().toString() ;
        Second = second.getText().toString();

        DBController db = new DBController(MainActivity.this);
        db.insertDataSubmit(First,Second);
        Toast.makeText(MainActivity.this, "Data Store Successfully ", Toast.LENGTH_SHORT).show();


    }

    private void getData()
    {
        get.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v)
            {
                DBController db = new DBController(MainActivity.this);
                String getDeviceSetupAddRule = db.getDataFromDatabase();
                System.out.println("My databse ::::: "+getDeviceSetupAddRule);

                String[] StartTime1 = getDeviceSetupAddRule.split("#:#:#");
                deviceStartNEndTime1 = StartTime1[0];
                deviceStartNEndTimeCount = StartTime1[1];
                timeCount = Integer.parseInt(deviceStartNEndTimeCount);
                System.out.println("deviceStartNEndTime1:::: "+deviceStartNEndTime1);
                System.out.println("deviceStartNEndTimeCount:::: "+deviceStartNEndTimeCount);


                String[] str;

                for(int i=1;i<=timeCount;i++)
                {
                    String passWord="";

                    str=deviceStartNEndTime1.split("\n");

                    StringTokenizer stringtokenizer1 = new StringTokenizer(str[i],">");
                    while (stringtokenizer1.hasMoreElements())
                    {
                        String naMe = stringtokenizer1.nextElement().toString();
                        passWord = stringtokenizer1.nextElement().toString();
                       String passWord2 = stringtokenizer1.nextElement().toString();


                        NameList.add(naMe);
                        PasswordList.add(passWord);
                        IDList.add(passWord2);

                    }
                }
            }
        });

    }

    private void CheckEditTextStatus()
    {
                Name = name.getText().toString() ;
                Password = password.getText().toString();

                if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(Password))
                {

                    EditTextEmptyHold = false ;

                }
                else
                    {

                    EditTextEmptyHold = true ;
                }


    }


    private void delete()
    {

        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               DBController db = new DBController(MainActivity.this);
                db.deleteDeviceList("sdfds");
                Toast.makeText(MainActivity.this, "Data Delete Successfully ", Toast.LENGTH_SHORT).show();


               // SQLiteDataBaseQueryHolder1 = "delete from table_name where rowid=2";

               /* try
                {
                    sqLiteDatabaseObj.execSQL("delete from table_name where rowid=2");
                    Toast.makeText(MainActivity.this,"Data deleted Successfully", Toast.LENGTH_LONG).show();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }*/

            }
        });

    }

    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase("AndroidJSonDataBase.db", Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild(){

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS table_name( name TEXT, password TEXT)");
        //sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS table_name( name VARCHAR, password VARCHAR);");
       // sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS table_name(name_id INTEGER NOT NULL, name VARCHAR, password VARCHAR);");

    }


    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHold == true)
        {

            SQLiteDataBaseQueryHolder = "INSERT INTO table_name(name,password) VALUES('"+Name+"', '"+Password+"');";

            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            Toast.makeText(MainActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(MainActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }


    public void EmptyEditTextAfterDataInsert()
    {
        name.getText().clear();
        password.getText().clear();
    }


}