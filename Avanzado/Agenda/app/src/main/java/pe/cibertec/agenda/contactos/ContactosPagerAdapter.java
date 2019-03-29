package pe.cibertec.agenda.contactos;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ContactosPagerAdapter extends FragmentStatePagerAdapter {

    private Fragment[] fragments = new Fragment[] {
            ListaContactosFragment.newInstance(false),
            ListaContactosFragment.newInstance(true)
    };

    private String[] titulos = new String[] {
      "Contactos",
      "Favoritos"
    };

    public ContactosPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titulos[position];
    }
}
