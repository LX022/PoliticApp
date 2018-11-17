package clabersoftware.politicapp.DataBase.async;

import android.os.AsyncTask;

import clabersoftware.politicapp.DataBase.AppDatabase;
import clabersoftware.politicapp.DataBase.Entity.VotingLineEntity;

public class VotingLineAsync extends AsyncTask<Void, Void, Object> {

    AppDatabase db;
    String task;
    Object o;

    public VotingLineAsync(AppDatabase db, String task, Object o) {
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
                return db.votingLineDao().insert((VotingLineEntity) o);
            case "getAll":
                return db.votingLineDao().getAllVotingLines();
            case "getById":
                return db.votingLineDao().getVotingLineById(Long.parseLong((String.valueOf(o))));
            case "getVotingLineByIdVotingObject":
                return db.votingLineDao().getVotingLineByIdVotingObject(Long.parseLong((String.valueOf(o))));
            case "delete":
                db.votingLineDao().delete((VotingLineEntity) o);
                break;
            case "update":
                db.votingLineDao().update((VotingLineEntity) o);
                break;
            case "deleteAll":
                db.votingLineDao().deleteAll();
                break;
            case "getVotingLineByPolitician":
                return db.votingLineDao().getVotingLineByPolitician(Long.parseLong((String.valueOf(o))));
        }
        return null;
    }
}
