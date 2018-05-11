package com.example.rexv666480.oddoventas.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rexv666480.oddoventas.Entidades.Cliente;
import com.example.rexv666480.oddoventas.Entidades.PedidoVenta;
import com.example.rexv666480.oddoventas.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rexv666480 on 09/05/2018.
 */

public  class AdapterClientes extends BaseAdapter {

    @BindView(R.id.txtNombreCliente)
    TextView txtNombreCliente;

    @BindView(R.id.txtDireccionCliente)
    TextView txtDireccionCliente;

    @BindView(R.id.txtCorreoCliente)
    TextView txtCorreoCliente;


    @BindView(R.id.imageCliente)
    ImageView imageCliente;


    protected Activity activity;
    protected List<Cliente> items;


    public AdapterClientes(Activity activity, List<Cliente> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.listview_item_clientes, null);
        }
        ButterKnife.bind(this,v);
        try {
            Cliente c = items.get(position);
            txtCorreoCliente.setText(c.getEmail());
            txtDireccionCliente.setText(c.getCountry_id()[1].toString()+" "+c.getStreet());
            txtNombreCliente.setText(c.getDisplay_name());
            if (c.getImage_small() != null) {
                if(!c.getImage_small().equals("")) {
                    byte[] data = Base64.decode(c.getImage_small(), Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    imageCliente.setImageBitmap(bitmap);
                }
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  v;
    }
}
