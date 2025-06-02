package com.sam.firebaseauthentication.authentication

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

interface AuthRepository {
    fun  signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )
    fun  signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun signOut()
}


class AuthRepositoryImpl @Inject constructor(
    private  val auth: FirebaseAuth
): AuthRepository{
    override fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener{authResult ->
                Log.d("TAG", "authResult: $authResult")
//                authResult.user?.sendEmailVerification()

                // Handle success
                onSuccess()
            }
            .addOnFailureListener{ exception ->
                Log.d("TAG", "exception: $exception")
                // Handle failure
                onFailure(exception)
            }
    }

    override fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                Log.d("TAG", "authResult: $authResult")
                onSuccess()
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "exception: $exception")
                onFailure(exception)
            }

    }

    override fun signOut() {
        auth.signOut()
    }
}