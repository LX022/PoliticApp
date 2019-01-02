package clabersoftware.politicapp.DataBase;

import android.app.Application;

/*
Stockage de l'id de l'utilisateur connecté*/

public class GlobalData extends Application {
    Long IdConnected;
    String UuidConnected;

    public String getUuidConnected() {
        return UuidConnected;
    }

    public void setUuidConnected(String uuidConnected) {
        UuidConnected = uuidConnected;
    }

    public Long getIdConnected() {
        return IdConnected;
    }

    public void setIdConnected(Long idConnected) {
        IdConnected = idConnected;
    }

}
