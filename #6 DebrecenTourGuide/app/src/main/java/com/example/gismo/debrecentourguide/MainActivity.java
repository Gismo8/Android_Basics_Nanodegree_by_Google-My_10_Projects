package com.example.gismo.debrecentourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListItemFragment listItemFragment;

    ArrayList<ListItem> sightList = new ArrayList<>();
    ArrayList<ListItem> cafeList = new ArrayList<>();
    ArrayList<ListItem> restaurantList = new ArrayList<>();
    ArrayList<ListItem> hotelList = new ArrayList<>();

    private String cheapest;
    private String cheap;
    private String moderate;
    private String expensive;

    private String twoStar;
    private String threeStar;
    private String fourStar;
    private String fiveStar;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        cheapest = getString(R.string.cheapest);
        cheap = getString(R.string.cheap);
        moderate = getString(R.string.moderate);
        expensive = getString(R.string.expensive);
        twoStar = getString(R.string.twoStars);
        threeStar = getString(R.string.threeStars);
        fourStar = getString(R.string.fourStars);
        fiveStar = getString(R.string.fiveStars);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillCafeList();
        fillRestaurantList();
        fillSightList();
        fillHotelList();
        fragmentManager = getSupportFragmentManager();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_sights:
                openListItemFragment(sightList, getString(R.string.sights));
                break;
            case R.id.nav_cafes:
                openListItemFragment(cafeList, getString(R.string.cafés));
                break;
            case R.id.nav_restaurants:
                openListItemFragment(restaurantList, getString(R.string.restaurants));
                break;
            case R.id.nav_hotels:
                openListItemFragment(hotelList, getString(R.string.hotels));
                break;
            case R.id.nav_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType(getString(R.string.text_plain));
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.sharevia)));
                break;
            case R.id.nav_send:
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.debrecen_awesome));
                emailIntent.setType(getString(R.string.plain_text));
                startActivity(Intent.createChooser(emailIntent, getString(R.string.send_email)));
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void fillCafeList() {
        cafeList.add(new CafeVM(getString(R.string.voltegyszer_name), getString(R.string.voltegyszer_details), getString(R.string.open_times_9_21), moderate));
        cafeList.add(new CafeVM(getString(R.string.pepepanini_name), getString(R.string.pepepanini_details), getString(R.string.open_times_9_21), cheap));
        cafeList.add(new CafeVM(getString(R.string.kismandula_name), getString(R.string.kismandula_details), getString(R.string.open_times_9_21), moderate));
        cafeList.add(new CafeVM(getString(R.string.maszek_name), getString(R.string.maszek_details), getString(R.string.open_times_9_21), moderate));
        cafeList.add(new CafeVM(getString(R.string.butiq_name), getString(R.string.butiq_details), getString(R.string.open_times_9_21), expensive));
        cafeList.add(new CafeVM(getString(R.string.drazse_name), getString(R.string.drazse_details), getString(R.string.open_times_9_21), cheapest));
    }

    public void fillRestaurantList() {
        restaurantList.add(new RestaurantVM(getString(R.string.melange_name), getString(R.string.melange_details), getString(R.string.open_times_9_21), expensive));
        restaurantList.add(new RestaurantVM(getString(R.string.kasmsmir_name), getString(R.string.kashmir_details), getString(R.string.open_times_9_21), moderate));
        restaurantList.add(new RestaurantVM(getString(R.string.belga_name), getString(R.string.belga_details), getString(R.string.open_times_9_21), expensive));
        restaurantList.add(new RestaurantVM(getString(R.string.ikon_name), getString(R.string.ikon_details), getString(R.string.open_times_9_21), expensive));
        restaurantList.add(new RestaurantVM(getString(R.string.bonita_name), getString(R.string.bonita_details), getString(R.string.open_times_9_21), expensive));
        restaurantList.add(new RestaurantVM(getString(R.string.calico_name), getString(R.string.calico_details), getString(R.string.open_times_9_21), expensive));
        restaurantList.add(new RestaurantVM(getString(R.string.csokonai_name), getString(R.string.csokonai_details), getString(R.string.open_times_9_21), expensive));
    }

    public void fillSightList() {
        sightList.add(new SightVM(getString(R.string.greatchurch_name), getString(R.string.greatchurch_details), R.drawable.greatchurch));
        sightList.add(new SightVM(getString(R.string.smallchurch_name), getString(R.string.smallchurch_details), R.drawable.smallchurch));
        sightList.add(new SightVM(getString(R.string.aranybika_name), getString(R.string.aranybika_details), R.drawable.aranybika));
        sightList.add(new SightVM(getString(R.string.lyciumtree_name), getString(R.string.lyciumtree_details), R.drawable.lyciumtree));
        sightList.add(new SightVM(getString(R.string.university_name), getString(R.string.univerity_details), R.drawable.university));
    }

    private void fillHotelList() {
        hotelList.add(new HotelVM((getString(R.string.aranybika_name)), getString(R.string.aranybika_details), twoStar, cheap));
        hotelList.add(new HotelVM((getString(R.string.divinus_name)), getString(R.string.divinus_details), fiveStar, expensive));
        hotelList.add(new HotelVM((getString(R.string.platan_name)), getString(R.string.platan_details), fourStar, moderate));
        hotelList.add(new HotelVM((getString(R.string.eszti_name)), getString(R.string.eszti_details), twoStar, cheap));
        hotelList.add(new HotelVM((getString(R.string.erdospuszta_name)), getString(R.string.erdospuszta_details), fourStar, moderate));
        hotelList.add(new HotelVM((getString(R.string.centrum_name)), getString(R.string.centrum_details), threeStar, moderate));
        hotelList.add(new HotelVM((getString(R.string.lyceum_name)), getString(R.string.lyceum_details), twoStar, cheap));
    }

    private void openListItemFragment(ArrayList<ListItem> items, String title) {
        listItemFragment = new ListItemFragment(items, title);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, listItemFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
