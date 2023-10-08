package com.alysa.pemesanan.ui.viewNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alysa.pemesanan.R
import com.alysa.pemesanan.databinding.FragmentViewnoteBinding
import com.alysa.pemesanan.ui.viewmodels.addNoteViewModel

class viewNoteFragment : Fragment() {

    private lateinit var viewmodels: addNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodels = ViewModelProvider(requireActivity()).get(addNoteViewModel::class.java)
    }

    private var _binding: FragmentViewnoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewnoteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val inTable = root.findViewById<TextView>(R.id.inTable)
        val code = root.findViewById<TextView>(R.id.code)
        val inprice = root.findViewById<TextView>(R.id.inprice)
        val indate = root.findViewById<TextView>(R.id.indate)

        inTable.text = "${viewmodels.inTable}"
        code.text = "${viewmodels.code}"
        inprice.text = "${viewmodels.inprice}"
        indate.text = "${viewmodels.indate}"

        val textView: TextView = binding.textview
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
