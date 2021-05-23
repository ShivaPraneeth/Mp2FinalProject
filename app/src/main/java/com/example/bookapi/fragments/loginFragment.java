package com.example.bookapi.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookapi.FirebaseComponents;
import com.example.bookapi.R;
import com.example.bookapi.dashActivity;
import com.example.bookapi.firebasecallbacks.firebasecallbacks.LoginCallbacks;
import com.example.bookapi.utils.NetworkCheck;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class loginFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    Button btn_login;
    EditText edt_email, edt_pass;
    TextView forgotpassword;
    FirebaseComponents firebaseComponents;
    NavController navController;

    TextView txt_register;

    public loginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loginfragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null)
        {
            updateUI();
            Toast.makeText(getActivity().getApplicationContext(), "User Already Signin.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseComponents = new FirebaseComponents(firebaseAuth, getActivity());
        navController = Navigation.findNavController(getActivity(), R.id.login_host_fragment);

        forgotpassword=view.findViewById(R.id.forgotPassword);
        btn_login = view.findViewById(R.id.btnlogin);
        edt_email = view.findViewById(R.id.inputloginEmail);
        edt_pass = view.findViewById(R.id.inputloginpassword);
        txt_register = view.findViewById(R.id.textViewSignUp);


        btn_login.setOnClickListener(view1->{

            NetworkCheck networkCheck = new NetworkCheck();

            if (networkCheck.checkNetwork(getActivity()))
            {
                firebaseComponents.loginUser(edt_email.getText().toString(), edt_pass.getText().toString(), new LoginCallbacks() {
                    @Override
                    public void loginCallBack(boolean loginSuccess) {

                        if (loginSuccess == true)
                        {
                            firebaseUser = firebaseAuth.getCurrentUser();
                            updateUI();
                        }else{
                            Toast.makeText(getActivity().getApplicationContext(), "Authentication Failed!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else
            {
                Snackbar snackbar = Snackbar.make(view, "No Internet!!!", Snackbar.LENGTH_INDEFINITE);
                snackbar.show();
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText resetmail= new EditText(view.getContext());
                AlertDialog.Builder forgotpassword= new AlertDialog.Builder(view.getContext());
                forgotpassword.setTitle("Reset Password?");
                forgotpassword.setMessage("Enter Your Email To Receive Reset Link.");
                forgotpassword.setView(resetmail);

                forgotpassword.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String mail=resetmail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(),"Reset Link Sent To Your Email.",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(),"Error: Reset Link is Not Sent."+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                forgotpassword.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
                forgotpassword.create().show();
            }
        });

        txt_register.setOnClickListener(view2->{
            navController.navigate(R.id.registerfragment);
        });
    }

    private void updateUI(){
        Toast.makeText(getActivity().getApplicationContext(), "Login Success!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), dashActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
