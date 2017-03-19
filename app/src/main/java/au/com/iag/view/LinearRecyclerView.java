package au.com.iag.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by siddiquim on 3/19/17.
 */

public class LinearRecyclerView extends RecyclerView {

    public LinearRecyclerView(Context context) {
        this(context, null);
    }

    public LinearRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayoutManagerAndDivider();
    }

    private void setLayoutManagerAndDivider() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        addItemDecoration(dividerItemDecoration);
    }
}
