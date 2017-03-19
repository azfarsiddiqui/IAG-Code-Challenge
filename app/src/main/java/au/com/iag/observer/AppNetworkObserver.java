package au.com.iag.observer;

import android.content.Context;
import android.widget.Toast;

import au.com.iag.R;
import rx.Observer;

/**
 * Created by siddiquim on 3/19/17.
 */

public class AppNetworkObserver<T> implements Observer<T> {

    private Context mContext;

    public AppNetworkObserver(Context context) {
        mContext = context;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(mContext, R.string.generic_network_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(T t) {

    }
}
