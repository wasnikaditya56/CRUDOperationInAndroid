package com.negaveez.crudoperationinandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.webkit.WebView;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class WebActivity extends AppCompatActivity
{
    WebView webview;
    Button button;
    Context mContext;
//D:\AndroidProjects\CRUDOperationInAndroid\app\src\main\res\assets
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();



//********************************
 /*       File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),  "assets");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String path =folder.getPath();
        Uri myImagesdir = Uri.parse("content://" + path );
        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
        pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenintent.setDataAndType(myImagesdir, "application/msword");

     //   intent.setDataAndType(myImagesdir,"");
      //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       // intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
*/


//****************
      /*  File file = new File(Environment.getExternalStorageDirectory(),
                "privacy_policy_sec.docx");
        Uri path = Uri.fromFile(file);
        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
        pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenintent.setDataAndType(path, "application/msword");
        try {
           startActivity(pdfOpenintent);
        }
        catch (ActivityNotFoundException e) {
    e.printStackTrace();
        }*/


       /* setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       // button = (Button) findViewById( R.id.mybutt );

        String pdf = "file:///D:/privacy_policy_sec.docx";
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
      //  webview.getSettings().setPluginsEnabled(true);
        webview.getSettings().setAllowFileAccess(true);
        //File file = new File(Environment.getExternalStorageDirectory() + "/privacy_policy_sec.docx");
        File file = new File(Environment.getExternalStorageDirectory() + "file:///D:/privacy_policy_sec.docx");

        final Uri uri = Uri.fromFile(file);

        webview.loadUrl(uri.toString());
*/
     /*   File myFile = new File("file:///D:/privacy_policy_sec.docx");
        try {
            FileOpen.openFile(mContext, myFile);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }



    public static class FileOpen {

        public static void openFile(Context context, File url) throws IOException {
            // Create URI
            File file=url;
            Uri uri = Uri.fromFile(file);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            // Check what kind of file you are trying to open, by comparing the url with extensions.
            // When the if condition is matched, plugin sets the correct intent (mime) type,
            // so Android knew what application to use to open the file
            if (url.toString().contains(".doc") || url.toString().contains(".docx"))
            {
                // Word document
                intent.setDataAndType(uri, "application/msword");
            }
            else {

                intent.setDataAndType(uri, "*/*");
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }



}