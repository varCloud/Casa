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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rexv666480.oddoventas.Entidades.AgregarPedidoVenta.NuevoProducto;
import com.example.rexv666480.oddoventas.Entidades.Cliente;
import com.example.rexv666480.oddoventas.Entidades.TemplateProducto;
import com.example.rexv666480.oddoventas.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rexv666480 on 22/05/2018.
 */

public class AdapterNuevoPedidoVenta extends BaseAdapter {

    @BindView(R.id.imageNuevoPedidoVenta)
    ImageView imageNuevoPedidoVenta;

    @BindView(R.id.txtSubTotal)
    TextView txtSubTotal;

    @BindView(R.id.txtPrecioUnitario)
    EditText txtPrecioUnitario;

    @BindView(R.id.txtDescuento)
    EditText txtDescuento;

    @BindView(R.id.txtCantidadPedido)
    EditText txtCantidadPedido;

    @BindView(R.id.txtImpuesto)
    EditText txtImpuesto;

    @BindView(R.id.txtUnidadMedida)
    EditText txtUnidadMedida;



    protected Activity activity;
    protected List<NuevoProducto> items;


    public AdapterNuevoPedidoVenta(Activity activity, List<NuevoProducto> items) {
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
            v = inf.inflate(R.layout.listview_item_productos, null);
        }
        try {
            ButterKnife.bind(this,v);
            NuevoProducto c = items.get(position);
            /*txtAmano.setText("A mano: $"+c.getQty_available().toString()+" Unidad(es)");
            txtPrecio.setText("Precio: $"+c.getLst_price().toString());
            txtNombreProducto.setText(c.getName());
            */
            if (c.getImage_small() != null) {
                if(!c.getImage_small().equals("")) {
                    byte[] data = Base64.decode(c.getImage_small(), Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    imageNuevoPedidoVenta.setImageBitmap(bitmap);
                }
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  v;
    }
}
