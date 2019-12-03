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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastrarPessoasActivity extends AppCompatActivity {
    EditText edNome, edCpf, edSexo;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pessoas);
        edNome = findViewById(R.id.edNome);
        edCpf = findViewById(R.id.edCpf);
        edSexo = findViewById(R.id.edSexo);
        myRef = FirebaseDatabase.getInstance().getReference();


    }


    public void salvarPessoa(View view) {
        Pessoa person = new Pessoa();
        person.setNome(edNome.getText().toString());
        person.setCpf(edCpf.getText().toString());
        person.setSexo(edSexo.getText().toString());
        myRef.child("Pessoas").push().setValue(person);

    }

}

