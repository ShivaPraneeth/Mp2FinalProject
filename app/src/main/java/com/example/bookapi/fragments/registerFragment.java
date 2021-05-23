package com.example.bookapi.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookapi.FirebaseComponents;
import com.example.bookapi.R;
import com.example.bookapi.firebasecallbacks.firebasecallbacks.RegisterCallbacks;
import com.example.bookapi.models.Person;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class registerFragment extends Fragment {

    EditText edt_email, edt_pass, edt_cpass, edt_name, edt_location;
    Button btn_register;
    TextView alr;

    private FirebaseFirestore firestore;
    private NavController navController;
    private FirebaseAuth firebaseAuth;


    public registerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registerfragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_email = view.findViewById(R.id.edt_rEmail);
        edt_pass = view.findViewById(R.id.edt_rPassword);
        edt_cpass = view.findViewById(R.id.edt_rCPassword);
        edt_name = view.findViewById(R.id.edt_rUsername);
        edt_location = view.findViewById(R.id.edt_rlocation);
        btn_register = view.findViewById(R.id.btnRegister);
        alr=view.findViewById(R.id.alreadyHaveAccount);

        navController = Navigation.findNavController(getActivity(), R.id.login_host_fragment);

        btn_register.setOnClickListener(view1 -> {
            if (!checkEmptyFields())
            {
                if(edt_pass.getText().length()<6)
                {
                    edt_pass.setError("Password must have 6 characters!");
                    edt_pass.requestFocus();
                }else
                {
                    if(!edt_pass.getText().toString().equals(edt_cpass.getText().toString()))
                    {
                        edt_cpass.setError("Password not match!");
                        edt_cpass.requestFocus();
                    }else {
                        String email =edt_email.getText().toString();
                        String pass =edt_pass.getText().toString();
                        String name =edt_name.getText().toString();
                        String location =edt_location.getText().toString();

                        Person person = new Person(email, pass, name, location);

                        FirebaseComponents firebaseComponents = new FirebaseComponents(firestore, firebaseAuth, getActivity());

                        firebaseComponents.cretaeUser(person, new RegisterCallbacks() {
                            @Override
                            public void registerSuccess(Boolean success) {
                                if (success)
                                {
                                    Toast.makeText(getActivity().getApplicationContext(), "Registration Success!", Toast.LENGTH_SHORT).show();
                                    FirebaseAuth.getInstance().signOut();
                                    navController.navigate(R.id.loginfragment);
                                }else{
                                    Toast.makeText(getActivity().getApplicationContext(), "Registration Error!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                }
            }
        });


        alr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.loginfragment);
            }
        });

    }

    public boolean checkEmptyFields()
    {
        if(TextUtils.isEmpty(edt_email.getText().toString()))
        {
            edt_email.setError("Email cannot be empty!");
            edt_email.requestFocus();
            return true;
        }else if (TextUtils.isEmpty(edt_pass.getText().toString()))
        {
            edt_pass.setError("Password cannot be empty!");
            edt_pass.requestFocus();
            return true;
        }else if (TextUtils.isEmpty(edt_cpass.getText().toString()))
        {
            edt_cpass.setError("Confirm Password cannot be empty!");
            edt_cpass.requestFocus();
            return true;
        }else if (TextUtils.isEmpty(edt_name.getText().toString()))
        {
            edt_name.setError("Name cannot be empty!");
            edt_name.requestFocus();
            return true;
        }else if (TextUtils.isEmpty(edt_location.getText().toString()))
        {
            edt_location.setError("Location cannot be empty!");
            edt_location.requestFocus();
            return true;
        }
        return false;
    }
}