package com.neugelb.themoviedb.external.rx

import io.reactivex.Scheduler

class SchedulerProvider(
    val subscribe: Scheduler,
    val observe: Scheduler,
    val unsubscribe: Scheduler
)
