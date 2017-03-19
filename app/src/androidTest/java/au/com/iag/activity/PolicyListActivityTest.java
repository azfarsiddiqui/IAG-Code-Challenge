package au.com.iag.activity;


import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import au.com.iag.R;
import au.com.iag.dal.PolicyDataLayer;
import au.com.iag.entity.Policy;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PolicyListActivityTest {

    @Rule
    public ActivityTestRule<PolicyListActivity> mActivityTestRule = new ActivityTestRule<>(PolicyListActivity.class);

    @Test
    public void testPolicyListApiResponse() {
        PolicyDataLayer policyDataLayer = new PolicyDataLayer();

        policyDataLayer.getList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Policy>>() {
                    @Override
                    public void onCompleted() {
                        Assert.assertTrue("API response parsed successfully.", true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Assert.fail("API response error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(List<Policy> policyList) {

                    }
                });

        waitForApiResponse();
    }

    @Test
    public void testPolicyListUIState() {
        waitForApiResponse();
        onView(withId(R.id.policy_lrv)).perform().check(matches(isDisplayed()));
    }

    @Test
    public void testOrientationChange() {
        waitForApiResponse();
        mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.policy_lrv)).perform().check(matches(isDisplayed()));
    }

    private void waitForApiResponse() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
