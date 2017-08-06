package com.oz_heng.apps.android.ccafamilydaycare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Url of CCA FDC
    private final String CCA_FDC_URL = "http://www.ccasfdc.org.au/";
    // CCA FDC email address
    private final String CCA_FDC_EMAIL_ADDRESS = "fdc@ccas.org.au";
    // Url of CCA FDC on Google Maps
    private final String CCA_FDC_ON_GOOGLE_MAPS = "https://www.google.com.au/maps/place/2+Dickson+Ave,+West+Ryde+NSW+2114/@-33.8062937,151.086607,17z/data=!4m13!1m7!3m6!1s0x6b12a44e1f2ed9b3:0xad57bc445457c27!2s2+Dickson+Ave,+West+Ryde+NSW+2114!3b1!8m2!3d-33.8065383!4d151.0865966!3m4!1s0x6b12a44e1f2ed9b3:0xad57bc445457c27!8m2!3d-33.8065383!4d151.0865966";
    // CCA FDC phone number
    private final String CCA_FDC_PHONE_NUMBER = "+61298740066";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Set setOnClickListener on address TextView to launch a Web browser on the
         * organisation location on Google Maps.
         */
        TextView address = (TextView) findViewById(R.id.org_address);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebPage(CCA_FDC_ON_GOOGLE_MAPS);
            }
        });

        /* Set setOnClickListener on webUrl TextView to launch a Web browser with the
         * organisation web site.
         */
        TextView webUrl = (TextView) findViewById(R.id.org_web_url);
        webUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebPage(CCA_FDC_URL);
            }
        });

        /* Set setOnClickListener on email TextView to launch an email app with
         * organisation email address in To: and "Enquiry" as subject.
         */
        TextView email = (TextView) findViewById(R.id.org_email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeEmail(CCA_FDC_EMAIL_ADDRESS, "Enquiry");
            }
        });

        /* Set setOnClickListener on phone TextView to launch a phone call app with
         * organisation phone number ready to call.
         */
        TextView phone = (TextView) findViewById(R.id.org_call);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialPhoneNumber(CCA_FDC_PHONE_NUMBER);
            }
        });
    }

    /*
     * The following helper methods are from
     * https://developer.android.com/guide/components/intents-common.html
     */

    /**
     * Open a Web browser at the url address.
     *
     * @param url a {@link String}
     */
    private void openWebPage(String url) {
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Open an email app with the given address and subject.
     *
     * @param address a {@link String}
     * @param subject a {@link String}
     */
    private void composeEmail(String address, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:" + address)); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Open a phone call app with the given phoneNumber.
     *
     * @param phoneNumber a {@link String}
     */
    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}



