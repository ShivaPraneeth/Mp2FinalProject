package com.example.bookapi.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bookapi.FirebaseComponents;
import com.example.bookapi.R;
import com.example.bookapi.firebasecallbacks.firebasecallbacks.ReadCallbacks;
import com.example.bookapi.models.Data;
import com.example.bookapi.models.Items;
import com.example.bookapi.models.Person;
import com.example.bookapi.models.ResponseModal;
import com.example.bookapi.utils.APIClient;
import com.example.bookapi.utils.BooksAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class dashboardFragment extends Fragment implements BooksAdapter.OnRecycleClickListener{


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    FirebaseComponents firebaseComponents;
    TextView txt_headerText;
    RecyclerView recyclerView;
    BooksAdapter booksAdapter;


  /*  @BindView(R.id.recycleView)
    RecyclerView recyclerView;


    @BindView(R.id.swipeRefresh)
  SwipeRefreshLayout swipeRefreshLayout;

   */




//    APIInterface apiInterface;

    public dashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//     ButterKnife.bind(view);

          recyclerView = view.findViewById(R.id.recycleView);
       // swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        booksAdapter= new BooksAdapter();
        getAllData();

        NavigationView navigationView = getActivity().findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);
        txt_headerText = headerView.findViewById(R.id.txt_header);

        firebaseComponents = new FirebaseComponents(firebaseFirestore, firebaseUser);

        firebaseComponents.readFireStore(new ReadCallbacks() {
            @Override
            public void onCallbacks(Person person) {

                txt_headerText.setText(person.getName());
            }
        });


    }
    public void getAllData(){
        Call<ResponseModal> data= APIClient.getAPIService().getAllData();

        data.enqueue(new Callback<ResponseModal>() {
            @Override
            public void onResponse(Call<ResponseModal> call, Response<ResponseModal> response) {
                if(response.isSuccessful()){
                    ResponseModal data= response.body();
                    booksAdapter.setdata(data.getItems());
                    recyclerView.setAdapter(booksAdapter);

                    for(Items book:data.getItems()){
                        Log.v("Tag",book.getVolumeInfo().getTitle());
                    }

                   // Log.d("sucess",response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseModal> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onMovieClick(int position) {

    }
}