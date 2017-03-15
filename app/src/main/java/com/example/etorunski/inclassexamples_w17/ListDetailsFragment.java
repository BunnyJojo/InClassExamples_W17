import android.app.Fragment;
import android.content.Context;

/**
 * Created by etorunski on 2017-03-15.
 */

public class ListDetailsFragment extends Fragment {
    Context parent;
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = context;
    }
}
