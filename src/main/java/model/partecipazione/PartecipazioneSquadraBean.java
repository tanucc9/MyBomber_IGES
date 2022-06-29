package model.partecipazione;

public class PartecipazioneSquadraBean {
    private int idSquadra;
    private String idEvento;

    public int getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(int idSquadra) {
        this.idSquadra = idSquadra;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public String toString() {
        return "PartecipazioneSquadraBean{" +
                "idSquadra=" + idSquadra +
                ", idEvento='" + idEvento + '\'' +
                '}';
    }
}
