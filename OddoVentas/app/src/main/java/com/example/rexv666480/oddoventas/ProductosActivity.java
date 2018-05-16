package com.example.rexv666480.oddoventas;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rexv666480.oddoventas.Adapters.AdapterClientes;
import com.example.rexv666480.oddoventas.Adapters.AdapterProductos;
import com.example.rexv666480.oddoventas.Entidades.Producto;
import com.example.rexv666480.oddoventas.Entidades.TemplateProducto;
import com.example.rexv666480.oddoventas.Odoo.OdooConect;
import com.example.rexv666480.oddoventas.Odoo.OdooUtil;
import com.example.rexv666480.oddoventas.Utilerias.Loading;
import com.example.rexv666480.oddoventas.Utilerias.PreferencesManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Arrays.asList;

public class ProductosActivity extends AppCompatActivity {

    @BindView(R.id.lvProductos)
    ListView lvProductos;
    private OdooConect odoo;
    private Loading loading;
    private int user_id = 0;
    private Activity activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        activity=this;
        context= this;
        ButterKnife.bind(this);
        try{
            user_id = Integer.parseInt(PreferencesManager.loadString(context, "usID", "0"));
            odoo = new OdooConect();
            loading = new Loading(context);
            ObtenerTemplateProductos();

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public void ObtenerTemplateProductos()
    {
        loading.ShowLoading("Cargando Productos...");

        AsyncTask asyncTask = new AsyncTask<Object,Object,Object>() {

            @Override
            protected Object doInBackground(Object... params) {
                Object result=null;
                try {
                    List conditions = asList(asList(
                            asList("sale_ok", "=", true)
                    ));

                    Map<String, List> filtros = new HashMap() {{
                        put("fields", asList("name",
                                "currency_id",
                                "product_variant_count",
                                "lst_price",
                                "qty_available",
                                "type",
                                "product_variant_ids",
                                "image_small",
                                "uom_id",
                                "default_code",
                                "__last_update"));
                        /*put("limit", 5);*/
                    }};

                    result=  odoo.getXmlClienteObject().call("execute_kw", odoo.getDb(), user_id, odoo.getPassword(), "product.template", "search_read", conditions, filtros);
                }catch (Exception ex)
                {
                    loading.CerrarLoading();
                    ex.printStackTrace();
                }
                return  result;
            }

            @Override
            protected void onPostExecute(Object result) {
                try {
                    if (result != null) {
                        List<TemplateProducto> productos = OdooUtil.ObtenerTemplateProductos(result);
                        if (productos != null) {
                            AdapterProductos adapter = new AdapterProductos(activity, productos);
                            lvProductos.setAdapter(adapter);
                        }
                    }
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                loading.CerrarLoading();
            }
        };

        asyncTask.execute();
    }
}
