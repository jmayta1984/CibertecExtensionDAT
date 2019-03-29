package pe.edu.cibertec.agendafragment;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Fragment frDetail = new DetailContactFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frDetailContact, frDetail)
                    .commit();
        }


    }
}
