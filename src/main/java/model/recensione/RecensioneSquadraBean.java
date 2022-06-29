package model.recensione;

public class RecensioneSquadraBean {
    private int idSquadraRecensore;
    private int idSquadreRecensito;
    private String idEvento;
    private float valutazione;
    private String descrizione;

    public int getIdSquadraRecensore() {
        return idSquadraRecensore;
    }

    public void setIdSquadraRecensore(int idSquadraRecensore) {
        this.idSquadraRecensore = idSquadraRecensore;
    }

    public int getIdSquadreRecensito() {
        return idSquadreRecensito;
    }

    public void setIdSquadreRecensito(int idSquadreRecensito) {
        this.idSquadreRecensito = idSquadreRecensito;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public float getValutazione() {
        return valutazione;
    }

    public void setValutazione(float valutazione) {
        this.valutazione = valutazione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "RecensioneSquadraBean{" +
                "idSquadraRecensore=" + idSquadraRecensore +
                ", idSquadreRecensito=" + idSquadreRecensito +
                ", idEvento='" + idEvento + '\'' +
                ", valutazione=" + valutazione +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
