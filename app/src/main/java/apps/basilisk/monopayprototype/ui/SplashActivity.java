package apps.basilisk.monopayprototype.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
        finish();
    }
}


