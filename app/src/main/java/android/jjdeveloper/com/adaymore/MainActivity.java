package android.jjdeveloper.com.adaymore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseDatabase dataBase;
    private DatabaseReference dataBaseRoot, dataBaseMessage;
    private ValueEventListener messageListener;
    private Button button, button2, button3;
    private EditText editText;
    private TextView textView;

    private void init(){

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        event();

    }

    private void event(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Boton pulsado", Toast.LENGTH_SHORT).show();

                //Detiene el evento que muestra el contenido de message de la base de datos(messageListener definido mas abajo)
                dataBaseMessage.removeEventListener(messageListener);

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(2709));
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "PASADOR");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "PRUEBA");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                sendDataAnalytics(view.getId(), "Boton1");

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String var = editText.getText().toString();

                dataBaseMessage.setValue(var);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Boton pulsado", Toast.LENGTH_SHORT).show();

                    /*String key = mDatabase.child("posts").push().getKey();
                    Post post = new Post(userId, username, title, body);
                    Map<String, Object> postValues = post.toMap();

                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/posts/" + key, postValues);
                    childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

                    mDatabase.updateChildren(childUpdates);*/

                    String key = dataBaseRoot.push().getKey();

                    ArrayList<Integer> array = new ArrayList<>();
                    array.add(7);
                    array.add(8);
                    array.add(30);

                    Persona persona = new Persona("Juanjo", "Fernandez", array);
                    Map<String, Object> personaValues = persona.toMap();

                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/" + key, personaValues);

                    dataBaseRoot.updateChildren(childUpdates);

                    dataBase.getReference("/" + key + "/loteria").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            GenericTypeIndicator<ArrayList<Integer>> t = new GenericTypeIndicator<ArrayList<Integer>>() {};
                            ArrayList<Integer> value = dataSnapshot.getValue(t);

                            for (Integer numero: value) {
                                Toast.makeText(MainActivity.this, ""+numero, Toast.LENGTH_SHORT).show();
                            }
                            
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    dataBase.getReference("/" + key).removeValue();

                    //----------------------------------------
                    Log.d("Prueba", ""+view.getId());
                    Log.d("Prueba", ""+button.getId());

                    sendDataAnalytics(view.getId(), "Boton3");

            }
        });

        /*dataBaseMessage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                textView.setText(dataSnapshot.getKey() + ": " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Prueba", "Failed to read value.", error.toException());
            }
        });*/

        messageListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                textView.setText(dataSnapshot.getKey() + ": " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Prueba", "Failed to read value.", databaseError.toException());
            }
        };
        dataBaseMessage.addValueEventListener(messageListener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        mFirebaseAnalytics.setMinimumSessionDuration(10000);
        mFirebaseAnalytics.setSessionTimeoutDuration(1800000);

        dataBase = FirebaseDatabase.getInstance();
        dataBaseRoot = dataBase.getReference();
        dataBaseMessage = dataBase.getReference("message");

        /*Bundle bundle = new Bundle();
        bundle.putString("Prueba", "Funciona");

        mFirebaseAnalytics.logEvent("LogPrueba", bundle);

        mFirebaseAnalytics.setUserProperty("favorite_food", "Hola");*/

        init();

    }

    private void sendDataAnalytics(int id, String name){

        String button_name = "Click2";

        Bundle bundle = new Bundle();
        bundle.putInt("ButtonId", id);
        bundle.putString("ButtonName", name);

        mFirebaseAnalytics.logEvent(button_name, bundle);
        //mFirebaseAnalytics.resetAnalyticsData();

        /*Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, button_name);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);*/

    }

}
