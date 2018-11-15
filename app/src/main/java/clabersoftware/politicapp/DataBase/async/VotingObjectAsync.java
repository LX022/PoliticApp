package clabersoftware.politicapp.DataBase.async;

import android.os.AsyncTask;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingObjectEntity;

public class VotingObjectAsync extends AsyncTask<Void, Void, Object> {

    AppDatabase db;
    String task;
    Object o;

    public VotingObjectAsync(AppDatabase db, String task, Object o) {
        super();
        this.db = db;
        this.task = task;
        this.o = o;
    }

    @Override
    protected Object doInBackground(Void... params) {
        switch (task) {
            case "add":
                return db.votingObjectDao().add((VotingObjectEntity) o);
            case "getAll":
                return db.votingObjectDao().getAll();
            case "getById":
                return db.votingObjectDao().getById(Long.parseLong((String.valueOf(o))));
            case "delete":
                db.votingObjectDao().delete((VotingObjectEntity) o);
                break;
            case "update":
                db.votingObjectDao().update((VotingObjectEntity) o);
                break;
        }
        return null;
    }
}
