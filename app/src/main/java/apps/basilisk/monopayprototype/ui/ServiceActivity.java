package apps.basilisk.monopayprototype.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import apps.basilisk.monopayprototype.R;
import apps.basilisk.monopayprototype.databinding.ActivityMainBinding;
import apps.basilisk.monopayprototype.ui.fragment.faq.FaqFragment;
import apps.basilisk.monopayprototype.ui.fragment.home.HomeFragment;
import apps.basilisk.monopayprototype.ui.registration.RegistrationActivity;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.actionbar_title);
        actionBar.setBackgroundDrawable(getDrawable(R.drawable.shape_toolbar));

        switchFragment(HomeFragment.newInstance(), false, false);
    }

    public void switchFragment(Fragment fragment, boolean addToBackStack, boolean hideActionBar) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_fragment, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();

        if (hideActionBar) {
            hideActionBar();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_faq:
                switchFragment(FaqFragment.newInstance(), true, true);
                return true;
            case R.id.action_registration:
                Intent intent = new Intent(this, RegistrationActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void hideActionBar() {
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

}
