package io.github.blist;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* Modified at
    Dec 5, 2019 */
public class ToDoFragment extends Fragment {

    private BListDB db;
    private ListView lv;

    public ToDoFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_to_do, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        lv = v.findViewById(R.id.to_do_listview);
        db = new BListDB(getActivity());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
                Map<String, Object> map = (Map<String, Object>) lv.getItemAtPosition(i);
                final String title = (String) map.get("title");
                final int bListId = Integer.valueOf((String) map.get("id"));

                new MaterialAlertDialogBuilder(getActivity())
                        .setMessage("Complete the task " + title + "?")
                        .setPositiveButton("Complete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                completeBList(bListId);

                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                if (Build.VERSION.SDK_INT >= 26) {
                                    ft.setReorderingAllowed(false);
                                }
                                ft.detach(ToDoFragment.this).attach(ToDoFragment.this).commit();
                                getActivity().recreate();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });

        updateView();
    }

    private void completeBList(int bListId) {
        int id = bListId;

        if (!String.valueOf((Integer)id).isEmpty()) {
            try {
                db.updateCompletedBList(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateView() {
        ArrayList<HashMap<String, String>> data = db.readBList();

        int res = R.layout.b_list_details;
        String[] from = {"title", "date", "budget"};
        int[] to = {R.id.title, R.id.date, R.id.budget};

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, res, from, to);
        lv.setAdapter(adapter);

    }

}