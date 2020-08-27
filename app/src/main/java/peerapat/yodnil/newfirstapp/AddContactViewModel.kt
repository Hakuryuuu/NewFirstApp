package peerapat.yodnil.newfirstapp

import android.app.Application
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import kotlinx.coroutines.*
import peerapat.yodnil.newfirstapp.Database.AddContact
import peerapat.yodnil.newfirstapp.Database.AddContactDatabaseDao
import peerapat.yodnil.newfirstapp.databinding.FragmentContactBinding

class AddContactViewModel(
    val database: AddContactDatabaseDao,
    private val binding: FragmentContactBinding,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val contacts = database.get()
    val contactString = Transformations.map(contacts) { contacts ->
        formatContact(contacts)
    }

    private fun formatContact(contact: List<AddContact>): Spanned {
        val sb = StringBuilder()
        sb.apply {
            //append(resources.getString(R.string.title))
            contact.forEach {
                append(it.id)
                append(" : ")
                append(it.name)
                append(", ")
                append(it.phone)
                append("<br>")
                append(it.age)
                append("<br>")
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onContactAdd() {
        uiScope.launch {
            val newContact = AddContact()
            newContact.name = binding.editTextTextPersonName.text.toString()
            newContact.phone = binding.editTextTextPersonPhone.text.toString()
            insert(newContact)
        }
    }

    private suspend fun insert(contact: AddContact) {
        withContext(Dispatchers.IO) {
            database.insert(contact)
        }
    }

}
