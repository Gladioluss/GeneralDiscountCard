package com.example.generaldiscountcard.ui.menuScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.generaldiscountcard.MainActivity
import com.example.generaldiscountcard.databinding.FragmentMenuScreenBinding

class MenuScreenFragment : Fragment() {

    private var _binding: FragmentMenuScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
                ViewModelProvider(this).get(MenuScreenViewModel::class.java)

        _binding = FragmentMenuScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val ma: MainActivity = this.activity as MainActivity

        val displayNameEditText: EditText = binding.editTextLogin
        val passwordEditText: EditText = binding.editTextPassword

        val saveButton: Button = binding.buttonSave
        saveButton.setOnClickListener {
            if (displayNameEditText.text.isNotEmpty()) {
                ma.updateProfileDisplayName(displayNameEditText.text.toString())
                displayNameEditText.setText("")
            }

            if (passwordEditText.text.isNotEmpty()) {
                ma.updatePassword(passwordEditText.text.toString())
                passwordEditText.setText("")
            }
        }

        val logoutButton: Button = binding.buttonLogout
        logoutButton.setOnClickListener {
            ma.signOut()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}