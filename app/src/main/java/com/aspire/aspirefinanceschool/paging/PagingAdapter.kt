package com.aspire.aspirefinanceschool.paging

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aspire.aspirefinanceschool.R
import com.aspire.aspirefinanceschool.model.StudentNames

class PagingAdapter(
    private val itemClickListener: ItemClickListener
) :
    PagingDataAdapter<StudentNames, PagingAdapter.QuoteViewHolder>(COMPARATOR) {
    private var studentMarksValue: String? = ""

    interface ItemClickListener {
        fun onItemClick(studentDetails: StudentNames, studentMarks: String? = null)
    }

    inner class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentName: TextView = itemView.findViewById<TextView>(R.id.nameOfStudent)
        val studentMarks: EditText = itemView.findViewById<EditText?>(R.id.input_studentMarks)

        init {
            studentMarks.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    studentMarksValue = s.toString()
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }
    }

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.studentName.text = item.name
            holder.itemView.setOnClickListener {
                studentMarksValue = holder.studentMarks.text.toString()
                if (studentMarksValue == "")
                    studentMarksValue = null
                itemClickListener.onItemClick(item, studentMarksValue)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return QuoteViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<StudentNames>() {
            override fun areItemsTheSame(oldItem: StudentNames, newItem: StudentNames): Boolean {
                return oldItem.serialnumber == newItem.serialnumber
            }

            override fun areContentsTheSame(oldItem: StudentNames, newItem: StudentNames): Boolean {
                return oldItem == newItem
            }
        }
    }
}