package co.oncelog.blockcall.manager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.List;

import co.oncelog.blockcall.dao.BlackList;

/**
 * Created by son_g on 9/3/2017.
 */
public class PhoneListManager{

    private static PhoneListManager instance;
    private List<BlackList> dao;

    public static PhoneListManager getInstance() {
        if (instance == null)
            instance = new PhoneListManager();
        return instance;
    }

    public static void setInstance(PhoneListManager instance) {
        PhoneListManager.instance = instance;
    }

    public List<BlackList> getDao() {
        return dao;
    }

    public void setDao(List<BlackList> dao) {
        this.dao = dao;
    }

}
