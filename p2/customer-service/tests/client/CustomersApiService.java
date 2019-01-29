package client;


import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;


import domain.CustomerAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CustomersApiService {
  /**
   * A client needs to be able to get all registered customer  accounts
   * 
   * @return Call&lt;List&lt;Customer&gt;&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("customers")
  Call<List<CustomerAccount>> getCustomers();
    

  /**
   * A client needs to be able to create a new customer account
   * 
   * @param customerAccount
   * @return Call&lt;Customer&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("customers")
  Call<CustomerAccount> postACustomer(
    @retrofit2.http.Body CustomerAccount customerAccount
  );
  
  /**
   * Update an existing account
   * 
   * @param username  (required)
   * @param customerAccount
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("customers/account/{username}")
  Call<Void> updateAnAccount(
    @retrofit2.http.Path("username") String username, @retrofit2.http.Body CustomerAccount customerAccount
  );

}
