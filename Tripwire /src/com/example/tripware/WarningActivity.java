package com.example.tripware;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.*;
import android.widget.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulusoyy on 24.04.2015.
 */
public class WarningActivity extends Activity {
    ArrayAdapter adapter;


    public int Position = 3;

    String kelime;
    String Kritik = "(Kritik)";
    String Normal = "(Normal)";
    int konum1 = 0;

    File parent = null;
    String dosyasum1;
    String dosyasum2;
    SummaryActivity summary;
    String bosluk = "   ";
    String bos = " ";

    Button btn_kaydet;
    BufferedWriter writer1 = null;
    BufferedWriter writer2 = null;
    BufferedWriter writer3 = null;
    BufferedWriter writer4 = null;
    BufferedWriter writer5 = null;
    Button btn_geri;
    ArrayList<String> Warning;
    int kontrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.warning);
        Bundle b = getIntent().getExtras();
        Warning = b.getStringArrayList("filename");

        String[] liste = new String[]{"Kritik", "Normal"};

        TextView selection = (TextView) findViewById(R.id.selection);


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
        Button btn_kaydet1 = (Button) findViewById(R.id.button_kaydet);
        Button btn_kaydet2 = (Button) findViewById(R.id.button_kaydet2);
        btn_kaydet1.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                try {
                    writer1 = new BufferedWriter(new FileWriter("/sdcard/Tripwire/Kritik.txt", true));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer3 = new BufferedWriter(new FileWriter("/sdcard/Tripwire/Secilenler.txt", true));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer4 = new BufferedWriter(new FileWriter("/sdcard/Tripwire/KritikSave2.txt", true));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                for (String value : Warning) {

                    File f = new File(value);
                    try {
                        parent = f.getCanonicalFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dosyasum2 = summary.getMd5OfFile(parent.getCanonicalFile());
                        System.out.println(dosyasum2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        writer1.write(value);
                        writer1.write(bosluk);
                        writer1.write(dosyasum2);
                        writer1.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        writer3.write(value);
                        writer3.write(Kritik);

                        writer3.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        writer4.write(value);
                        writer4.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    writer1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    writer4.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);

            }

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_layout,
                    (ViewGroup) findViewById(R.id.toast_layout_root));

        });





        btn_kaydet2.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {

                try {
                    writer2 = new BufferedWriter(new FileWriter("/sdcard/Tripwire/Normal.txt", true));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer3 = new BufferedWriter(new FileWriter("/sdcard/Tripwire/Secilenler.txt", true));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    writer5 = new BufferedWriter(new FileWriter("/sdcard/Tripwire/NormalSave2.txt", true));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                for (String value : Warning) {
                    File f = new File(value);
                    try {
                        parent = f.getCanonicalFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dosyasum2 = summary.getMd5OfFile(parent.getCanonicalFile());
                        System.out.println(dosyasum2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        writer2.write(value);
                        writer2.write(bosluk);
                        writer2.write(dosyasum2);
                        writer2.newLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        writer3.write(value);
                        writer3.write(Normal);
                        writer3.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        writer5.write(value);
                        writer5.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        writer2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    try {
                        writer3.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        writer5.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);

            }
        });


    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
        }

        return false;
        // Disable back button..............
    }



}
