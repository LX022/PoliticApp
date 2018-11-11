package clabersoftware.politicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import clabersoftware.politicapp.R;
import clabersoftware.politicapp.UserInterface.Politician.PoliticianOld;

public class PoliticianAdapter extends ArrayAdapter<PoliticianOld> {

    public PoliticianAdapter(Context context, List<PoliticianOld> politicianList) {
        super(context, 0, politicianList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_politician,parent, false);
        }

        PoliticianAdapter.PoliticianHolder viewHolder = (PoliticianAdapter.PoliticianHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new PoliticianAdapter.PoliticianHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.partyNameField);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.partyName = (TextView) convertView.findViewById(R.id.partyShortName);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        PoliticianOld p = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.name.setText(p.getName());
        viewHolder.description.setText(p.getDescription());
        viewHolder.partyName.setText(p.getParty().getShortName());

        return convertView;
    }

    private class PoliticianHolder{
        public TextView name;
        public TextView description;
        public TextView partyName;

    }
}
