package client;


import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import domain.Sale;
import domain.Summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SalesApiService {
  /**
   * Create a new sale.
   * 
   * @param sale  (required)
   * @return Call&lt;Sale&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("sales")
  Call<Sale> createANewSale(
    @retrofit2.http.Body Sale sale
  );

  /**
   * Get all sales for a specific customer.
   * 
   * @param id customer ID (required)
   * @return Call&lt;List&lt;Sale&gt;&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("sales/customer/{id}")
  Call<List<Sale>> getAllSalesForASpecificCustomer(
    @retrofit2.http.Path("id") String id
  );
  
   /**
   * Gets a summary for a customer
   * 
   * @param id Customers id (required)
   * @return Call&lt;Summary&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @GET("sales/customer/{id}/summary")
  Call<Summary> getSummaryForACustomer(
    @retrofit2.http.Path("id") String id
  );

}
