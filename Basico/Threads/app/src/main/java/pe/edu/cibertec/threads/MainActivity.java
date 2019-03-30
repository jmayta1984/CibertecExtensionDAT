package pe.edu.cibertec.threads;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ProgressBar pbLoad;
    Button btStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbLoad = findViewById(R.id.pbLoad);
        btStart = findViewById(R.id.btStart);

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ProgressBarTask().execute(4);
            }
        });
    }

    private class ProgressBarTask extends AsyncTask<Integer, Integer, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLoad.setProgress(0);
            btStart.setEnabled(false);
            btStart.setText("En progreso");
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            int progress = 0;
            pbLoad.setMax(integers[0]);

            for (int i=0; i<integers[0]; i++){
                try {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(++progress);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pbLoad.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            btStart.setEnabled(true);
            btStart.setText(R.string.bt_start);
        }

    }
}
