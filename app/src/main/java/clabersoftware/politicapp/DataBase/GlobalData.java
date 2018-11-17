package clabersoftware.politicapp.DataBase;

import android.app.Application;

/*
Stockage de l'id de l'utilisateur connecté*/

public class GlobalData extends Application {
    Long IdConnected;

    public Long getIdConnected() {
        return IdConnected;
    }

    public void setIdConnected(Long idConnected) {
        IdConnected = idConnected;
    }

}
