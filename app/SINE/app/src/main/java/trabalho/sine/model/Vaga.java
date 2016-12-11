package trabalho.sine.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "vagas")
public class Vaga {
    @DatabaseField(id = true) private Long id;
    @DatabaseField private String titulo;
    @DatabaseField private String descricao;
    @DatabaseField private String endereco;
    @DatabaseField private String cidade;
    @DatabaseField private String funcao;
    @DatabaseField private String salario;
    @DatabaseField private String empresa;
    @DatabaseField private String url_sine;


    @DatabaseField private boolean favoritado = false;

    public boolean isFavoritado() {
        return favoritado;
    }

    public void setFavoritado(boolean favoritado) {
        this.favoritado = favoritado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getUrl_sine() {
        return url_sine;
    }

    public void setUrl_sine(String url_sine) {
        this.url_sine = url_sine;
    }
}
