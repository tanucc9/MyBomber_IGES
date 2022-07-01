package model.squadra;

import java.io.InputStream;

public class LogoSquadraBean {
    private int idLogoSquadra;
    private String nome;
    private String url;

    public int getIdLogoSquadra() {
        return idLogoSquadra;
    }

    public void setIdLogoSquadra(int idLogoSquadra) {
        this.idLogoSquadra = idLogoSquadra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "LogoSquadraBean{" +
                "idLogoSquadra=" + idLogoSquadra +
                ", nome='" + nome + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
