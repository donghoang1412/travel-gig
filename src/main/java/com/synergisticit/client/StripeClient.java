package com.synergisticit.client;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
@Component
public class StripeClient {
    @Autowired
    StripeClient() {
        Stripe.apiKey = "sk_test_51IzF9OLMycJd47MxG6UL0IVgbGtURVb5wauoxx1WSp29NmB8OdRWtnLrC4EgIDRSbbNNt2JCLGZ4IByfsTafEmR200cSyEfEB1";
    }
    /*public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("email", email);
        customerParams.put("source", token);
        return Customer.create(customerParams);
    }
    private Customer getCustomer(String id) throws Exception {
        return Customer.retrieve(id);
    } */

    public PaymentIntent chargeCustomerMoney (String token, long amount) throws StripeException {    
    	PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder().setAmount(amount)
				.setCurrency("usd").setConfirm(true).setPaymentMethod(token)
				.setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.MANUAL).build();
		return PaymentIntent.create(createParams);
    }
    
    /*public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
        String sourceCard = getCustomer(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);
        return charge;
    } */
}