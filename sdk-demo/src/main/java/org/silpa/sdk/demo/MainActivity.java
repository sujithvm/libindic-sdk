package org.silpa.sdk.demo;

/**
 * Created by sujith on 18/5/14.
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private String[] mModuleTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private static final int SOUNDEX = 0;
    private static final int PAYYANS = 1;
    private static final int KATAPAYADI = 2;
    private static final int STEMMER = 3;
    private static final int SCRIPT_RENDERER = 4;
    private static final int CHARACTER_DETAILS = 5;
    private static final int FORTUNE = 6;
    private static final int SYLLABIFIER = 7;
    private static final int TRANSLITERATOR = 8;
    private static final int NGRAM = 9;
    private static final int SHINGLING = 10;
    private static final int INEXACT_SEARCH = 11;
    private static final int GUESS_LANGUAGE = 12;
    private static final int TEXT_SIMILARITY = 13;
    private static final int HYPHENATION = 14;
    private static final int UCA_SORT = 15;
    private static final int SPELL_CHECKER = 16;

    private static String LOG_TAG = "Main Activity";

    @SuppressLint("AppCompatMethod")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_main);

        mTitle = mDrawerTitle = getTitle();
        mModuleTitles = getResources().getStringArray(
                R.array.modules_titles_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item_layout, mModuleTitles));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(getMenuItem(item))) {
            return true;
        }

        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        // change fragments here
        Fragment fragment = null;

        switch (position) {
            case SOUNDEX:
                fragment = new SoundexFragment();
                break;

            case PAYYANS:
                fragment = new PayyansFragment();
                break;

            case FORTUNE:
                fragment = new FortuneFragment();
                break;

            case STEMMER:
                fragment = new StemmerFragment();
                break;

            case SYLLABIFIER:
                fragment = new SyllabifierFragment();
                break;

            case KATAPAYADI:
                fragment = new KatapayadiFragment();
                break;

            case CHARACTER_DETAILS:
                fragment = new CharacterDetailsFragment();
                break;

            case SCRIPT_RENDERER:
                fragment = new ScriptRendererFragment();
                break;

            case TRANSLITERATOR:
                fragment = new TransliteratorFragment();
                break;

            case NGRAM:
                fragment = new NgramFragment();
                break;

            case SHINGLING:
                fragment = new ShinglingFragment();
                break;

            case INEXACT_SEARCH:
                fragment = new InexactSearchFragment();
                break;

            case GUESS_LANGUAGE:
                fragment = new GuessLanguageFragment();
                break;

            case TEXT_SIMILARITY:
                fragment = new TextSimilarityFragment();
                break;

            case HYPHENATION:
                fragment = new HyphenationFragment();
                break;

            case UCA_SORT:
                fragment = new UCASortFragment();
                break;

            case SPELL_CHECKER:
                fragment = new SpellCheckerFragment();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }

        mDrawerList.setItemChecked(position, true);
        setTitle(mModuleTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @SuppressLint("AppCompatMethod")
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private android.view.MenuItem getMenuItem(final MenuItem item) {
        return new android.view.MenuItem() {
            @Override
            public int getItemId() {
                return item.getItemId();
            }

            public boolean isEnabled() {
                return true;
            }

            @Override
            public boolean collapseActionView() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean expandActionView() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public ActionProvider getActionProvider() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public View getActionView() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public char getAlphabeticShortcut() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public int getGroupId() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public Drawable getIcon() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Intent getIntent() {
                // TODO Auto-generated method stub
                return null;
            }


            @Override
            public char getNumericShortcut() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public int getOrder() {
                // TODO Auto-generated method stub
                return 0;
            }


            @Override
            public CharSequence getTitle() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public CharSequence getTitleCondensed() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public boolean hasSubMenu() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public SubMenu getSubMenu() {
                return null;
            }

            @Override
            public boolean isActionViewExpanded() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isCheckable() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isChecked() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isVisible() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public android.view.MenuItem setActionProvider(ActionProvider actionProvider) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setActionView(View view) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setActionView(int resId) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setAlphabeticShortcut(char alphaChar) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setCheckable(boolean checkable) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setChecked(boolean checked) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setEnabled(boolean enabled) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setIcon(Drawable icon) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setIcon(int iconRes) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setIntent(Intent intent) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setNumericShortcut(char numericChar) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public ContextMenu.ContextMenuInfo getMenuInfo() {
                return null;
            }

            @Override
            public android.view.MenuItem setShortcut(char numericChar, char alphaChar) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void setShowAsAction(int actionEnum) {
                // TODO Auto-generated method stub

            }

            @Override
            public android.view.MenuItem setShowAsActionFlags(int actionEnum) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setTitle(CharSequence title) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setTitle(int title) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setTitleCondensed(CharSequence title) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public android.view.MenuItem setVisible(boolean visible) {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }
}
