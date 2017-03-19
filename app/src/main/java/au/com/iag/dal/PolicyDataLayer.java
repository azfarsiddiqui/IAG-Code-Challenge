package au.com.iag.dal;

import java.util.HashMap;
import java.util.List;

import au.com.iag.entity.Policy;
import au.com.iag.network.APIInterface;
import rx.Observable;

/**
 * Created by siddiquim on 3/19/17.
 */

public class PolicyDataLayer extends BaseDataLayer {

    APIInterface mAPIInterface;

    public PolicyDataLayer() {
        super();
        mAPIInterface = getRetrofit().create(APIInterface.class);
    }

    @Override
    protected String getBaseUrl() {
        return "https://www.dropbox.com/s/r23f4oxq3qri8n3/";
    }

    public Observable<List<Policy>> getList() {
        HashMap<String, Integer> queryMap = new HashMap<>();
        queryMap.put("dl", 1);
        return mAPIInterface.getPolicyList(queryMap);
    }
}
