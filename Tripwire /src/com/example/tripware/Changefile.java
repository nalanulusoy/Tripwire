package com.example.tripware;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import javax.sql.DataSource;
import java.io.*;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ulusoyy on 06.05.2015.
 */
public class Changefile extends ListActivity {
    String path = "/sdcard/Tripwire";
    String tripwire = "Tripwire.txt";
    File file_tripwire;
    BufferedWriter writer;
    CustomListAdapter adapter;
    ArrayList<String> Change_list;
    String değişen="TRİPWİRE DEĞİŞEN DOSYALAR";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changefile);
        Bundle b = getIntent().getExtras();


         Change_list = b.getStringArrayList("change");
        final ListView listView = getListView();
        file_tripwire = new File(path, tripwire);



        adapter = new CustomListAdapter(this,Change_list);
        ListView lv = getListView();
        setListAdapter(adapter);



        final Button btn_geri = (Button) findViewById(R.id.button_geri);
        btn_geri.setOnClickListener(new Button.OnClickListener()

        {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        ResultActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu3, menu);

        return true;

    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.anasayfa:
                Intent intent2 = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent2);


                return true;
            case R.id.mail:


                try {
                    writer = new BufferedWriter(new FileWriter(file_tripwire, true));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer.write(değişen);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                for (String filename:Change_list) {
                    System.out.println(filename);
                    try {
                        writer.write(filename);
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }



                sendIntentToGmailApp(file_tripwire);
                 DosyaSil(tripwire);
                return true;



            default:

                return super.onContextItemSelected(item);

        }

    }
    private void sendIntentToGmailApp(File Tripwire) {
        if (Tripwire != null) {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_SUBJECT,"Tripwire Değişen Dosyaların Bilgilendirmesi");
            email.putExtra(Intent.EXTRA_TEXT,file_tripwire);
            email.putExtra(Intent.EXTRA_STREAM,Uri.parse("file://" + file_tripwire.getAbsoluteFile()));
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Send Text File"));
        }
    }

    public static void DosyaSil(String URL) {
        File f = new File(URL); // yol belirtmeyip sadece dosya ismi belirttiğimiz zaman otomatik olarak bulunduğu klasöre göre işlem yapar.

        if (!f.exists()) { // eğer dosya yoksa
            System.out.println("Dosya bulunamadığından silinemedi");
        } else {
            f.delete(); // eğer dosyamız varsa.. // silme işlemi gerçekleştirir.
            System.out.println(f.getName() + " adlı dosya başarılı bir şekilde silinmiştir.");
        }
    }
}