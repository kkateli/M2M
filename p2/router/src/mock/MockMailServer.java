/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mock;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import java.io.IOException;

/**
 *
 * @author jiaweili
 */
public class MockMailServer {

    public static void main(String[] args) throws IOException {
        String subject = "update-items";
        String body = "{\n" +
"  \"created_at\": \"2018-05-18 01:28:08\",\n" +
"  \"customer\": {\n" +
"    \"balance\": \"0.00000\",\n" +
"    \"company_name\": null,\n" +
"    \"contact_first_name\": \"kate\",\n" +
"    \"contact_last_name\": \"li\",\n" +
"    \"created_at\": \"2018-05-18 01:25:00\",\n" +
"    \"custom_field_1\": null,\n" +
"    \"custom_field_2\": null,\n" +
"    \"custom_field_3\": null,\n" +
"    \"custom_field_4\": null,\n" +
"    \"customer_code\": \"kate-HVW4\",\n" +
"    \"customer_group_id\": \"0afa8de1-147c-11e8-edec-2b197906d816\",\n" +
"    \"date_of_birth\": null,\n" +
"    \"deleted_at\": null,\n" +
"    \"do_not_email\": false,\n" +
"    \"email\": \"kateli@gmail.com\",\n" +
"    \"enable_loyalty\": true,\n" +
"    \"fax\": null,\n" +
"    \"first_name\": \"kate\",\n" +
"    \"id\": \"0afa8de1-147c-11e8-edec-5a3a487670f5\",\n" +
"    \"last_name\": \"li\",\n" +
"    \"loyalty_balance\": \"0.00000\",\n" +
"    \"mobile\": null,\n" +
"    \"note\": null,\n" +
"    \"phone\": null,\n" +
"    \"points\": 0,\n" +
"    \"sex\": null,\n" +
"    \"updated_at\": \"2018-05-18 01:28:08\",\n" +
"    \"year_to_date\": \"339.79999\"\n" +
"  },\n" +
"  \"customer_id\": \"0afa8de1-147c-11e8-edec-5a3a487670f5\",\n" +
"  \"deleted_at\": null,\n" +
"  \"id\": \"ea526336-c717-942a-11e8-5a3aa9e22f80\",\n" +
"  \"invoice_number\": \"6\",\n" +
"  \"note\": \"\",\n" +
"  \"register_id\": \"0afa8de1-147c-11e8-edec-4b6fd0400eb4\",\n" +
"  \"register_sale_payments\": [\n" +
"    {\n" +
"      \"amount\": 339.8,\n" +
"      \"id\": \"ea526336-c717-942a-11e8-5a3ab61b272c\",\n" +
"      \"payment_date\": \"2018-05-18T01:28:04Z\",\n" +
"      \"payment_type\": {\n" +
"        \"has_native_support\": false,\n" +
"        \"id\": \"1\",\n" +
"        \"name\": \"Cash\"\n" +
"      },\n" +
"      \"payment_type_id\": 1,\n" +
"      \"retailer_payment_type\": {\n" +
"        \"config\": null,\n" +
"        \"id\": \"0afa8de1-1450-11e8-edec-056e6ed01696\",\n" +
"        \"name\": \"Cash\",\n" +
"        \"payment_type_id\": \"1\"\n" +
"      },\n" +
"      \"retailer_payment_type_id\": \"0afa8de1-1450-11e8-edec-056e6ed01696\"\n" +
"    }\n" +
"  ],\n" +
"  \"register_sale_products\": [\n" +
"    {\n" +
"      \"discount\": \"0.00000\",\n" +
"      \"id\": \"ea526336-c717-942a-11e8-5a3aac3ee433\",\n" +
"      \"loyalty_value\": \"0.00000\",\n" +
"      \"price\": \"234.69565\",\n" +
"      \"price_set\": false,\n" +
"      \"price_total\": \"234.69565\",\n" +
"      \"product_id\": \"0afa8de1-147c-11e8-edec-056e6f5a732f\",\n" +
"      \"quantity\": 1,\n" +
"      \"tax\": \"35.20435\",\n" +
"      \"tax_id\": \"0afa8de1-1450-11e8-edec-056e6ec70277\",\n" +
"      \"tax_total\": \"35.20435\"\n" +
"    },\n" +
"    {\n" +
"      \"discount\": \"0.00000\",\n" +
"      \"id\": \"ea526336-c717-942a-11e8-5a3aae60d9d7\",\n" +
"      \"loyalty_value\": \"0.00000\",\n" +
"      \"price\": \"60.78261\",\n" +
"      \"price_set\": false,\n" +
"      \"price_total\": \"60.78261\",\n" +
"      \"product_id\": \"0afa8de1-147c-11e8-edec-056e701f4190\",\n" +
"      \"quantity\": 1,\n" +
"      \"tax\": \"9.11739\",\n" +
"      \"tax_id\": \"0afa8de1-1450-11e8-edec-056e6ec70277\",\n" +
"      \"tax_total\": \"9.11739\"\n" +
"    }\n" +
"  ],\n" +
"  \"sale_date\": \"2018-05-18T01:28:04Z\",\n" +
"  \"short_code\": \"hbi2kh\",\n" +
"  \"source\": \"USER\",\n" +
"  \"source_id\": null,\n" +
"  \"status\": \"CLOSED\",\n" +
"  \"taxes\": [\n" +
"    {\n" +
"      \"id\": \"6ecd4ad7-056e-11e8-adec-0afa8de11450\",\n" +
"      \"name\": \"GST\",\n" +
"      \"rate\": \"0.15000\",\n" +
"      \"tax\": 44.32174\n" +
"    }\n" +
"  ],\n" +
"  \"totals\": {\n" +
"    \"total_loyalty\": \"0.00000\",\n" +
"    \"total_payment\": \"339.80000\",\n" +
"    \"total_price\": \"295.47826\",\n" +
"    \"total_tax\": \"44.32174\",\n" +
"    \"total_to_pay\": \"0.00000\"\n" +
"  },\n" +
"  \"updated_at\": \"2018-05-18T01:28:08+00:00\",\n" +
"  \"user\": {\n" +
"    \"created_at\": \"2018-04-29 06:23:48\",\n" +
"    \"display_name\": \"Jiawei Li\",\n" +
"    \"email\": \"liji8162@student.otago.ac.nz\",\n" +
"    \"id\": \"0afa8de1-147c-11e8-edec-4b75e07beac9\",\n" +
"    \"name\": \"liji8162\",\n" +
"    \"target_daily\": null,\n" +
"    \"target_monthly\": null,\n" +
"    \"target_weekly\": null,\n" +
"    \"updated_at\": \"2018-04-29 06:23:48\"\n" +
"  },\n" +
"  \"user_id\": \"0afa8de1-147c-11e8-edec-4b75e07beac9\",\n" +
"  \"version\": 7028701334\n" +
"}";

        // create a mock server than can receive (SMTP) and host (IMAP)
        GreenMail greenMail = new GreenMail(ServerSetupTest.SMTP_IMAP);

        // add a user
        greenMail.setUser("test@localhost", "test@localhost", "password");

        // start the server
        greenMail.start();

        System.out.println("IMAP Server Ready on port " + greenMail.getImap().getPort());

        // a loop for sending 5 messages
        for (int i = 0; i < 5; i++) {

            // let the user press enter to send a message
            System.out.println("Press Enter to send E-Mail " + (i + 1) + " of 5.");
            System.in.read();

            // send a message
            GreenMailUtil.sendTextEmailTest("test@localhost", "test@localhost",
                    subject, body);
        }

        // shutdown
        System.out.println("All E-Mails sent.  Press enter to shutdown mail server.");
        System.in.read();
        greenMail.stop();
    }

}
