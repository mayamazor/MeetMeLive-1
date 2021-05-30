package com.example.meetmelive;

import android.app.Notification;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.meetmelive.adapter.GridAdapter;
import com.example.meetmelive.model.DataModel;
import com.example.meetmelive.model.ModelFirebase;
import com.example.meetmelive.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Nearby extends Fragment {

    // creating a variable for our
    // grid view, arraylist and
    // firebase Firestore.
    GridView gridadapter;
    ArrayList<DataModel> dataModelArrayList;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  return inflater.inflate(R.layout.fragment_nearby, container, false);  activiygrid
        view = inflater.inflate(R.layout.fragment_nearby, container, false);
        Log.d("Nearby", "Birthday is " + User.getInstance().getDateOfBirth());

        mAuth = FirebaseAuth.getInstance();

//        ModelFirebase.setUserAppData(User.getInstance().getEmail());

        // below line is use to initialize our variables.
        gridadapter = view.findViewById(R.id.idGVCourses);
        dataModelArrayList = new ArrayList<>();

        // initializing our variable for firebase
        // firestore and getting its instance.
        db = FirebaseFirestore.getInstance();

        // here we are calling a method
        // to load data in our list view.
        loadDatainGridView();
        return view;
    }

    private void loadDatainGridView() {

        db.collection("userProfileData")
//                .whereEqualTo("gender", User.getInstance().getPreferSex())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("*** COLLECTION *** ", "GENDER IS " +  document.get("gender") +
                                        "PREFER SEX IS " + User.getInstance().getPreferSex());

                                if (document.get("gender").equals(User.getInstance().getPreferSex()))
                                    dataModelArrayList.add(document.toObject(DataModel.class));
                            }

                            GridAdapter adapter = new GridAdapter(getContext(), dataModelArrayList);
                            gridadapter.setAdapter(adapter);
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

//        db.collection("userProfileData")
////                .whereEqualTo("gender", User.getInstance().getPreferSex())
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                // after getting this list we are passing
//                                // that list to our object class.
//                                DataModel dataModel = document.toObject(DataModel.class);
//
//                                if(User.getInstance().getPreferSex().equals("Female") && document.get("gender").equals("Female")) {
//                                    dataModel = document.toObject(DataModel.class);
//                                    dataModelArrayList.add(dataModel);
//                                }
//
//                                if(User.getInstance().getPreferSex().equals("Male") && document.get("gender").equals("Male")) {
//                                    dataModel = document.toObject(DataModel.class);
//                                    dataModelArrayList.add(dataModel);
//                                }
//                                // after getting data from Firebase
//                                // we are storing that data in our array list
////                                dataModelArrayList.add(dataModel);
//                                Log.d("TAG", document.getId() + " => " + document.getData());
//
//                            }
//
//                            Log.d("ARRAY LIST", "" + dataModelArrayList);
//
//                            GridAdapter adapter = new GridAdapter(getActivity(), dataModelArrayList);
//                            gridadapter.setAdapter(adapter);
//                        } else {
//                            Log.d("TAG", "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
    }
}


//        // below line is use to get data from Firebase
//        // firestore using collection in android.
//        Log.d("NearBy", "Looking for is " + User.getInstance().getPreferSex());
//        db.collection("userProfileData").whereEqualTo("gender", User.getInstance().getPreferSex()).get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        // after getting the data we are calling on success method
//                        // and inside this method we are checking if the received
//                        // query snapshot is empty or not.
//                        if (!queryDocumentSnapshots.isEmpty()) {
//                            // if the snapshot is not empty we are hiding our
//                            // progress bar and adding our data in a list.
//                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                            for (DocumentSnapshot d : list) {
//
//                                // after getting this list we are passing
//                                // that list to our object class.
//                                DataModel dataModel = d.toObject(DataModel.class);
//
//                                // after getting data from Firebase
//                                // we are storing that data in our array list
//                                dataModelArrayList.add(dataModel);
//                            }
//                            // after that we are passing our array list to our adapter class.
//                            GridAdapter adapter = new GridAdapter(getActivity(), dataModelArrayList);
//
//                            // after passing this array list
//                            // to our adapter class we are setting
//                            // our adapter to our list view.
//                            gridadapter.setAdapter(adapter);
//                        } else {
//                            // if the snapshot is empty we are displaying a toast message.
//                            Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                // we are displaying a toast message
//                // when we get any error from Firebase.
//                Toast.makeText(getContext(), "Fail to load data..", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


//        DocumentReference docRef = db.collection("Data").document("2");
//
//// Source can be CACHE, SERVER, or DEFAULT.
//        Source source = Source.CACHE;
//
//// Get the document, forcing the SDK to use the offline cache
//        docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    // Document found in the offline cache
//                    DocumentSnapshot document = task.getResult();
//                    Log.d("TAG", "Cached document data: " + document.getData());
//                }
//
//                else {
//                    Log.d("res", "Cached get failed: ", task.getException());
//                }
//            }
//        });



//    private void loadDatainGridView() {
//        // below line is use to get data from Firebase
//        // firestore using collection in android.
//
//        db.collection("Data")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//
//                                //maya added
//                                DataModel dm=new DataModel();
//                                dm.fromMap(document.getData());
//                                dataModelArrayList.add(dm);
//
//
//                                Log.d("TAG", document.getId() + " => " + document.getData());
//                            }
//
//                        //maya added
//
////                            List<QuerySnapshot> list = task.getResult();
////                            for (QuerySnapshot d : list) {
////
////                                // after getting this list we are passing
////                                // that list to our object class.
////                                DataModel dataModel = d.toObject(DataModel.class);
////
////                                // after getting data from Firebase
////                                // we are storing that data in our array list
////                                dataModelArrayList.add(dataModel);
////                            }
//                            // after that we are passing our array list to our adapter class.
//
//
//                            //GridAdapter adapter = new GridAdapter(Nearby.this, dataModelArrayList);
//
//                            //maya added
//                              GridAdapter adapter=new GridAdapter(getContext(),dataModelArrayList);
//                            //maya added
//
//                            // after passing this array list
//                            // to our adapter class we are setting
//                            // our adapter to our list view.
//                            gridadapter.setAdapter(adapter);
//                        } else {
//                            Log.d("TAG", "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
////        db.collection("data").get()
////                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
////
////                    @Override
////                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
////                        // after getting the data we are calling on success method
////                        // and inside this method we are checking if the received
////                        // query snapshot is empty or not.
////                        if (true) {
////                            Log.d("nearby", "Query: here: "+ queryDocumentSnapshots);
////                            // if the snapshot is not empty we are hiding our
////                            // progress bar and adding our data in a list.
////                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
////                            for (DocumentSnapshot d : list) {
////
////                                // after getting this list we are passing
////                                // that list to our object class.
////                                DataModel dataModel = d.toObject(DataModel.class);
////
////                                // after getting data from Firebase
////                                // we are storing that data in our array list
////                                dataModelArrayList.add(dataModel);
////                            }
////                            // after that we are passing our array list to our adapter class.
////
////
////                            //GridAdapter adapter = new GridAdapter(Nearby.this, dataModelArrayList);
////
////                            //maya added
////                              GridAdapter adapter=new GridAdapter(getContext(),dataModelArrayList);
////                            //maya added
////
////                            // after passing this array list
////                            // to our adapter class we are setting
////                            // our adapter to our list view.
////                            gridadapter.setAdapter(adapter);
////                        } else {
////                            // if the snapshot is empty we are displaying a toast message.
////                            Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
////                        }
////                    }
////                }).addOnFailureListener(new OnFailureListener() {
////            @Override
////            public void onFailure(@NonNull Exception e) {
////                // we are displaying a toast message
////                // when we get any error from Firebase.
////                Toast.makeText(getContext(), "Fail to load data..", Toast.LENGTH_SHORT).show();
////            }
////        });
//    }