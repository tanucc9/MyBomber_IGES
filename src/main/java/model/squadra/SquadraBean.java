package model.squadra;

public class SquadraBean {
    private int idSquadra;
    private String nome;
    private String nomeAbbreviato;
    private String citta;
    private String descrizione;
    private int logo;
    private String capitano;

    public int getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(int idSquadra) {
        this.idSquadra = idSquadra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAbbreviato() {
        return nomeAbbreviato;
    }

    public void setNomeAbbreviato(String nomeAbbreviato) {
        this.nomeAbbreviato = nomeAbbreviato;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getCapitano() {
        return capitano;
    }

    public void setCapitano(String capitano) {
        this.capitano = capitano;
    }

    @Override
    public String toString() {
        return "SquadraBean{" +
                "idSquadra=" + idSquadra +
                ", nome='" + nome + '\'' +
                ", nomeAbbreviato='" + nomeAbbreviato + '\'' +
                ", citta='" + citta + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", logo='" + logo + '\'' +
                ", capitano='" + capitano + '\'' +
                '}';
    }
}
