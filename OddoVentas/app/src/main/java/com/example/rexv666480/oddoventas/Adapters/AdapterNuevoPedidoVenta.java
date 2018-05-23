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

    @BindView(R.id.txtSubTotalNP)
    TextView txtSubTotalNP;

    @BindView(R.id.txtPrecioUnitarioNP)
    TextView txtPrecioUnitarioNP;

    @BindView(R.id.txtDescuentoNP)
    TextView txtDescuentoNP;

    @BindView(R.id.txtCantidadPedidoNP)
    TextView txtCantidadPedidoNP;




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
            v = inf.inflate(R.layout.listview_item_nuevo_pedido_venta, null);
        }
        try {
            ButterKnife.bind(this,v);
            NuevoProducto c = items.get(position);
            txtCantidadPedidoNP .setText("Cantidad: "+c.getCantidadPedido().toString()+" "+c.getUnidadDeMedidaDescripcion());
            txtDescuentoNP.setText("Descuento: "+c.getDescuento().toString());
            txtSubTotalNP.setText("Subtotal: "+c.getSubtotal().toString());
            txtPrecioUnitarioNP.setText("Precio Unitario: "+c.getDescripcionProducto());
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
