package br.ifsc.edu.firebasesamplelucas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    RecyclerView recyclerView;
    ArrayList<Pessoa> arraylistpessoas;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            Log.d("FirebaseAuth", mUser.getEmail());
        } else {
            Log.d("FirebaseAuth", "Usuário não autenticado");
            finish();
        }

        findViewById(R.id.btnCadPessoas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        getApplicationContext(),
                        CadastrarPessoasActivity.class
                );
                startActivity(i);
            }
        });

        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });

        recyclerView = findViewById(R.id.idrecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        arraylistpessoas = new ArrayList<Pessoa>();
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getApplicationContext(),
                        DividerItemDecoration.VERTICAL)
        );

        //my firebase
        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("Pessoas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Pessoa person = objSnapshot.getValue(Pessoa.class);
                    person.setId(objSnapshot.getKey());
                    arraylistpessoas.add(person);
                }

                PessoaAdapter pessoaAdapter = new PessoaAdapter(
                        getApplicationContext(),
                        R.layout.item_pessoa_list,
                        arraylistpessoas
                );

                recyclerView.setAdapter(pessoaAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

//    public void logOut(View view) {
//        mAuth.signOut();
//        finish();
//    }
}
