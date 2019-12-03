package br.ifsc.edu.firebasesamplelucas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {
    Context myContext;
    int myResource;
    ArrayList<Pessoa> dataset;

    public PessoaAdapter(Context c, int res, ArrayList pessoas) {
        this.myContext = c;
        this.myResource = res;
        this.dataset = pessoas;
    }


//-----------------------------------------------------------------------------------------------------------------------//

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Metodo para inglar uma holder
        LayoutInflater infalter = LayoutInflater.from(myContext);
        View itemView = infalter.inflate(myResource, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Pessoa pessoa = dataset.get(position);
        //Populando a Holder
        //Formatador de numero

        holder.tvId.setText(pessoa.id);
        holder.tvNome.setText(pessoa.getNome());
        holder.tvCpf.setText(pessoa.getCpf());
        holder.tvSexo.setText(pessoa.getSexo());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    //Definição da Holder
    //Holder -> "caixa modelo" ou padrão
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvNome;
        TextView tvCpf;
        TextView tvSexo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //Associar identificadores locais a objetos da layout instanciado
            tvId = itemView.findViewById(R.id.idTextId);
            tvNome = itemView.findViewById(R.id.textViewNome);
            tvCpf = itemView.findViewById(R.id.textViewCPF);
            tvSexo = itemView.findViewById(R.id.textViewSexo);
        }
    }


}
