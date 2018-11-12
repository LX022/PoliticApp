package clabersoftware.politicapp.DataBase.async.Party;

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
                System.out.println("je suis dans le insert");
                return db.partyDao().insert((PartyEntity) o);
            case "getAll":
                System.out.println("je suis dans le getAll");
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
