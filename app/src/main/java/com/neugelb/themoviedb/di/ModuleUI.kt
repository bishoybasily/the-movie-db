package com.neugelb.themoviedb.di

import android.content.Context
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.bishoybasily.recyclerview.GridSpacingItemDecoration
import com.neugelb.themoviedb.external.dagger.Count
import com.neugelb.themoviedb.external.dagger.LayoutManager
import com.neugelb.themoviedb.external.dagger.Orientation
import com.neugelb.themoviedb.helper.SystemHelper
import dagger.Module
import dagger.Provides


@Module
class ModuleUI {

    @Provides
    fun itemAnimator(): DefaultItemAnimator {
        return DefaultItemAnimator().apply { addDuration = 500; removeDuration = 500 }
    }

    @LayoutManager(Count._2, Orientation.PORTRAIT)
    @Provides
    fun gridLayoutManagerVerticalBI(context: Context): GridLayoutManager {
        return GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
    }

    @LayoutManager(Count._3, Orientation.PORTRAIT)
    @Provides
    fun gridLayoutManagerVerticalTRI(context: Context): GridLayoutManager {
        return GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
    }

    @LayoutManager(Count._2, Orientation.LANDSCAPE)
    @Provides
    fun staggeredGridLayoutManagerHorizontalBI(context: Context): GridLayoutManager {
        return GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
    }

    @LayoutManager(Count._3, Orientation.LANDSCAPE)
    @Provides
    fun staggeredGridLayoutManagerHorizontalTRI(context: Context): GridLayoutManager {
        return GridLayoutManager(context, 3, RecyclerView.HORIZONTAL, false)
    }

    @LayoutManager(Count._2)
    @Provides
    fun gridSpacingItemDecorationBI(systemHelper: SystemHelper): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(systemHelper.dpTOpx(6.0f), Count._2.value)
    }

    @LayoutManager(Count._3)
    @Provides
    fun gridSpacingItemDecorationTRI(systemHelper: SystemHelper): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(systemHelper.dpTOpx(6.0f), Count._3.value)
    }

}