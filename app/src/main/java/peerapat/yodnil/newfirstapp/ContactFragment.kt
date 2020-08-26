package peerapat.yodnil.newfirstapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.room.Database
import peerapat.yodnil.newfirstapp.Database.AddContactDatabase
import peerapat.yodnil.newfirstapp.databinding.FragmentContactBinding


class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var binding = DataBindingUtil.inflate<FragmentContactBinding>(
            inflater,
            R.layout.fragment_contact,
            container,
            false
        )
        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application
        val dataSource = AddContactDatabase.getInstance(application).addContactDatabaseDao
        val viewModelFactory = AddContactViewmodelFactory(dataSource, binding, application)
        val contactViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(AddContactViewModel::class.java)
        binding.contactViewModel = contactViewModel
        binding.lifecycleOwner = this

        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.nav_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!,
            view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }

}
