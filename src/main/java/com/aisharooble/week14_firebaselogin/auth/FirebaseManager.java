package com.aisharooble.week14_firebaselogin.auth;

import androidx.annotation.NonNull;

import com.aisharooble.week14_firebaselogin.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseManager {

    FirebaseAuth auth;
    MainActivity mainActivity;

    public FirebaseManager(MainActivity activity) {
        mainActivity = activity;
        auth = FirebaseAuth.getInstance();
        setupAuthStateListener();
    }

    private void setupAuthStateListener() {
        auth.addIdTokenListener(new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    mainActivity.hideLogOutButton();
                    System.out.println("You are signed out.");
                } else {
                    mainActivity.showLogOutButton();
                    System.out.println("You are signed in.");
                }
            }
        });
    }

    public void login(String email, String password, final MainActivity activity) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            activity.goToSecretPage();
                            System.out.println("Login successful " + task.getResult());
                        } else {
                            System.out.println("Login failed " + task.getException());
                        }
                    }
                });
    }

    public void logOut() {
        auth.signOut();
    }

    public void register(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mainActivity.goToSecretPage();
                            System.out.println("You've been registered." + task.getResult().getUser().getEmail());
                        } else {
                            System.out.println("Something went wrong" + task.getException());
                        }
                    }
                });
    }

}
