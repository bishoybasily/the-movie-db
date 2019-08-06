package com.neugelb.themoviedb.helper

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SearchView
import io.reactivex.Observable
import javax.inject.Inject


class ReactiveHelper
@Inject
constructor() {

    fun searchView(searchView: SearchView?): Observable<String> {
        return Observable.create { emitter ->
            searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String): Boolean {
                    emitter.onNext(newText); return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    emitter.onNext(query); return true
                }

            })

        }
    }

    fun editText(editText: EditText?): Observable<String> {
        return Observable.create { emitter ->
            editText?.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {
                    emitter.onNext(s.toString())
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

            })
        }
    }

}



