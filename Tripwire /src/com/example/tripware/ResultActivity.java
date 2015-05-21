package com.example.tripware;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
public class ResultActivity extends ListActivity {
    Scanner s = null;
    ArrayList<String> Secilenler;

    String new_path = "/sdcard";
    int linenumber_kritik = 0;

    int linenumber_normal = 0;
    String dosyasum;
    SummaryActivity summary;
    File parent = null;

    String bosluk = "   ";

    int Kritik_line = 0;

    int Normal_line = 0;
    Scanner ss1;
    Scanner ss2;
    Scanner sc1;
    Scanner sc2;
    BufferedWriter writer1 = null;
    BufferedWriter writer2 = null;
    int Kritik = 1;
    int Normal = 1;

    ArrayList<String> kritik_list;
    ArrayList<String> normal_list;
    ArrayList<String> change_list;

CustomListAdapter adapter;

    String change;
    String Kritik_kontrol = "KritikKontrol.txt";
    String Normal_kontrol = "NormalKontrol.txt";
    File file_kritik_kontrol;
    File file_normal_kontrol;
    String path = "/sdcard/Tripwire";

    String Kritik_Dosya="Kritik Dosyalar";
    String Normal_Dosya="Normal Dosyalar";
    File f1;
    File f2;
    File f3;
    File f4;
    Scanner   scanner1;
    Scanner   scanner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        change_list= new ArrayList<String>();
        File folder = new File(new_path + "/Tripwire");
        boolean success = true;
        if (!folder.exists()) {
            //Toast.makeText(MainActivity.this, "Directory Does Not Exist, Create It", Toast.LENGTH_SHORT).show();
            success = folder.mkdir();
        }
        if (success) {
            //Toast.makeText(MainActivity.this, "Directory Created", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(MainActivity.this, "Failed - Error", Toast.LENGTH_SHORT).show();
        }

        f2 = new File("/sdcard/Tripwire/Normal.txt");
        f1 = new File("/sdcard/Tripwire/Kritik.txt");
        f3=new File("/sdcard/Tripwire/Secilenler.txt");
        f4=new File("/sdcard/Tripwire/KritikKontrol.txt");
        file_kritik_kontrol = new File(path, this.Kritik_kontrol);
        file_normal_kontrol = new File(path, this.Normal_kontrol);


        if(f1.exists()) {
            try {
                writer1 = new BufferedWriter(new FileWriter(file_kritik_kontrol, true));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                ss1 = new Scanner(new File("/sdcard/Tripwire/KritikSave2.txt"));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            kritik_list = new ArrayList<String>();

            while (ss1.hasNext()) {
                kritik_list.add(ss1.next());
            }

            ss1.close();
            if (Kritik == 1) {

                for (String value : kritik_list) {

                    File file = new File(value);


                    try {
                        parent = file.getCanonicalFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {

                        dosyasum = summary.getMd5OfFile(parent.getCanonicalFile());


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        writer1.write(value);
                        writer1.write(bosluk);
                        writer1.write(dosyasum);
                        writer1.newLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                try {
                    writer1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }



        try {
            scanner1 = new Scanner(new File("/sdcard/Tripwire/KritikKontrol.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            scanner2 = new Scanner(new File("/sdcard/Tripwire/Kritik.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }







if(f4.exists()) {
    compareTextToast(scanner1, scanner2, Kritik_Dosya);
}
        Changefile.DosyaSil("/sdcard/Tripwire/KritikKontrol.txt");










        if(f3.exists()) {
            try {
                s = new Scanner(new File("/sdcard/Tripwire/Secilenler.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Secilenler = new ArrayList<String>();
            while (s.hasNext()) {
                Secilenler.add(s.next());

            }
            s.close();

            adapter = new CustomListAdapter(this,Secilenler);
            setListAdapter(adapter);


        }




        final Button btn_geri = (Button) findViewById(R.id.button_geri);


        btn_geri.setOnClickListener(new Button.OnClickListener()

                                    {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplicationContext(),
                                                    MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }

        );







        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu2, menu);

        return true;

    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.kontrol:
                if(f1.exists()) {
                    try {
                        writer1 = new BufferedWriter(new FileWriter(file_kritik_kontrol, true));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        ss1 = new Scanner(new File("/sdcard/Tripwire/KritikSave2.txt"));

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    kritik_list = new ArrayList<String>();

                    while (ss1.hasNext()) {
                        kritik_list.add(ss1.next());
                    }

                    ss1.close();
                    if (Kritik == 1) {

                        for (String value : kritik_list) {

                            File file = new File(value);


                            try {
                                parent = file.getCanonicalFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {

                                dosyasum = summary.getMd5OfFile(parent.getCanonicalFile());


                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {
                                writer1.write(value);
                                writer1.write(bosluk);
                                writer1.write(dosyasum);
                                writer1.newLine();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                        try {
                            writer1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }


                if(f2.exists()) {
                    try {
                        writer2 = new BufferedWriter(new FileWriter(file_normal_kontrol, true));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    try {
                        ss2 = new Scanner(new File("/sdcard/Tripwire/NormalSave2.txt"));

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                    normal_list = new ArrayList<String>();


                    while (ss2.hasNext()) {
                        normal_list.add(ss2.next());

                    }

                    ss2.close();

                    if (Normal == 1) {
                        for (String value : normal_list) {

                            File file = new File(value);

                            try {
                                parent = file.getCanonicalFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {

                                dosyasum = summary.getMd5OfFile(parent.getCanonicalFile());


                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {
                                writer2.write(value);
                                writer2.write(bosluk);
                                writer2.write(dosyasum);
                                writer2.newLine();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        try {
                            writer2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }


                int kritik = 0;

                int normal = 0;
                if (f1.exists()) {
                    try {

                        sc1 = new Scanner(new File("/sdcard/Tripwire/KritikKontrol.txt"));
                        sc2 = new Scanner(new File("/sdcard/Tripwire/Kritik.txt"));
                        compareTextFiles(sc1, sc2, Kritik_Dosya);
                        Kritik = 1;
                        Normal = 0;

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                if (f2.exists()) {
                    try {
                        sc1 = new Scanner(new File("/sdcard/Tripwire/NormalKontrol.txt"));
                        sc2 = new Scanner(new File("/sdcard/Tripwire/Normal.txt"));
                        compareTextFiles(sc1, sc2, Normal_Dosya);
                        Normal = 1;
                        Kritik = 0;


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                Changefile.DosyaSil("/sdcard/Tripwire/KritikKontrol.txt");
                Changefile.DosyaSil("/sdcard/Tripwire/NormalKontrol.txt");
                Intent intent1 = new Intent(getApplicationContext(),
                        Changefile.class);
                intent1.putStringArrayListExtra("change", change_list);

                startActivity(intent1);
                return true;
            case R.id.sistem:
                Changefile.DosyaSil("/sdcard/Tripwire/Kritik.txt");
                Changefile.DosyaSil("/sdcard/Tripwire/KritikKontrol.txt");
                Changefile.DosyaSil("/sdcard/Tripwire/KritikSave2.txt");
                Changefile.DosyaSil("/sdcard/Tripwire/Normal.txt");
                Changefile.DosyaSil("/sdcard/Tripwire/NormalSave2.txt");
                Changefile.DosyaSil("/sdcard/Tripwire/NormalKontrol.txt");
                Changefile.DosyaSil("/sdcard/Tripwire/Secilenler.txt");
                Intent intent2 = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent2);

                return true;

            case R.id.baslat:

                askReboot();

                return true;


            default:

                return super.onContextItemSelected(item);

        }

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== android.view.KeyEvent.KEYCODE_BACK){}

        return false;
        // Disable back button..............
    }


    protected void askReboot() {
        /* Ask if user wants to reboot */
        new AlertDialog.Builder(this)
                .setMessage("Cihazınızı yeniden başlatmak istiyormusunuz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        execCommandAsRoot("reboot");
                    }
                })
                .setNegativeButton("Hayır", null)
                .create().show();
    }

    public boolean execCommandAsRoot(String path) {
        try {
            String[] cmd = {"su", "-c", path};
            Process p = Runtime.getRuntime().exec(cmd);
            int exitCode = p.waitFor();

            return (exitCode == 0);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
    public void compareTextFiles(Scanner s1, Scanner s2,String filename) {
        String line1;
        String line2;
        String değişen1="KRİTİK DOSYALAR";
        String değişen2="NORMAL DOSYALAR";
        int count1 = 0;
        int count2 = 0;
        int count=0;
        // while we have lines left in both files, compare and
        // print the lines that don't match
        if(filename.equals(Kritik_Dosya )&& count==0){
            change_list.add(değişen1);
        }
        if(filename.equals(Normal_Dosya)&&count==0){
            change_list.add(değişen2);
        }




        while (s1.hasNextLine() && s2.hasNextLine()) {
             line1 = s1.nextLine().trim();
            count1++;
            line2 = s2.nextLine().trim();
            count2++;

            if (!line1.equalsIgnoreCase(line2)) {

                StringTokenizer S = new StringTokenizer(line2);
                while (S.hasMoreTokens())
                // parçalanacak ifade varsa; true değeri döner ve döngü işlenir
                {
                    change = S.nextToken();
                    if (count %2 == 0) {

                        System.out.println(change);
                        change_list.add(change);

                    }
                    count++;

                }
                System.out.println("Line " + count1 + " differs.");
                System.out.println("< " + line1);
                System.out.println("> " + line2);
            } else {
                System.out.println("Line " + count1 + " matches.");

            }

        }
        while (s1.hasNextLine()) {
            line1 = s1.nextLine();
            count1++;
            System.out.println("Line " + count1 + " differs.");
            System.out.println("< " + line1);
            System.out.println("> ");


        }
        // any leftover lines in file2 count as differences
        while (s2.hasNextLine()) {

             line2 = s2.nextLine();
            count2++;
            System.out.println("Line " + count2 + " differs.");
            System.out.println("< ");
            System.out.println("> " + line2);

        }  // any leftover lines in file1 count as differences

    }


    public void compareTextToast(Scanner s1, Scanner s2,String filename) {

        String line1;
        String line2;

        int count1 = 0;
        int count2 =0;

        while (s1.hasNextLine() && s2.hasNextLine()) {
            line1 = s1.nextLine().trim();
            count1++;
            line2 = s2.nextLine().trim();
            count2++;

            if (!line1.equalsIgnoreCase(line2)) {
                if(Kritik_Dosya.equals(filename)) {
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.cust_toast,
                            (ViewGroup) findViewById(R.id.relativeLayout1));

                    Toast toast=new Toast(this);
                    toast.setView(view);
                    toast.show();
                }

                System.out.println("Line " + count1 + " differs.");
                System.out.println("< " + line1);
                System.out.println("> " + line2);
            } else {
                System.out.println("Line " + count1 + " matches.");

            }

        }
        while (s1.hasNextLine()) {
            line1 = s1.nextLine();
            count1++;
            System.out.println("Line " + count1 + " differs.");
            System.out.println("< " + line1);
            System.out.println("> ");


        }
        // any leftover lines in file2 count as differences
        while (s2.hasNextLine()) {

            line2 = s2.nextLine();
            count2++;
            System.out.println("Line " + count2 + " differs.");
            System.out.println("< ");
            System.out.println("> " + line2);

        }  // any l
    }



}