package co.oncelog.blockcall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import co.oncelog.blockcall.adapter.PhoneCallAdapter;
import co.oncelog.blockcall.dao.BlackList;
import co.oncelog.blockcall.dao.BlackListDAO;
import co.oncelog.blockcall.manager.PhoneListManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputNumber;
    Button btnAdd;
    ListView listView;
    static BlackListDAO blackListDAO;
    static PhoneCallAdapter phoneCallAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
    }

    private void initInstance() {
        inputNumber = (EditText) findViewById(R.id.inputNumber);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        blackListDAO = new BlackListDAO(this);

        listView = (ListView) findViewById(R.id.listView);
        phoneCallAdapter = new PhoneCallAdapter();
        listView.setAdapter(phoneCallAdapter);

        PhoneListManager.getInstance().setDao(BlackListDAO.getAllBlacklist());
    }

    @Override
    public void onClick(View view) {
        if(view == btnAdd){

            if(inputNumber.getText().toString().trim().length() == 10){
//                Toast.makeText(this,
//                        "ทดสอบ",
//                        Toast.LENGTH_SHORT).show();
                final BlackList phone = new BlackList();
                phone.setPhoneNumber(inputNumber.getText().toString().trim());
                blackListDAO.create(phone);
                PhoneListManager.getInstance().setDao(BlackListDAO.getAllBlacklist());
                phoneCallAdapter.notifyDataSetChanged();
            }
        }
    }
}
