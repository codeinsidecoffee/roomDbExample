package com.mrlonewolfer.roomdbexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtName,edtAge;
    ListView lstResult;
    Button btnSave,btnDelete,btnCancel;
    private  List<UserBean> listusers;
    private  UserBean userBean;
    private  UserDao userDao;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context=this;
        setContentView(R.layout.activity_main);

        edtName=findViewById(R.id.edtName);
        edtAge=findViewById(R.id.edtAge);

        lstResult =findViewById(R.id.lstResult);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
        btnDelete=findViewById(R.id.btnDelete);

        AppDatabaseCon appDatabaseCon= Room.databaseBuilder(this,AppDatabaseCon.class,"my_Db")
                .allowMainThreadQueries()
                .build();

        userDao=appDatabaseCon.userDao();

        fetchListofData();

        lstResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userBean=listusers.get(position);
                edtName.setText(userBean.getName());
                edtAge.setText(userBean.getAge()+"");
                btnSave.setText("Update");
                btnDelete.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);
            }
        });


    }

    private void fetchListofData() {
        listusers=userDao.getAllUserBeans();
        ArrayAdapter<UserBean> listArrayUserAdapter=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,listusers);
        lstResult.setAdapter(listArrayUserAdapter);
    }


    public void saveUser(View view) {
        if(userBean==null){
            userBean= new UserBean();
            userBean.setName(edtName.getText().toString());
            userBean.setAge(Integer.parseInt(edtAge.getText().toString()));

            userDao.saveUser(userBean);

        }
        else{
            userBean.setName(edtName.getText().toString());
            userBean.setAge(Integer.parseInt(edtAge.getText().toString()));

            userDao.updateUser(userBean);
        }
        resetAllData(view);
        fetchListofData();
    }

    private void resetAllData(View view) {
        edtName.setText("");
        edtAge.setText("");
        btnSave.setText("Save");
        btnDelete.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        userBean=null;
    }

    public void deleteUser(View view) {

        userDao.removeUser(userBean);
        resetAllData(view);
        fetchListofData();

    }


}
