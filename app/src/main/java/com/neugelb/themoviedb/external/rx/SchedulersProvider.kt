package com.neugelb.themoviedb.external.rx

import io.reactivex.Scheduler

class SchedulersProvider(
    val subscribe: Scheduler,
    val observe: Scheduler,
    val unsubscribe: Scheduler
)
