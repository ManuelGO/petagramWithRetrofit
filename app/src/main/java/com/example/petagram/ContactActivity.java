package com.example.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactActivity extends AppCompatActivity {
    EditText editTextName, editTextEmail, editTextFeedbackBody;
    Button btnSendEmail;
    String sEmail, sPassword;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        toolbar = (Toolbar) findViewById(R.id.action_bar_contact);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editTextName        = findViewById(R.id.EditTextName);
        editTextEmail       = findViewById(R.id.EditTextEmail);
        editTextFeedbackBody = findViewById(R.id.EditTextFeedbackBody);
        btnSendEmail        = findViewById(R.id.btnSendEmail);
        final String toAddress = "";

        // Sender email credentials:
        sEmail = "";
        sPassword = "";
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                //Init session:

                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail, sPassword);
                    }
                });

                try {
                    //Init email content:
                    Message message = new MimeMessage(session);
                    //Sender email:
                    message.setFrom(new InternetAddress(sEmail));
                    // Recipient:
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

                    //Message:
                    message.setText(editTextFeedbackBody.getText().toString().trim());

                    // send email:

                  //  new Send

                } catch (MessagingException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_about:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            case R.id.contact_item:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendFeedback(View view) {
        // @TODO
    }

    public class SendMail extends AsyncTask<Message, String, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ContactActivity.this, "Please wait", "Sending email...", true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();
            if (s.equals("Success")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'> Success </font>"));
                builder.setMessage("Mail send successfully");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        editTextName.setText("");
                        editTextEmail.setText("");
                        editTextFeedbackBody.setText("");
                    }
                });
                // show alert diaglo

                builder.show();
            } else {
                Toast.makeText(getApplicationContext(), "Algo ha salido mal", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

