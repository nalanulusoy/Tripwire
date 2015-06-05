package com.example.tripware;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Address;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import com.stericson.RootTools.Constants;
import com.stericson.RootTools.RootTools;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.*;


/**
 * Created by ulusoy on 22.12.2014.
 */
public class MainActivity extends ListActivity {

    String filename;
    String dosyaadı;
    private TextView tv;
    String secilenler = "Secilenler.txt";

    String kritik_Save2 = "KritikSave2.txt";
    String normal_Save2 = "NormalSave2.txt";
    private String path;
    private String com = "cd /data";
    ArrayList<String> ar;
    String normal = "Normal.txt";
    String kritik = "Kritik.txt";
    File file_kritik;
    File file_normal;
    File file_kritik_save2;
    File file_normal_save2;
    File file_secilenler;
    TextView selection;
    EditText inputSearch;
    Button btn_geri;
    List values;
    List dosya;
    List directory;
    int textlength = 0;

    CustomListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        registerForContextMenu(this.getListView());
        ExecuteAsRootBase.canRunRootCommands();
        btn_geri = (Button) findViewById(R.id.button_geri);


        btn_geri.setOnClickListener(new Button.OnClickListener()

                                    {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplicationContext(),
                                                    MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }

        );

        ar = new ArrayList<String>();
        /* dizinlerin başlıklarını ekliyoruz pathe */
        file_kritik = new File(path, this.kritik);
        try {
            file_kritik.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file_normal = new File(path, this.normal);
        try {
            file_normal.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file_secilenler = new File(path, this.secilenler);
        try {
            file_secilenler.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file_kritik_save2 = new File(path, this.kritik_Save2);
        try {
            file_kritik_save2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        file_normal_save2 = new File(path, this.normal_Save2);
        try {
            file_normal_save2.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Bundle b = getIntent().getExtras();

        path = "/";

        if (getIntent().hasExtra("path")) {
            path = getIntent().getStringExtra("path");

        }


        // Read all files sorted into the values-array
        values = new ArrayList();
        dosya = new ArrayList();
        directory = new ArrayList();
        final File dir = new File(path);
        if (!dir.canRead()) {
            try {
                Runtime.getRuntime().exec("su");
            } catch (IOException e) {
            }
            Environment.getRootDirectory();
            Environment.getDataDirectory();
            Environment.getDownloadCacheDirectory();

        }


        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("given param is not a directory");
        }
        File[] files = dir.listFiles();

        if (files != null) {
            for (File f : files) {


            }
        }


        final String[] list = dir.list();

        if (list != null) {
            for (String file : list) {
                File f = new File(file);

                if (f.isFile()) {

                    dosya.add(file);
                    System.out.println(dosya);
                }
                if (f.isDirectory()) {

                    directory.add(file);
                    System.out.println(directory);
                }


                if (!file.startsWith(".")) {

                    values.add(file);

                }

            }
        }


        Collections.sort(values);
        for (int i = 0; i < values.size(); i++) {
            String eleman;
            eleman = (String) values.get(i);
            File f = new File(eleman);
            if (f.isDirectory()) {


            }
            if (f.isFile()) {


            }


        }


        adapter = new CustomListAdapter(this, values);
        ListView lv = getListView();
        setListAdapter(adapter);


        selection = (TextView) findViewById(R.id.selection);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (count > 0) {


                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub



            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }

        return false;
        // Disable back button..............
    }


    private void toastMessage(String s) {
    }
    /*uygulamanın içinde terminal emilatör gibi kod çalıştırma*/

    public void runAsRoot(String command) throws IOException {
        Process p = Runtime.getRuntime().exec("su");


        DataOutputStream os = new DataOutputStream(p.getOutputStream());
        os.writeBytes(command + "\n");
        os.writeBytes("exit\n");
        os.flush();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu);


        return true;

    }

    private void getOverflowMenu() {

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {


            case R.id.izlenen:
                startActivity(new Intent(getApplicationContext(),
                        ResultActivity.class));
                return true;

            case R.id.Arama:

                inputSearch.setVisibility(View.VISIBLE);


                return true;
            case R.id.hakkında:
                startActivity(new Intent(getApplicationContext(),
                       About.class));

                return true;
            default:

                return super.onContextItemSelected(item);

        }

    }






    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String Warning_filename;

        filename = (String) getListAdapter().getItem(position);
        Warning_filename=filename;

        File f = new File(filename);

        File parent = null;
        try {
            parent = f.getCanonicalFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (parent != null) parent.mkdirs();

        if (path.endsWith(File.separator)) {
            filename = path + filename;

        } else {
            filename = path + File.separator + filename;

        }
        dosyaadı=filename.toString();

        ar.add(Warning_filename);




        if (new File(filename).isDirectory()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("path", filename);
            startActivity(intent);




        }

        else {



            Intent intent = new Intent(getApplicationContext(),
                    WarningActivity.class);

            intent.putStringArrayListExtra("filename", ar);

            startActivity(intent);

        }


    }





}







