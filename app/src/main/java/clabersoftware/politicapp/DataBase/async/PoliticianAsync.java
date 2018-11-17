package clabersoftware.politicapp.DataBase.async;

import android.os.AsyncTask;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PoliticianEntity;

public class PoliticianAsync extends AsyncTask<Void, Void, Object> {

    AppDatabase db;
    String task;
    Object o;

    public PoliticianAsync(AppDatabase db, String task, Object o) {
        super();
        this.db = db;
        this.task = task;
        this.o = o;
    }

    /*
     * La partie ci-après gère toute les actions que nous appelons dans les diverses activités.
     * Ces tâches sont appelé directement dans la DAO
     */
    @Override
    protected Object doInBackground(Void... params) {
        switch (task) {
            case "add":
                return db.politicianDao().add((PoliticianEntity) o);
            case "getAll":
                return db.politicianDao().getAllPoliticians();
            case "getById":
                return db.politicianDao().getPoliticianById(Long.parseLong((String.valueOf(o))));
            case "getIdByLogin":
                return db.politicianDao().getIdByLogin((String.valueOf(o)));
            case "getPassByLogin":
                return db.politicianDao().getPassByLogin((String.valueOf(o)));
            case "delete":
                db.politicianDao().delete((PoliticianEntity) o);
                break;
            case "deleteAll":
                db.politicianDao().deleteAll();
                break;
            case "update":
                db.politicianDao().update((PoliticianEntity) o);
                break;
        }
        return null;
    }
}
