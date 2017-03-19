package au.com.iag;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import au.com.iag.entity.Policy;

public class PolicyEntityTest {
    @Test
    public void testPoliciesSortOrder() throws Exception {
        Policy policy1 = new Policy();
        policy1.setRenewalDate(new Date());
        Thread.sleep(2);

        Policy policy2 = new Policy();
        policy2.setRenewalDate(new Date());
        Thread.sleep(2);

        Policy policy3 = new Policy();
        policy3.setRenewalDate(new Date());
        Thread.sleep(2);

        List<Policy> policyList = Arrays.asList(policy1, policy2, policy3);

        Collections.sort(policyList);

        Assert.assertTrue("Policy 3 should be at 0 index", policyList.get(0).equals(policy3));
    }

    @Test
    public void testHomeInsurancePolicyIconResId() {
        Policy policy = new Policy();
        policy.setType("Home Buildings Insurance");
        Assert.assertTrue("ic_home_insurance should be the resource of Home policy type.",
                policy.getIconResId() == R.drawable.ic_home_insurance);
    }

    @Test
    public void testCTPPolicyIconResId() {
        Policy policy = new Policy();
        policy.setType("CTP GreenSlip Insurance");
        Assert.assertTrue("ic_ctp should be the resource of CTP policy type.",
                policy.getIconResId() == R.drawable.ic_ctp);
    }

    @Test
    public void testCarInsurancePolicyIconResId() {
        Policy policy = new Policy();
        policy.setType("Comprehensive Car Insurance");
        Assert.assertTrue("ic_car_insurance should be the resource of Comprehensive car policy type.",
                policy.getIconResId() == R.drawable.ic_car_insurance);
    }
}