package pijay.dev.ligers._class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.fragment.app.FragmentStatePagerAdapter;

import pijay.dev.ligers.R;

public class SliderAdapter extends BaseAdapter {

    Context ctx;
    int images[];
    LayoutInflater inflate;

    public SliderAdapter(Context applicationContext, int images[]) {
        this.ctx = applicationContext;
        this.images = images;
        inflate = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = inflate.inflate(R.layout.splash_items, null);
        ImageView imgv = view.findViewById(R.id.image);
        imgv.setImageResource(images[position]);

        return view;
    }
}
