package co.oncelog.blockcall.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import co.oncelog.blockcall.PhonListItem;
import co.oncelog.blockcall.manager.PhoneListManager;

/**
 * Created by son_g on 9/7/2017.
 */

public class PhoneCallAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        if(PhoneListManager.getInstance().getDao() == null){
            return 0;
        }
        return PhoneListManager.getInstance().getDao().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PhonListItem item;
        if(view != null){
            item = (PhonListItem) view;
        }else{
            item = new PhonListItem(viewGroup.getContext());
        }
        item.setPhonNumber(PhoneListManager.getInstance().getDao().get(i).getPhoneNumber());
        return item;
    }
}
