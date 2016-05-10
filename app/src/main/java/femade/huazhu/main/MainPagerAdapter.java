package femade.huazhu.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import femade.huazhu.main.fragment.HomeFragment;
import femade.huazhu.main.fragment.ProfileFragment;
import femade.huazhu.main.fragment.RecommendHotelFragment;
import femade.huazhu.main.htrip.HTripFragment;
import femade.huazhu.main.fragment.WalletFragment;


class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new RecommendHotelFragment();
            case 2:
                return new HTripFragment();
            case 3:
                return new WalletFragment();
            case 4:
                return new ProfileFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

}
