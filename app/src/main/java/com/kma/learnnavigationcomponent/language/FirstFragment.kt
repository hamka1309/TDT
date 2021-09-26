package com.kma.learnnavigationcomponent.language

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.kma.learnnavigationcomponent.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment(), CheckboxAdapter.AdapterOnClick {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var recyclerView: RecyclerView? = null
    var adapterCheckbox: CheckboxAdapter? = null
    var checkboxViewModel: CheckboxViewModel? = null
    var cbSelectAll: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkboxViewModel = CheckboxViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_first, container, false)
        val container = rootView.findViewById<TextView>(R.id.firstFragment)
        container.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_firstFragment_to_secondFragment)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkboxViewModel?.getListCheckbox()
        recyclerView = activity?.findViewById(R.id.recyclerViewCheckbox)
        cbSelectAll = activity?.findViewById(R.id.cbSelectAll)
        adapterCheckbox = CheckboxAdapter()
        adapterCheckbox?.setCallback(this)
//        cbSelectAll?.setOnClickListener {
//            Log.i("hadtt", "onViewCreated cbSelectAll: " + cbSelectAll?.isChecked)
//        }
        checkboxViewModel?.liveDataA?.observe(viewLifecycleOwner, {
            val manager = GridLayoutManager(requireContext(), 2)
            manager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if(position==0) 2 else 1
                }
            }

            recyclerView?.layoutManager =manager
            recyclerView?.adapter = adapterCheckbox
            adapterCheckbox?.setListCheckbox(it)
        })
    }

    override fun onClickItem(item: Int) {
        Log.i("hadtt", "onClick: " + checkboxViewModel?.liveDataA?.value?.get(item)?.isCheck)
    }

}