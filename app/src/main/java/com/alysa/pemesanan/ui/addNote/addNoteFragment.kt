package com.alysa.pemesanan.ui.addNote

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alysa.pemesanan.databinding.FragmentAddnoteBinding
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddnoteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        intable = binding.inTable
        code = binding.code
        indate = binding.indate
        inprice = binding.inprice
        save = binding.save

        indate.setText(dateFormatter.format(calendar.time))

        indate.setOnClickListener {
            showDatePicker()
        }

        save.setOnClickListener {
            if (isValidation()) {
                simpanFileData()
            } else {
                Toast.makeText(requireContext(), "Please fill all data", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isValidation(): Boolean {
        return !intable.text.toString().isEmpty() &&
                !code.text.toString().isEmpty() &&
                !indate.text.toString().isEmpty() &&
                !inprice.text.toString().isEmpty()
    }

    private fun simpanFileData() {
        val isiFile = "${intable.text};${code.text};${indate.text};${inprice.text}"
        val file = File(requireContext().filesDir, intable.text.toString())

        var outputStream: FileOutputStream? = null
        try {
            file.createNewFile()
            outputStream = FileOutputStream(file, false)
            outputStream.write(isiFile.toByteArray())
            outputStream.flush()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showDatePicker() {
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            indate.setText(dateFormatter.format(calendar.time))
        }

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}
