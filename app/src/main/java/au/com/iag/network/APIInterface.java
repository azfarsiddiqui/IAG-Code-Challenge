package au.com.iag.network;

import java.util.List;
import java.util.Map;

import au.com.iag.entity.Policy;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by siddiquim on 3/19/17.
 */

public interface APIInterface {

    @GET("policies.json")
    @Headers("Cache-Control: no-cache")
    Observable<List<Policy>> getPolicyList(@QueryMap Map<String, Integer> queryMap);
}
