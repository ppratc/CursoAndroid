package com.curso.sergio.tabs;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener el ViewPager y configurar su PagerAdapter para mostrar elementos
        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        // Fragment manager para añadir el fragment al viewpager
        FragmentManager manager = getSupportFragmentManager();

        //Creamos un nuevo adapter con el constructor de MiPagerAdapter, usando el FragmentManager anterior
        PagerAdapter adapter = new MiPagerAdapter(manager);

        //Ponemos nuestro Adapter al view pager
        pager.setAdapter(adapter);

        //Ponemos nuestro ViewPager en el TabLayout
        tabLayout.setupWithViewPager(pager);

        // Añadiendo funcionalidad de cambios de tab
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }


}
