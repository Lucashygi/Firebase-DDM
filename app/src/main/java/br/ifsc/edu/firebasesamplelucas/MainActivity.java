package br.ifsc.edu.firebasesamplelucas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText edLogin, edSenha;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        edLogin = findViewById(R.id.edLogin);
        edSenha = findViewById(R.id.edSenha);

        //config realtime
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference();
//        databaseReference.child("Pessoas").child("id").setValue("Lucas");
//        Pessoa p = new Pessoa("Odair da Silva","123.000.000-00","M");
//        Pessoa e = new Pessoa("Lusca da Silva","000.456.000-00","M");
//        Pessoa s = new Pessoa("Edimar da Silva","000.000.789-00","M");
//        Pessoa o = new Pessoa("Laura da Silva","000.000.000-11","F");
//        databaseReference.child("Pessoas").push().setValue(p);
//        databaseReference.child("Pessoas").push().setValue(e);
//        databaseReference.child("Pessoas").push().setValue(s);
//        databaseReference.child("Pessoas").push().setValue(o);

//        databaseReference.child("Pessoas").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Pessoa p = dataSnapshot.getValue(Pessoa.class);
//                Log.d("DatabasePessoa", p.nome);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


    }

/*    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updadeUI(currentUser);
    }*/

    public void autenticar(View view) {
        String login = edLogin.getText().toString();
        String senha = edSenha.getText().toString();
//        login = "lucashygi@gmail.com";
//        senha = "123mudar";
        login = "lucashygi@gmail.com";
        senha = "123mudar";
        mAuth.signInWithEmailAndPassword(
                login,
                senha
        ).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //you can verify here if your Sing Up was completed successfully
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Login Completo com Sucesso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Falha no Login", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void cadastrar(View view) {
        mAuth.createUserWithEmailAndPassword(
                edLogin.getText().toString(),
                edSenha.getText().toString()
        ).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //you can verify here if your Sing Up was completed successfully
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Cadastro Concluido com Sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Falha ao Criar o Cadastro", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void resetarSenha(View view){
        if(!edLogin.getText().toString().trim().equals(""))
            mAuth.sendPasswordResetEmail(edLogin.getText().toString());
    }
}
