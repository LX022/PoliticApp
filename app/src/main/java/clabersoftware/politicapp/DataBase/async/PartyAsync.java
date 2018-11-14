package clabersoftware.politicapp.DataBase.async;

import android.os.AsyncTask;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.PartyEntity;

public class PartyAsync extends AsyncTask<Void, Void, Object> {

    AppDatabase db;
    String task;
    Object o;

    public PartyAsync(AppDatabase db, String task, Object o) {
        super();
        this.db = db;
        this.task = task;
        this.o = o;
    }

    @Override
    protected Object doInBackground(Void... params) {
        switch (task) {
            case "add":
                return db.partyDao().add((PartyEntity) o);
            case "getAll":
                return db.partyDao().getAll();
            case "getById":
                return db.partyDao().getById(Long.parseLong((String.valueOf(o))));
            case "delete":
                db.partyDao().delete((PartyEntity) o);
                break;
            case "update":
                db.partyDao().update((PartyEntity) o);
                break;
        }
        return null;
    }
}
