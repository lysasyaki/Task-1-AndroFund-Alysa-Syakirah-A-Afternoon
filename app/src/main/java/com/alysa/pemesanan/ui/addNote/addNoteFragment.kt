package com.alysa.pemesanan.ui.addNote

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alysa.pemesanan.databinding.FragmentAddnoteBinding
import com.alysa.pemesanan.ui.viewmodels.addNoteViewModel
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class addNoteFragment : Fragment() {

    private lateinit var intable: EditText
    private lateinit var code: EditText
    private lateinit var inprice: EditText
    private lateinit var indate: EditText
    private lateinit var save: Button
    private var _binding: FragmentAddnoteBinding? = null
    private val binding get() = _binding!!

    private val calendar = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    private lateinit var ViewModel: addNoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewModel = ViewModelProvider(requireActivity()).get(addNoteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddnoteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.txtadd
        val Tgl = binding.indate
        val Save = binding.save

        Save.setOnClickListener{

            val inTable = binding.inTable.text.toString()
            val code = binding.code.text.toString()
            val inprice = binding.inprice.text.toString()
            val indate = binding.indate.text.toString()

            ViewModel.inTable = inTable
            ViewModel.code = code
            ViewModel.inprice = inprice
            ViewModel.indate = indate

        }


        Tgl.setOnClickListener {
            // Mendapatkan tanggal saat ini
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Membuat DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    // Mengatur tanggal yang dipilih pada TextView
                    val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                    Tgl.text = Editable.Factory.getInstance().newEditable(selectedDate)
                },
                year, month, day
            )

            // Menampilkan DatePickerDialog
            datePickerDialog.show()
        }

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
