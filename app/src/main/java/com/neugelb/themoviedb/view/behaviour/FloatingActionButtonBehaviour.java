package com.neugelb.themoviedb.view.behaviour;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * Created by bishoy on 12/24/17.
 */

public class FloatingActionButtonBehaviour extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public FloatingActionButtonBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        child.setTranslationY(getFabTranslationYForSnackbar(parent, child));
        return true;
    }

    private float getFabTranslationYForSnackbar(CoordinatorLayout parent, FloatingActionButton fab) {
        float minOffset = 0;
        final List<View> dependencies = parent.getDependencies(fab);
        for (int i = 0, z = dependencies.size(); i < z; i++) {
            final View view = dependencies.get(i);
            if (view instanceof Snackbar.SnackbarLayout && parent.doViewsOverlap(fab, view)) {
                minOffset = Math.min(minOffset, view.getTranslationY() - view.getHeight());
            }
        }
        return minOffset;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull final FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);

        FloatingActionButton.OnVisibilityChangedListener listener = new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onShown(FloatingActionButton fab) {
                child.setVisibility(View.VISIBLE);
            }

            @Override
            public void onHidden(FloatingActionButton fab) {
                child.setVisibility(View.INVISIBLE);
            }
        };

        if (dyConsumed > 0) {
            child.hide(listener);
        } else if (dyConsumed < 0) {
            child.show(listener);
        }
    }
}
