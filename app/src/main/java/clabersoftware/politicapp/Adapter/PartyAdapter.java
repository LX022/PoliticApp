package clabersoftware.politicapp.Adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import clabersoftware.politicapp.UserInterface.Party.Party;
import clabersoftware.politicapp.R;

public class PartyAdapter extends ArrayAdapter<Party> {

    //parties est la liste des models à afficher
    public PartyAdapter(Context context, List<Party> parties) {
        super(context, 0, parties);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_parties,parent, false);
        }

        PartieViewHolder viewHolder = (PartieViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new PartieViewHolder();
            viewHolder.shortName = (TextView) convertView.findViewById(R.id.shortName);
            viewHolder.longName = (TextView) convertView.findViewById(R.id.longName);
            viewHolder.logo = (ImageView) convertView.findViewById(R.id.logo);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Party party = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.shortName.setText(party.getShortName());
        viewHolder.longName.setText(party.getLongName());
        viewHolder.logo.setImageDrawable(new ColorDrawable(party.getColor()));

        return convertView;
    }

    private class PartieViewHolder{
        public TextView shortName;
        public TextView longName;
        public ImageView logo;
    }
}
