package trabalho.sine.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import trabalho.sine.Servidor;
import trabalho.sine.controller.RequestURL;
import trabalho.sine.model.Vaga;
import trabalho.sine.model.VagasJSON;

/**
 * Created by saw on 15/12/16.
 */

public class AdpterScrollListener extends RecyclerView.OnScrollListener{

    private Context context;
    private RecyclerView mRecyclerView;
    private AdapterListView mAdapter;
    private LinearLayoutManager mLayoutManager;
    private String cidadeEstado, funcao;
    private int filtroIndex;
    private int totalItemCount;
    private int pos;
    private ProgressDialog dialog;

    public AdpterScrollListener(Context context, RecyclerView mRecyclerView, AdapterListView mAdapter,
                                LinearLayoutManager mLayoutManager, String cidadeEstado, String funcao,
                                int filtroIndex, int pos) {
        this.context = context;
        this.mRecyclerView = mRecyclerView;
        this.mAdapter = mAdapter;
        this.mLayoutManager = mLayoutManager;
        this.cidadeEstado = cidadeEstado;
        this.funcao = funcao;
        this.filtroIndex = filtroIndex;
        this.pos = pos;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        //Verifica se o scroll moveu
        if(dy > 0){
            //Obtem as informações referente aos itens do RecyclerView.
            int pastVisiblesItems, visibleItemCount;
            visibleItemCount = mLayoutManager.getChildCount();
            totalItemCount = mLayoutManager.getItemCount();
            pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
            //Verifica se chegou no ultimo elemento do recyclerview.
            if((visibleItemCount + pastVisiblesItems) == totalItemCount) {
                pos++;
                RequestURL req = new RequestURL(context);
                mostrarDialogoCarregando();
                totalItemCount--;
                mRecyclerView.scrollToPosition(totalItemCount);
                req.requestURL(String.format(Servidor.ENDERECO_SERVIDOR + "/vagas?idfuncao=%s&idcidade=%s&numPagina=%d" +
                        "&tipoOrdenacao=%d", funcao, cidadeEstado, pos, filtroIndex), new RequestURL.VolleyCallback() {
                    @Override
                    public void onSuccess(String response) {
                        int position = totalItemCount;
                        Gson gson = new Gson();
                        VagasJSON vagasJSON = gson.fromJson(response, VagasJSON.class);

                        for(Vaga v : vagasJSON.getVagas())
                            mAdapter.addItem(v,position++);
                        dialog.dismiss();
                    }
                });

            }
        }
    }

    public void mostrarDialogoCarregando(){
        dialog = new ProgressDialog(context);
        dialog.setMessage("Carregando dados");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.show();
    }
}
