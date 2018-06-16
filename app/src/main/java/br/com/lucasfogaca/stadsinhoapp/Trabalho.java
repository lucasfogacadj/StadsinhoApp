package br.com.lucasfogaca.stadsinhoapp;

public class Trabalho {

    private String Titulo;
    private String dataEntrega;
    private String dataConcluida;
    private String anotacoes;
    private Integer idUsuario;
    private Integer id;

    public Trabalho(String Titulo, String dataEntrega, String dataConcluida, String anotacoes, Integer idUsuario, Integer id){
        this.Titulo = Titulo;
        this.dataEntrega = dataEntrega;
        this.dataConcluida = dataConcluida;
        this.anotacoes = anotacoes;
        this.idUsuario = idUsuario;
        this.id = id;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getDataConcluida() {
        return dataConcluida;
    }

    public void setDataConcluida(String dataConcluida) {
        this.dataConcluida = dataConcluida;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
