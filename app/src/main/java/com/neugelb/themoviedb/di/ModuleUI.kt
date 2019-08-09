package com.neugelb.themoviedb.di

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.bishoybasily.recyclerview.GridSpacingItemDecoration
import com.gmail.bishoybasily.recyclerview.LinearHorizontalSpacingItemDecoration
import com.gmail.bishoybasily.recyclerview.LinearVerticalSpacingItemDecoration
import com.gmail.bishoybasily.recyclerview.SpacingItemDecoration
import com.neugelb.themoviedb.external.dagger.Count
import com.neugelb.themoviedb.external.dagger.LayoutManager
import com.neugelb.themoviedb.external.dagger.Orientation
import com.neugelb.themoviedb.external.dagger.ScopeMain
import com.neugelb.themoviedb.helper.SystemHelper
import dagger.Module
import dagger.Provides


@Module
class ModuleUI {

    @ScopeMain
    @Provides
    fun displayMetrics(windowManager: WindowManager): DisplayMetrics {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics
    }

    @Provides
    fun itemAnimator(): DefaultItemAnimator {
        return DefaultItemAnimator().apply { addDuration = 500; removeDuration = 500 }
    }

    @LayoutManager(orientation = Orientation.VERTICAL)
    @Provides
    fun linearLayoutManagerVertical(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    @LayoutManager(orientation = Orientation.HORIZONTAL)
    @Provides
    fun linearLayoutManagerHorizontal(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    @LayoutManager(Count._2, Orientation.VERTICAL)
    @Provides
    fun gridLayoutManagerVerticalBI(context: Context): GridLayoutManager {
        return GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
    }

    @LayoutManager(Count._3, Orientation.VERTICAL)
    @Provides
    fun gridLayoutManagerVerticalTRI(context: Context): GridLayoutManager {
        return GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
    }

    @LayoutManager(Count._2, Orientation.HORIZONTAL)
    @Provides
    fun staggeredGridLayoutManagerHorizontalBI(context: Context): GridLayoutManager {
        return GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
    }

    @LayoutManager(Count._3, Orientation.HORIZONTAL)
    @Provides
    fun staggeredGridLayoutManagerHorizontalTRI(context: Context): GridLayoutManager {
        return GridLayoutManager(context, 3, RecyclerView.HORIZONTAL, false)
    }

    @LayoutManager(orientation = Orientation.VERTICAL)
    @Provides
    fun linearVerticalSpacingItemDecoration(systemHelper: SystemHelper): SpacingItemDecoration {
        return LinearVerticalSpacingItemDecoration(systemHelper.dpTOpx(6.6f))
    }

    @LayoutManager(orientation = Orientation.HORIZONTAL)
    @Provides
    fun linearHorizontalSpacingItemDecoration(systemHelper: SystemHelper): SpacingItemDecoration {
        return LinearHorizontalSpacingItemDecoration(systemHelper.dpTOpx(6.6f))
    }

    @LayoutManager(Count._2)
    @Provides
    fun gridSpacingItemDecorationBI(systemHelper: SystemHelper): SpacingItemDecoration {
        return GridSpacingItemDecoration(systemHelper.dpTOpx(6.0f), Count._2.value)
    }

    @LayoutManager(Count._3)
    @Provides
    fun gridSpacingItemDecorationTRI(systemHelper: SystemHelper): SpacingItemDecoration {
        return GridSpacingItemDecoration(systemHelper.dpTOpx(6.0f), Count._3.value)
    }

}