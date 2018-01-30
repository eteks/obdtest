package com.testndk.root.testndk;

import android.os.AsyncTask;
import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity {

    private MobileServiceClient mClient;

    private MobileServiceTable<Obdcode> mToDoTable;

//    private ToDoItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url_data = stringFromJNI();

        try {
            mClient = new MobileServiceClient(
                    url_data,
                    this
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        final Obdcode item = new Obdcode();
        item.request_code = "test_request1";
        item.response_code = "test_response1";

        System.out.println("output_result");
        System.out.println("output_result"+item);

//        try {
//            mClient.getTable(Obdcode.class).insert(item).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        //Insert the new item
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final Obdcode entity = mClient.getTable(Obdcode.class).insert(item).get();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(!entity.isComplete()){
//                                mAdapter.add(entity);
                            }
                        }
                    });
                } catch (final Exception e) {
//                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }
        };

        runAsyncTask(task);

//        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
    }

    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
