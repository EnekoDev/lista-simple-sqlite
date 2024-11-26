package com.example.reto2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reto2.adapters.ElementAdapter
import com.example.reto2.dao.ElementDAO
import com.example.reto2.databinding.ActivityMainBinding
import com.example.reto2.model.Element

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding:ActivityMainBinding

    private lateinit var rvElementList:RecyclerView
    private lateinit var elementAdapter:ElementAdapter
    private lateinit var elementDAO:ElementDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()

        binding.btnNewElement.setOnClickListener(this)
        binding.btnClearList.setOnClickListener(this)
    }

    private fun initialize() {
        elementDAO = ElementDAO(this)
        rvElementList = binding.rvElementList
        val elements = elementDAO.getAllElements()
        rvElementList.layoutManager = LinearLayoutManager(this)
        elementAdapter = ElementAdapter(elements, this)
        rvElementList.adapter = elementAdapter
    }

    private fun updateList() {
        val elementList:List<Element> = elementDAO.getAllElements()
        elementAdapter.updateList(elementList)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnNewElement.id -> {
                val elementName = binding.etNewElement.text.toString().trim()
                if (elementName.isNotEmpty()) {
                    elementDAO.insertElement(Element(0, elementName))
                    updateList()
                    binding.etNewElement.text.clear()
                }
                updateList()
                binding.etNewElement.text.clear()

            }
            binding.btnClearList.id -> {
                elementDAO.clearList()
                updateList()
            }
        }
    }
}