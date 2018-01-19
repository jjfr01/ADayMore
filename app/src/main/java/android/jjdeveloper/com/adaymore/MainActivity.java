package android.jjdeveloper.com.adaymore;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseDatabase dataBase;
    private DatabaseReference dataBaseRoot, dataBaseMessage;
    private ValueEventListener messageListener;
    private FirebaseFirestore db;
    private Button button, button2, button3;
    private EditText editText;
    private TextView textView;

    private void init() {
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        event();

    }

    private void event() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Boton pulsado", Toast.LENGTH_SHORT).show();

                //Detiene el evento que muestra el contenido de message de la base de datos(messageListener definido mas abajo)
                //dataBaseMessage.removeEventListener(messageListener);

                db.collection("users")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot document : task.getResult()) {
                                        Log.d("Prueba", document.getId() + " => " + document.getData());
                                        Toast.makeText(MainActivity.this, "" + document.getId() + " => " + document.getData(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Log.w("Prueba", "Error getting documents.", task.getException());
                                }
                            }
                        });


                //sendDataAnalytics(view.getId(), "Boton1");

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String var = editText.getText().toString();

                //dataBaseMessage.setValue(var);

                /*// Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("first", "Ada");
                user.put("last", "Lovelace");
                user.put("born", 1815);

                // Add a new document with a generated ID
                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("Prueba", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Prueba", "Error adding document", e);
                            }
                        });

                // Create a new user with a first, middle, and last name
                user = new HashMap<>();
                user.put("first", "Alan");
                user.put("middle", "Mathison");
                user.put("last", editText.getText().toString());
                user.put("born", 1912);

                // Add a new document with a generated ID
                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("Prueba", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Prueba", "Error adding document", e);
                            }
                        });*/

                Map<String, Object> user = new HashMap<>();
                user.put("last", editText.getText().toString());

                db.collection("users")
                        .document("new-user")
                        .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Prueba", "Added");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Prueba", "Fallo");
                    }
                });

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Boton pulsado", Toast.LENGTH_SHORT).show();

                    /*String key = dataBaseRoot.push().getKey();

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

                    dataBase.getReference("/" + key).removeValue();*/

                //----------------------------------------

                //sendDataAnalytics(view.getId(), "Boton3");

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

        /*messageListener = new ValueEventListener() {
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
        dataBaseMessage.addValueEventListener(messageListener);*/

        db.collection("users").document("new-user").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("Prueba", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d("Prueba", "Current data: " + snapshot.getData());
                    Map<String, Object> recu = snapshot.getData();
                    //Toast.makeText(MainActivity.this, "" + recu.get("last"), Toast.LENGTH_SHORT).show();
                    textView.setText(recu.get("last").toString());
                } else {
                    Log.d("Prueba", "Current data: null");
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        mFirebaseAnalytics.setMinimumSessionDuration(20000);
        mFirebaseAnalytics.setSessionTimeoutDuration(1800000);

        /*dataBase = FirebaseDatabase.getInstance();
        dataBaseRoot = dataBase.getReference();
        dataBaseMessage = dataBase.getReference("message");*/

        db = FirebaseFirestore.getInstance();

        init();

    }

    private void sendDataAnalytics(int id, String name) {
        String button_name = "Press_Button";

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
