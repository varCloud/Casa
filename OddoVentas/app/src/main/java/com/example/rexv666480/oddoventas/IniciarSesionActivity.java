package com.example.rexv666480.oddoventas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rexv666480.oddoventas.Odoo.OdooConect;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCCallback;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCClient;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCException;
import com.example.rexv666480.oddoventas.Odoo.XmlRpc.XMLRPCServerException;
import com.example.rexv666480.oddoventas.Utilerias.Loading;
import com.google.gson.Gson;
import com.google.gson.internal.ObjectConstructor;

import java.net.MalformedURLException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IniciarSesionActivity extends AppCompatActivity {

    @BindView(R.id.txtContra)
    TextView txtContra;

    @BindView(R.id.txtUsuario)
    TextView txtUsuario;

    @BindView(R.id.btnIniciarSesion)
    Button IniciarSesion;

    private OdooConect odoo ;
    private Loading loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        ButterKnife.bind(this);
        try {

            odoo = new OdooConect();
            loading = new Loading(this);

        } catch (MalformedURLException e) {

        }
        IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    loading.ShowLoading("Cargando ...");
                    XMLRPCCallback listener = new XMLRPCCallback() {
                        public void onResponse(long id, Object result) {
                            Log.d("Usuario Obtenido:", String.valueOf(result));
                            loading.CerrarLoading();
                            Intent intent = new Intent(IniciarSesionActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        public void onError(long id, XMLRPCException error) {
                           error.printStackTrace();
                            loading.CerrarLoading();
                        }

                        @Override
                        public void onServerError(long id, XMLRPCServerException error) {
                            error.printStackTrace();
                            loading.CerrarLoading();
                        }

                    };
                    odoo.getXmlClienteCommon().callAsync(listener,"login",odoo.getDb(), txtUsuario.getText().toString(),txtContra.getText().toString());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void GuardarPreferences(String variable)
    {
        /*
        try{
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("paramVerificador", new Gson().toJson(verificador));
            editor.commit();
        }catch (Exception ex)
        {
            Toast("Error del sistema GuardarPreferences"+ex.getMessage());
        }
        */
    }
}
