package Loja;

public class Produto {
    
    private int codigo;
    private String nome;
    private String descricao;
    private double valor;
    private int quantidade;
    private String caminhoImagem;
    private int ano;

    public Produto(int codigo, String nome, String descricao, double valor, int quantidade, String caminhoImagem, int ano) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caminhoImagem = caminhoImagem;
        this.ano = ano;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }
    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
}