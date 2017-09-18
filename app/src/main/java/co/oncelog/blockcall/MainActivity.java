package co.oncelog.blockcall;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener;

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

        PermissionListener dialogPermissionListener =
                DialogOnDeniedPermissionListener.Builder
                        .withContext(this)
                        .withTitle("Phone permission")
                        .withMessage("Phone permission is needed")
                        .withButtonText(android.R.string.ok)
                        .build();

        PermissionListener compositePermissionListener = new CompositePermissionListener(dialogPermissionListener);

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CALL_PHONE)
                .withListener(compositePermissionListener)
                .check();

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
                inputNumber.setText("");
            }
        }
    }
}
