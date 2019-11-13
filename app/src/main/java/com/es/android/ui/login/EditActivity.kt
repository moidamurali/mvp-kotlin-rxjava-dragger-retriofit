package com.es.android.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText

import com.es.android.R
import com.es.android.data.Person
import com.es.android.constants.Constants
import com.es.android.database.AppDatabase
import com.es.android.database.AppExecutors
import java.util.concurrent.Executors

class EditActivity : AppCompatActivity() {
    internal lateinit var name: EditText
    internal lateinit var email: EditText
    internal lateinit var pincode: EditText
    internal lateinit var city: EditText
    internal lateinit var phoneNumber: EditText
    internal lateinit var button: Button
    internal var mPersonId: Int = 0
    internal var intent: Intent? = null
    private var mDb: AppDatabase? = null
    private lateinit var appExecutors : AppExecutors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        window.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        initViews()
        mDb = AppDatabase.getInstance(applicationContext)


        appExecutors = AppExecutors(Executors.newSingleThreadExecutor(),
                Executors.newFixedThreadPool(3),
                AppExecutors.MainThreadExecutor())

        intent = getIntent()
        if (intent != null && intent!!.hasExtra(Constants.UPDATE_Person_Id)) {
            button.text = "Update"

            mPersonId = intent!!.getIntExtra(Constants.UPDATE_Person_Id, -1)

            appExecutors.diskIO().execute(Runnable {
                val person = mDb!!.personDao().loadPersonById(mPersonId)
                populateUI(person)
            })


        }

    }

    private fun populateUI(person: Person?) {

        if (person == null) {
            return
        }

        name.setText(person.name)
        email.setText(person.email)
        phoneNumber.setText(person.number)
        pincode.setText(person.pincode)
        city.setText(person.city)
    }

    private fun initViews() {
        name = findViewById(R.id.edit_name)
        email = findViewById(R.id.edit_email)
        pincode = findViewById(R.id.edit_pincode)
        city = findViewById(R.id.edit_city)
        phoneNumber = findViewById(R.id.edit_number)
        button = findViewById(R.id.button)
        button.setOnClickListener { onSaveButtonClicked() }
    }

    fun onSaveButtonClicked() {
        val person = Person(
                name.text.toString(),
                email.text.toString(),
                phoneNumber.text.toString(),
                pincode.text.toString(),
                city.text.toString())

        appExecutors.diskIO().execute(Runnable {
            if (!intent!!.hasExtra(Constants.UPDATE_Person_Id)) {
                mDb!!.personDao().insertPerson(person)
            } else {
                person.id = mPersonId
                mDb!!.personDao().updatePerson(person)
            }
            finish()
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
