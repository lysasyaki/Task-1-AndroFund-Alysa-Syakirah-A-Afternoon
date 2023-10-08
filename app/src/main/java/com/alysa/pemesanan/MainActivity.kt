package com.alysa.pemesanan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alysa.pemesanan.databinding.ActivityMainBinding
import com.alysa.pemesanan.ui.addNote.addNoteFragment
import com.alysa.pemesanan.ui.viewNote.viewNoteFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(addNoteFragment())

        binding.bottonNavMenu.setOnItemReselectedListener {
            when(it.itemId){
                R.id.addNote -> replaceFragment(addNoteFragment())
                R.id.viewNote -> replaceFragment(viewNoteFragment())
            }
        }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }
}