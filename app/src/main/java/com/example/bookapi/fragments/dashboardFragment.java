package com.example.bookapi.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bookapi.FirebaseComponents;
import com.example.bookapi.R;
import com.example.bookapi.firebasecallbacks.firebasecallbacks.ReadCallbacks;
import com.example.bookapi.models.Person;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class dashboardFragment extends Fragment {


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    FirebaseComponents firebaseComponents;
    TextView txt_headerText;

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

//        ButterKnife.bind(view);

        //  recyclerView = view.findViewById(R.id.recycleView);
        //  swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);

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

     /*   swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieRecyclerViewAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        makeApiCall();

        recyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {

                isLoading = true;
                currentPage++;
                makeApiCall();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    public void makeApiCall()
    {
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<Model>getAllData( String.valueOf(currentPage));
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                Log.d("Dashboard Fragment", ""+response.body());

                MovieList movieList =response.body();
                Data data = movieList.data;
                List<Movie> movies = data.movies;
                myMovieList = movies;

                Log.d("Dashboard Fragment", "Movie Size"+movies.size());
                Log.d("Dashboard Fragment", "First Movie"+movies.get(0).title);


                if(currentPage != PAGE_START) adapter.removeLoading();

                adapter.addItems(movies);
                swipeRefreshLayout.setRefreshing(false);

                if (currentPage < totalPage)
                {
                    adapter.addLoading();
                }else{
                    isLastPage = true;
                }
                isLoading = false;
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

                call.cancel();
                Log.d("Dashboard Fragment", ""+t.getMessage());
            }
        });
    }

    @Override
    public void onRefresh() {

        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        makeApiCall();
    }

    @Override
    public void onMovieClick(int position) {

        Toast.makeText(getActivity().getApplicationContext(), myMovieList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
    }

      */
    }
}