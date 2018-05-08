package com.example.rexv666480.oddoventas.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.rexv666480.oddoventas.Entidades.PedidoVenta;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCCallback;
import com.example.rexv666480.oddoventas.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rexv666480 on 08/05/2018.
 */

public class AdapterPedidoVenta  extends BaseAdapter {

    @BindView(R.id.txtEstatus)
    TextView txtEstatus;

    @BindView(R.id.txtNombre)
    TextView txtNombre;

    @BindView(R.id.txtNoPedido)
    TextView txtNoPedido;

    @BindView(R.id.txtTotal)
    TextView txtTotal;

    protected Activity activity;
    protected List<PedidoVenta> items;

    public AdapterPedidoVenta (Activity activity, List<PedidoVenta> items) {
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
            v = inf.inflate(R.layout.listview_item_pedido_venta, null);
        }
        ButterKnife.bind(this,v);
        PedidoVenta p = items.get(position);
        txtNombre.setText(p.getPartner_id()[1].toString());
        txtTotal.setText(p.getAmount_total().toString());
        txtNoPedido.setText(p.getName()+" "+p.getDate_order());
        txtEstatus.setText(p.getInvoice_status());
        return  v;
    }
}
