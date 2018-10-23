package clabersoftware.politicapp.VotingObject;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import clabersoftware.politicapp.Party.Party;
import clabersoftware.politicapp.Party.PartyAdapter;
import clabersoftware.politicapp.R;

public class VotingObjectAdapter extends ArrayAdapter<VotingObject> {

    public VotingObjectAdapter(Context context, List<VotingObject> votingObjectList) {
        super(context, 0, votingObjectList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_voting_object,parent, false);
        }

        VotingObjectAdapter.VotingObjectHolder viewHolder = (VotingObjectAdapter.VotingObjectHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new VotingObjectAdapter.VotingObjectHolder();
            viewHolder.designation = (TextView) convertView.findViewById(R.id.designation);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.state = (CheckBox) convertView.findViewById(R.id.checkboxState);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        VotingObject vo = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.designation.setText(vo.getDesignation());
        viewHolder.description.setText(vo.getDescription());
        viewHolder.state.setChecked(vo.isState());

        return convertView;
    }

    private class VotingObjectHolder{
        public TextView designation;
        public TextView description;
        public CheckBox state;
    }
}
