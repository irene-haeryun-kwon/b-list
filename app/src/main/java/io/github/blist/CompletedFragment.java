package io.github.blist;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

/* Modified at
    Dec 2, 2019 */
public class CompletedFragment extends Fragment {

    private BListDB db;
    private ListView lv;

    public CompletedFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_completed, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        lv = v.findViewById(R.id.completed_listview);
        db = new BListDB(getActivity());

        updateView();
    }

    private void updateView() {
        ArrayList<HashMap<String, String>> data = db.readCompletedBList();

        int res = R.layout.b_list_details;
        String[] from = {"title", "date", "budget"};
        int[] to = {R.id.title, R.id.date, R.id.budget};

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, res, from, to);
        lv.setAdapter(adapter);

    }

}