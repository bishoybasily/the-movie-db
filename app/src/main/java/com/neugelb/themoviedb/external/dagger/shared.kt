package com.neugelb.themoviedb.external.dagger

import javax.inject.Qualifier
import javax.inject.Scope


@Scope
annotation class ScopeMain

@Scope
annotation class ScopeMovie

@Qualifier
annotation class LayoutManager(val count: Count = Count.NONE, val orientation: Orientation = Orientation.VERTICAL)

@Qualifier
annotation class Local

@Qualifier
annotation class Network

enum class Count(val value: Int) {
    NONE(0),
    _2(2),
    _3(3),
    _4(4),
    _5(5)

}

enum class Orientation {
    VERTICAL,
    HORIZONTAL
}
