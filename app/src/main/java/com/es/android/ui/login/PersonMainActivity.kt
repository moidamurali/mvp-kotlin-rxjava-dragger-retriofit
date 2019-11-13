package com.es.android.ui.login


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View

import com.es.android.R
import com.es.android.ui.adapters.PersonAdaptor
import com.es.android.database.AppDatabase
import com.es.android.database.AppExecutors
import java.util.concurrent.Executors

class PersonMainActivity : AppCompatActivity() {
    internal var floatingActionButton: FloatingActionButton ?= null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: PersonAdaptor? = null
    private var mDb: AppDatabase? = null
    private lateinit var appExecutors : AppExecutors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_activity_main)
        floatingActionButton = findViewById(R.id.addFAB)

        appExecutors = AppExecutors(Executors.newSingleThreadExecutor(),
                Executors.newFixedThreadPool(3),
                AppExecutors.MainThreadExecutor())

        floatingActionButton?.setOnClickListener(View.OnClickListener { startActivity(Intent(this@PersonMainActivity, EditActivity::class.java)) })

        mRecyclerView = findViewById(R.id.recyclerView)

        mRecyclerView!!.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = PersonAdaptor(this)
        mRecyclerView!!.adapter = mAdapter
        mDb = AppDatabase.getInstance(applicationContext)
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            // Called when a user swipes left or right on a ViewHolder
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                // Here is where you'll implement swipe to delete
                appExecutors.diskIO().execute(Runnable {
                    val position = viewHolder.adapterPosition
                    val tasks = mAdapter!!.getTasks()
                    //mDb!!.personDao().delete(tasks!!.get(position))
                    retrieveTasks()
                })
            }
        }).attachToRecyclerView(mRecyclerView)

    }

    override fun onResume() {
        super.onResume()
        retrieveTasks()
    }

    private fun retrieveTasks() {
        appExecutors.diskIO().execute(Runnable {
            val persons = mDb!!.personDao().loadAllPersons()
            runOnUiThread { mAdapter!!.set(persons) }
        })


    }
}
