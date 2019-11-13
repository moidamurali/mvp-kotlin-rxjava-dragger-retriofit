package com.es.android.ui.adapters


import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.es.android.R
import com.es.android.constants.Constants
import com.es.android.data.Person
import com.es.android.database.AppDatabase
import com.es.android.ui.login.EditActivity


class PersonAdaptor(private val context: Context) : RecyclerView.Adapter<PersonAdaptor.MyViewHolder>() {
    var personsList: List<Person>? = null
        set(personList) {
            field = personList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.person_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: PersonAdaptor.MyViewHolder, i: Int) {
        myViewHolder.name.setText(personsList!![i].name)
        myViewHolder.email.setText(personsList!![i].email)
        myViewHolder.number.setText(personsList!![i].number)
        myViewHolder.pincode.setText(personsList!![i].pincode)
        myViewHolder.city.setText(personsList!![i].city)
    }

    override fun getItemCount(): Int {
        return if (personsList == null) {
            0
        } else personsList!!.size

    }

    fun set(mPersonList: List<Person>) {
        personsList = mPersonList
        notifyDataSetChanged()
    }

    fun getTasks(): Any {
        return personsList!!
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var email: TextView
        var pincode: TextView
        var number: TextView
        var city: TextView
        var editImage: ImageView
        var mDb: AppDatabase

        init {
            mDb = AppDatabase.getInstance(context)
            name = itemView.findViewById(R.id.person_name)
            email = itemView.findViewById(R.id.person_email)
            pincode = itemView.findViewById(R.id.person_pincode)
            number = itemView.findViewById(R.id.person_number)
            city = itemView.findViewById(R.id.person_city)
            editImage = itemView.findViewById(R.id.edit_Image)
            editImage.setOnClickListener {
                val elementId = personsList!![adapterPosition].id
                val i = Intent(context, EditActivity::class.java)
                i.putExtra(Constants.UPDATE_Person_Id, elementId)
                context.startActivity(i)
            }
        }
    }
}
