package com.aspire.aspirefinanceschool.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aspire.aspirefinanceschool.databinding.FragmentHomePageBinding
import com.aspire.aspirefinanceschool.model.StudentNames
import com.aspire.aspirefinanceschool.paging.PagingAdapter

class HomePageFragment : Fragment() {

    private val viewModel: HomePageViewModel by viewModels()
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentHomePageBinding.inflate(inflater, container, false).also {
        binding = it
        recyclerView = binding.recyclerView
        initViews()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    //defining all the observable content
    private fun initObservers() {
        viewModel.listOfStudents.observe(requireActivity()){
            adapter.submitData(lifecycle, it)
        }
    }

    //initialising the views
    private fun initViews() {
        adapter = PagingAdapter(object : PagingAdapter.ItemClickListener {
            override fun onItemClick(studentDetails: StudentNames, studentMarks: String?) {
                if (studentMarks != null) {
                    navigateToStudentDetailsFragment(studentDetails.name, studentMarks)
                } else {
                    Toast.makeText(requireContext(), "Enter scores first", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    //navigating to student details fragment with studentName and studentMarks
    private fun navigateToStudentDetailsFragment(studentName: String, studentMarks: String) {
        findNavController().navigate(
            HomePageFragmentDirections.actionHomePageFragmentToStudentDetails(
                studentName,
                studentMarks
            )
        )
    }


}