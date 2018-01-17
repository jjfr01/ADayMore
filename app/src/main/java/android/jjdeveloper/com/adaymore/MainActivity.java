package android.jjdeveloper.com.adaymore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private Button button, button2, button3;
    private EditText editText;

    private void init(){

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        editText = findViewById(R.id.editText);

        event();

    }

    private void event(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Boton pulsado", Toast.LENGTH_SHORT).show();

                /*String button_name = "Boton_Prueba";

                Log.d("Prueba", ""+view.getId());
                Log.d("Prueba", ""+button.getId());

                Bundle bundle = new Bundle();
                bundle.putInt("ButtonId", view.getId());

                mFirebaseAnalytics.logEvent(button_name, bundle);*/

                sendData(view.getId(), "Boton1");

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                String var = editText.getText().toString();

                myRef.setValue(var);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Boton pulsado", Toast.LENGTH_SHORT).show();

                Log.d("Prueba", ""+view.getId());
                Log.d("Prueba", ""+button.getId());

                sendData(view.getId(), "Boton3");

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        /*Bundle bundle = new Bundle();
        bundle.putString("Prueba", "Funciona");

        mFirebaseAnalytics.logEvent("LogPrueba", bundle);

        mFirebaseAnalytics.setUserProperty("favorite_food", "Hola");*/

        init();

    }

    private void sendData(int id, String name){

        String button_name = "Boton_Prueba";

        Bundle bundle = new Bundle();
        bundle.putInt("ButtonId", id);
        bundle.putString("ButtonName", name);

        mFirebaseAnalytics.logEvent(button_name, bundle);

        /*Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, button_name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "button");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);*/

    }

}
