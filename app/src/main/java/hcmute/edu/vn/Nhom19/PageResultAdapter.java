package hcmute.edu.vn.Nhom19;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageResultAdapter extends FragmentStatePagerAdapter {
    public PageResultAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        Fragment frag= null;
        switch (position){
            case 0:
                frag = new FragmentOneResult();
                break;
            case 1:
                frag = new FragmentTwoResult();
                break;
            case 2:
                frag = new FragmentThreeResult();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "One";
                break;
            case 1:
                title = "Two";
                break;
            case 2:
                title = "Three";
                break;
        }
        return title;
    }
}
