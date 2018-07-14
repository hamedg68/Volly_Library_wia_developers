package ir.hamedandroid.volly_library_wia_developers.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ir.hamedandroid.volly_library_wia_developers.Models.Contact;
import ir.hamedandroid.volly_library_wia_developers.R;

/**
 * Created by hamed on 3/1/18.
 */

public class JSONRequestAdapter extends RecyclerView.Adapter<JSONRequestAdapter.GSONRequestViewHolder> {

    private ArrayList<Contact> contacts;
    private Activity activity;

    public JSONRequestAdapter(ArrayList<Contact> contacts, Activity activity) {
        this.contacts = contacts;
        this.activity = activity;
    }

    public void addData(ArrayList<Contact> contactList) {
        if (contacts != null)
            contacts = new ArrayList<>();

        contacts.addAll(contactList);
        notifyDataSetChanged();
    }

    public void clear() {
        contacts.clear();
        notifyDataSetChanged();
    }

    @Override
    public GSONRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_gson_request_adapter, parent, false);
        return new GSONRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GSONRequestViewHolder holder, int position) {

        Contact contact = contacts.get(position);

        holder.txtName.setText(contact.getName());
        holder.txtPhone.setText(contact.getPhone());
        holder.txtEmail.setText(contact.getEmail());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class GSONRequestViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtPhone, txtEmail;

        public GSONRequestViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtEmail = itemView.findViewById(R.id.txtEmail);
        }
    }

}
