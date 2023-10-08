package com.alysa.pemesanan.ui.viewNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alysa.pemesanan.R
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class viewNoteFragment : Fragment() {

    private lateinit var intable: EditText
    private lateinit var code: EditText
    private lateinit var inprice: EditText
    private lateinit var indate: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_viewnote, container, false)

        intable = rootView.findViewById(R.id.inTable)
        code = rootView.findViewById(R.id.code)
        indate = rootView.findViewById(R.id.indate)
        inprice = rootView.findViewById(R.id.inprice)

        val fileName = intable.text.toString()
            bacaDataUser(fileName)

        return rootView
    }

    private fun bacaDataUser(fileName: String) {
        val sdcard = requireContext().filesDir
        val file = File(sdcard, fileName)
        if (file.exists()) {
            val text = StringBuilder()
            try {
                val br = BufferedReader(FileReader(file))
                var line: String? = br.readLine()
                while (line != null) {
                    text.append(line)
                    line = br.readLine()
                }
                br.close()
            } catch (e: IOException) {
                println("Error " + e.message)
            }
            val data = text.toString()
            val dataUser = data.split(";")

            intable.setText(dataUser[0])
            code.setText(dataUser[1])
            indate.setText(dataUser[2])
            inprice.setText(dataUser[3])
        } else {
            Toast.makeText(requireContext(), "Data not found", Toast.LENGTH_SHORT).show()
        }
    }
}
