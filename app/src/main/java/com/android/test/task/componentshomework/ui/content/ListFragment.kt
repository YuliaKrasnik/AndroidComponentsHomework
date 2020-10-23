package com.android.test.task.componentshomework.ui.content

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.test.task.componentshomework.R
import com.android.test.task.componentshomework.base.BaseFragment

class ListFragment : BaseFragment() {
    companion object {
        private const val REQUEST_CODE_READ_CONTACT: Int = 1
    }

    private var readContactGranted = false
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentLayout = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = fragmentLayout.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        return fragmentLayout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val hasReadContactPermission = context?.let {
            ContextCompat.checkSelfPermission(it, android.Manifest.permission.READ_CONTACTS)
        }
        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            readContactGranted = true
        } else {
            requestPermissions(
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                REQUEST_CODE_READ_CONTACT
            )
        }

        if (readContactGranted) {
            loadContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_READ_CONTACT) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readContactGranted = true
            }
        }

        if (readContactGranted) {
            loadContacts()
        } else {
            Toast.makeText(context, "Необходимо разрешение", Toast.LENGTH_LONG).show()
        }

    }

    private fun loadContacts() {
        val listContacts = mutableListOf<String>()
        val cursor = context?.contentResolver?.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.let {
            while (it.moveToNext()) {
                val fullName =
                    it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phone =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                listContacts.add("Имя: $fullName , Телефон:  $phone")
            }
            cursor.close()
        }

        createRecyclerViewAdapter(listContacts)
    }

    private fun createRecyclerViewAdapter(list: MutableList<String>) {
        recyclerView.adapter = ListAdapter(list)
    }

}