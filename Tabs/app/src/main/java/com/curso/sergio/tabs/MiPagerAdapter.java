package com.curso.sergio.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by usuario on 13/06/2016.
 */
public class MiPagerAdapter extends FragmentStatePagerAdapter {

    //Constructor de mi adapter con un FragmentManager
    public MiPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    //método que nos retorna el Fragment que le corresponde a cada posición del Tab
    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag=new PelisFragment();
                break;
            case 1:
                frag=new SeriesFragment();
                break;

        }
        return frag;
    }


    //Método que retorna el número de elementos. En mi caso siempre será 2
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="Pelis";
                break;
            case 1:
                title="Series";
                break;

        }

        return title;
    }
}