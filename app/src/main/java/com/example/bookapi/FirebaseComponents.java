package com.example.bookapi;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;


import com.example.bookapi.firebasecallbacks.firebasecallbacks.FirestoreWrite;
import com.example.bookapi.firebasecallbacks.firebasecallbacks.LoginCallbacks;
import com.example.bookapi.firebasecallbacks.firebasecallbacks.ReadCallbacks;
import com.example.bookapi.firebasecallbacks.firebasecallbacks.RegisterCallbacks;
import com.example.bookapi.models.Person;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseComponents {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private Activity activity;
    Person person;


    public FirebaseComponents() {
    }

    public FirebaseComponents(FirebaseFirestore firebaseFirestore, FirebaseUser firebaseUser) {
        this.firebaseFirestore = firebaseFirestore;
        this.firebaseUser = firebaseUser;
    }

    public FirebaseComponents(FirebaseAuth firebaseAuth, Activity activity) {
        this.firebaseAuth = firebaseAuth;
        this.activity = activity;
    }

    public FirebaseComponents(FirebaseFirestore firebaseFirestore, FirebaseAuth firebaseAuth, Activity activity) {
        this.firebaseFirestore = firebaseFirestore;
        this.firebaseAuth = firebaseAuth;
        this.activity = activity;
    }

    public void loginUser(String email, String pass, LoginCallbacks loginCallbacks)
    {
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(activity, task -> {

            if(task.isSuccessful())
            {
                loginCallbacks.loginCallBack(true);
            }else{
                loginCallbacks.loginCallBack(false);
            }

        });
    }

    public void readFireStore(ReadCallbacks readCallbacks)
    {
        DocumentReference docRef =  firebaseFirestore.collection("User").document(firebaseUser.getUid());

        docRef.get().addOnCompleteListener(task -> {

            if (task.isSuccessful())
            {
                DocumentSnapshot doc = task.getResult();

                Log.d("FirebaseFirestore", doc.getString("Name"));

                person = new Person(doc.getString("Email"), doc.getString("Name"), doc.getString("Location"));

                readCallbacks.onCallbacks(person);
            }else{
                Log.d("FirebaseComponents", task.getException().toString());
            }
        });
    }

    public void cretaeUser(Person person, RegisterCallbacks registerCallbacks)
    {
        firebaseAuth.createUserWithEmailAndPassword(person.getEmail(), person.getPassword())
                .addOnCompleteListener(activity, task -> {

                    if (task.isSuccessful())
                    {
                       FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                       writeFireStore(person, firebaseUser, new FirestoreWrite() {
                           @Override
                           public void fireStoreWrite(boolean success) {
                               registerCallbacks.registerSuccess(success);
                           }
                       });
                    }else{
                        Toast.makeText(activity.getApplicationContext(), "Registration Error!!!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void writeFireStore(Person person, FirebaseUser firebaseUser, FirestoreWrite firestoreWrite)
    {

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("Name", person.getName());
        userMap.put("Email", person.getEmail());
        userMap.put("Location", person.getLocation());

        firebaseFirestore.collection("User").document(firebaseUser.getUid())
                .set(userMap).addOnCompleteListener(activity, task -> {

                    if (task.isSuccessful())
                    {
                        Toast.makeText(activity.getApplicationContext(), "Registration Success!", Toast.LENGTH_SHORT).show();
                        firestoreWrite.fireStoreWrite(true);
                    }else{
                        Toast.makeText(activity.getApplicationContext(), "Firestore Error!!!", Toast.LENGTH_SHORT).show();
                        firestoreWrite.fireStoreWrite(false);
                    }
        });
    }
}
