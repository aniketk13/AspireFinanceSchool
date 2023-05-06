package com.aspire.aspirefinanceschool.studentDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aspire.aspirefinanceschool.databinding.FragmentStudentDetailsBinding

class StudentDetails : Fragment() {
    private lateinit var binding: FragmentStudentDetailsBinding
    private val studentDetailArgs: StudentDetailsArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentStudentDetailsBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    //applying the received data from prev screen to this screen views
    private fun initViews() {
        binding.apply {
            studentName.text = studentDetailArgs.studentName
            studentScore.text = studentDetailArgs.studentMarks
        }
    }
}