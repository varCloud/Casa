package com.example.rexv666480.oddoventas.Pages;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rexv666480.oddoventas.Adapters.AdapterNuevoPedidoVenta;
import com.example.rexv666480.oddoventas.Entidades.AgregarPedidoVenta.NuevoProducto;
import com.example.rexv666480.oddoventas.Interfaces.Updateable;
import com.example.rexv666480.oddoventas.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PagePedidoVentaProducto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PagePedidoVentaProducto#newInstance} factory method to
 * create an instance of this fragment.
 */


public class PagePedidoVentaProducto extends Fragment implements Updateable {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  AdapterNuevoPedidoVenta adapterNP;
    public List<NuevoProducto> productoList;

    @BindView(R.id.LvNuevoPedidoVenta)
    ListView LvNuevoPedidoVenta;

    private MenuItem menuItemDelete;

    public PagePedidoVentaProducto() {
    }

    public static PagePedidoVentaProducto newInstance(String param1, String param2) {
        PagePedidoVentaProducto fragment = new PagePedidoVentaProducto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page_pedido_venta_producto, container, false);
        ButterKnife.bind(this, v);
        return v;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        try {
            inflater.inflate(R.menu.main, menu);
            menuItemDelete = menu.findItem(R.id.action_delete);
            menuItemDelete.setVisible(false);
            menuItemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    adapterNP.removeSelectedPersons();
                    adapterNP.notifyDataSetChanged();
                    showDeleteMenu(false);
                    return true;
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void showDeleteMenu(boolean show) {
        menuItemDelete.setVisible(show);
    }

    @Override
    public void update() {
        try {
            setHasOptionsMenu(true);
            if (productoList != null) {
                adapterNP = new AdapterNuevoPedidoVenta(getActivity(), productoList);
                LvNuevoPedidoVenta.setAdapter(adapterNP);
                LvNuevoPedidoVenta.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        adapterNP.handleLongPress(position, view);
                        if (adapterNP.getListPersonsSelected().size() > 0) {
                            showDeleteMenu(true);
                        } else {
                            showDeleteMenu(false);
                        }
                        return true;
                    }
                });
            } else {
                if(adapterNP != null)
                {
                    adapterNP.RemoveAllItems();
                    adapterNP.notifyDataSetChanged();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
